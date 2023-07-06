package models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Pedido {
    @OneToMany(cascade = CascadeType.ALL)
    private List<Item> items;
    @OneToOne(cascade = CascadeType.ALL)
    private Factura factura;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numero;
    private double monto;
    @OneToOne(cascade = CascadeType.ALL)
    private Descuento descuento;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Impuesto> impuestosAplicados;

    public Pedido(List<Item> items, double monto, Descuento descuento, List<Impuesto> impuestosAplicados) {
        this.items = items;
        this.monto = monto;
        this.descuento = descuento;
        this.impuestosAplicados = impuestosAplicados;
    }

    public Factura generarFactura(String operadorInterviniente) {
        this.factura = new Factura(monto, calcularTotal(), operadorInterviniente, this);
        return factura;
    }

    public Factura generarFactura() {
        this.factura = new Factura(monto, calcularTotal(), this);
        return this.factura;
    }

    public double calcularTotal() {
        double montoSubtotal = monto;
        double valorImpuesto = impuestosAplicados.stream().map(impuesto -> impuesto.calcularImpuesto(montoSubtotal)).reduce(0.0, Double::sum);
        double valorDescuento = descuento.calcularDescuento(montoSubtotal);
        return montoSubtotal - valorDescuento + valorImpuesto;
    }

    @Override
    public String toString() {
        String toString = "\n\nPedido: " + numero + " | monto: $" + monto + " descuento: " + descuento.getPorcentaje() + "%\n";

        toString += "\nImpuestos:\n";
        for (Impuesto impuesto : impuestosAplicados) {
            toString += impuesto.getNombre() + "\n";
        }

        toString += "\nItems:\n";
        for (Item item : items) {
            toString += item.toString();
        }

        return toString;
    }
}

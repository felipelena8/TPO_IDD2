package models;

import controllers.ControllerUsuarios;
import lombok.Data;

import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    private int numero;
    private double monto;
    @OneToOne(cascade = CascadeType.ALL)
    private Descuento descuento;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Impuesto> impuestosAplicados;

    public Pedido(List<Item> items, double monto, Descuento descuento, List<Impuesto> impuestosAplicados) {
        ControllerUsuarios.getInstancia().getSession().getPedidos().add(this);
        this.items = items;
        this.monto = monto;
        this.descuento = descuento;
        this.impuestosAplicados = impuestosAplicados;
    }

    public Factura generarFactura(String operadorInterviniente) {
        this.factura = new Factura(monto, calcularTotal(), operadorInterviniente, this);
        return factura;
    }

    public void generarFactura() {
        this.factura = new Factura(monto, calcularTotal(), this);
    }

    public double calcularTotal() {
        double montoSubtotal = monto;
        double valorImpuesto = impuestosAplicados.stream().map(impuesto -> impuesto.calcularImpuesto(montoSubtotal)).reduce(0.0, Double::sum);
        double valorDescuento = descuento.calcularDescuento(montoSubtotal);
        return montoSubtotal - valorDescuento + valorImpuesto;
    }

    @Override
    public String toString() {
        return "Pedido{" + "numero=" + numero +
                ", monto=" + monto +
                ", descuento=" + descuento +
                ", impuestosAplicados=" + impuestosAplicados +
                ", items=" + items +

                '}';
    }
}

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
    private Usuario usuario;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int numeroPedido;
    private double precio;
    @OneToOne(cascade = CascadeType.ALL)
    private Descuento descuento;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Impuesto> impuestosAplicados;

    public Pedido(List<Item> items, double precio, Descuento descuento, List<Impuesto> impuestosAplicados) {
        ControllerUsuarios.getInstancia().getSession().getPedidos().add(this);
        this.items = items;
        this.precio = precio;
        this.descuento=descuento;
        this.impuestosAplicados= impuestosAplicados;
    }

    private void generarFactura(String operadorInterviniente) {
        this.factura = new Factura(precio, calcularTotal(),operadorInterviniente, this);
    }

    private void generarFactura() {
        this.factura = new Factura(precio, calcularTotal(),this);
    }

    public double calcularTotal(){
        double precioSubtotal = precio;
        double valorImpuesto = impuestosAplicados.stream().map(impuesto -> impuesto.calcularImpuesto(precioSubtotal)).reduce(0.0, Double::sum);
        double valorDescuento = descuento.calcularPrecio(precioSubtotal);
        System.out.println(descuento);
        return precioSubtotal-valorDescuento+valorImpuesto;
    }
}

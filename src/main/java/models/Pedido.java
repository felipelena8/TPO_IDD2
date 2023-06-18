package models;

import controllers.ControllerUsuarios;

import java.util.List;

public class Pedido {
    private List<Item> items;
    private Factura factura;
    private int numero;
    private double precio;

    public Pedido(List<Item> items, double precio) {
        this.items = items;
        this.precio = precio;
        ControllerUsuarios.getInstancia().getSession().getPedidos().add(this);
    }

    private void generarFactura(String operadorInterviniente) {
        this.factura = new Factura(precio, items, operadorInterviniente);
    }

    private void generarFactura() {
        this.factura = new Factura(precio, items);
    }
}

package models;

import models.MedioPago.MedioPago;

import java.time.Instant;
import java.util.Date;
import java.util.List;

public class Factura {
    private Date fechaHoraEmision;
    private double precio;
    private List<Item> items;
    private String operadorInterviniente;
    private Pago pago;
    private Pedido pedido;

    public Factura(double precio, List<Item> items,Pedido pedido) {
        this.fechaHoraEmision = new Date();
        this.precio = precio;
        this.items = items;
        this.pedido = pedido;
    }

    public Factura(double precio, List<Item> items, String operadorInterviniente,Pedido pedido) {
        this.fechaHoraEmision = new Date();
        this.precio = precio;
        this.items = items;
        this.operadorInterviniente = operadorInterviniente;
        this.pedido = pedido;
    }


    public void generarPago(MedioPago medioPago) {
        pago = new Pago(medioPago, precio);
    }


}

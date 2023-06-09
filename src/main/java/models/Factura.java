package models;

import models.MedioPago.MedioPago;

import java.time.Instant;
import java.util.List;

public class Factura {
    private Instant fechaHoraEmision;
    private double precio;
    private List<Item> items;
    private String operadorInterviniente;
    private Pago pago;

    public Factura(double precio, List<Item> items) {
        this.fechaHoraEmision = Instant.now();
        this.precio = precio;
        this.items = items;
    }

    public void generarPago(MedioPago medioPago){
        pago = new Pago(medioPago, precio);
    }

    public Factura( double precio, List<Item> items, String operadorInterviniente) {
        this.fechaHoraEmision = Instant.now();
        this.precio = precio;
        this.items = items;
        this.operadorInterviniente = operadorInterviniente;
    }
}

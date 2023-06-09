package models;

import models.MedioPago.MedioPago;

public class Pago {
    private MedioPago medioPago;
    private double precio;
    private int numeroTransaccion;
    public Pago(MedioPago medioPago, double precio) {
        this.medioPago = medioPago;
        this.precio = precio;
    }
}

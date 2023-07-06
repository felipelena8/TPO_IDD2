package models;

import models.MedioPago.MedioPago;

import javax.persistence.*;

@Entity
public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int numeroTransaccion;
    @OneToOne(cascade = CascadeType.ALL)
    private MedioPago medioPago;
    private double precio;

    public Pago(MedioPago medioPago, double precio) {
        this.medioPago = medioPago;
        this.precio = precio;
    }
}

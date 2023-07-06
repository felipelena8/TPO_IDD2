package models;

import controllers.ControllerUsuarios;

import javax.persistence.*;

@Entity
public class Pago {
    @OneToOne(cascade = CascadeType.ALL)
    private MedioPago medioPago;
    private double precio;

    public Pago(MedioPago medioPago, double precio) {
        this.medioPago = medioPago;
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Pago{medio de pago= "+medioPago+", precio= " + precio+"}";
    }
}

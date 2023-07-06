package models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

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
        return "\n\nPago | Medio de pago: " + medioPago.toString() + " | precio: $" + precio;
    }
}

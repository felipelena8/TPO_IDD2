package models;

import javax.persistence.Entity;

@Entity
public class Descuento {
    private double porcentaje;

    public Descuento(double porcentaje) {
        this.porcentaje = porcentaje;
    }

    public double calcularDescuento(double precio) {
        return precio * (porcentaje / 100.0);
    }

    @Override
    public String toString() {
        return "Descuento porcentaje= " + porcentaje;
    }
}

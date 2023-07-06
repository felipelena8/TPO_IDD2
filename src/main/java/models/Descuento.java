package models;

import javax.persistence.Entity;

@Entity
public class Descuento {
    private double porcentaje;

    public Descuento(double porcentaje) {
        this.porcentaje=porcentaje;
    }

    public double calcularPrecio(double precio){
        System.out.println(porcentaje/100/0);
        return precio*(1.0-porcentaje/100.0);
    }

    @Override
    public String toString() {
        return "Descuento porcentaje= " + porcentaje;
    }
}

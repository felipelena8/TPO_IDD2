package models;

import javax.persistence.Entity;

@Entity
public abstract class Impuesto {
    private String nombre;
    public abstract double calcularImpuesto(double precio);

    public Impuesto(String nombre){
        this.nombre=nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }
}

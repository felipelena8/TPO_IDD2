package models;

public abstract class Impuesto {
    private String nombre;

    public Impuesto(String nombre) {
        this.nombre = nombre;
    }

    public abstract double calcularImpuesto(double precio);

    @Override
    public String toString() {
        return nombre;
    }
}

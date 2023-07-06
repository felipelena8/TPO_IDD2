package models;

import javax.persistence.Entity;

@Entity
public class ImpuestoIVA extends Impuesto {

    private static double porcentaje = 21;

    @Override
    public double calcularImpuesto(double precio) {
        return precio * porcentaje / 100;
    }

    public ImpuestoIVA() {
        super("IVA");
    }
}

package models;

import javax.persistence.Entity;

@Entity
public class ImpuestoIVA extends Impuesto {

    private static double porcentaje = 21;

    public ImpuestoIVA() {
        super("IVA");
    }

    @Override
    public double calcularImpuesto(double precio) {
        return precio * porcentaje / 100;
    }
}

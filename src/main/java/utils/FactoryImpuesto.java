package utils;

import models.CondicionFiscal;
import models.Impuesto;
import models.ImpuestoIVA;

import java.util.ArrayList;
import java.util.List;

public class FactoryImpuesto {
    public static List<Impuesto> crearImpuestos(CondicionFiscal condicionFiscal){
        List<Impuesto> impuestos= new ArrayList<>();
        switch (condicionFiscal){
            case CONSUMIDOR_FINAL, RESPONSABLE_INSCRIPTO, MONOTRIBUTISTA-> {impuestos.add(new ImpuestoIVA());}
            case EXENTO -> {}//No pagan IVA
            case AUTONOMO -> {}//No pagan IVA
            case NO_ALCANZADO -> {}//No pagan IVA
        }
        return impuestos;
    }
}

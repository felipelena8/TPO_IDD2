package utils;

import models.Categoria;
import models.Descuento;

public class FactoryDescuento {
    public static Descuento crearDescuento(Categoria categoria){
        switch (categoria){
            case LOW -> {
                return new Descuento(0);
            }
            case MEDIUM -> {
                return new Descuento(20);
            }
            case TOP -> {
                return new Descuento(40);
            }
        }
        return null;
    }
}

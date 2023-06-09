package models;

import java.util.List;

public class Carrito {
    private List<Item> items;
    private Usuario usuario;

    public double precioTotal(){
        return items.stream().map(item -> item.precioItem()).reduce(0.0,(a,b) -> a+b);
    }
}

package models;

public class Item {
    private Producto producto;
    private int cantidad;

    public double precioItem(){
        return producto.getPrecio() * cantidad;
    }

}

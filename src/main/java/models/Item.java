package models;

import lombok.Data;

@Data
public class Item {
    private Producto producto;
    private int cantidad;

    public double precioItem() {
        return producto.getPrecio() * cantidad;
    }

    public Item(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "Item{" +
                "producto=" + producto +
                ", cantidad=" + cantidad +
                "}\n";
    }
}

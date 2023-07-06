package models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Data
@Entity
public class Item {
    @OneToOne
    private Producto producto;
    private int cantidad;

    public Item(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public double precioItem() {
        return producto.getPrecio() * cantidad;
    }

    @Override
    public String toString() {
        return "{producto{ descripcion=" + producto.getDescripcion() + ", precio=" + producto.getPrecio() +
                "}, cantidad=" + cantidad +
                "}\n";
    }
}

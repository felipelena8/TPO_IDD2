package models;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Producto {
    private int id;
    private String descripcion;
    private List<String> imagenesUrl;
    private double precio;
    private int stock;
    private List<Comentario> comentarios;
    private List<String> videosUrl;

    public Producto(int id, String descripcion, double precio, int stock) {
        this.id = id;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
        imagenesUrl = new ArrayList<>();
        comentarios = new ArrayList<>();
        videosUrl = new ArrayList<>();
    }

    public String productoLog() {
        return "id: " + id + ", descripcion: " + descripcion + ", imagenes: " + imagenesUrl + ", precio: " + precio + ", videos: " + videosUrl;
    }
}

package models;

import controllers.ControllerProductos;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Data
public class ProductoDTO {
    private int id;
    private String descripcion;
    private List<String> imagenesUrl;
    private double precio;
    private int stock;
    private List<Comentario> comentarios;
    private List<String> videosUrl;

    public ProductoDTO(Producto producto) {
        this.id = producto.getId();
        this.descripcion = producto.getDescripcion();
        this.precio = producto.getPrecio();
        this.stock = producto.getStock();
        imagenesUrl = new ArrayList<>(producto.getImagenesUrl());
        comentarios = new ArrayList<>(producto.getComentarios());
        videosUrl = new ArrayList<>(producto.getVideosUrl());
    }

    public void agregarImagenUrl(String imagen) {
        imagenesUrl.add(imagen);
    }

    public void agregarVideoUrl(String videoUrl) {
        videosUrl.add(videoUrl);
    }

    public void agregarComentario(Comentario comentario) {
        comentarios.add(comentario);
    }

    public Producto createProducto() {
        Producto prod = new Producto(getId(), getDescripcion(), getPrecio(), getStock());
        prod.setImagenesUrl(getImagenesUrl());
        prod.setComentarios(getComentarios());
        prod.setVideosUrl(getVideosUrl());
        return prod;
    }
}

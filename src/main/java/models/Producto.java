package models;

import controllers.ControllerProductos;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Producto {
    private int id;

    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL)
    private List<String> imagenesUrl;
    private double precio;
    private int stock;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Comentario> comentarios;
    @OneToMany(cascade = CascadeType.ALL)
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

    public void agregarImagenUrl(String imagen) {
        imagenesUrl.add(imagen);
        persistir();
    }

    public void agregarVideoUrl(String videoUrl) {
        videosUrl.add(videoUrl);
        persistir();
    }

    public void agregarComentario(Comentario comentario) {
        comentarios.add(comentario);
        persistir();
    }

    public void persistir() {
        ControllerProductos.getInstancia().actualizarProducto(this);
    }
}

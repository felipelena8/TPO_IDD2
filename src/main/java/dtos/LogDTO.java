package dtos;

import lombok.Data;

import java.util.List;

@Data
public class LogDTO {
    private String tipo_registro;
    private int codigo;
    private double prev_precio;
    private int prev_stock;
    private String prev_descripcion;
    private List<String> prev_imagenesUrl;
    private List<String> prev_videosUrl;
    private List<String> prev_comentarios;
    private double new_precio;
    private int new_stock;
    private String new_descripcion;
    private List<String> new_imagenesUrl;
    private List<String> new_videosUrl;
    private List<String> new_comentarios;

    public LogDTO(String tipo_registro, int codigo, double prev_precio, int prev_stock, String prev_descripcion, List<String> prev_imagenesUrl, List<String> prev_videosUrl, List<String> prev_comentarios, double new_precio, int new_stock, String new_descripcion, List<String> new_imagenesUrl, List<String> new_videosUrl, List<String> new_comentarios) {
        this.tipo_registro = tipo_registro;
        this.codigo = codigo;
        this.prev_precio = prev_precio;
        this.prev_stock = prev_stock;
        this.prev_descripcion = prev_descripcion;
        this.prev_imagenesUrl = prev_imagenesUrl;
        this.prev_videosUrl = prev_videosUrl;
        this.prev_comentarios = prev_comentarios;
        this.new_precio = new_precio;
        this.new_stock = new_stock;
        this.new_descripcion = new_descripcion;
        this.new_imagenesUrl = new_imagenesUrl;
        this.new_videosUrl = new_videosUrl;
        this.new_comentarios = new_comentarios;
    }
}

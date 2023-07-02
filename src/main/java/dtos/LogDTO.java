package dtos;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

@Data
public class LogDTO {
    private String tipo_registro;
    private int codigo;
    private double prev_precio;
    private int prev_stock;
    private String prev_descripcion;
    private double new_precio;
    private int new_stock;
    private String new_descripcion;

    public LogDTO(String tipo_registro, int codigo, double prev_precio, int prev_stock, String prev_descripcion, double new_precio, int new_stock, String new_descripcion) {
        this.tipo_registro = tipo_registro;
        this.codigo = codigo;
        this.prev_precio = prev_precio;
        this.prev_stock = prev_stock;
        this.prev_descripcion = prev_descripcion;
        this.new_precio = new_precio;
        this.new_stock = new_stock;
        this.new_descripcion = new_descripcion;
    }
}

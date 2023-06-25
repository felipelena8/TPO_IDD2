package models;

import lombok.Data;
import models.MedioPago.MedioPago;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Usuario implements Serializable {
    @Id
    private int id;
    private String nombre;
    private String username;
    private String password;
    private String direccion;
    private String dni;
    private Carrito carrito = new Carrito(this);
    private List<Pedido> pedidos;
    private Categoria categoria;
    private List<MedioPago> mediosPago;
    private int minutosPorDia;
    private CondicionFiscal condicionFiscal;

    public Usuario(int id, String nombre, String username, String password, String direccion, String dni, CondicionFiscal condicionFiscal) {
        this.id = id;
        this.nombre = nombre;
        this.username = username;
        this.password = password;
        this.direccion = direccion;
        this.dni = dni;
        this.condicionFiscal = condicionFiscal;
        this.mediosPago = new ArrayList<>();
        this.pedidos = new ArrayList<>();

    }
}

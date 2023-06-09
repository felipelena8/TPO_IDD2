package models;

import lombok.Data;
import models.MedioPago.MedioPago;

import java.util.ArrayList;
import java.util.List;

@Data
public class Usuario {
    private String nombre;
    private String username;
    private String password;
    private String direccion;
    private String dni;
    private Carrito carrito;
    private List<Pedido> pedidos;
    private Categoria categoria;
    private List<MedioPago> mediosPago;
    private int minutosPorDia;
    private CondicionFiscal condicionFiscal;

    public Usuario(String nombre, String username, String password, String direccion, String dni, CondicionFiscal condicionFiscal) {
        this.nombre = nombre;
        this.username = username;
        this.password = password;
        this.direccion = direccion;
        this.dni = dni;
        this.condicionFiscal = condicionFiscal;

        mediosPago=new ArrayList<>();
        pedidos=new ArrayList<>();
    }
}

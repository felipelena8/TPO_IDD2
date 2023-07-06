package models;

import controllers.ControllerUsuarios;
import lombok.Data;
import models.MedioPago.MedioPago;
import utils.Tiempo;

import javax.persistence.*;
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
    @Transient
    private Carrito carrito = new Carrito(this);
    @OneToMany(cascade = CascadeType.ALL)
    private List<Pedido> pedidos;
    private Categoria categoria;
    @OneToMany(cascade = CascadeType.ALL)
    private List<MedioPago> mediosPago;
    private int minutosPorDia;
    private CondicionFiscal condicionFiscal;
    @OneToOne(cascade = CascadeType.ALL)
    private Tiempo tiempoEnDia;

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
        categoria=Categoria.LOW;
        this.tiempoEnDia=new Tiempo();
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", direccion='" + direccion + '\'' +
                ", dni='" + dni + '\'' +
                ", tiempo en el dia = " + tiempoEnDia+
                ", categoria= "+categoria+'}';
    }

    public void persistir(){
        ControllerUsuarios.getInstancia().actualizar(this);
    }
}

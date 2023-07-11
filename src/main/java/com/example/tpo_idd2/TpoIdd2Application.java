package com.example.tpo_idd2;

import config.CassandraConnectionPool;
import controllers.ControllerProductos;
import controllers.ControllerUsuarios;
import dtos.UsuarioDTO;
import models.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.cassandra.CassandraDataAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import utils.Utils;

@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, CassandraDataAutoConfiguration.class})
public class TpoIdd2Application {

    public static void main(String[] args) {
        SpringApplication.run(TpoIdd2Application.class, args);
        CassandraConnectionPool.connect();


        // Creamos 4 usuarios
        System.out.println("\nSe crean 4 usuarios: -Felipe Costa- -Lucas Muñoz- -Francisco fontana- -Ignacio Cesarani-");
        Utils.pausar();
        Usuario user1 = new Usuario(1, "Felipe Costa", "felipelena", "uade1234", "Lima 757", "44967716", CondicionFiscal.CONSUMIDOR_FINAL);
        Usuario user2 = new Usuario(2, "Lucas Munoz", "lucasmunoz", "uade1234", "Lima 757", "38000000", CondicionFiscal.NO_ALCANZADO);
        Usuario user3 = new Usuario(3, "Francisco Fontana", "franciscofontana", "uade1234", "Lima 757", "44000000", CondicionFiscal.AUTONOMO);
        Usuario user4 = new Usuario(4, "Ignacio Cesarani", "ignaciocesarani", "uade1234", "Lima 757", "43000000", CondicionFiscal.MONOTRIBUTISTA);

        System.out.println("");

        // Se guardan los usuarios creados en la base de datos de ObjectDB
        System.out.println("Se guardan los usuarios creados");
        Utils.pausar();

        ControllerUsuarios.getInstancia().registrarUsuario(user1);
        ControllerUsuarios.getInstancia().registrarUsuario(user2);
        ControllerUsuarios.getInstancia().registrarUsuario(user3);
        ControllerUsuarios.getInstancia().registrarUsuario(user4);

        System.out.println("");

        System.out.println("Se inicia sesion en el usuario Felipe Costa");
        ControllerUsuarios.getInstancia().iniciarSesion(new UsuarioDTO("felipelena", "uade1234"));
        Usuario sesion = ControllerUsuarios.getInstancia().getSession();
        Utils.pausar();

        // Se crean 3 productos: Sacapuntas, Lapicera y Regla
        System.out.println("Se crean 3 productos: Sacapuntas, Lapicera y Regla");
        Utils.pausar();
        Producto prod2 = new Producto(2, "Lapicera", 1000, 100);
        ControllerProductos.getInstancia().agregarProducto(new Producto(1, "Sacapuntas", 500, 100));
        ControllerProductos.getInstancia().agregarProducto(prod2);
        ControllerProductos.getInstancia().agregarProducto(new Producto(3, "Regla", 40, 100));

        System.out.println("Se realizan modificaciones en el producto");
        System.out.println("Para realizar modificar el producto se utiliza un DTO");
        ProductoDTO prodDto2 = new ProductoDTO(prod2);
        System.out.println("Se agrega un comentario al producto");
        Utils.pausar();
        System.out.println("Se agregan videos al producto");
        Utils.pausar();
        prodDto2.setPrecio(300);
        prodDto2.setStock(30);
        prodDto2.getImagenesUrl().add("IMAGEN.PNG");
        prodDto2.getImagenesUrl().add("IMAGEN2.PNG");
        ControllerProductos.getInstancia().actualizarProducto(prodDto2);
        Utils.pausar();

        MedioPago tarjetaFelipe = MedioPago.TARJETA;
        System.out.println("Se agrega un medio de pago: " + tarjetaFelipe);
        sesion.getMediosPago().add(tarjetaFelipe);


        System.out.println("Agregado de productos al carrito");
        Utils.pausar();
        Carrito cart1 = ControllerUsuarios.getInstancia().getSession().getCarrito();
        System.out.println("Se agregan 4 unidades del producto: " + ControllerProductos.getInstancia().buscarProducto(2).getDescripcion());
        Utils.pausar();
        cart1.agregarItem(2, 4);
        System.out.println("Se agregan 3 unidades del producto: " + ControllerProductos.getInstancia().buscarProducto(1).getDescripcion());
        Utils.pausar();
        cart1.agregarItem(1, 3);
        System.out.println("Se agregan 2 unidades del producto: " + ControllerProductos.getInstancia().buscarProducto(2).getDescripcion());
        cart1.agregarItem(2, 2);
        Utils.pausar();
        System.out.println("Se vuelve al estado anterior del carrito");
        Utils.pausar();
        cart1.estadoAnterior();


        System.out.println("Se cambia la categoria del usuario a TOP para recibir un descuento");
        sesion.setCategoria(Categoria.TOP);
        Utils.pausar();

        System.out.println("Se genera el pedido");
        Utils.pausar();
        Pedido pedido = cart1.generarPedido();
        System.out.println(pedido);
        Utils.pausar();

        System.out.println("Se genera la factura del pedido");
        Utils.pausar();
        Factura factura = pedido.generarFactura();
        System.out.println(factura);

        System.out.println("Se genera el pago de la tarjeta");
        Utils.pausar();
        factura.generarPago(tarjetaFelipe);
    }
}

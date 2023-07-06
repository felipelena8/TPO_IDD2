package com.example.tpo_idd2;

import com.datastax.oss.driver.api.core.cql.ResultSet;
import config.CassandraConnectionPool;
import controllers.ControllerProductos;
import controllers.ControllerUsuarios;
import dtos.UsuarioDTO;
import models.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.cassandra.CassandraDataAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, CassandraDataAutoConfiguration.class})
public class TpoIdd2Application {

    public static void main(String[] args) {
        SpringApplication.run(TpoIdd2Application.class, args);

        CassandraConnectionPool dbLogs = CassandraConnectionPool.getInstancia();
        dbLogs.buscarLogsPorProducto(1);
        ResultSet hola = dbLogs.buscarLogsPorProductoYFechas(1, "2023-07-06 04:05:34", "2023-07-06 04:08:11");
        System.out.println("registros: " + hola.all().get(2).getList("new_videos", String.class).toString());

        // Creamos 4 usuarios
        System.out.println("\nSe crean 4 usuarios: -Felipe Costa- -Lucas Muñoz- -Francisco fontana- -Ignacio Cesarani-");
        Usuario user1 = new Usuario(1, "Felipe Costa", "felipelena", "uade1234", "Lima 757", "44967716", CondicionFiscal.CONSUMIDOR_FINAL);
        Usuario user2 = new Usuario(2, "Lucas Munoz", "lucasmunoz", "uade1234", "Lima 757", "38000000", CondicionFiscal.NO_ALCANZADO);
        Usuario user3 = new Usuario(3, "Francisco Fontana", "franciscofontana", "uade1234", "Lima 757", "44000000", CondicionFiscal.AUTONOMO);
        Usuario user4 = new Usuario(4, "Ignacio Cesarani", "ignaciocesarani", "uade1234", "Lima 757", "43000000", CondicionFiscal.MONOTRIBUTISTA);

        System.out.println("");

        // Se guardan los usuarios creados en la base de datos de ObjectDB
        System.out.println("Se guardan los usuarios creados");
        ControllerUsuarios.getInstancia().registrarUsuario(user1);
        ControllerUsuarios.getInstancia().registrarUsuario(user2);
        ControllerUsuarios.getInstancia().registrarUsuario(user3);
        ControllerUsuarios.getInstancia().registrarUsuario(user4);

        System.out.println("");

        // Se crean 3 productos: Sacapuntas, Lapicera y Regla
        System.out.println("Se crean 3 productos: Sacapuntas, Lapicera y Regla");
        Producto prod1 = new Producto(1, "Sacapuntas", 500, 100);
        ControllerProductos.getInstancia().agregarProducto(prod1);
        ControllerProductos.getInstancia().agregarProducto(new Producto(2, "Lapicera", 30, 100));
        ControllerProductos.getInstancia().agregarProducto(new Producto(3, "Regla", 40, 100));

        // Se inicia sesion en el usuario Felipe Costa
        System.out.println("Se inicia sesion en el usuario Felipe Costa");
        ControllerUsuarios.getInstancia().iniciarSesion(new UsuarioDTO("felipelena", "uade1234"));


        //Producto prod1 = ControllerProductos.getInstancia().buscarProducto(1);
        prod1.agregarComentario(new Comentario(ControllerUsuarios.getInstancia().getSession(), "hola"));
        prod1.agregarVideoUrl("Video1.png");
        prod1.agregarVideoUrl("video2.png");
/*
        System.out.println("Se inicia sesion en el usuario Felipe Costa");
        ControllerUsuarios.getInstancia().iniciarSesion(new UsuarioDTO("felipelena", "uade1234"));
        Usuario sesion = ControllerUsuarios.getInstancia().getSession();
        System.out.println(sesion.getPedidos());
        System.out.println(sesion);
        Carrito cart1 = ControllerUsuarios.getInstancia().getSession().getCarrito();
        Pedido pedido =cart1.generarPedido();
        System.out.println(pedido);
        Factura factura=pedido.generarFactura("Operador interviniente");
        System.out.println(factura);
*/
        
//        Carrito cart1 = ControllerUsuarios.getInstancia().getSession().getCarrito();
//        Pedido pedido =cart1.generarPedido();
//        System.out.println(pedido.calcularTotal());

    }
}

package com.example.tpo_idd2;

import config.CassandraConnectionPool;
import controllers.ControllerProductos;
import controllers.ControllerUsuarios;
import dtos.UsuarioDTO;
import models.Carrito;
import models.CondicionFiscal;
import models.Producto;
import models.Usuario;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.cassandra.CassandraDataAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import repositories.CarritoRepository;
import repositories.UserRepository;
import utils.Tiempo;


// @SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class, CassandraDataAutoConfiguration.class })
public class TpoIdd2Application {

    public static void main(String[] args) {
        SpringApplication.run(TpoIdd2Application.class, args);

       //CassandraConnectionPool pool = CassandraConnectionPool.getInstancia();
        //String valor = pool.getValue("asd");
        //System.out.println("DIOS POR FAVOR: " + valor);


        // Creamos 4 usuarios
        System.out.println("\nSe crean 4 usuarios: -Felipe Costa- -Lucas Muñoz- -Francisco fontana- -Ignacio Cesarani-");
        Usuario user1 = new Usuario(1, "Felipe Costa", "felipelena", "uade1234", "Lima 757", "44967716", CondicionFiscal.EXENTO);
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
//
//        System.out.println("");
//
//        // Se inicia sesion en el usuario Felipe Costa
        System.out.println("Se inicia sesion en el usuario Felipe Costa");
         ControllerUsuarios.getInstancia().iniciarSesion(new UsuarioDTO("felipelena", "uade1234"));
        System.out.println(ControllerUsuarios.getInstancia().getSession());
//
//        System.out.println("");
//
//        // Se crean 3 productos: Sacapuntas, Lapicera y Regla
//        System.out.println("Se crean 3 productos: Sacapuntas, Lapicera y Regla");
//        ControllerProductos.getInstancia().agregarProducto(new Producto(1, "Sacapuntas", 500));
//        ControllerProductos.getInstancia().agregarProducto(new Producto(2, "Lapicera", 30));
//        ControllerProductos.getInstancia().agregarProducto(new Producto(3, "Regla", 40));
//
//        System.out.println("");
//
//        // Se actualiza el precio del sacapuntas 500 -> 100
//        System.out.println("Se actualiza el precio del sacapuntas 500 -> 100");
//        ControllerProductos.getInstancia().actualizarProducto(new Producto(1, "Sacapuntas", 100));
//
//        System.out.println("");
//
//        //ControllerProductos.getInstancia().eliminarProducto(2);
//
//
//        CarritoRepository repo = new CarritoRepository();
//        Carrito cart = repo.read(ControllerUsuarios.getInstancia().getSession());
//        System.out.println("Se crea un carrito vacio");
//        cart.vaciar();
//        System.out.println("Se agrega Sacapuntas al carrito");
//        cart.agregarItem(1,5);
//        System.out.println("Se agrega Regla al carrito");
//        cart.agregarItem(3,7);
//        System.out.println("Se agrega Lapicera al carrito");
//        cart.agregarItem(2,3);
//        System.out.println("Se elimina Lapicera del carrito\n");
//        cart.eliminarItem(2);
//        System.out.println(cart);
//
//        System.out.println("");
//        System.out.println("Se lleva al carrito al estado anterior\n");
//        cart.estadoAnterior();
//        System.out.println(cart);
//
//        System.out.println("");
//
//        cart.estadoPosterior();
//        System.out.println(cart);
//        cart.agregarItem(2,4);
//        System.out.println(cart);
//        cart.vaciar();
//        System.out.println(cart);
//        cart.estadoPosterior();
//        cart.estadoPosterior();
//        cart.estadoPosterior();
//        cart.eliminarItem(2);
//        cart.eliminarItem(2);
        //UserRepository repo = new UserRepository();
        //System.out.println(repo.read());
    }}
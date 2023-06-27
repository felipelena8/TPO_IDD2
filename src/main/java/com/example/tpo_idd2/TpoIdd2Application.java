package com.example.tpo_idd2;

import controllers.ControllerProductos;
import controllers.ControllerUsuarios;
import dtos.UsuarioDTO;
import models.Carrito;
import models.CondicionFiscal;
import models.Producto;
import models.Usuario;
import org.springframework.boot.SpringApplication;
import repositories.CarritoRepository;

public class TpoIdd2Application {

    public static void main(String[] args) {
        SpringApplication.run(TpoIdd2Application.class, args);

        // Creamos 4 usuarios
        Usuario user1 = new Usuario(1, "Felipe Costa", "felipelena", "uade1234", "Lima 757", "44967716", CondicionFiscal.EXENTO);
        Usuario user2 = new Usuario(2, "Lucas Munoz", "lucasmunoz", "uade1234", "Lima 757", "38000000", CondicionFiscal.NO_ALCANZADO);
        Usuario user3 = new Usuario(3, "Francisco Fontana", "franciscofontana", "uade1234", "Lima 757", "44000000", CondicionFiscal.AUTONOMO);
        Usuario user4 = new Usuario(4, "Ignacio Cesarani", "ignaciocesarani", "uade1234", "Lima 757", "43000000", CondicionFiscal.MONOTRIBUTISTA);

        // Se guardan los usuarios creados en la base de datos de ObjectDB

        // TODO: hay un problema al crear los usuarios, objectdb no puede guardar el carrito como una clase, tiene que ser un tipo de dato soportado
        // TODO: el usuario felipelena se pudo guardar porque (lo mas probable) es que se haya guardado en la db antes de que se agregara el atributo carrito a la clase Usuario
        ControllerUsuarios.getInstancia().registrarUsuario(user1);
        ControllerUsuarios.getInstancia().registrarUsuario(user2);
        ControllerUsuarios.getInstancia().registrarUsuario(user3);
        ControllerUsuarios.getInstancia().registrarUsuario(user4);

        ControllerUsuarios.getInstancia().iniciarSesion(new UsuarioDTO("felipelena", "uade1234"));
        ControllerProductos.getInstancia().agregarProducto(new Producto(1, "Sacapuntas", 500));
        ControllerProductos.getInstancia().agregarProducto(new Producto(2, "Lapicera", 30));
        ControllerProductos.getInstancia().agregarProducto(new Producto(3, "Regla", 40));
        ControllerProductos.getInstancia().actualizarProducto(new Producto(1, "Sacapuntas", 100));
        //ControllerProductos.getInstancia().eliminarProducto(2);
        CarritoRepository repo = new CarritoRepository();
        Carrito cart = repo.read(ControllerUsuarios.getInstancia().getSession());
        cart.vaciar();
        cart.agregarItem(1,5);
        cart.agregarItem(3,7);
        cart.agregarItem(2,3);
        cart.eliminarItem(2);
        System.out.println(cart);
        cart.estadoAnterior();
        System.out.println(cart);
        cart.estadoPosterior();
        System.out.println(cart);
        cart.agregarItem(2,4);
        System.out.println(cart);
        cart.vaciar();
        System.out.println(cart);
        cart.estadoPosterior();
        cart.estadoPosterior();
        cart.estadoPosterior();
        cart.eliminarItem(2);
        cart.eliminarItem(2);

    }
}

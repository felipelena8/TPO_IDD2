package com.example.tpo_idd2;

import controllers.ControllerProductos;
import controllers.ControllerUsuarios;
import models.CondicionFiscal;
import models.Producto;
import models.Usuario;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class TpoIdd2Application {

    public static void main(String[] args) {
        SpringApplication.run(TpoIdd2Application.class, args);

        Usuario user1 = new Usuario("Felipe","felipelena", "uade1234","Alsina 1663","44967716",CondicionFiscal.EXENTO);
        Usuario user2 = new Usuario("Felipe","felipelena", "uade1234","Alsina 1664","44967716",CondicionFiscal.EXENTO);
        ControllerUsuarios.getInstancia().registrarUsuario(user1);
        ControllerUsuarios.getInstancia().registrarUsuario(user2);
        ControllerProductos.getInstancia().agregarProducto(new Producto(1, "Sacapuntas", 500));
        ControllerProductos.getInstancia().agregarProducto(new Producto(2, "Lapicera", 30));
        ControllerProductos.getInstancia().actualizarProducto(new Producto(1, "Sacapuntas", 100));
        ControllerProductos.getInstancia().eliminarProducto(1);
    }

}

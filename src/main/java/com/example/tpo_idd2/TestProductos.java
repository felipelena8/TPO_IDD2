package com.example.tpo_idd2;

import controllers.ControllerProductos;
import models.Producto;

import java.util.List;

public class TestProductos {
    public static void main(String[] args) {

        System.out.println("Productos persistidos: ");
        List<Producto> productos = ControllerProductos.getInstancia().productos();
        for(Producto prod:productos){
            System.out.println(prod);
        }
    }
}

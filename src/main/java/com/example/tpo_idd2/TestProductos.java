package com.example.tpo_idd2;

import controllers.ControllerProductos;
import models.Producto;

import java.util.List;

public class TestProductos {
    public static void main(String[] args) {
        List<Producto> productos = ControllerProductos.getInstancia().productos();
        for(Producto prod:productos){
            System.out.println(prod);
        }
    }
}

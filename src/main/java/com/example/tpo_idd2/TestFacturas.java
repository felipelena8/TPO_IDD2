package com.example.tpo_idd2;

import controllers.ControllerFactura;
import controllers.ControllerProductos;
import models.Factura;
import models.Producto;

import java.util.List;

public class TestFacturas {
    public static void main(String[] args) {

        System.out.println("Facturas persistidas: ");
        List<Factura> facturas = ControllerFactura.traerFacturas();
    for(Factura factura: facturas){
            System.out.println(factura);
        }
    }
}

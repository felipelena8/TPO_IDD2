package controllers;

import models.Factura;
import repositories.FacturaRepository;

import java.util.List;

public class ControllerFactura {

    public static List<Factura> traerFacturas(){
        FacturaRepository repo = new FacturaRepository();
        return repo.readAll();
    }

}

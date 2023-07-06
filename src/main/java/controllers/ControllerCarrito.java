package controllers;

import models.Carrito;
import models.Usuario;
import repositories.CarritoRepository;

public class ControllerCarrito {
    private static ControllerCarrito instancia = null;
    private CarritoRepository repo;

    private ControllerCarrito() {
        repo = new CarritoRepository();
    }

    public static ControllerCarrito getInstancia() {
        if (instancia == null) {
            instancia = new ControllerCarrito();
        }
        return instancia;
    }

    public Carrito buscarCarrito(Usuario usuario) {
        return repo.read(usuario);
    }
}

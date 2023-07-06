package controllers;

import models.Pedido;

import java.util.List;

public class ControllerPedidos {
    private static ControllerPedidos instancia = null;

    private ControllerPedidos() {
    }

    public static ControllerPedidos getInstancia() {
        if (instancia == null) {
            instancia = new ControllerPedidos();
        }
        return instancia;
    }

    public void agregarPedido(Pedido pedido) {

    }
}

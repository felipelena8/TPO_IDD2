package controllers;

import lombok.Getter;
import models.Pedido;
import repositories.PedidoRepository;

import java.util.List;

public class ControllerPedidos {
    private static ControllerPedidos instancia = null;

    @Getter
    private PedidoRepository repo = new PedidoRepository();

    private List<Pedido> pedidos;

    private ControllerPedidos() {
    }

    public static ControllerPedidos getInstancia() {
        if (instancia == null) {
            instancia = new ControllerPedidos();
        }
        return instancia;
    }

    public void agregarPedido(Pedido pedido) {

        pedidos.add(pedido);
        repo.guardarPedido(pedido);
    }
}

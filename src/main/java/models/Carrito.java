package models;

import controllers.ControllerProductos;
import lombok.Data;
import repositories.CarritoRepository;
import utils.FactoryDescuento;
import utils.FactoryImpuesto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class Carrito implements Serializable {
    private List<Item> items;
    private Usuario usuario;
    private CarritoRepository repo = new CarritoRepository();

    public Carrito(Usuario usuario) {
        items = new ArrayList<>();
        this.usuario = usuario;
    }

    public double precioTotal() {
        return items.stream().map(Item::precioItem).reduce(0.0, Double::sum);
    }

    public Pedido generarPedido() {
        if (!estaVacio()) {
            Pedido pedido = new Pedido(items, precioTotal(), FactoryDescuento.crearDescuento(usuario.getCategoria()), FactoryImpuesto.crearImpuestos(usuario.getCondicionFiscal()));
            usuario.getPedidos().add(pedido);
            return pedido;
        }
        return null;
    }

    public void agregarItem(int idProducto, int cantidad) {
        if (cantidad <= 0) {
            return;
        }
        Producto prod = ControllerProductos.getInstancia().buscarProducto(idProducto);
        if (prod == null) {
            System.out.println("No existe el producto");
            return;
        }
        Item existe = items.stream().filter(item -> item.getProducto().getId() == idProducto).findFirst().orElse(null);
        if (existe == null) {
            items.add(new Item(prod, cantidad));
        } else {
            existe.setCantidad(existe.getCantidad() + cantidad);
        }

        repo.agregarItem(idProducto, cantidad, getUsuario().getId());
    }

    public void eliminarItem(int idProducto) {
        Producto prod = ControllerProductos.getInstancia().buscarProducto(idProducto);
        if (prod == null) {
            System.out.println("No existe el producto");
            return;
        }
        if (!items.stream().map(item -> item.getProducto()).anyMatch(producto -> producto.getId() == idProducto)) {
            System.out.println("No existe el producto en el carrito");
            return;
        }
        this.items = items.stream().filter(item -> item.getProducto().getId() != idProducto).toList();//eliminacion de memoria
        repo.eliminarProducto(idProducto, usuario.getId());
    }

    public void estadoAnterior() {
        if (repo.operacion(usuario.getId()) == 0) {
            System.out.println("Ya no hay estados anteriores");
            return;
        }
        repo.decrementarOperacion(usuario.getId());
        repo.read(usuario);
    }

    public void estadoPosterior() {
        if (repo.operacion(usuario.getId()) == repo.largoCarrito(usuario.getId())) {
            System.out.println("Ya no hay estados posteriores");
            return;
        }
        repo.incrementarOperacion(usuario.getId());
        repo.read(usuario);
    }

    public void vaciar() {
        items = new ArrayList<>();
        getRepo().limpiarCarrito(getUsuario().getId());
    }

    public boolean estaVacio() {
//        JedisPool pool = RedisConnectionPool.getInstancia().getConnection();
//        Jedis jedis = pool.getResource();
//        Long size = jedis.llen(usuario.getId() + "_cart:productos");
//        pool.close();
        return items.size() == 0;
    }

    @Override
    public String toString() {
        return "Carrito{" +
                "items=" + items +
                '}';
    }
}

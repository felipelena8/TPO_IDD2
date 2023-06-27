package models;

import config.RedisConnectionPool;
import controllers.ControllerPedidos;
import controllers.ControllerProductos;
import lombok.Data;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import repositories.CarritoRepository;

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

    public void generarPedido() {
        if (!estaVacio()) {
            Pedido pedido = new Pedido(items, precioTotal());
            usuario.getPedidos().add(pedido);
            ControllerPedidos.getInstancia().agregarPedido(pedido);
        }
    }

    public void agregarItem(int idProducto, int cantidad) {
        if (cantidad <= 0) {
            return;
        }
        Producto prod = ControllerProductos.getInstancia().buscarProducto(idProducto);
        if (prod == null) {
            return;
        }
        Item itemCarrito = items.stream().filter(item -> item.getProducto().getId() == idProducto).findFirst().orElse(null);
        if (itemCarrito == null) {
            items.add(new Item(prod, cantidad));
        } else {
            itemCarrito.setCantidad(cantidad);
        }
        repo.save(this);
    }

    public void eliminarItem(int idProducto) {
        JedisPool pool = RedisConnectionPool.getInstancia().getConnection();
        Jedis jedis = pool.getResource();
        Long indice = jedis.lpos(usuario.getId() + "_cart:productos", Integer.toString(idProducto));
        if (indice != null) {
            jedis.lrem(usuario.getId() + "_cart:productos", 0, Integer.toString(idProducto));
            jedis.lset(usuario.getId() + "_cart:cantidad", indice, "delete");
            jedis.lrem(usuario.getId() + "_cart:cantidad", 0, "delete");
        }

        pool.close();
    }

    public void decrementarCantidadProducto(int idProducto, int cantidad) {
        JedisPool pool = RedisConnectionPool.getInstancia().getConnection();
        Jedis jedis = pool.getResource();
        Long indice = jedis.lpos(usuario.getId() + "_cart:productos", Integer.toString(idProducto));
        if (indice != null) {
            int cantVieja = Integer.parseInt(jedis.lindex(usuario.getId() + "_cart:cantidad", indice));
            if (cantVieja - cantidad <= 0) {
                jedis.lrem(usuario.getId() + "_cart:productos", 0, Integer.toString(idProducto));
                jedis.lset(usuario.getId() + "_cart:cantidad", indice, "delete");
                jedis.lrem(usuario.getId() + "_cart:cantidad", 0, "delete");
            } else {
                jedis.lset(usuario.getId() + "_cart:cantidad", indice, Integer.toString(cantVieja - cantidad));
            }
        }

        pool.close();
    }

    public void incrementarCantidadProducto(int idProducto, int cantidad) {
        JedisPool pool = RedisConnectionPool.getInstancia().getConnection();
        Jedis jedis = pool.getResource();
        Long indice = jedis.lpos(usuario.getId() + "_cart:productos", Integer.toString(idProducto));
        if (indice != null) {
            int cantVieja = Integer.parseInt(jedis.lindex(usuario.getId() + "_cart:cantidad", indice));
            jedis.lset(usuario.getId() + "_cart:cantidad", indice, Integer.toString(cantVieja + cantidad));

        }
    }

    public void vaciar() {
        JedisPool pool = RedisConnectionPool.getInstancia().getConnection();
        Jedis jedis = pool.getResource();
        jedis.del(usuario.getId() + "_cart:productos");
        jedis.del(usuario.getId() + "_cart:cantidad");
        pool.close();
    }

    public boolean estaVacio() {
        JedisPool pool = RedisConnectionPool.getInstancia().getConnection();
        Jedis jedis = pool.getResource();
        Long size = jedis.llen(usuario.getId() + "_cart:productos");
        pool.close();
        return size == 0;
    }

    @Override
    public String toString() {
        return "Carrito{" +
                "items=" + items +
                '}';
    }
}

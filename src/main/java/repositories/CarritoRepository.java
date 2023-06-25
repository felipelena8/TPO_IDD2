package repositories;

import config.RedisConnectionPool;
import controllers.ControllerProductos;
import models.Carrito;
import models.Item;
import models.Producto;
import models.Usuario;
import redis.clients.jedis.Jedis;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CarritoRepository {
    private RedisConnectionPool pool = RedisConnectionPool.getInstancia();

    public void save(Carrito carrito){
        limpiarCarrito(carrito);
        Jedis jedis =pool.getConnection().getResource();
        String[] idsProductos = carrito.getItems().stream().map(item -> Integer.toString(item.getProducto().getId())).toList().toArray(new String[0]);
        String[] cantidadProductos = carrito.getItems().stream().map(item -> Integer.toString(item.getCantidad())).toList().toArray(new String[0]);
        jedis.lpush(carrito.getUsuario().getId() + "_cart:productos", idsProductos);
        jedis.lpush(carrito.getUsuario().getId() + "_cart:cantidad", cantidadProductos);
        jedis.close();
    }
    public Carrito read(Usuario usuario){
        Jedis jedis =pool.getConnection().getResource();
        List<Producto> productos =jedis.lrange(usuario.getId()+ "_cart:productos",0,-1).stream().map(s -> ControllerProductos.getInstancia().buscarProducto(Integer.parseInt(s))).toList();
        List<Integer> cantidades = jedis.lrange(usuario.getId()+ "_cart:productos",0,-1).stream().map(Integer::parseInt).toList();
        List<Item> items = new ArrayList<>();
        int index=0;
        for (Producto prod:productos){
            items.add(new Item(prod, cantidades.get(index++)));
        }
        jedis.close();
        usuario.getCarrito().setItems(items);
        return usuario.getCarrito();
    }
    public void limpiarCarrito(Carrito carrito){
        Jedis connection =pool.getConnection().getResource();
        connection.del(carrito.getUsuario().getId() + "_cart:productos");
        connection.del(carrito.getUsuario().getId() + "_cart:cantidad");
        connection.close();
    }


}

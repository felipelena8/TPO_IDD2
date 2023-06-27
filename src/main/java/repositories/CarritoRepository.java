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

    public int operacion(int idUsuario){
        Jedis jedis = pool.getConnection().getResource();
        try {
            int operacion = Integer.parseInt(jedis.get(idUsuario + "_cart:operacion"));
            jedis.quit();
            return operacion;
        }catch (Exception e){
            jedis.quit();
            return 0;}
    }

    public Carrito read(Usuario usuario) {
        Jedis jedis = pool.getConnection().getResource();
        List<Producto> productos = jedis.lrange(usuario.getId() + "_cart:productos", 0, -1).stream().map(s -> ControllerProductos.getInstancia().buscarProducto(Integer.parseInt(s))).toList();
        List<Integer> cantidades = jedis.lrange(usuario.getId() + "_cart:cantidad", 0, -1).stream().map(Integer::parseInt).toList();
        int cantOperaciones = operacion(usuario.getId());
        List<Item> items = new ArrayList<>();
        for (int i=0; i<cantOperaciones;i++) {
            int finalI = i;
            Item existe = items.stream().filter(item -> item.getProducto().equals(productos.get(finalI))).findFirst().orElse(null);
            if(existe!=null){
                existe.setCantidad(existe.getCantidad()+cantidades.get(i));
            }else {
                items.add(new Item(productos.get(i), cantidades.get(i)));
            }
        }

        jedis.quit();
        usuario.getCarrito().setItems(items.stream().filter(item -> item.getCantidad()!=0).collect(Collectors.toList()));
        return usuario.getCarrito();
    }

    public void limpiarCarrito(int idUsuario) {
        Jedis connection = pool.getConnection().getResource();
        connection.del(idUsuario + "_cart:productos");
        connection.del(idUsuario + "_cart:cantidad");
        connection.del(idUsuario + "_cart:operacion");
        connection.quit();
    }


    public void agregarItem(int idProducto, int cantidad, int idUsuario) {
        Jedis connection = pool.getConnection().getResource();
        int cantOperaciones = operacion(idUsuario)-1;
        if(cantOperaciones!=largoCarrito(idUsuario)){
            connection.ltrim(idUsuario + "_cart:productos", 0, cantOperaciones);
            connection.ltrim(idUsuario + "_cart:cantidad",0, cantOperaciones);
        }
        incrementarOperacion(idUsuario);
        connection.rpush(idUsuario + "_cart:productos", String.valueOf(idProducto));
        connection.rpush(idUsuario + "_cart:cantidad", String.valueOf(cantidad));
        connection.quit();
    }

    public void eliminarProducto(int idProducto, int idUsuario){
        Jedis connection = pool.getConnection().getResource();
        int cantOperaciones = operacion(idUsuario);
        if(cantOperaciones!=largoCarrito(idUsuario)){
            connection.ltrim(idUsuario + "_cart:productos", 0, cantOperaciones-1);
            connection.ltrim(idUsuario + "_cart:cantidad",0, cantOperaciones-1);
        }
        connection.rpush(idUsuario + "_cart:cantidad", String.valueOf(cantidadProducto(idProducto,idUsuario)*-1));
        connection.rpush(idUsuario + "_cart:productos", String.valueOf(idProducto));
        incrementarOperacion(idUsuario);
        connection.quit();
    }

    public long cantidadProducto(int idProducto, int idUsuario){
        Jedis connection = pool.getConnection().getResource();
        int cantProducto = 0;
        List<String> productosId = connection.lrange(idUsuario + "_cart:productos", 0, -1);
        List<Integer> cantidades = connection.lrange(idUsuario + "_cart:cantidad", 0, -1).stream().map(Integer::parseInt).toList();
        int index=0;
        for (String producto:productosId){
            if(Integer.parseInt(producto)==idProducto){
                cantProducto+=cantidades.get(index);
            }
            index++;
        }
        connection.quit();
        return cantProducto;
    }

    public long largoCarrito(int idUsuario){
        Jedis connection = pool.getConnection().getResource();
        long largo = connection.llen(idUsuario + "_cart:productos");
        connection.quit();
        return largo;
    }

    public void incrementarOperacion(int idUsuario){
        Jedis connection = pool.getConnection().getResource();
        connection.incr(idUsuario + "_cart:operacion");
        connection.quit();
    }

    public void decrementarOperacion(int idUsuario){
        Jedis connection = pool.getConnection().getResource();
        connection.decr(idUsuario+ "_cart:operacion");
        connection.quit();
    }
}

package models;

import config.RedisConnectionPool;
import controllers.ControllerPedidos;
import controllers.ControllerUsuarios;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Carrito implements Serializable {
    private List<Item> items;
    private Usuario usuario;

    public Carrito(Usuario usuario) {
        items = new ArrayList<>();
        this.usuario = usuario;
    }

    public double precioTotal(){
        return items.stream().map(item -> item.precioItem()).reduce(0.0,(a,b) -> a+b);
    }

    public void generarPedido(){
        if(!estaVacio()){
            Pedido pedido = new Pedido(items, precioTotal());
            usuario.getPedidos().add(pedido);
            ControllerPedidos.getInstancia().agregarPedido(pedido);
        }
    }

    public void agregarItem(int idProducto, int cantidad){
        JedisPool pool = RedisConnectionPool.getInstancia().getConnection();
        Jedis jedis = pool.getResource();
            Long pos = jedis.lpos(usuario.getId()+"_cart:productos",Integer.toString(idProducto));
            if(pos!=null){
                String cantVieja = jedis.lindex(usuario.getId()+"_cart:cantidad", pos);
                jedis.lset(usuario.getId()+"_cart:cantidad", pos,  Integer.toString(Integer.parseInt(cantVieja) + cantidad));
            }else {


                jedis.lpush(usuario.getId() + "_cart:productos", Integer.toString(idProducto));
                jedis.lpush(usuario.getId() + "_cart:cantidad", Integer.toString(cantidad));
            }
        pool.close();
    }

    public void eliminarItem(int idProducto){
        JedisPool pool = RedisConnectionPool.getInstancia().getConnection();
        Jedis jedis = pool.getResource();
        Long indice = jedis.lpos(usuario.getId()+"_cart:productos", Integer.toString(idProducto));
        if(indice!= null){
            jedis.lrem(usuario.getId()+"_cart:productos",0, Integer.toString(idProducto));
            jedis.lset(usuario.getId()+"_cart:cantidad", indice, "delete");
            jedis.lrem(usuario.getId()+"_cart:cantidad", 0, "delete");
        }

        pool.close();
    }

    public void decrementarCantidadProducto(int idProducto, int cantidad){
        JedisPool pool = RedisConnectionPool.getInstancia().getConnection();
        Jedis jedis = pool.getResource();
        Long indice = jedis.lpos(usuario.getId()+"_cart:productos", Integer.toString(idProducto));
        if(indice!= null){
            int cantVieja = Integer.parseInt(jedis.lindex(usuario.getId()+"_cart:cantidad",indice));
            if(cantVieja-cantidad<=0){
                jedis.lrem(usuario.getId()+"_cart:productos",0, Integer.toString(idProducto));
                jedis.lset(usuario.getId()+"_cart:cantidad", indice, "delete");
                jedis.lrem(usuario.getId()+"_cart:cantidad", 0, "delete");
            }else{
                jedis.lset(usuario.getId()+"_cart:cantidad", indice, Integer.toString(cantVieja-cantidad));
            }
        }

        pool.close();
    }

    public void incrementarCantidadProducto(int idProducto, int cantidad){
        JedisPool pool = RedisConnectionPool.getInstancia().getConnection();
        Jedis jedis = pool.getResource();
        Long indice = jedis.lpos(usuario.getId()+"_cart:productos", Integer.toString(idProducto));
        if(indice!= null){
            int cantVieja = Integer.parseInt(jedis.lindex(usuario.getId()+"_cart:cantidad",indice));
                jedis.lset(usuario.getId()+"_cart:cantidad", indice, Integer.toString(cantVieja+cantidad));

        }
    }

    public void vaciar(){
        JedisPool pool = RedisConnectionPool.getInstancia().getConnection();
        Jedis jedis = pool.getResource();
        jedis.del(usuario.getId()+"_cart:productos");
        jedis.del(usuario.getId()+"_cart:cantidad");
        pool.close();
    }

    public boolean estaVacio(){
        JedisPool pool = RedisConnectionPool.getInstancia().getConnection();
        Jedis jedis = pool.getResource();
        Long size = jedis.llen(usuario.getId()+"_cart:productos");
        pool.close();
        return size==0;
    }

}

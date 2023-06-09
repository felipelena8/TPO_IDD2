package controllers;

import logger.*;
import models.Producto;

import java.util.ArrayList;
import java.util.List;

public class ControllerProductos {
    private List<Producto> productos;
    private static ControllerProductos instancia = null;
    private final Logger logger = new Logger("logProductos.txt");
    private ControllerProductos() {
        productos = new ArrayList<>();
    }

    public static ControllerProductos getInstancia() {
        if(instancia == null){
            instancia = new ControllerProductos();
        }
        return instancia;
    }

    public void agregarProducto(Producto producto){
        if(buscarProducto(producto.getId())==null){
            productos.add(producto);
            logger.cambiarTipoLog(new CreateLog());
            logger.registrar(new RegistroLog(producto));
        }
    }

    public void eliminarProducto(int id){
        Producto producto = buscarProducto(id);
        if(producto !=null){
            productos.remove(producto);
        logger.cambiarTipoLog(new DeleteLog());
        logger.registrar(new RegistroLog(producto));}
    }

    public void actualizarProducto(Producto producto){
        Producto prodViejo = buscarProducto(producto.getId());
        if(prodViejo!=null){
            logger.cambiarTipoLog(new UpdateLog());
            logger.registrar(new RegistroLog(prodViejo,producto));
        }

    }

    public Producto buscarProducto(int id){
        return productos.stream().filter(producto -> producto.getId()==id).findFirst().orElse(null);
    }
}

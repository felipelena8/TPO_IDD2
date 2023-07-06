package controllers;

import logger.*;
import models.Producto;
import repositories.ProductoRepository;

import java.util.List;

public class ControllerProductos {
    private static ControllerProductos instancia = null;
    private final Logger logger = new Logger();
    private final ProductoRepository repo;

    private ControllerProductos() {
        repo = new ProductoRepository();
    }

    public static ControllerProductos getInstancia() {
        if (instancia == null) {
            instancia = new ControllerProductos();
        }
        return instancia;
    }
    public List<Producto> productos(){
        return repo.readAll();
    }

    public void agregarProducto(Producto producto) {
        if (buscarProducto(producto.getId()) == null) {
            System.out.println("Se ha creado un nuevo producto");
            repo.save(producto);
            logger.cambiarTipoLog(new CreateLog());
            logger.registrar(new RegistroLog(producto));
        }else{
            System.out.println("Ya existe un producto con el codigo "+producto.getId());
        }
    }

    public void eliminarProducto(int id) {
        Producto producto = buscarProducto(id);
        if (producto != null) {
            repo.delete(producto);
            logger.cambiarTipoLog(new DeleteLog());
            logger.registrar(new RegistroLog(producto));
        }
    }

    public void actualizarProducto(Producto producto) {
        Producto prodViejo = buscarProducto(producto.getId());
        if (prodViejo != null) {
            repo.update(prodViejo,producto);
            logger.cambiarTipoLog(new UpdateLog());
            logger.registrar(new RegistroLog(prodViejo, producto));
        }

    }

    public Producto buscarProducto(int id) {
        return repo.readPorId(id);
    }
}

package repositories;

import config.ObjectDBConnectionPool;
import lombok.Getter;
import models.Producto;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class ProductoRepository {
    @Getter
    private EntityManager em = ObjectDBConnectionPool.getInstancia().getConnection();

    public void save(Producto producto) {

        em.getTransaction().begin();
        em.persist(producto);
        em.getTransaction().commit();
    }

    public List<Producto> readAll(){

        Query query = em.createQuery("SELECT u FROM Producto u");
        List<Producto>  productos = query.getResultList();
        return  productos;
    }

    public Producto readPorId(int id) {
        Query query = em.createQuery("SELECT p FROM Producto p WHERE p.id = :id");
        query.setParameter("id",id);
        Producto prod = null;
        try {
            prod = (Producto) query.getResultList().get(0);
        }catch (Exception e){

        }
        return prod;
    }

    public void delete(Producto producto) {
        em.getTransaction().begin();
        em.remove(producto);
        em.getTransaction().commit();
    }

    public void update(Producto productoViejo, Producto productoNuevo) {
        em.getTransaction().begin();
        productoViejo.setDescripcion(productoNuevo.getDescripcion());
        productoViejo.setImagenesUrl(productoNuevo.getImagenesUrl());
        productoViejo.setVideosUrl(productoNuevo.getVideosUrl());
        productoViejo.setPrecio(productoNuevo.getPrecio());
        productoViejo.setStock(productoNuevo.getStock());
        productoViejo.setComentarios(productoNuevo.getComentarios());
        System.out.println(productoNuevo);
        em.getTransaction().commit();
    }
}

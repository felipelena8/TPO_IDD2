package repositories;

import config.ObjectDBConnectionPool;
import models.Carrito;
import models.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class UserRepository {

    public void save(Usuario usuario) {
        EntityManager em = ObjectDBConnectionPool.getConnection();
        em.getTransaction().begin();
        em.persist(usuario);
        em.getTransaction().commit();
        em.close();
    }

    public Usuario readPorId(int id) {
        EntityManager em = ObjectDBConnectionPool.getConnection();
        Query query = em.createQuery("SELECT u FROM Usuario u WHERE u.id = :id");
        query.setParameter("id", id);
        Usuario user = (Usuario) query.getResultList().get(0);
        em.close();
        return user;
    }

    public Usuario readPorUsername(String username) {
        EntityManager em = ObjectDBConnectionPool.getConnection();
        Query query = em.createQuery("SELECT u FROM Usuario u WHERE u.username = :username");
        query.setParameter("username", username);
        Usuario user =null;
        try {
            user= (Usuario) query.getResultList().get(0);
        }catch (Exception e){
            System.out.println("No hay ningun usuario con username "+ username);
        }
        em.close();
        return user;
    }

}

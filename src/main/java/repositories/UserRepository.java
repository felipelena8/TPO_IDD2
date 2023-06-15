package repositories;

import config.ObjectDBConnectionPool;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import models.Usuario;

public class UserRepository {

    public void save(Usuario usuario){
        EntityManager em = ObjectDBConnectionPool.getConnection();
        em.getTransaction().begin();
        em.persist(usuario);
        em.getTransaction().commit();
        em.close();
    }

    public Usuario read(int id){
        EntityManager em = ObjectDBConnectionPool.getConnection();
        Query query = em.createQuery("SELECT u FROM Usuario u WHERE u.id = :id");
        query.setParameter("id", id);
        Usuario user =  (Usuario) query.getResultList().get(0);
        em.close();
        return user;
    }
}

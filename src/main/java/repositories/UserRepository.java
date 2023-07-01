package repositories;

import config.ObjectDBConnectionPool;
import models.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class UserRepository {

    private EntityManager em = ObjectDBConnectionPool.getConnection();

    public void closeConnection(){
        em.close();
    }

    public void save(Usuario usuario) {
        usuario.setCarrito(null);
        em.getTransaction().begin();
        em.persist(usuario);
        em.getTransaction().commit();
    }

    public Usuario readPorId(int id) {
        Query query = em.createQuery("SELECT u FROM Usuario u WHERE u.id = :id");
        query.setParameter("id", id);
        Usuario user = (Usuario) query.getResultList().get(0);
        return user;
    }

    public Usuario readPorUsername(String username) {
        Query query = em.createQuery("SELECT u FROM Usuario u WHERE u.username = :username");
        query.setParameter("username", username);
        Usuario user = null;
        try {
            user = (Usuario) query.getResultList().get(0);
        } catch (Exception e) {
            System.out.println("No hay ningun usuario con username " + username);
        }
        return user;
    }

}

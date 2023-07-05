package repositories;

import config.ObjectDBConnectionPool;
import lombok.Getter;
import models.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class UserRepository {
    @Getter
    private EntityManager em = ObjectDBConnectionPool.getConnection();

    public void closeConnection(){
        em.close();
    }

    public void save(Usuario usuario) {
        em.getTransaction().begin();
        em.persist(usuario);
        em.getTransaction().commit();
    }


    public List<Usuario> read(){
        Query query = em.createQuery("SELECT u FROM Usuario u");
        List<Usuario>  usuarios = query.getResultList();
        return  usuarios;
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

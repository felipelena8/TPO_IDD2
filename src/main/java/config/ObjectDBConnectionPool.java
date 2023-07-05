package config;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ObjectDBConnectionPool {
    private static EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("bd.odb");
    private EntityManager em = emf.createEntityManager();
    private static ObjectDBConnectionPool pool;

    public EntityManager getConnection() {
        return em;
    }

    private ObjectDBConnectionPool() {

    }


    public static ObjectDBConnectionPool getInstancia() {
        if (pool == null) {
            pool = new ObjectDBConnectionPool();
        }
        return pool;
    }
}

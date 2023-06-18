package config;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ObjectDBConnectionPool {
    private static EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("usuarios.odb");//objectdb:$objectdb/db/points
    private static ObjectDBConnectionPool pool;

    private ObjectDBConnectionPool() {

    }

    public static EntityManager getConnection() {
        return emf.createEntityManager();
    }

    public ObjectDBConnectionPool getInstancia() {
        if (pool == null) {
            pool = new ObjectDBConnectionPool();
        }
        return pool;
    }


}

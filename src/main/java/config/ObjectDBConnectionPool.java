package config;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;

public class ObjectDBConnectionPool {

    private static EntityManagerFactory emf = crearFactory();
    private EntityManager em = emf.createEntityManager();
    private static ObjectDBConnectionPool pool;

    public EntityManager getConnection() {
        return em;
    }

    private ObjectDBConnectionPool() {

    }

    private static EntityManagerFactory crearFactory(){
        Map<String, String> properties = new HashMap<>();
        properties.put("objectdb.max.persistable.types", "20");
        return Persistence.createEntityManagerFactory("bd.odb", properties);
    }

    public static ObjectDBConnectionPool getInstancia() {
        if (pool == null) {
            pool = new ObjectDBConnectionPool();
        }
        return pool;
    }
}

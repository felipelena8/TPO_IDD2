package config;

import javax.persistence.*;
public class ObjectDBConnectionPool {
    private static  EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("$objectdb/db/usuarios.odb");//objectdb:$objectdb/db/points
    private static ObjectDBConnectionPool pool;
    private ObjectDBConnectionPool(){

    }

    public ObjectDBConnectionPool getInstancia(){
        if(pool==null) {
            pool = new ObjectDBConnectionPool();
        }
        return pool;
    }
    public static EntityManager getConnection(){
        return emf.createEntityManager();
    }


}

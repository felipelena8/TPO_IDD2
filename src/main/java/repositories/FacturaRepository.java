package repositories;

import config.ObjectDBConnectionPool;
import lombok.Getter;
import models.Factura;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class FacturaRepository {
    @Getter
    private EntityManager em = ObjectDBConnectionPool.getInstancia().getConnection();


    public void save(Factura factura) {
        em.getTransaction().begin();
        em.persist(factura);
        em.getTransaction().commit();
    }

    public List<Factura> readAll() {
        Query query = em.createQuery("SELECT u FROM Factura u");
        try {
            List<Factura> facturas = query.getResultList();
            return facturas;
        }catch (Exception e){}
        return new ArrayList<Factura>();
    }


}

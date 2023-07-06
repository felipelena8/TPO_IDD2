package repositories;

import config.ObjectDBConnectionPool;
import lombok.Getter;
import models.Pedido;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class PedidoRepository {
    @Getter
    private EntityManager em = ObjectDBConnectionPool.getInstancia().getConnection();

    public void guardarPedido(Pedido pedido) {
        em.getTransaction().begin();
        em.persist(pedido);
        em.getTransaction().commit();
    }

    public Pedido getPedidoById(int numeroPedido){
        Query query = em.createQuery("SELECT u FROM Pedido p WHERE p.numeroPedido = :numeroPedido");
        query.setParameter("numeroPedido", numeroPedido);
        Pedido pedido = null;
        try {
            pedido = (Pedido) query.getResultList().get(0);
        } catch (Exception e) {
            System.out.println("No hay ningun pedido con ese numbero " + numeroPedido);
        }

        return pedido;
    }
}

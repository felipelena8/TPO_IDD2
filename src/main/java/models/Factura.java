package models;

import models.MedioPago.MedioPago;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.util.Date;

@Entity
public class Factura {
    private Date fechaHoraEmision;
    private Date fechaVencimiento;
    @OneToOne(cascade = CascadeType.ALL)
    private Pedido pedido;
    private double total;
    private double subtotal;
    private String operadorInterviniente;
    @OneToOne(cascade = CascadeType.ALL)
    private Pago pago;

    public Factura(double subtotal, double total, Pedido pedido) {
        this.fechaHoraEmision = new Date();
        this.pedido = pedido;
        this.subtotal = subtotal;
        this.total = total;
    }

    public Factura(double subtotal, double total, String operadorInterviniente, Pedido pedido) {
        this.fechaHoraEmision = new Date();
        this.subtotal = subtotal;
        this.operadorInterviniente = operadorInterviniente;
        this.pedido = pedido;
        this.total = total;
    }

    public Pago generarPago(MedioPago medioPago) {
        pago = new Pago(medioPago, total);
        return pago;
    }

    public boolean estaAbonada(){
        return pago!=null;
    }


}

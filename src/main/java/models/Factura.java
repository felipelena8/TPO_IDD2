package models;

import controllers.ControllerUsuarios;
import lombok.Data;

import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Calendar;
import java.util.Date;

@Entity
@Data
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
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaHoraEmision);
        calendar.add(Calendar.MONTH,1);
        this.fechaVencimiento= calendar.getTime();
        this.pedido = pedido;
        this.subtotal = subtotal;
        this.total = total;
    }

    public Factura(double subtotal, double total, String operadorInterviniente, Pedido pedido) {
        this.fechaHoraEmision = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaHoraEmision);
        calendar.add(Calendar.MONTH,1);
        this.fechaVencimiento= calendar.getTime();
        this.subtotal = subtotal;
        this.operadorInterviniente = operadorInterviniente;
        this.pedido = pedido;
        this.total = total;

    }

    public void generarPago(MedioPago medioPago) {
        if(ControllerUsuarios.getInstancia().getSession().getMediosPago().contains(medioPago)){
            pago = new Pago(medioPago, total);
            System.out.println("Se ha realizado el pago");
            System.out.println(pago);
        }
    }

    public boolean estaAbonada(){
        return pago!=null;
    }

    @Override
    public String toString() {
        return "Factura{ fechaHoraEmision=" + fechaHoraEmision +
                ", fechaVencimiento=" + fechaVencimiento +
                ", total=" + total +
                ", subtotal=" + subtotal +
                ", items= " + pedido.getItems()+
                ", operadorInterviniente='" + operadorInterviniente +
                ", pago=" + pago +", descuento= "+pedido.getDescuento()+
                ", impuestos =" + pedido.getImpuestosAplicados()+
                '}';
    }
}

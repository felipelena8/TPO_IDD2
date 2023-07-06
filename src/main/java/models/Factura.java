package models;

import controllers.ControllerUsuarios;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
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
        calendar.add(Calendar.MONTH, 1);
        this.fechaVencimiento = calendar.getTime();
        this.pedido = pedido;
        this.subtotal = subtotal;
        this.total = total;
    }

    public Factura(double subtotal, double total, String operadorInterviniente, Pedido pedido) {
        this.fechaHoraEmision = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaHoraEmision);
        calendar.add(Calendar.MONTH, 1);
        this.fechaVencimiento = calendar.getTime();
        this.subtotal = subtotal;
        this.operadorInterviniente = operadorInterviniente;
        this.pedido = pedido;
        this.total = total;

    }

    public void generarPago(MedioPago medioPago) {
        if (ControllerUsuarios.getInstancia().getSession().getMediosPago().contains(medioPago)) {
            pago = new Pago(medioPago, total);
            System.out.println("Se ha realizado el pago");
            System.out.println(pago);
        }
    }

    public boolean estaAbonada() {
        return pago != null;
    }

    @Override
    public String toString() {
        String toString = "\n\nFactura:";
        toString += "\nFecha emisión: " + fechaHoraEmision;
        toString += "\nTotal: " + total;
        toString += "\nSubtotal: " + subtotal;
        toString += "\nItems:\n";
        for (Item item : pedido.getItems()) {
            toString += item.toString();
        }
        toString += "\nOperador Interviniente: " + operadorInterviniente;
        toString += "\nPago: " + pago.toString();
        toString += "\nImpuestos:\n";
        for (Impuesto impuesto : pedido.getImpuestosAplicados()) {
            toString += impuesto.getNombre() + "\n";
        }
        toString += "\nDescuento: " + pedido.getDescuento().getPorcentaje() + "%";
        return toString;
    }
}

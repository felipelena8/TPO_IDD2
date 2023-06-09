package logger;

import lombok.Getter;
import models.Producto;

public class RegistroLog {
    @Getter
    private Producto estadoAnterior;
    @Getter
    private Producto nuevoEstado;

    public RegistroLog(Producto nuevoEstado) {
        this.nuevoEstado = nuevoEstado;
    }

    public RegistroLog(Producto estadoAnterior, Producto nuevoEstado) {
        this.estadoAnterior = estadoAnterior;
        this.nuevoEstado = nuevoEstado;
    }
}

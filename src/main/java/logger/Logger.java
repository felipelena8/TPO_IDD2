package logger;

import lombok.Getter;
import lombok.Setter;

public class Logger {
    @Setter
    @Getter
    private TipoLog tipoLog;

    public Logger() {
    }

    public void registrar(RegistroLog registro) {
        tipoLog.registrar(registro);
    }

    public void cambiarTipoLog(TipoLog tipoLog) {
        this.tipoLog = tipoLog;
    }

}

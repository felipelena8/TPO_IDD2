package logger;

import lombok.Getter;
import lombok.Setter;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Logger {
    @Setter @Getter
    private String nombreArchivo;
    private TipoLog tipoLog;

    public Logger(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public void registrar(RegistroLog registro){
        try {
            tipoLog.registrar(getArchivo(),registro);
        } catch (IOException e) {
            System.out.println("Error en el registro de modificaciones de productos: "+e);
        }
    }
    public void cambiarTipoLog(TipoLog tipoLog){
        this.tipoLog=tipoLog;
    }

    public PrintWriter getArchivo() throws IOException {
        return new PrintWriter(new FileWriter(nombreArchivo, true));
    }
}

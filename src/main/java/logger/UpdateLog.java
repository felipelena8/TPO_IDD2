package logger;

import java.io.PrintWriter;

public class UpdateLog implements TipoLog{
    @Override
    public void registrar(PrintWriter archivo, RegistroLog registro) {
        archivo.println("UPDATE: Estado Anterior["+registro.getEstadoAnterior().productoLog()+"] -> Nuevo Estado["+registro.getNuevoEstado().productoLog()+"]");
        archivo.close();
    }
}

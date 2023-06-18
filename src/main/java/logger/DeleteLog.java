package logger;

import java.io.PrintWriter;

public class DeleteLog implements TipoLog {
    @Override
    public void registrar(PrintWriter archivo, RegistroLog registro) {
        archivo.println("DELETE: Producto Eliminado[" + registro.getNuevoEstado().productoLog() + "]");
        archivo.close();
    }
}

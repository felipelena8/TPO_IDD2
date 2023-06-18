package logger;

import java.io.PrintWriter;

public class CreateLog implements TipoLog {
    @Override
    public void registrar(PrintWriter archivo, RegistroLog registro) {
        archivo.println("CREATE: Nuevo Producto[" + registro.getNuevoEstado().productoLog() + "]");
        archivo.close();
    }
}

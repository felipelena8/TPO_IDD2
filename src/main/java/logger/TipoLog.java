package logger;

import java.io.PrintWriter;

public interface TipoLog {
    void registrar(PrintWriter archivo, RegistroLog registro);
}

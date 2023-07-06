package config;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import dtos.LogDTO;

import java.nio.file.Paths;

public class CassandraConnectionPool {

    // Connect with Astra DB

    private static CqlSession session = null;

    private static CassandraConnectionPool instancia = null;

    private CassandraConnectionPool() {
    }

    public static CassandraConnectionPool getInstancia() {
        if (instancia == null) {
            instancia = new CassandraConnectionPool();
        }
        return instancia;
    }

    public static void connect() {
        session = CqlSession.builder()
                .withCloudSecureConnectBundle(Paths.get("src/main/java/config/secure-connect.zip"))
                .withAuthCredentials("QtWropsYScUHfXSeteRKXZzy", "4.qUoUNF0rtbQAIz79eFeOXnTDyutj_i-qZbOuPSGzY2AJ8b4TusL,ODWu4NfLpfj7FbCNzfKw1,DveddJr2vnPQhyw4hXj9rd4Z.UN6+TTigxxNuqIMUZQhbMpuj624")
                .build();
    }

    public void borrarDatosTabla() {
        if (session == null)
            connect();

        session.execute("truncate logs.registro_logs;");
    }

    public void crearRegistroUPDATE(LogDTO registroDTO) {
        if (session == null)
            connect();

        String prev_imagenesUrl = registroDTO.getPrev_imagenesUrl().size() > 0 ? "'" + String.join("', '", registroDTO.getPrev_imagenesUrl()) + "'" : "";
        String prev_videosUrl = registroDTO.getPrev_videosUrl().size() > 0 ? "'" + String.join("', '", registroDTO.getPrev_videosUrl()) + "'" : "";

        String new_imagenesUrl = registroDTO.getNew_imagenesUrl().size() > 0 ? "'" + String.join("', '", registroDTO.getNew_imagenesUrl()) + "'" : "";
        String new_videosUrl = registroDTO.getNew_videosUrl().size() > 0 ? "'" + String.join("', '", registroDTO.getNew_videosUrl()) + "'" : "";

        session.execute("INSERT INTO logs.registro_logs(tipo_registro, codigo, prev_precio, prev_stock, prev_descripcion, prev_imagenes, prev_videos, new_precio, new_stock, new_descripcion, new_imagenes, new_videos, datetime ) VALUES ( 'UPDATE', " + registroDTO.getCodigo() + ", " + registroDTO.getPrev_precio() + ", " + registroDTO.getPrev_stock() + ", '" + registroDTO.getPrev_descripcion() + "', [" + prev_imagenesUrl + "], [" + prev_videosUrl + "],  " + registroDTO.getNew_precio() + ", " + registroDTO.getNew_stock() + ", '" + registroDTO.getNew_descripcion() + "', [" + new_imagenesUrl + "], [" + new_videosUrl + "], " + "toTimeStamp(now()));");
    }

    public void crearRegistroCREATE(LogDTO registroDTO) {
        if (session == null)
            connect();

        String new_imagenesUrl = registroDTO.getNew_imagenesUrl().size() > 0 ? "'" + String.join("', '", registroDTO.getNew_imagenesUrl()) + "'" : "";
        String new_videosUrl = registroDTO.getNew_videosUrl().size() > 0 ? "'" + String.join("', '", registroDTO.getNew_videosUrl()) + "'" : "";

        session.execute("INSERT INTO logs.registro_logs(tipo_registro, codigo, new_precio, new_stock, new_descripcion, new_imagenes, new_videos, datetime ) VALUES ( 'CREATE', " + registroDTO.getCodigo() + ", " + registroDTO.getNew_precio() + ", " + registroDTO.getNew_stock() + ", '" + registroDTO.getNew_descripcion() + "', [" + new_imagenesUrl + "], [" + new_videosUrl + "], " + "toTimeStamp(now()));");
    }

    public void crearRegistroDELETE(LogDTO registroDTO) {
        if (session == null)
            connect();

        String prev_imagenesUrl = registroDTO.getPrev_imagenesUrl().size() > 0 ? "'" + String.join("', '", registroDTO.getPrev_imagenesUrl()) + "'" : "";
        String prev_videosUrl = registroDTO.getPrev_videosUrl().size() > 0 ? "'" + String.join("', '", registroDTO.getPrev_videosUrl()) + "'" : "";

        session.execute("INSERT INTO logs.registro_logs(tipo_registro, codigo, prev_precio, prev_stock, prev_descripcion, prev_imagenes, prev_videos, datetime ) VALUES ( 'DELETE', " + registroDTO.getCodigo() + ", " + registroDTO.getPrev_precio() + ", " + registroDTO.getPrev_stock() + ", '" + registroDTO.getPrev_descripcion() + "', [" + prev_imagenesUrl + "], [" + prev_videosUrl + "], " + "toTimeStamp(now()));");
    }

    public ResultSet buscarLogsPorProducto(int numeroProducto) {
        if (session == null)
            connect();

        return session.execute("select * from logs.registro_logs where codigo = " + numeroProducto + ";");
    }

    public ResultSet buscarLogsPorProductoYFechas(int numeroProducto, String fechaMin, String fechaMax) {
        if (session == null)
            connect();

        return session.execute("select * from logs.registro_logs where codigo = " + numeroProducto + " AND datetime > '" + fechaMin + "' AND datetime < '" + fechaMax + "' allow filtering;");
    }
}

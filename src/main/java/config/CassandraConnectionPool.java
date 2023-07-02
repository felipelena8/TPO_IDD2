package config;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import dtos.LogDTO;

import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.Date;

public class CassandraConnectionPool {

    // Connect with Astra DB

    private static CqlSession session = null;

    private static CassandraConnectionPool instancia = null;

    private CassandraConnectionPool(){}

    public static CassandraConnectionPool getInstancia() {
        if (instancia == null) {
            instancia = new CassandraConnectionPool();
        }
        return instancia;
    }


    public static void connect(){
        session = CqlSession.builder()
                .withCloudSecureConnectBundle(Paths.get("src/main/java/config/secure-connect.zip"))
                .withAuthCredentials("QtWropsYScUHfXSeteRKXZzy","4.qUoUNF0rtbQAIz79eFeOXnTDyutj_i-qZbOuPSGzY2AJ8b4TusL,ODWu4NfLpfj7FbCNzfKw1,DveddJr2vnPQhyw4hXj9rd4Z.UN6+TTigxxNuqIMUZQhbMpuj624")
                .build();
    }

    public void borrarDatosTabla(){
        if(session==null)
            connect();

        session.execute("truncate logs.producto_logs;");

    }

    public void crearRegistroUPDATE(LogDTO registroDTO){
        if(session==null)
            connect();

        session.execute("INSERT INTO logs.producto_logs(tipo_registro, codigo, prev_precio, prev_stock, prev_descripcion, new_precio, new_stock, new_descripcion, datetime ) VALUES ( 'UPDATE', " + registroDTO.getCodigo() + ", " + registroDTO.getPrev_precio() + ", " + registroDTO.getPrev_stock() + ", '" + registroDTO.getPrev_descripcion() + "', " + registroDTO.getNew_precio() + ", " + registroDTO.getNew_stock() + ", '" + registroDTO.getNew_descripcion() + "', " + "toTimeStamp(now()));");

    }

    public void crearRegistroCREATE(LogDTO registroDTO){
        if(session==null)
            connect();

        session.execute("INSERT INTO logs.producto_logs(tipo_registro, codigo, new_precio, new_stock, new_descripcion, datetime ) VALUES ( 'CREATE', " + registroDTO.getCodigo() + ", " + registroDTO.getNew_precio() + ", " + registroDTO.getNew_stock() + ", '" + registroDTO.getNew_descripcion() + "', " + "toTimeStamp(now()));");
    }

    public void crearRegistroDELETE(LogDTO registroDTO){
        if(session==null)
            connect();

        session.execute("INSERT INTO logs.producto_logs(tipo_registro, codigo, prev_precio, prev_stock, prev_descripcion, datetime ) VALUES ( 'DELETE', " + registroDTO.getCodigo() + ", " + registroDTO.getPrev_precio() + ", " + registroDTO.getPrev_stock() + ", '" + registroDTO.getPrev_descripcion() + "', " + "toTimeStamp(now()));");
    }
}

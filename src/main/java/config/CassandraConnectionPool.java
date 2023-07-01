package config;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import java.nio.file.Paths;

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

    public String getValue(String key) {
        if(session==null)
            connect();

        String value = "";

        // Execute the Query to fetch the value for a specific key
        ResultSet rs = session.execute("select * from logs.lista_reproduccion");
        Row row = rs.one();

        if (row != null) {
            value = row.getString("album");
        } else {
            System.out.println("Error en la conexion.");
        }

        return value;
    }
/*
    public static void main(String[] args){
        String value = getValue("key");
        System.out.println("value: " + value);

    }*/
}

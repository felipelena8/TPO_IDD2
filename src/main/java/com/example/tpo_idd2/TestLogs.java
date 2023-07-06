package com.example.tpo_idd2;

import com.datastax.oss.driver.api.core.cql.ResultSet;
import config.CassandraConnectionPool;
import utils.Utils;

public class TestLogs {
    public static void main(String[] args) {
        ResultSet resultado = CassandraConnectionPool.getInstancia().buscarLogsPorProductoYFechas(2, "2023-07-06 16:00:00","2023-07-07");
        Utils.leerResultadoCassandra(resultado);
    }
}

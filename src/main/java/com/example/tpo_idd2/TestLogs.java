package com.example.tpo_idd2;

import com.datastax.oss.driver.api.core.cql.ResultSet;
import config.CassandraConnectionPool;
import utils.Utils;

public class TestLogs {
    public static void main(String[] args) {
        ResultSet resultado = CassandraConnectionPool.getInstancia().buscarLogsPorProducto(2);

        System.out.println("Buscar todos los logs en el producto 2 realizados entre el 06-07-2023 4PM y el 07-07-2023");
        System.out.println();
        Utils.leerResultadoCassandra(resultado);
    }
}

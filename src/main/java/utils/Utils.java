package utils;

import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;

import java.util.List;
import java.util.Scanner;

public class Utils {
    private static Scanner sc = new Scanner(System.in);

    public static void pausar() {
        System.out.println("\nPresione enter para continuar...\n");
        sc.nextLine();
    }

    public static void leerResultadoCassandra(ResultSet rs) {
        List<Row> rows = rs.all();
        for (Row row : rows) {
            System.out.println(leerRow(row));
        }

    }

    public static String leerRow(Row row) {
        String s = "codigo: " + row.getInt("codigo") +
                ", datetime: " + row.getInstant("datetime") +
                ", tipo_registro: " + row.getString("tipo_registro") +
                ", new_descripcion:  " + row.getString("new_descripcion") +
                ", new_imagenes:  " + row.getList("new_imagenes", String.class) +
                ", new_precio:  " + row.getDouble("new_precio") +
                ", new_stock:  " + row.getInt("new_stock") +
                ", new_videos: " + row.getList("new_videos", String.class) +
                ", prev_descripcion:  " + row.getString("prev_descripcion") +
                ", prev_imagenes:  " + row.getList("prev_imagenes", String.class) +
                ", prev_precio:  " + row.getDouble("prev_precio") +
                ", prev_stock:  " + row.getInt("prev_stock") +
                ", prev_videos: " + row.getList("prev_videos", String.class);
        return s;
    }
}

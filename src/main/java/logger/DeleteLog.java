package logger;

import config.CassandraConnectionPool;
import dtos.LogDTO;

import java.util.ArrayList;
import java.util.List;

public class DeleteLog implements TipoLog {

    @Override
    public void registrar(RegistroLog registro) {

        LogDTO logDTO = new LogDTO(
                "DELETE",
                registro.getNuevoEstado().getId(),
                registro.getEstadoAnterior().getPrecio(),
                registro.getEstadoAnterior().getStock(),
                registro.getEstadoAnterior().getDescripcion(),
                registro.getEstadoAnterior().getImagenesUrl(),
                registro.getEstadoAnterior().getVideosUrl(),
                0,
                0,
                "",
                new ArrayList<>(),
                new ArrayList<>()
        );

        CassandraConnectionPool pool = CassandraConnectionPool.getInstancia();
        pool.crearRegistroDELETE(logDTO);
    }
}

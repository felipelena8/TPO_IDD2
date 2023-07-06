package logger;

import config.CassandraConnectionPool;
import dtos.LogDTO;

import java.util.ArrayList;

public class CreateLog implements TipoLog {
    @Override
    public void registrar(RegistroLog registro) {

        LogDTO logDTO = new LogDTO(
                "CREATE",
                registro.getNuevoEstado().getId(),
                0,
                0,
                "",
                new ArrayList<>(),
                new ArrayList<>(),
                registro.getNuevoEstado().getPrecio(),
                registro.getNuevoEstado().getStock(),
                registro.getNuevoEstado().getDescripcion(),
                registro.getNuevoEstado().getImagenesUrl(),
                registro.getNuevoEstado().getVideosUrl()
        );

        CassandraConnectionPool pool = CassandraConnectionPool.getInstancia();
        pool.crearRegistroCREATE(logDTO);

    }
}

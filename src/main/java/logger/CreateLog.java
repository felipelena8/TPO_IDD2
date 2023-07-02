package logger;

import config.CassandraConnectionPool;
import dtos.LogDTO;

public class CreateLog implements TipoLog {
    @Override
    public void registrar(RegistroLog registro) {

        LogDTO logDTO = new LogDTO(
                "CREATE",
                registro.getNuevoEstado().getId(),
                0,
                0,
               "",
                registro.getNuevoEstado().getPrecio(),
                registro.getNuevoEstado().getStock(),
                registro.getNuevoEstado().getDescripcion()
        );

        CassandraConnectionPool pool = CassandraConnectionPool.getInstancia();
        pool.crearRegistroCREATE(logDTO);

    }
}

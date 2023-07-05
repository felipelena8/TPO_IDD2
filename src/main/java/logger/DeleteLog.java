package logger;

import config.CassandraConnectionPool;
import dtos.LogDTO;

public class DeleteLog implements TipoLog {
    @Override
    public void registrar(RegistroLog registro) {

        LogDTO logDTO = new LogDTO(
                "DELETE",
                registro.getNuevoEstado().getId(),
                registro.getEstadoAnterior().getPrecio(),
                registro.getEstadoAnterior().getStock(),
                registro.getEstadoAnterior().getDescripcion(),
                0,
                0,
                ""
        );

        CassandraConnectionPool pool = CassandraConnectionPool.getInstancia();
        pool.crearRegistroDELETE(logDTO);
    }
}

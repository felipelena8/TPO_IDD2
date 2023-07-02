package logger;

import config.CassandraConnectionPool;
import dtos.LogDTO;

public class UpdateLog implements TipoLog {
    @Override
    public void registrar(RegistroLog registro) {
        LogDTO logDTO = new LogDTO(
                "UPDATE",
                registro.getNuevoEstado().getId(),
                registro.getEstadoAnterior().getPrecio(),
                registro.getEstadoAnterior().getStock(),
                registro.getEstadoAnterior().getDescripcion(),
                registro.getNuevoEstado().getPrecio(),
                registro.getNuevoEstado().getStock(),
                registro.getNuevoEstado().getDescripcion()
        );

        CassandraConnectionPool pool = CassandraConnectionPool.getInstancia();
        pool.crearRegistroUPDATE(logDTO);
    }
}

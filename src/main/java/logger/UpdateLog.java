package logger;

import config.CassandraConnectionPool;
import dtos.LogDTO;
import java.util.List;

public class UpdateLog implements TipoLog {
    @Override
    public void registrar(RegistroLog registro) {

        List<String> prev_comentarios = registro.getEstadoAnterior().getComentarios().stream().map(comentario -> comentario.toString()).toList();
        List<String> new_comentarios = registro.getNuevoEstado().getComentarios().stream().map(comentario -> comentario.toString()).toList();

        LogDTO logDTO = new LogDTO(
                "UPDATE",
                registro.getNuevoEstado().getId(),
                registro.getEstadoAnterior().getPrecio(),
                registro.getEstadoAnterior().getStock(),
                registro.getEstadoAnterior().getDescripcion(),
                registro.getEstadoAnterior().getImagenesUrl(),
                registro.getEstadoAnterior().getVideosUrl(),
                prev_comentarios,
                registro.getNuevoEstado().getPrecio(),
                registro.getNuevoEstado().getStock(),
                registro.getNuevoEstado().getDescripcion(),
                registro.getNuevoEstado().getImagenesUrl(),
                registro.getNuevoEstado().getVideosUrl(),
                new_comentarios
        );

        CassandraConnectionPool pool = CassandraConnectionPool.getInstancia();
        pool.crearRegistroUPDATE(logDTO);
    }
}

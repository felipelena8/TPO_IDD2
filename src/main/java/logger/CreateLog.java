package logger;

import config.CassandraConnectionPool;
import dtos.LogDTO;

import java.util.ArrayList;
import java.util.List;

public class CreateLog implements TipoLog {
    @Override
    public void registrar(RegistroLog registro) {

        List<String> new_comentarios = registro.getNuevoEstado().getComentarios().stream().map(comentario -> comentario.toString()).toList();

        LogDTO logDTO = new LogDTO(
                "CREATE",
                registro.getNuevoEstado().getId(),
                0,
                0,
                "",
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>(),
                registro.getNuevoEstado().getPrecio(),
                registro.getNuevoEstado().getStock(),
                registro.getNuevoEstado().getDescripcion(),
                registro.getNuevoEstado().getImagenesUrl(),
                registro.getNuevoEstado().getVideosUrl(),
                new_comentarios
        );

        CassandraConnectionPool pool = CassandraConnectionPool.getInstancia();
        pool.crearRegistroCREATE(logDTO);

    }
}

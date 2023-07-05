package utils;

import controllers.ControllerUsuarios;
import lombok.Getter;
import models.Categoria;
import models.Usuario;
import repositories.UserRepository;

import java.util.*;

public class BotCategoriaUsuario {
    private int periodo =60000; // un minuto en milisegundos
    private Timer timer = new Timer();
    private TimerTask tarea1 = new TimerTask(){
        @Override
        public void run() {
            UserRepository repo = ControllerUsuarios.getInstancia().getRepo();
            List<Usuario> usuarios = repo.read();
            repo.getEm().getTransaction().begin();
            for(Usuario user:usuarios){
                user.setCategoria(Categoria.LOW);
                user.setTiempoEnDia(new Tiempo());
            }
            repo.getEm().getTransaction().commit();
        }
    };
    private TimerTask tarea2 = new TimerTask() {//Se cuenta el tiempo en sesion y se va cambiando la categoria segun corresponda
      @Override
        public void run() {
            UserRepository repo = ControllerUsuarios.getInstancia().getRepo();
            Usuario usuario= ControllerUsuarios.getInstancia().getSession();
            if(usuario!=null){
                repo.getEm().getTransaction().begin();
                usuario.getTiempoEnDia().sumarMilisegundos(periodo);
                if(usuario.getTiempoEnDia().getMinutos()>=7){
                    usuario.setCategoria(Categoria.TOP);
                }else if(usuario.getTiempoEnDia().getMinutos()>=2){
                    usuario.setCategoria(Categoria.MEDIUM);
                }else{
                    usuario.setCategoria(Categoria.LOW);
                }
                repo.getEm().getTransaction().commit();
                System.out.println(repo.readPorUsername(usuario.getUsername()));
            }

        }
    };

    public void sumarTiempoSession(){
        timer.schedule(tarea2, 0, periodo);
    }

    public BotCategoriaUsuario(){
        iniciarBot();
    }

    public void iniciarBot(){
        resetearCategoriaYTiempo();
        sumarTiempoSession();
    }

    public void resetearCategoriaYTiempo(){//Al finalizar el dia la categoria del usuario y el tiempo de session se reincian
        long periodo = 24 * 60 * 60 * 1000; // 24 horas en milisegundos
        // Obtener la fecha y hora actual
        Calendar calendar = Calendar.getInstance();
        Date currentTime = calendar.getTime();
        // Establecer la hora deseada para la primera ejecución (00:00 del siguiente día)
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.add(Calendar.DAY_OF_YEAR, 1); // Siguiente día
        // Calcular el retraso hasta la próxima ejecución
        long delay = calendar.getTimeInMillis() - currentTime.getTime();
        timer.schedule(tarea1, delay, periodo);
    }
}

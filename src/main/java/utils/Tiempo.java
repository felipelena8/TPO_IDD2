package utils;

import lombok.Getter;

import javax.persistence.Entity;

@Entity
public class Tiempo {
    @Getter
    private int horas;
    @Getter
    private int minutos;
    @Getter
    private int segundos;

    public Tiempo(int horas, int minutos, int segundos) {
        this.horas = horas;
        this.minutos = minutos;
        this.segundos = segundos;
    }

    public Tiempo() {
        this.horas = 0;
        this.minutos = 0;
        this.segundos = 0;
    }

    public void sumarMilisegundos(int milisegundos) {
        int segundos = milisegundos / 1000;
        sumarSegundos(segundos);
    }

    public void sumarHoras(int horas) {
        this.horas += horas;
    }

    public void sumarMinutos(int minutos) {
        int suma = this.minutos + minutos;
        if (suma > 59) {
            sumarHoras(suma / 60);
            this.minutos = suma % 60;
        } else {
            this.minutos += minutos;
        }
    }

    public void sumarSegundos(int segundos) {
        int suma = this.segundos + segundos;
        if (suma > 59) {
            sumarMinutos(suma / 60);
            this.segundos = suma % 60;
        } else {
            this.segundos += segundos;
        }
    }

    @Override
    public String toString() {
        return horas + ":" + minutos + ":" + segundos;
    }
}

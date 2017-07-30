package industries.marin.procows.Models;

import java.sql.Time;
import java.util.Date;

/**
 * Created by RicardoM on 19/01/2017.
 */

public class Alarma {
    long id;
    String nombre, fecha, hora, parto;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getParto() {
        return parto;
    }

    public void setParto(String parto) {
        this.parto = parto;
    }
}

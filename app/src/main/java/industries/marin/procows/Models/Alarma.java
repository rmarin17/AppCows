package industries.marin.procows.models;

/**
 * Created by RicardoM on 19/01/2017.
 */

public class Alarma {
    long id;
    String nombre, fechaince, hora, parto, tipoince, procelo, toro;

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

    public String getFechaince() {
        return fechaince;
    }

    public void setFechaince(String fechaince) {
        this.fechaince = fechaince;
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

    public String getTipoince() {
        return tipoince;
    }

    public void setTipoince(String tipoince) {
        this.tipoince = tipoince;
    }

    public String getProcelo() {
        return procelo;
    }

    public void setProcelo(String procelo) {
        this.procelo = procelo;
    }

    public String getToro() {
        return toro;
    }

    public void setToro(String toro) {
        this.toro = toro;
    }
}

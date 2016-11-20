package app.genex.com.junosty;

/**
 * Created by Invitado1 on 13/11/2016.
 */

public class PerfilHelper {

    private String Nombre, boleta;


    public PerfilHelper(){

    }

    public PerfilHelper(String nombre, String boleta) {
        Nombre = nombre;
        this.boleta = boleta;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getBoleta() {
        return boleta;
    }

    public void setBoleta(String boleta) {
        this.boleta = boleta;
    }
}

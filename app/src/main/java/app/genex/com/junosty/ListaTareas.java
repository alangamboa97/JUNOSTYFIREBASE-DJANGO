package app.genex.com.junosty;

/**
 * Created by Invitado1 on 05/11/2016.
 */

public class ListaTareas {

    private String Nombre, boleta , Tarea;

    public ListaTareas(){

    }

    public ListaTareas(String nombre, String boleta, String Tarea) {

        Nombre = nombre;
        this.boleta = boleta;
        this.Tarea = Tarea;
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


    public String getTarea() {
        return Tarea;
    }

    public void setTarea1(String tarea) {
        Tarea = tarea;
    }
}

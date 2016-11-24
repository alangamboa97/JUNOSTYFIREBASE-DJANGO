package app.genex.com.junosty;

/**
 * Created by Invitado1 on 05/11/2016.
 */

public class ListaTareas {

    private String Tarea;
    private String DescripciónT;

    public String getDescripciónT() {
        return DescripciónT;
    }

    public void setDescripciónT(String descripciónT) {
        DescripciónT = descripciónT;
    }



    public ListaTareas(String Tarea2, String Tarea1, String Tarea, String DescripciónT) {



        this.Tarea = Tarea;
        this.Tarea1 = Tarea1;
        this.Tarea2 = Tarea2;
        this.DescripciónT = DescripciónT;
    }

    public String getTarea1() {
        return Tarea1;
    }

    public void setTarea1(String tarea1) {
        Tarea1 = tarea1;

    }

    public String getTarea2() {
        return Tarea2;
    }

    public void setTarea2(String tarea2) {
        Tarea2 = tarea2;
    }

    private String Tarea1;
    private String Tarea2;

    public ListaTareas(){

    }






    public String getTarea() {
        return Tarea;
    }


}

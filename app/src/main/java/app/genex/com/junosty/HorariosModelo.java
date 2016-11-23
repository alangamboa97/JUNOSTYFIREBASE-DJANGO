package app.genex.com.junosty;

/**
 * Created by Invitado1 on 23/11/2016.
 */

public class HorariosModelo {
    private String Materia ;
    private String Descripción;


    public HorariosModelo(){


    }

    public HorariosModelo(String Materia, String Descripción){
        this.Materia = Materia;
        this.Descripción = Descripción;
    }
    public String getMateria() {
        return Materia;
    }



    public void setNombreMateria(String Materia) {
        Materia = Materia;
    }

    public String getDescripción() {
        return Descripción;
    }

    public void setDescMateria(String descMateria) {
        Descripción = descMateria;
    }



}

package app.genex.com.junosty;

/**
 * Created by Invitado1 on 23/11/2016.
 */

public class HorariosModelo {
    private String Materia ;
    private String DescMateria;


    public HorariosModelo(){


    }

    public HorariosModelo(String Materia, String DescMateria){
        this.Materia = Materia;
        this.DescMateria = DescMateria;
    }
    public String getMateria() {
        return Materia;
    }



    public void setNombreMateria(String Materia) {
        Materia = Materia;
    }

    public String getDescMateria() {
        return DescMateria;
    }

    public void setDescMateria(String descMateria) {
        DescMateria = descMateria;
    }



}

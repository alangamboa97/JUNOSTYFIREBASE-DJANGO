package app.genex.com.junosty;

/**
 * Created by Invitado1 on 23/11/2016.
 */

public class HorariosModelo {
    private String NombreMateria ;
    private String DescMateria;


    public HorariosModelo(){


    }

    public HorariosModelo(String NombreMateria, String DescMateria){
        this.NombreMateria = NombreMateria;
        this.DescMateria = DescMateria;
    }
    public String getNombreMateria() {
        return NombreMateria;
    }



    public void setNombreMateria(String nombreMateria) {
        NombreMateria = nombreMateria;
    }

    public String getDescMateria() {
        return DescMateria;
    }

    public void setDescMateria(String descMateria) {
        DescMateria = descMateria;
    }



}

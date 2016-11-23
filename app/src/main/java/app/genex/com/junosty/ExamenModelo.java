package app.genex.com.junosty;

/**
 * Created by Invitado1 on 22/11/2016.
 */

public class ExamenModelo {
    private String Examen;
    private String DescExamen;

    public ExamenModelo(){

    }
    public ExamenModelo(String Examen, String DescExamen) {



        this.Examen = Examen;
        this.DescExamen = DescExamen;

    }

    public String getDescExamen() {
        return DescExamen;
    }

    public void setDescExamen(String descExamen) {
        DescExamen = descExamen;
    }

    public String getExamen() {
        return Examen;
    }

    public void setExamen(String examen) {
        Examen = examen;
    }




}

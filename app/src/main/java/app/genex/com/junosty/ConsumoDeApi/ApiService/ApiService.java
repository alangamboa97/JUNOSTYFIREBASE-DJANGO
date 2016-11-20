package app.genex.com.junosty.ConsumoDeApi.ApiService;

import java.util.List;

import app.genex.com.junosty.ConsumoDeApi.Modelos.Usuario;
import app.genex.com.junosty.ConsumoDeApi.Modelos.UsuarioRespuesta;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Invitado1 on 20/11/2016.
 */

public interface ApiService {
    @GET("horario/?format=json")
    Call<List<UsuarioRespuesta>> obtenerListaUsuario();

    @POST("horario/")
    Call<Usuario> insertStudentInfo(@Field("username") String username);


}


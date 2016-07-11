package sotrh.izeni.mehprojectjava;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;

/**
 * Created by izeni on 7/11/16.
 */
public interface MehService {
    String BASE_URL = "https://api.meh.com/";

    @GET("1/current.json?apikey=" + Secret.API_KEY)
    Call<ResponseWrapper> getCurrentDeal();
}

package sotrh.izeni.mehprojectjava.service;

import retrofit2.Call;
import retrofit2.http.GET;
import sotrh.izeni.mehprojectjava.ResponseWrapper;
import sotrh.izeni.mehprojectjava.data.Secret;

/**
 * Created by izeni on 7/11/16.
 */
public interface MehService {
    String BASE_URL = "https://api.meh.com/";

    @GET("1/current.json?apikey=" + Secret.API_KEY)
    Call<ResponseWrapper> getCurrentDeal();
}

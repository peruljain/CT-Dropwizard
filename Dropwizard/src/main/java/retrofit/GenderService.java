package retrofit;

import models.GenderModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface GenderService {
    @GET
    Call<GenderModel> getGender(@Url String url, @Query("name") String name);
}

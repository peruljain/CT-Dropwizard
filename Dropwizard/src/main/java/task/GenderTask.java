package task;

import models.GenderModel;
import retrofit.GenderService;
import retrofit2.Call;

import java.util.Optional;
import java.util.concurrent.Callable;

public class GenderTask implements Callable<Optional<GenderModel>> {

    private final String name;
    private final GenderService genderService;

    public GenderTask(String name, GenderService genderService) {
        this.name = name;
        this.genderService = genderService;
    }

    @Override
    public Optional<GenderModel> call() throws Exception {
        Call<GenderModel> genderModel = genderService.getGender("http://api.genderize.io/", name);
        return Optional.of(genderModel.execute().body());
    }
}

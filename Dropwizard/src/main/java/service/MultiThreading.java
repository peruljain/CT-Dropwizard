package service;

import api.models.GenderResponseModel;
import lombok.RequiredArgsConstructor;
import models.GenderModel;
import retrofit.GenderService;
import task.GenderTask;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class MultiThreading {

    private final ExecutorService executorService;
    private final GenderService genderService;

    public List<GenderResponseModel> run() {
        List<String> names = Arrays.asList("perul", "saurabh", "vaibhav", "maulik", "amit");
        List<GenderTask> tasks = new ArrayList<>();
        for (String name : names) {
            tasks.add(new GenderTask(name, genderService));
        }
        List<GenderModel> responses = new ArrayList<>();
        try {
            List<Future<Optional<GenderModel>>> futureList = executorService.invokeAll(tasks);

            for (Future<Optional<GenderModel>> result : futureList) {
                try {
                    Optional<GenderModel> genderModel = result.get();
                    genderModel.ifPresent(responses::add);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<GenderResponseModel> result = new ArrayList<>();

        for (GenderModel model : responses) {
            result.add(
                    GenderResponseModel.builder()
                            .name(model.getName())
                            .gender(model.getGender())
                            .build());
        }

        return result;
    }
}

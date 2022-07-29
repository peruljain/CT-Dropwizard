package bootstrap;

import base.BaseDropwizardModule;
import com.google.inject.Provides;
import io.dropwizard.setup.Environment;
import lombok.RequiredArgsConstructor;
import retrofit.GenderService;
import retrofit.RetrofitServiceGenerator;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class SampleModule extends BaseDropwizardModule<SampleConfiguration> {

    private final SampleConfiguration sampleConfiguration;
    private final Environment environment;

    @Override
    protected void configureModule() {
        bind(SampleConfiguration.class).toInstance(sampleConfiguration);
        bind(Environment.class).toInstance(environment);
    }

    @Provides
    @Singleton
    public ExecutorService executorService(SampleConfiguration sampleConfiguration) {
        return Executors.newFixedThreadPool(sampleConfiguration.getExecutorConfig().getPoolSize());
    }

    @Provides
    @Singleton
    public RetrofitServiceGenerator retrofitServiceGenerator() {
        return new RetrofitServiceGenerator();
    }

    @Provides
    @Singleton
    public GenderService genderService(RetrofitServiceGenerator retrofitServiceGenerator) {
        return retrofitServiceGenerator.createService(GenderService.class);
    }
}

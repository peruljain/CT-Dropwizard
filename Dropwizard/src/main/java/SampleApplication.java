import base.BaseApplication;
import bootstrap.SampleConfiguration;
import bootstrap.SampleModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import io.dropwizard.setup.Bootstrap;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import org.reflections.Reflections;

public class SampleApplication extends BaseApplication<SampleConfiguration> {

    public static void main(String[] args) throws Exception {
        new SampleApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<SampleConfiguration> bootstrap) {
        super.initialize(bootstrap);
        final SwaggerBundle<SampleConfiguration> swaggerBundle =
                new SwaggerBundle<SampleConfiguration>() {
                    @Override
                    protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(
                            SampleConfiguration sampleConfiguration) {
                        return sampleConfiguration.getSwaggerBundleConfiguration();
                    }
                };
        bootstrap.addBundle(swaggerBundle);
    }

    @Override
    protected Reflections initializeReflections() {
        return new Reflections("");
    }

    @Override
    protected Injector initializeInjector() {
        Injector injector =
                Guice.createInjector(new SampleModule(getConfiguration(), getEnvironment()));
        return injector;
    }
}

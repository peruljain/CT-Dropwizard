package base;

import com.google.inject.Injector;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Environment;
import lombok.Getter;
import org.reflections.Reflections;

import javax.ws.rs.Path;
import java.util.Set;

public abstract class BaseApplication<T extends Configuration> extends Application<T> {

    @Getter private T configuration;
    @Getter private Environment environment;
    @Getter private Injector injector;
    @Getter private Reflections reflections;

    @Override
    public void run(T configuration, Environment environment) throws Exception {
        this.configuration = configuration;
        this.environment = environment;
        preRun();
        registerEnvironmentServices();
    }

    private void preRun() {
        this.injector = initializeInjector();
        this.reflections = initializeReflections();
    }

    private void registerEnvironmentServices() {
        registerResources();
    }

    private void registerResources() {
        Set<Class<?>> resourceClasses = reflections.getTypesAnnotatedWith(Path.class);
        for (Class<?> resourceClass : resourceClasses) {
            environment.jersey().register(injector.getInstance(resourceClass));
        }
    }

    protected abstract Reflections initializeReflections();

    protected abstract Injector initializeInjector();
}

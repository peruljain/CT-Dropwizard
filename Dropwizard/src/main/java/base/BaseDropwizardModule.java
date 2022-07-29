package base;

import io.dropwizard.Configuration;
import ru.vyarus.dropwizard.guice.module.support.DropwizardAwareModule;

public abstract class BaseDropwizardModule<T extends Configuration>
        extends DropwizardAwareModule<T> {

    @Override
    protected void configure() {
        configureModule();
    }

    protected abstract void configureModule();
}

package bootstrap;

import com.codahale.metrics.health.HealthCheck;

public class SampleHealthCheck extends HealthCheck {

    private final String message;

    public SampleHealthCheck(String message) {
        this.message = message;
    }

    @Override
    protected Result check() throws Exception {
        final String testMessage = String.format(message, "testing");
        if (!testMessage.equals("testing")) {
            return Result.unhealthy("Testing message wrong");
        }
        return Result.healthy();
    }
}

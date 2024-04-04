package acetoys.simulation;

import io.gatling.javaapi.core.OpenInjectionStep;
import io.gatling.javaapi.core.PopulationBuilder;
import static io.gatling.javaapi.core.CoreDsl.*;

import java.time.Duration;



public class TestPopulation {
    private static final int USER_COUNT = (Integer.parseInt(System.getProperty("userCount","5")));
    private static final Duration RAMP_DURATION = Duration.ofSeconds(Integer.parseInt(System.getProperty("rampDuration","60")));


    public static PopulationBuilder instantUsers =
            TestScenario.defaultLoadTest
                    .injectOpen(
                            OpenInjectionStep.nothingFor(Duration.ofSeconds(5)),
                            OpenInjectionStep.atOnceUsers(USER_COUNT)
                    );
    public static PopulationBuilder rampUsers =
            TestScenario.defaultLoadTest
                    .injectOpen(
                            OpenInjectionStep.nothingFor(Duration.ofSeconds(5)),
                            rampUsers(USER_COUNT).during(RAMP_DURATION)
                    );
}

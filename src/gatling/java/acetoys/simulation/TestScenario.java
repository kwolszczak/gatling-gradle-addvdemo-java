package acetoys.simulation;

import acetoys.pageobjects.*;
import acetoys.session.UserSession;
import io.gatling.javaapi.core.*;

import java.time.Duration;

import static io.gatling.javaapi.core.CoreDsl.*;
public class TestScenario {

    private static final Duration TEST_DURATION = Duration.ofSeconds(Integer.parseInt(System.getProperty("duration","60")));

    public static ScenarioBuilder defaultLoadTest =
            scenario("Default Load Test")
                    .during(TEST_DURATION).on(
                            randomSwitch().on(
                                    percent(60).then(UserJourney.browserStore),
                                    percent(30).then(UserJourney.abandonBasket),
                                    percent(10).then(UserJourney.completePurchase)
                            )
                    );

    public static ScenarioBuilder highPurchaseLoadTest =
            scenario("Default Load Test")
                    .during(TEST_DURATION).on(
                            randomSwitch().on(
                                    percent(30).then(UserJourney.browserStore),
                                    percent(30).then(UserJourney.abandonBasket),
                                    percent(40).then(UserJourney.completePurchase)
                            )
                    );
}

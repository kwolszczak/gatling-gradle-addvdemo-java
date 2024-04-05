package acetoys;

import acetoys.pageobjects.*;
import acetoys.session.UserSession;
import acetoys.simulation.TestScenario;
import acetoys.simulation.UserJourney;
import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import java.time.Duration;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

public class RecordedSimulation extends Simulation {

  private final String BASE_URL = "https://acetoys.uk";
    private static final String TEST_TYPE = System.getProperty("testType","instantUser");

    private HttpProtocolBuilder httpProtocol = http
    .baseUrl(BASE_URL)
    .inferHtmlResources(AllowList(), DenyList(".*\\.js", ".*\\.css", ".*\\.gif", ".*\\.jpeg", ".*\\.jpg", ".*\\.ico", ".*\\.woff", ".*\\.woff2", ".*\\.(t|o)tf", ".*\\.png", ".*\\.svg", ".*detectportal\\.firefox\\.com.*"))
   // .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
    .acceptEncodingHeader("gzip, deflate, br")
    .acceptLanguageHeader("en-GB,en-US;q=0.9,en;q=0.8");
  //  .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.0.0 Safari/537.36");




  {
      if (TEST_TYPE.equals("instantUser")) {
          setUp(TestScenario.defaultLoadTest.injectOpen(atOnceUsers(1))).protocols(httpProtocol)
                  .assertions(
                          global().responseTime().mean().lt(200),
                          global().successfulRequests().percent().gt(99.0),
                          forAll().responseTime().max().lt(300)
                  );
      }
      else
      {
          setUp(TestScenario.highPurchaseLoadTest.injectOpen(atOnceUsers(1))).protocols(httpProtocol);
      }

  }
}

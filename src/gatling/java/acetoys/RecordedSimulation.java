package acetoys;

import acetoys.pageobjects.*;
import acetoys.session.UserSession;
import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

public class RecordedSimulation extends Simulation {

  private final String BASE_URL = "https://acetoys.uk";
  private HttpProtocolBuilder httpProtocol = http
    .baseUrl(BASE_URL)
    .inferHtmlResources(AllowList(), DenyList(".*\\.js", ".*\\.css", ".*\\.gif", ".*\\.jpeg", ".*\\.jpg", ".*\\.ico", ".*\\.woff", ".*\\.woff2", ".*\\.(t|o)tf", ".*\\.png", ".*\\.svg", ".*detectportal\\.firefox\\.com.*"))
   // .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
    .acceptEncodingHeader("gzip, deflate, br")
    .acceptLanguageHeader("en-GB,en-US;q=0.9,en;q=0.8");
  //  .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.0.0 Safari/537.36");


  private ScenarioBuilder scn = scenario("RecordedSimulation")
          .exec(UserSession.initSession)
          .exec(StaticPages.homePage)
          .pause(2)
          .exec(Category.productListByCategory)
          .pause(2)
          .exec(Category.loadPage)
          .pause(2)

          .exec(Product.addProduct)
          .pause(2)
          .exec(Category.productListByCategory)
          .pause(2)
          .exec(Product.addProduct)
          .pause(2)
          .exec(Product.addProduct)
          .pause(2)
          .exec(Cart.goToTheCartView)
          .pause(2)
          .exec(Cart.goToTheCartView)
          .pause(2)
          .exec(Cart.increaseProduct19)
          .pause(2)
          .exec(Cart.increaseProduct19)
          .pause(2)
          .exec(Cart.decreaseProduct19)
          .pause(2)
          .exec(Cart.goToTheCheckout)

          .randomSwitch().on(
                  percent(10).then(Customer.logout) // 10% uzyj logout
          );


  {
	  setUp(scn.injectOpen(atOnceUsers(1))).protocols(httpProtocol);
  }
}

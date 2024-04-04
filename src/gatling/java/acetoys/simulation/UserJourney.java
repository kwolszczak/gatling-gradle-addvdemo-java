package acetoys.simulation;

import acetoys.pageobjects.*;
import acetoys.session.UserSession;
import io.gatling.javaapi.core.*;

import java.time.Duration;

import static io.gatling.javaapi.core.CoreDsl.*;

public class UserJourney {

    private static final Duration LOW_PAUSE = Duration.ofMillis(1000);
    private static final Duration HIGH_PAUSE = Duration.ofMillis(3000);


    public static ChainBuilder browserStore =
            exec(UserSession.initSession)
                    .exec(StaticPages.homePage)
                    .pause(LOW_PAUSE,HIGH_PAUSE)
                    .repeat(3).on(
                            exec(Category.productListByCategory)
                                    .pause(LOW_PAUSE,HIGH_PAUSE)
                                    .exec(Category.cyclePagesOfProducts)
                                    .pause(LOW_PAUSE,HIGH_PAUSE)

                    );

    public static ChainBuilder abandonBasket =
            exec(UserSession.initSession)
                    .exec(StaticPages.homePage)
                    .pause(LOW_PAUSE,HIGH_PAUSE)
                    .exec(Product.addProduct);

    public static ChainBuilder completePurchase =
            exec(UserSession.initSession)
                    .exec(StaticPages.homePage)
                    .pause(LOW_PAUSE,HIGH_PAUSE)
                    .exec(Product.addProduct)
                    .pause(LOW_PAUSE,HIGH_PAUSE)
                    .exec(Cart.goToTheCartView)
                    .pause(LOW_PAUSE,HIGH_PAUSE)
                    .exec(Cart.increaseProduct)
                    .pause(LOW_PAUSE)
                    .exec(Cart.decreaseProduct)
                    .pause(LOW_PAUSE,HIGH_PAUSE)
                    .exec(Cart.goToTheCheckout)
                    .pause(LOW_PAUSE,HIGH_PAUSE)
                    .randomSwitch().on(
                            percent(60).then(Customer.logout) // 10% uzyj logout
                    );


}

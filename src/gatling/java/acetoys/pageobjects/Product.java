package acetoys.pageobjects;

import acetoys.session.UserSession;
import io.gatling.javaapi.core.*;


import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;
public class Product {

    private static final FeederBuilder<Object> PRODUCT_FEEDER= jsonFile("data/productDetails.json").circular();

    public static ChainBuilder addProduct =
            feed(PRODUCT_FEEDER)
                    .exec(UserSession.increaseItemsInBasket)
                    .exec(UserSession.increaseBasketTotal)
                    .exec(
                            http("Add #{name} to the cart")
                            .get("/cart/add/#{id}")
                                    .check(substring("You have <span>#{itemsInBasket}</span> products in your Basket."))
                                    .check(substring(  "  Total\n" +
                                            "  <span\n" +
                                            "  >$#{basketTotalPrice}</span>\n" +
                                            "  products in your Basket."))
                    )

            ;

}

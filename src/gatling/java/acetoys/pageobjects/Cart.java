package acetoys.pageobjects;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;
public class Cart {

    public static ChainBuilder goToTheCartView =
            exec(   http("Go to the cart")
                    .get("/cart/view")
                    .check(css("#CategoryHeader").is("Cart Overview"))
            );

    public static ChainBuilder increaseProduct19 =
            exec(       http("Increase product 19")
                            .get("/cart/add/19?cartPage=true")
            );
    public static ChainBuilder decreaseProduct19 =
            exec(             http("Decrease product 19")
                    .get("/cart/subtract/19")
            );

    public static ChainBuilder goToTheCheckout =
            exec(                  http("go to the checkout")
                    .get("/cart/checkout")
            );
}

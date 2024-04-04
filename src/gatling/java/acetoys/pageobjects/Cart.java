package acetoys.pageobjects;

import acetoys.session.UserSession;
import io.gatling.javaapi.core.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;
public class Cart {

    public static ChainBuilder goToTheCartView =
            doIf(session -> !session.getBoolean("customerLoggedIn"))
                    .then(
                            exec(Customer.login)
                    )
            .exec(session -> {
                        System.out.println(session);
                        return session;
                    })
            .exec( http("Go to the cart")
                    .get("/cart/view")
                    .check(css("#CategoryHeader").is("Cart Overview"))
            );

    public static ChainBuilder increaseProduct =
            exec(UserSession.increaseItemsInBasket).
            exec(UserSession.increaseBasketTotal).
            exec(
                    http("Increase product #{name}")
                            .get("/cart/add/#{id}?cartPage=true")
                            .check(css("#grandTotal").isEL("$#{basketTotalPrice}"))
            );
    public static ChainBuilder decreaseProduct =
            exec(UserSession.decreaseItemsInBasket).
            exec(UserSession.decreaseBasketTotal).
            exec(
                    http("Decrease product #{name}")
                            .get("/cart/subtract/#{id}")
                            .check(css("#grandTotal").isEL("$#{basketTotalPrice}"))
            );

    public static ChainBuilder goToTheCheckout =
            exec(
                    http("go to the checkout")
                            .get("/cart/checkout")
            );
}

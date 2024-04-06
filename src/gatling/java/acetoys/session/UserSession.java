package acetoys.session;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import java.text.DecimalFormat;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

public class UserSession {

    private static final DecimalFormat df = new DecimalFormat("0.00");

    public static ChainBuilder initSession =
            exec(flushCookieJar())
                    .exec(session -> session.set("productsListPageNumber", 1))
                    .exec(session -> session.set("customerLoggedIn", false))
                    .exec(session -> session.set("basketTotalPrice", 0.00))
                    .exec(session -> session.set("itemsInBasket", 0));
              /*      .exec(
                            session -> {
                        // !!! Session instances are immutable, meaning that methods such as set return a new instance and leave the original instance unmodified!
                                Session newSession =  session.set("customerLoggedIn", false);
                                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>");
                        //  System.out.println(session.getBoolean("customerLoggedIn"));
                                return newSession;
                    });*/

    public static ChainBuilder increaseItemsInBasket =
            exec(session -> {
                int itemsInBasket = session.getInt("itemsInBasket");
                return session.set("itemsInBasket", itemsInBasket + 1);
            });
    public static ChainBuilder decreaseItemsInBasket =
            exec(session -> {
                int itemsInBasket = session.getInt("itemsInBasket");
                return session.set("itemsInBasket", itemsInBasket - 1);
            });

    public static ChainBuilder increaseBasketTotal =
            exec(session -> {
                double currentTotal = session.getDouble("basketTotalPrice");
                double itemPrice = session.getDouble("price");
                return session.set("basketTotalPrice", df.format(currentTotal + itemPrice));
            });
    public static ChainBuilder decreaseBasketTotal =
            exec(session -> {
                double currentTotal = session.getDouble("basketTotalPrice");
                double itemPrice = session.getDouble("price");
                return session.set("basketTotalPrice", df.format(currentTotal - itemPrice));
            });



}

package acetoys.pageobjects;

import io.gatling.javaapi.core.*;
import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;
public class Product {

    private static final FeederBuilder<Object> PRODUCT_FEEDER= jsonFile("data/productDetails.json").circular();

    public static ChainBuilder addProduct =
            feed(PRODUCT_FEEDER).
            exec(
                    http("Add #{name} to the cart")
                            .get("/cart/add/#{id}")
            );
}

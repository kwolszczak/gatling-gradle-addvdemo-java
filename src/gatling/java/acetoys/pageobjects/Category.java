package acetoys.pageobjects;


import io.gatling.javaapi.core.*;


import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

public class Category {

    private static final FeederBuilder<String> CATEGORY_FEEDER = csv("data/categoryDetails.csv").circular();

    public static ChainBuilder productListByCategory =
            feed(CATEGORY_FEEDER).
            exec(
                    http("Go to #{categoryName}")
                    .get("/category/#{categorySlug}")
                     //       .header(header_0)
                    .check(css("#CategoryName").isEL("#{categoryName}"))
            );

    public static ChainBuilder loadPage1OfProducts =
            exec(
                    http("Load page 1")
                            .get("/category/all?page=1")
                            .check(css(".page-item.active").is("2"))
            );

    public static ChainBuilder loadPage2OfProducts =
            exec(
                    http("Load page 2")
                            .get("/category/all?page=2")
                            .check(css(".page-item.active").is("3"))
            );




    /*  private Map<CharSequence, String> headers_0 = Map.ofEntries(
    Map.entry("Sec-Fetch-Dest", "document"),
    Map.entry("Sec-Fetch-Mode", "navigate"),
    Map.entry("Sec-Fetch-Site", "same-origin"),
    Map.entry("Sec-Fetch-User", "?1"),
    Map.entry("Upgrade-Insecure-Requests", "1"),
    Map.entry("sec-ch-ua", "Chromium\";v=\"122\", \"Not(A:Brand\";v=\"24\", \"Google Chrome\";v=\"122"),
    Map.entry("sec-ch-ua-mobile", "?0"),
    Map.entry("sec-ch-ua-platform", "macOS")
  );*/

}

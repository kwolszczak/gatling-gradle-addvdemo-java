package acetoys.pageobjects;


import io.gatling.javaapi.core.*;


import java.util.Map;

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

    public static ChainBuilder loadPage =
            exec(
                    session -> {
                        int currentPageNumber = session.getInt("productsListPageNumber");
                        int totalPages = session.getInt("categoryPages");
                        int nextPageNumber = currentPageNumber +1;
                        boolean morePages = currentPageNumber < totalPages;
                        System.out.println(">>>> Page: "+currentPageNumber+"/"+totalPages+" >>>> Is there a next Page?: "+morePages);
                        return session.setAll(Map.of(
                                "currentPage",currentPageNumber,
                                "nextPage",nextPageNumber,
                                "morePages",morePages)
                        );
                    }
            ).asLongAs("#{morePages}").on(
                    exec(
                            http("Load page #{currentPage} of Product: #{categoryName}")
                                    .get("/category/#{categorySlug}?page=#{currentPage}")
                                    .check(css(".page-item.active").isEL("#{nextPage}"))
                    )
                            .exec(
                                    session -> {
                                        int currentPageNumber = session.getInt("currentPage");
                                        int totalPages = session.getInt("categoryPages");
                                        currentPageNumber++;
                                        boolean morePages = currentPageNumber < totalPages;
                                        return session.setAll(Map.of(
                                                "currentPage",currentPageNumber,
                                                "nextPage",currentPageNumber+1,
                                                "morePages",morePages)
                                        );
                                    }

                            )
            )

           ;




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

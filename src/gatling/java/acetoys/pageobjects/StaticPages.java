package acetoys.pageobjects;

import io.gatling.javaapi.core.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

public class StaticPages {

    public static ChainBuilder homePage =
            exec(
                    http("Load Home Page")
                            .get("/")
                            .check(css("#_csrf","content").saveAs("token"))
                            .check(status().is(200))
                            .check(status().not(404),status().not(500))
                            .check(substring("Ace Toys"))
                            .check(regex(".nline \\D"))
            );
}

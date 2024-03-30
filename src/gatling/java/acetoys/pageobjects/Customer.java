package acetoys.pageobjects;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;


import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;


public class Customer {


    public static ChainBuilder login =
            exec(http("Login")
                    .post("/login")
                    .formParam("_csrf", "#{token}")
                    .formParam("username", "user1")
                    .formParam("password", "pass")
                    .check(css("#_csrf", "content").saveAs("token"))
            )
                    .exec(session -> session.set("customerLoggedIn",true));


    public static ChainBuilder logout =
            exec(http("Logout")
                    .post("/logout")
                    .formParam("_csrf", "#{token}")
                    .check(css("#LoginLink").is("Login")));
}
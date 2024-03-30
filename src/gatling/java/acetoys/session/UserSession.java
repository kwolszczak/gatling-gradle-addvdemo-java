package acetoys.session;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

public class UserSession
{

    public static ChainBuilder initSession =
            exec(flushCookieJar())
                    .exec(session -> session.set("productsListPageNumber",1))
                    .exec(session -> session.set("customerLoggedIn",false));
              /*      .exec(
                            session -> {
                        // !!! Session instances are immutable, meaning that methods such as set return a new instance and leave the original instance unmodified!
                                Session newSession =  session.set("customerLoggedIn", false);
                                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>");
                        //  System.out.println(session.getBoolean("customerLoggedIn"));
                                return newSession;
                    });*/

}

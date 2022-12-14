import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;

public class GetAndPostExample {

    @Test
    public void testGet() {
        RestAssured.baseURI = "https://reqres.in/api";

        when().
                get("/users?page=2").
                then().
                statusCode(200).
                body("data.size()", is(6)).
                body("data.first_name", hasItems("George", "Rachel"))
                        .log().all();
    };

    @Test
    public void testPut() {

        JSONObject request = new JSONObject();

        request.put("name", "Ernesto Perez");
        request.put("job", "QA Automation");

        RestAssured.baseURI =  "https://reqres.in/api";

        given().
                header("Content-Type", "application/json").
                contentType(ContentType.JSON).
                body(request.toJSONString()).
                when().
                put("/users/2").
                then().
                statusCode(200).
                log().all();

    }
}


import static io.restassured.RestAssured.get;
import static org.hamcrest.CoreMatchers.equalTo;

import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;

public class Testing {
    @BeforeClass
    public static void init() {
        RestAssured.baseURI = "http://localhost/1";
        RestAssured.port = 8080;
    }

    @Test
    public void testUserFetchesSuccess() {
        get("/api/jxrs")
                .then()
                .body("first_name", equalTo("Janssen!"));
    }
}
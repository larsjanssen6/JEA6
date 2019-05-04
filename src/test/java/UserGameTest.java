import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class UserGameTest {
    @BeforeClass
    public static void setup()
    {
        RestAssured.baseURI = "http://localhost/1/api";
        RestAssured.port = 8080;
        String token = UserSetup.getToken();
        RestAssured.requestSpecification = new RequestSpecBuilder().addHeader("Authorization", "Bearer "+token).build();
    }

    @Test
    public void getAll() {
        given()
                .contentType("application/json")
                .when().get("/game/" + UserSetup.userId).then()
                .statusCode(200);
    }
}
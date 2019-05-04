import Authentication.UserDTO;
import io.restassured.RestAssured;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class AuthenticationTest {
    private static String email;

    @BeforeClass
    public static void setup()
    {
        RestAssured.baseURI = "http://localhost/1/api";
        RestAssured.port = 8080;
    }

    @Test
    public void registerUser() {
        email = RandomStringUtils.randomAlphabetic(10) + "@gmail.com";

        UserDTO user = new UserDTO();
        user.setFirstName("Lars");
        user.setLastName("Janssen");
        user.setPassword("test");
        user.setEmail(email);
        given()
                .contentType("application/json")
                .body(user)
                .when().post("/jwt/register").then()
                .statusCode(200);
    }

    @Test
    public void loginUserSuccesful() {
        UserDTO user = new UserDTO();
        user.setFirstName("Lars");
        user.setLastName("Janssen");
        user.setPassword("test");
        user.setEmail(email);
        given()
                .contentType("application/json")
                .body(user)
                .when().post("/jwt/login").then()
                .statusCode(200);
    }

    @Test
    public void loginUserUnSuccesful() {
        UserDTO user = new UserDTO();
        user.setFirstName("Lars");
        user.setLastName("Janssen");
        user.setPassword("wrong-password!");
        user.setEmail(email);
        given()
                .contentType("application/json")
                .body(user)
                .when().post("/jwt/login").then()
                .statusCode(401);
    }
}
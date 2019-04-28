import Authentication.UserDTO;
import domain.User;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Testing {
    @BeforeClass
    public static void setup()
    {
        RestAssured.baseURI = "http://localhost/1/api";
        RestAssured.port = 8080;
        String token = UserSetup.getToken();
        System.out.println(token);
        RestAssured.requestSpecification = new RequestSpecBuilder().addHeader("Authorization", "Bearer "+token).build();
    }

    @Test
    public void createUser() {
        UserDTO user = new UserDTO();
        user.setFirstName("Lars");
        user.setLastName("Janssen");
        user.setPassword("test");
        user.setEmail("larsjanssen64@gmail.com");
        given()
                .contentType("application/json")
                .body(user)
                .when().post("/jwt/register").then()
                .statusCode(200).extract().response().as(User.class);
    }
}
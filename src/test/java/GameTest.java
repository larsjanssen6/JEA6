import domain.Game.GameDTO;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GameTest {
    @BeforeClass
    public static void setup()
    {
        RestAssured.baseURI = "http://localhost/1/api";
        RestAssured.port = 8080;
        String token = UserSetup.getToken();
        RestAssured.requestSpecification = new RequestSpecBuilder().addHeader("Authorization", "Bearer "+token).build();
    }

    @Test
    public void newGame() {
        GameDTO gameDTO = new GameDTO();
        gameDTO.setName("TestGame");
        gameDTO.setDescription("TestDescription");

        given()
                .contentType("application/json")
                .body(gameDTO)
                .when().post("/game/" + UserSetup.userId).then()
                .statusCode(200);
    }

    @Test
    public void deleteGame() {
        GameDTO gameDTO = new GameDTO();
        gameDTO.setId(1111);
        gameDTO.setName("TestGame");
        gameDTO.setDescription("TestDescription");

        given()
                .contentType("application/json")
                .body(gameDTO)
                .when().post("/game/" + UserSetup.userId).then()
                .statusCode(200);

        given()
                .contentType("application/json")
                .body(gameDTO)
                .when().post("/game/" + 1111).then()
                .statusCode(200);
    }
}
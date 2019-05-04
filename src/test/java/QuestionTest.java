import domain.Game.Game;
import domain.Game.GameDTO;
import domain.Question.QuestionDTO;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Date;

import static io.restassured.RestAssured.given;

public class QuestionTest {
    @BeforeClass
    public static void setup()
    {
        RestAssured.baseURI = "http://localhost/1/api";
        RestAssured.port = 8080;
        String token = UserSetup.getToken();
        RestAssured.requestSpecification = new RequestSpecBuilder().addHeader("Authorization", "Bearer "+token).build();
    }

    @Test
    public void getQuestions() {
        given()
                .contentType("application/json")
                .when().get("/question/1111").then()
                .statusCode(200);
    }
}
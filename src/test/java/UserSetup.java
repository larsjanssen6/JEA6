import Authentication.UserDTO;
import domain.UserLogin;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;


public class UserSetup {
    private static String userEmail = "larsjanssen64@gmail.com";
    private static String userPassword = "larstest";
    private static String token;

    public UserSetup(String specialUserEmail,String specialUserPassword) {
        userEmail = specialUserEmail;
        userPassword = specialUserPassword;
    }

    public static String getToken(){
        if (!login()){
            registerCustomer();
            login();
        }

        return token;
    }
    public static String getOwnerToken(){
        return token;
    }
    private static void registerCustomer() {
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName("Lars");
        userDTO.setLastName("Janssen");
        userDTO.setEmail("larsjanssen64@gmail.com");

        given().contentType("application/json")
                .body(userDTO)
                .when().post("/jwt/register")
                .then().statusCode(200);

    }

    private static boolean login() {
        UserLogin userLogin = new UserLogin();
        userLogin.setEmail(userEmail);
        userLogin.setPassword(userPassword);
        Response r = given()
                .contentType("application/json")
                .body(userLogin)
                .when().post("/jwt/login").then()
                .extract().response();
        System.out.println("Login statuscode: " + r.getStatusCode());
        if (r.getStatusCode() == 200) {
            token = r.getBody().jsonPath().getString("data");
            System.out.println("Customer jwttoken: " +token);
            return true;
        }
        return false;
    }
}
import Authentication.UserDTO;
import domain.User.UserLogin;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class UserSetup {

    public static String userEmail = "lars@gmail.com";
    public static String password = "RmUxae9qZwXhhBhVdTkBZEY8KQMsCga49FhhmKxrWX7QeRdEcd";
    public static String token;
    public static String userId;

    public UserSetup(String specialUserEmail,String specialUserPassword) {
        userEmail = specialUserEmail;
        password = specialUserPassword;
    }

    static String getToken(){
        if (!login()){
            registerCustomer();
            login();
        }
        return token;
    }

    public static void registerCustomer() {
        UserDTO user = new UserDTO();
        user.setFirstName("Lars");
        user.setLastName("Janssen");
        user.setPassword(password);
        user.setEmail(userEmail);
        given()
                .contentType("application/json")
                .body(user)
                .when().post("/jwt/register").then()
                .statusCode(200);

    }

    private static boolean login() {
        UserLogin userLogin = new UserLogin();
        userLogin.setEmail(userEmail);
        userLogin.setPassword(password);
        Response r = given()
                .contentType("application/json")
                .body(userLogin)
                .when().post("/jwt/login").then()
                .extract().response();

        if (r.getStatusCode() == 200) {
            token = r.getBody().jsonPath().getString("token");
            userId = r.getBody().jsonPath().getString("userId");
            System.out.println("Customer jwttoken: " +token);
            return true;
        }
        return false;
    }
}
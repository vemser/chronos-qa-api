package data.factory;

import io.restassured.http.ContentType;
import model.LoginRequestDTO;
import org.apache.http.HttpStatus;
import specs.AuthSpec;
import specs.NoAuthSpec;
import utils.props.GetProperties;

import static io.restassured.RestAssured.given;

public class TokenFactory {

    private static final String BEARER = "Bearer ";

    public static String getToken() {
        return given()
                .spec(NoAuthSpec.setup())
                .body(GetProperties.LOGIN_DATA())
                .when()
                .post(GetProperties.LOGIN_URI())
                .then()
                    .statusCode(HttpStatus.SC_OK)
                    .extract().response().asString();
    }

    public static String getToken(LoginRequestDTO loginRequestDTO) {
        return BEARER + given()
                .spec(NoAuthSpec.setup())
                .body(loginRequestDTO)
                .when()
                .post(GetProperties.LOGIN_URI())
                .then()
                //.contentType(ContentType.JSON)
                .statusCode(HttpStatus.SC_OK)
                .extract().body().asString();
    }

    public static String getTokenAdmin() {
        return getToken(GetProperties.LOGIN_DATA_ADMIN());
    }

    public static String getTokenGestor() {
        return getToken(GetProperties.LOGIN_DATA_GESTOR());
    }

    public static String getTokenInstrutor() {
        return getToken(GetProperties.LOGIN_DATA_INSTRUTOR());
    }

    public static String getTokenGp() {
        return getToken(GetProperties.LOGIN_DATA_GP());
    }
}

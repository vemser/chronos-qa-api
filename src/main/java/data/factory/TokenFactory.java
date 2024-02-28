package data.factory;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import specs.NoAuthSpec;
import utils.props.GetProperties;

import static io.restassured.RestAssured.given;

public class TokenFactory {

    public static String getToken() {
        return given()
                    .spec(NoAuthSpec.setup())
                    .body(GetProperties.LOGIN_DATA())
                .when()
                    .post(GetProperties.LOGIN_URI())
                .then()
                    .contentType(ContentType.TEXT)
                    .statusCode(HttpStatus.SC_OK)
                    .extract().response().asString();
    }
}

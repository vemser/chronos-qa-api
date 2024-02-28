package specs;

import data.factory.TokenFactory;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import utils.props.GetProperties;

public class FotoSpec {

    public static RequestSpecification setup() {
        return new RequestSpecBuilder()
                .setBaseUri(GetProperties.BASE_URI())
                .addHeader("Authorization", TokenFactory.getToken())
                .build();
    }

    public static RequestSpecification setupWithoutToken() {
        return new RequestSpecBuilder()
                .setBaseUri(GetProperties.BASE_URI())
                .build();
    }


}

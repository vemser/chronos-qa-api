package specs;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import utils.props.GetProperties;

public class InicialSpecs {

    public static RequestSpecification setup() {
        return new RequestSpecBuilder()
                .setBaseUri(GetProperties.BASE_URI())
                .setContentType(ContentType.JSON)
                .build();
    }
}
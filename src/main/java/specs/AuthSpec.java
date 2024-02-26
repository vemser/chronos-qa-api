package specs;

import data.factory.TokenFactory;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

/**
 * SPEC para requisições que demandam autenticação
 */
public class AuthSpec {
    public static RequestSpecification setup() {
        return new RequestSpecBuilder()
                .addRequestSpecification(InicialSpecs.setup())
                .addHeader("Authorization", TokenFactory.getToken())
                .build();
    }
}

package specs;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

/**
 * SPEC para Requisições que não precisam de Autenticação
 */
public class NoAuthSpec {

    public static RequestSpecification setup() {
        return new RequestSpecBuilder()
                .addRequestSpecification(InicialSpecs.setup())
                .build();
    }

}

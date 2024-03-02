package client;


import io.restassured.response.Response;
import model.diaNaoUtil.DiaNaoUtilRequestDTO;
import specs.AuthSpec;
import specs.InicialSpecs;

import static io.restassured.RestAssured.given;

public class CalendarioClient {
    private static final String PATH_CALENDARIO_GERAL = "/calendario/geral";
    private static final String PATH_CALENDARIO_GERAL_MES = "/calendario/geral";

    private static final String PATH_CALENDARIO_GERAL_EDICAO = "/calendario/edicao" + "/${idEdicao}";
    private static final String PATH_CALENDARIO_GERAL_EXCEL = "/calendario/export/excel" + "/${idEdicao}";


    public Response buscarEGerarCalendarioGeral() {
        return
                given()
                        .spec(AuthSpec.setup())
                        .when()
                        .get(PATH_CALENDARIO_GERAL);
    }

    public Response buscarEGerarCalendarioGeralSemAuth() {
        return
                given()
                        .spec(InicialSpecs.setup())
                        .when()
                        .get(PATH_CALENDARIO_GERAL);
    }

    public Response buscarEGerarCalendarioGeralPorMes(Integer mes) {
        return
                given()
                        .spec(AuthSpec.setup())
                        .log().all()
                        .queryParam("mes", mes)
                        .when()
                        .get(PATH_CALENDARIO_GERAL_MES);
    }

    public Response buscarEGerarCalendarioGeralPorMesSemAuth(Integer mes) {
        return
                given()
                        .spec(InicialSpecs.setup())
                        .queryParam("mes", mes)
                        .when()
                        .get(PATH_CALENDARIO_GERAL_MES);
    }
    public Response buscarEGerarCalendarioGeralExcel(Integer integer) {
        return
                given()
                        .spec(AuthSpec.setup())
                        .pathParam("idEdicao", integer)
                        .when()
                        .get(PATH_CALENDARIO_GERAL_EXCEL);
    }

    public Response buscarEGerarCalendarioGeralExcelSemAuth(Integer integer) {
        return
                given()
                        .spec(InicialSpecs.setup())
                        .pathParam("idEdicao", integer)
                        .when()
                        .get(PATH_CALENDARIO_GERAL_EXCEL);
    }

    public Response buscarEGerarCalendarioGeralDaEdicao(Integer integer) {
        return
                given()
                        .spec(AuthSpec.setup())
                        .pathParam("idEdicao", integer)
                        .when()
                        .get(PATH_CALENDARIO_GERAL_EDICAO);
    }

    public Response buscarEGerarCalendarioGeralDaEdicaoSemAuth(Integer integer) {
        return
                given()
                        .spec(InicialSpecs.setup())
                        .pathParam("idEdicao", integer)
                        .when()
                        .get(PATH_CALENDARIO_GERAL_EDICAO);
    }
}

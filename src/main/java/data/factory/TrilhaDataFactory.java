package data.factory;

import model.TrilhaRequestDTO;
import net.datafaker.Faker;
import org.apache.commons.lang3.StringUtils;

import java.util.Locale;

import static org.hamcrest.Matchers.hasLength;

public class TrilhaDataFactory {


    public static TrilhaRequestDTO trilhaComTodosOsCampos(){
        TrilhaRequestDTO trilhaReq = new TrilhaRequestDTO();
        trilhaReq.setNome(Factory.FAKER.name().firstName());
        trilhaReq.setDescricao(Factory.FAKER.lorem().sentence());
        trilhaReq.setStatus("INATIVO");

        return trilhaReq;


    }
    public static TrilhaRequestDTO trilhaSemCampoObrigatorioPreenchido() {
        TrilhaRequestDTO trilhaReq = new TrilhaRequestDTO();
        trilhaReq.setNome(StringUtils.EMPTY);
        trilhaReq.setDescricao(Factory.FAKER.lorem().sentence());
        trilhaReq.setStatus(StringUtils.EMPTY);

        return trilhaReq;

    }
    public static  TrilhaRequestDTO trilhaComCamposObrigatoriosPreenchidos(){
        TrilhaRequestDTO trilhaReq = new TrilhaRequestDTO();
        trilhaReq.setNome(Factory.FAKER.name().firstName());
        trilhaReq.setStatus(Factory.FAKER.lorem().sentence());

        return trilhaReq;


    }

    public static  TrilhaRequestDTO trilhaComCampoNomeCom1Caracter(){
        TrilhaRequestDTO trilhaReq = new TrilhaRequestDTO();
        trilhaReq.setNome("a");
        trilhaReq.setDescricao(Factory.FAKER.lorem().sentence());
        trilhaReq.setStatus("ATIVO");

        return trilhaReq;
    }

    public static  TrilhaRequestDTO trilhaComCampoNomeCom51Caracteres(){
        TrilhaRequestDTO trilhaReq = new TrilhaRequestDTO();
        trilhaReq.setNome(Factory.FAKER.number().digits(51));
        trilhaReq.setDescricao(Factory.FAKER.lorem().sentence());
        trilhaReq.setStatus("ATIVO");

        return trilhaReq;
    }

    public static  TrilhaRequestDTO trilhaComCampoDescricaoCom256Caracteres(){
        TrilhaRequestDTO trilhaReq = new TrilhaRequestDTO();
        trilhaReq.setNome(Factory.FAKER.name().firstName());
        trilhaReq.setDescricao(hasLength(256).toString());
        trilhaReq.setStatus("ATIVO");

        return trilhaReq;
    }

}








package data.factory;

import model.TrilhaRequestDTO;
import net.datafaker.Faker;
import org.apache.commons.lang3.StringUtils;

import java.util.Locale;

public class TrilhaDataFactory {


    public static TrilhaRequestDTO trilhaComTodosOsCampos(){
        TrilhaRequestDTO trilhaReq = new TrilhaRequestDTO();
        trilhaReq.setNome(Factory.FAKER.name().firstName());
        trilhaReq.setDescricao(Factory.FAKER.lorem().sentence());
        trilhaReq.setStatus(0);

        return trilhaReq;


    }
    public static TrilhaRequestDTO trilhaSemCampoObrigatorioPreenchido() {
        TrilhaRequestDTO trilhaReq = new TrilhaRequestDTO();
        trilhaReq.setNome(StringUtils.EMPTY);
        trilhaReq.setDescricao(Factory.FAKER.lorem().sentence());
        trilhaReq.setStatus(null);

        return trilhaReq;

    }}





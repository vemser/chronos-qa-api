package data.factory;

import model.trilha.TrilhaRequestDTO;
import org.apache.commons.lang3.StringUtils;

public class TrilhaDataFactory {


    public static TrilhaRequestDTO trilhaComTodosOsCampos(){
        TrilhaRequestDTO trilhaReq = new TrilhaRequestDTO();
        trilhaReq.setNome(Factory.FAKER.animal().name() + Factory.FAKER.name().fullName());
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
        trilhaReq.setDescricao(Factory.FAKER.lorem().sentence());
        trilhaReq.setStatus("ATIVO");

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
        trilhaReq.setDescricao(Factory.FAKER.number().digits(256));
        trilhaReq.setStatus("ATIVO");

        return trilhaReq;
    }
   public static Integer idInvalido(){
        return Factory.FAKER.number().numberBetween(999,9999);
   }
    public static  TrilhaRequestDTO trilhaComCampoDescricaoCom255(){
        TrilhaRequestDTO trilhaReq = new TrilhaRequestDTO();
        trilhaReq.setNome(Factory.FAKER.name().firstName());
        trilhaReq.setDescricao(Factory.FAKER.number().digits(255));
        trilhaReq.setStatus("ATIVO");

        return trilhaReq;
    }

    public static  TrilhaRequestDTO trilhaComCampoDescricaoVazio(){
        TrilhaRequestDTO trilhaReq = new TrilhaRequestDTO();
        trilhaReq.setNome(Factory.FAKER.name().firstName());
        trilhaReq.setDescricao("");
        trilhaReq.setStatus("ATIVO");

        return trilhaReq;
    }


}








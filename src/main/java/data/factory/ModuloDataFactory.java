package data.factory;

import model.ModuloRequestDTO;
import org.apache.commons.lang3.StringUtils;

public class ModuloDataFactory {
    public static ModuloRequestDTO moduloComTodosOsCampos(){
        ModuloRequestDTO moduloReq = new ModuloRequestDTO();
        moduloReq.setNome("Modulo " + Factory.FAKER.number().digits(3));
        moduloReq.setLoginResponsavel("admin.teste");
        moduloReq.setConteudoProgramatico(Factory.FAKER.lorem().sentence(11));
        moduloReq.setStatus("ATIVO");

        return moduloReq;
    }

    public static ModuloRequestDTO moduloSemCamposObrigatoriosPreenchidos() {
        ModuloRequestDTO moduloReq = new ModuloRequestDTO();
        moduloReq.setLoginResponsavel("admin.teste");
        moduloReq.setConteudoProgramatico(Factory.FAKER.lorem().sentence(11));

        return moduloReq;
    }

    public static ModuloRequestDTO moduloSemCamposNaoObrigatoriosPreenchidos() {
        ModuloRequestDTO moduloReq = new ModuloRequestDTO();
        moduloReq.setNome("Modulo " + Factory.FAKER.number().digits(3));
        moduloReq.setLoginResponsavel("");
        moduloReq.setConteudoProgramatico("");
        moduloReq.setStatus("ATIVO");

        return moduloReq;
    }

    public static ModuloRequestDTO moduloComNomeComCaracterAMenos() {
        ModuloRequestDTO moduloReq = new ModuloRequestDTO();
        moduloReq.setNome("A");
        moduloReq.setLoginResponsavel("admin.teste");
        moduloReq.setConteudoProgramatico(Factory.FAKER.lorem().sentence());
        moduloReq.setStatus("ATIVO");

        return moduloReq;
    }
    public static ModuloRequestDTO moduloComNomeComCaracterAMais() {
        ModuloRequestDTO moduloReq = new ModuloRequestDTO();
        moduloReq.setNome(Factory.FAKER.lorem().sentence(51));
        moduloReq.setLoginResponsavel("admin.teste");
        moduloReq.setConteudoProgramatico(Factory.FAKER.lorem().sentence(11));
        moduloReq.setStatus("ATIVO");

        return moduloReq;
    }

    public static ModuloRequestDTO moduloComConteudoProgramaticoComCaracterAMenos() {
        ModuloRequestDTO moduloReq = new ModuloRequestDTO();
        moduloReq.setNome(Factory.FAKER.lorem().sentence(10));
        moduloReq.setLoginResponsavel("admin.teste");
        moduloReq.setConteudoProgramatico(Factory.FAKER.lorem().sentence(10));
        moduloReq.setStatus("ATIVO");

        return moduloReq;
    }

    public static ModuloRequestDTO moduloComConteudoProgramaticoComCaracterAMais() {
        ModuloRequestDTO moduloReq = new ModuloRequestDTO();
        moduloReq.setNome(Factory.FAKER.lorem().sentence(10));
        moduloReq.setLoginResponsavel("admin.teste");
        moduloReq.setConteudoProgramatico(Factory.FAKER.lorem().sentence(1));
        moduloReq.setStatus("ATIVO");

        return moduloReq;
    }
}



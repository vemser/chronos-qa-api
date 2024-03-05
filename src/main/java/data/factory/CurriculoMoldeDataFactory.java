package data.factory;


import model.curriculoMolde.CurriculoMoldeRequestDTO;

import java.io.File;

public class CurriculoMoldeDataFactory {
    public static File gerarCurriculoDOCX() {
        return new File("C:\\Users\\rapha\\projeto-final-api\\chronos-qa-api\\src\\main\\resources\\documents\\CV_padrao_DBC_-_Vem_Ser_QA.docx");
    }

    public static CurriculoMoldeRequestDTO gerarCurriculoMoldeComTodosOsCampos() {
        CurriculoMoldeRequestDTO curriculoReq = new CurriculoMoldeRequestDTO();
        curriculoReq.setQualificacoes("Sou de Engenharia da Computação, estou em busca de uma oportunidade...");
        curriculoReq.setEmpresa("DBC Company");
        curriculoReq.setDescricao("O Programa Vem Ser DBC é uma formação completa com duração 3 meses...");
        curriculoReq.setCargo("Estagiário Vem Ser");
        curriculoReq.setConhecimentos("Java, Spring, React");
        return curriculoReq;
    }

    public static CurriculoMoldeRequestDTO gerarCurriculoMoldeVazio() {
        CurriculoMoldeRequestDTO curriculoReq = new CurriculoMoldeRequestDTO();
        return curriculoReq;
    }

    public static CurriculoMoldeRequestDTO gerarCurriculoMoldeComTodosOsCamposECampoQualificacoesComCaracteresAMais() {
        CurriculoMoldeRequestDTO curriculoReq = new CurriculoMoldeRequestDTO();
        curriculoReq.setQualificacoes(Factory.FAKER.lorem().sentence(601));
        curriculoReq.setEmpresa(Factory.FAKER.lorem().sentence());
        curriculoReq.setDescricao(Factory.FAKER.lorem().sentence());
        curriculoReq.setCargo(Factory.FAKER.lorem().sentence());
        curriculoReq.setConhecimentos(Factory.FAKER.lorem().sentence());
        return curriculoReq;
    }

    public static CurriculoMoldeRequestDTO gerarCurriculoMoldeComTodosOsCamposECampoEmpresaComCaracteresAMais() {
        CurriculoMoldeRequestDTO curriculoReq = new CurriculoMoldeRequestDTO();
        curriculoReq.setQualificacoes(Factory.FAKER.lorem().sentence());
        curriculoReq.setEmpresa(Factory.FAKER.lorem().sentence(51));
        curriculoReq.setDescricao(Factory.FAKER.lorem().sentence());
        curriculoReq.setCargo(Factory.FAKER.lorem().sentence());
        curriculoReq.setConhecimentos(Factory.FAKER.lorem().sentence());
        return curriculoReq;
    }

    public static CurriculoMoldeRequestDTO gerarCurriculoMoldeComTodosOsCamposECampoDescricaoComCaracteresAMais() {
        CurriculoMoldeRequestDTO curriculoReq = new CurriculoMoldeRequestDTO();
        curriculoReq.setQualificacoes(Factory.FAKER.lorem().sentence());
        curriculoReq.setEmpresa(Factory.FAKER.lorem().sentence());
        curriculoReq.setDescricao(Factory.FAKER.lorem().sentence(601));
        curriculoReq.setCargo(Factory.FAKER.lorem().sentence());
        curriculoReq.setConhecimentos(Factory.FAKER.lorem().sentence());
        return curriculoReq;
    }

    public static CurriculoMoldeRequestDTO gerarCurriculoMoldeComTodosOsCamposECampoCargoComCaracteresAMais() {
        CurriculoMoldeRequestDTO curriculoReq = new CurriculoMoldeRequestDTO();
        curriculoReq.setQualificacoes(Factory.FAKER.lorem().sentence());
        curriculoReq.setEmpresa(Factory.FAKER.lorem().sentence());
        curriculoReq.setDescricao(Factory.FAKER.lorem().sentence());
        curriculoReq.setCargo(Factory.FAKER.lorem().sentence(51));
        curriculoReq.setConhecimentos(Factory.FAKER.lorem().sentence());
        return curriculoReq;
    }

    public static CurriculoMoldeRequestDTO gerarCurriculoMoldeComTodosOsCamposECampoConhecimentoComCaracteresAMais() {
        CurriculoMoldeRequestDTO curriculoReq = new CurriculoMoldeRequestDTO();
        curriculoReq.setQualificacoes(Factory.FAKER.lorem().sentence());
        curriculoReq.setEmpresa(Factory.FAKER.lorem().sentence());
        curriculoReq.setDescricao(Factory.FAKER.lorem().sentence());
        curriculoReq.setCargo(Factory.FAKER.lorem().sentence());
        curriculoReq.setConhecimentos(Factory.FAKER.lorem().sentence(501));
        return curriculoReq;
    }
}

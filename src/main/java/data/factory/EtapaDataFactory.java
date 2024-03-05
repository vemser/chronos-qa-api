package data.factory;

import model.etapa.EtapaRequestDTO;

import static data.factory.Factory.RANDOM;


public class EtapaDataFactory {
    public static EtapaRequestDTO etapaComTodosOsCampos(){
        EtapaRequestDTO etapaReq = new EtapaRequestDTO();
        etapaReq.setNome(Factory.FAKER.name().firstName());
        etapaReq.setDuracaoDiaUtil(RANDOM.nextInt(100));
        etapaReq.setOrdemExecucao(RANDOM.nextInt(5) + 5);
        return etapaReq;
    }

    public static EtapaRequestDTO etapaSemCamposObrigatoriosPreenchidos(){
        EtapaRequestDTO etapaReq = new EtapaRequestDTO();
        return etapaReq;
    }

    public static EtapaRequestDTO etapaComCampoNomeComCaracterAMais(){
        EtapaRequestDTO etapaReq = new EtapaRequestDTO();
        etapaReq.setNome(Factory.FAKER.lorem().sentence(52));
        etapaReq.setDuracaoDiaUtil(RANDOM.nextInt(100));
        etapaReq.setOrdemExecucao(RANDOM.nextInt(3) + 1);
        return etapaReq;
    }

    public static EtapaRequestDTO etapaComCampoDuracaoDiaUtilNegativo(){
        EtapaRequestDTO etapaReq = new EtapaRequestDTO();
        etapaReq.setNome(Factory.FAKER.lorem().sentence(52));
        etapaReq.setDuracaoDiaUtil(-1);
        etapaReq.setOrdemExecucao(RANDOM.nextInt(3) + 1);
        return etapaReq;
    }

}

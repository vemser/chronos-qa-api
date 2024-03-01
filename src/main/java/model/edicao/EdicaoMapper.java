package model.edicao;

public class EdicaoMapper {

    public static EdicaoRequestDTO converterParaRequestBody(EdicaoResponseDTO responseDTO) {
        return EdicaoRequestDTO.builder()
                .nome(responseDTO.getNome())
                .dataInicial(responseDTO.getDataInicial())
                .dataFinal(responseDTO.getDataFinal())
                .descricao(responseDTO.getDescricao())
                .status(responseDTO.getStatus())
                .build();
    }
}

package model.edicao;

public class EdicaoMapper {

    public static EdicaoRequestDTO converterParaRequestBody(EdicaoResponseDTO responseDTO) {
        return EdicaoRequestDTO.builder()
                .nome(responseDTO.getNome())
                .dataInicial(responseDTO.getDataInicial())
                .descricao(responseDTO.getDescricao())
                .status(responseDTO.getStatus())
                .build();
    }
}

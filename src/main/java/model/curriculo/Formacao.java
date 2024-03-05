package model.curriculo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Formacao {
    private Integer idFormacao;
    private String escolaridade;
    private String instituicao;
    private String curso;
    private String status;
    private String dataInicio;
    private String dataConclusao;
}

package model.curriculo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Experiencia {
    private Integer idExperiencia;
    private String empresa;
    private String descricao;
    private String cargo;
    private String dataInicio;
    private String dataFim;

}

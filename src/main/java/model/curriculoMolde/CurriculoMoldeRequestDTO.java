package model.curriculoMolde;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CurriculoMoldeRequestDTO {
    private String qualificacoes;
    private String empresa;
    private String descricao;
    private String cargo;
    private String conhecimentos;
}

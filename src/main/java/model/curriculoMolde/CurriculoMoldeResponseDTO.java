package model.curriculoMolde;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.trilha.TrilhaResponseDTO;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CurriculoMoldeResponseDTO {
    private Integer idCurriculoMolde;
    private TrilhaResponseDTO trilha;
    private String arquivo;
    private String qualificacoes;
    private String empresa;
    private String descricao;
    private String cargo;
    private List<String> conhecimentosList;
}

package model.curriculo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CurriculoResponseDTO {
    private Integer idCurriculo;
    private Integer idEstagiario;
    private String arquivo;
    private String qualificacoes;
    private List<Experiencia> experiencias;
    private List<Idioma> idiomas;
    private List<Conhecimento> conhecimentos;
    private List<Formacao> formacoes;
}

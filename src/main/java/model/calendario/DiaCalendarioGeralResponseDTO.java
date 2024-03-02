package model.calendario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DiaCalendarioGeralResponseDTO {
    private String dia;
    private Integer idEdicao;
    private String edicao;
    private Integer idEtapa;
    private String etapa;
    private String critico;
    private String cor;
    private String feriado;
}

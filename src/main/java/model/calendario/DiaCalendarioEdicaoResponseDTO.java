package model.calendario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DiaCalendarioEdicaoResponseDTO {
    private String dia;
    private Integer idEtapa;
    private String etapa;
    private String critico;
    private String cor;
    private List<String> areas;
    private String feriado;
}

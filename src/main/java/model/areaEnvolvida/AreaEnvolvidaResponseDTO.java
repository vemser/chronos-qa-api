package model.areaEnvolvida;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.modulo.ModuloResponseDTO;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AreaEnvolvidaResponseDTO {
    private String nome;
    private Integer idAreaEnvolvida;
    private List<ModuloResponseDTO> modulos;
}

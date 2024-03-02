package model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

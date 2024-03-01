package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AreaEnvolvidaRequestDTO {
    private String nome;
    private Integer idAreaEnvolvida;

}

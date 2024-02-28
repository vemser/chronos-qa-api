package model.foto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FotoResponseDTO {
    private Integer idFoto;
    private String nome;
    private String tipo;
    private String arquivo;
}

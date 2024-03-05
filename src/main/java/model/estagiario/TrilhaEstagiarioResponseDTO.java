package model.estagiario;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.foto.FotoResponseDTO;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TrilhaEstagiarioResponseDTO {
    private Integer idTrilha;
    private String nome;
    private String descricao;
    private String status;
    private FotoResponseDTO foto;
}

package model.trilha;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.modulo.ModuloResponseDTO;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrilhaResponseDTO extends TrilhaRequestDTO {

    private Integer idTrilha;
    private String nome;
    private String status;
    private String descricao;
    private String message;
    private List<ModuloResponseDTO> modulos;
    private String timestamp;
    private String errors;
    private FotoResponseDTO foto;

}

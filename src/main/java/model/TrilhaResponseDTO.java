package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrilhaResponseDTO extends TrilhaRequestDTO {

    private String nome;
    private String status;
    private String descricao;
    private Integer idTrilha;
    private String message;

}

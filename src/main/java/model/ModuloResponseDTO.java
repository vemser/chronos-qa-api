package model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ModuloResponseDTO {
    private Integer idModulo;
    private String nome;
    private String nomeResponsavel;
    private String conteudoProgramatico;
    private String status;
    private String cargoResponsavel;
}

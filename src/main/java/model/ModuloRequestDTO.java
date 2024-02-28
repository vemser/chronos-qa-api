package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ModuloRequestDTO {
    private String nome;
    private String loginResponsavel;
    private String conteudoProgramatico;
    private String status;
}

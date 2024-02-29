package model.edicao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EdicaoResponseDTO {
    private String nome;
    private LocalDate dataInicial;
    private LocalDate dataFinal;
    private String descricao;
    private Integer idEdicao;
    private String status;
    private List etapas;
    private List trilhas;
    private List estagiarios;
    private String foto;
}

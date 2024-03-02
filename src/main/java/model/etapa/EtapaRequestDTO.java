package model.etapa;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EtapaRequestDTO {
    private String nome;
    private Integer ordemExecucao;
    private Integer duracaoDiaUtil;
}

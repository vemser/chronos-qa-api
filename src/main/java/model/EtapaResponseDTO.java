package model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EtapaResponseDTO {
    private Integer idEtapa;
    private String nome;
    private Integer ordemExecucao;
    private Integer duracaoDiaUtil;
    private AreaEnvolvidaResponseDTO areaEnvolvidaDTO;
}

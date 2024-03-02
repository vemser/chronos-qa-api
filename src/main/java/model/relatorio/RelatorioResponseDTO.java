package model.relatorio;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RelatorioResponseDTO {
    public QuantidadeDesligamentosResponseDTO desligamentos;
    public QuantidadeEstagiariosResponseDTO estagiarios;
    public RegioesResponseDTO regioes;
    public List<QuantidadeEstagirariosPorTrilhasResponseDTO> trilhas;

}

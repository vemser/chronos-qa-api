package model.relatorio;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegioesResponseDTO {
    public int norte;
    public int nordeste;
    public int centroOeste;
    public int sudeste;
    public int sul;
}

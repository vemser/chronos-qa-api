package model.diaNaoUtil;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiaNaoUtilRequestDTO {


    private String descricao;
    private String dataInicial;
    private String dataFinal;
    private String repeticaoAnual;
}

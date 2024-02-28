package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class TrilhaRequestDTO {
    private String nome;
    private String status;
    private String descricao;


    @Override
    public String toString() {
        return "TrilhaReq{" +
                "nome='" + nome + '\'' +
                ", status='" + status + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}

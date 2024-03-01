package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EstagiarioRequestDTO {
    private String cpf;
    private String nome;
    private String telefone;
    private String dataNascimento;
    private String emailPessoal;
    private String emailCorporativo;
    private String cidade;
    private String estado;
    private String curso;
    private String instituicaoEnsino;
    private String github;
    private String linkedin;
    private String status;
    private String observacao;
    private Integer idEdicao;
    private Integer idTrilha;
}
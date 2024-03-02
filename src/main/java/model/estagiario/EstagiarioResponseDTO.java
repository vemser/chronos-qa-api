package model.estagiario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.edicao.EdicaoResponseDTO;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EstagiarioResponseDTO {
    private Integer idEstagiario;
    private String cpf;
    private String nome;
    private String telefone;
    private LocalDate dataNascimento;
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
    private TrilhaEstagiarioResponseDTO trilha;
    private EdicaoEstagiarioResponseDTO edicao;
}

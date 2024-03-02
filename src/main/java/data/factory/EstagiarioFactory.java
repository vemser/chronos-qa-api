package data.factory;

import model.EstagiarioRequestDTO;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;

public class EstagiarioFactory {

    public static EstagiarioRequestDTO estagiarioValido(Integer idTrilha, Integer idEdicao) {

        return EstagiarioRequestDTO.builder()
                .cpf(Factory.cpf())
                .nome(Factory.nome())
                .telefone(Factory.telefone())
                .dataNascimento(LocalDate.of(1990, 01, 26))
                .emailPessoal(Factory.email())
                .emailCorporativo(Factory.email())
                .cidade("Porto Alegre")
                .estado("RS")
                .curso("Análise e Desenvolvimento de Sistemas")
                .instituicaoEnsino("Pontifícia Universidade Example")
                .github("https://github.c/example")
                .linkedin("https://linkedin.c/example")
                .status("DIS")
                .observacao("Desligado devido ao comportamento")
                .idEdicao(idEdicao)
                .idTrilha(idTrilha)
                .build();
    }
    public static EstagiarioRequestDTO estagiarioComCpfNaoPreenchidoCorretamente() {

        return EstagiarioRequestDTO.builder()
                .cpf("00")
                .nome(Factory.nome())
                .telefone(Factory.telefone())
                .dataNascimento(LocalDate.of(1990, 01, 26))
                .emailPessoal(Factory.email())
                .emailCorporativo(Factory.email())
                .cidade("Porto Alegre")
                .estado("RS")
                .curso("Análise e Desenvolvimento de Sistemas")
                .instituicaoEnsino("Pontifícia Universidade Example")
                .github("https://github.c/example")
                .linkedin("https://linkedin.c/example")
                .status("DIS")
                .observacao("Desligado devido ao comportamento")
                .idEdicao(-1)
                .idTrilha(-1)
                .build();
    }
    public static EstagiarioRequestDTO estagiarioComCpfNulo() {

        return EstagiarioRequestDTO.builder()
                .cpf(null)
                .nome(Factory.nome())
                .telefone(Factory.telefone())
                .dataNascimento(LocalDate.of(1990, 01, 26))
                .emailPessoal(Factory.email())
                .emailCorporativo(Factory.email())
                .cidade("Porto Alegre")
                .estado("RS")
                .curso("Análise e Desenvolvimento de Sistemas")
                .instituicaoEnsino("Pontifícia Universidade Example")
                .github("https://github.c/example")
                .linkedin("https://linkedin.c/example")
                .status("DIS")
                .observacao("Desligado devido ao comportamento")
                .idEdicao(-1)
                .idTrilha(-1)
                .build();
    }
    public static EstagiarioRequestDTO estagiarioComNomeNaoPreenchido() {

        return EstagiarioRequestDTO.builder()
                .cpf(Factory.cpf())
                .nome(StringUtils.EMPTY)
                .telefone(Factory.telefone())
                .dataNascimento(LocalDate.of(1990, 01, 26))
                .emailPessoal(Factory.email())
                .emailCorporativo(Factory.email())
                .cidade("Porto Alegre")
                .estado("RS")
                .curso("Análise e Desenvolvimento de Sistemas")
                .instituicaoEnsino("Pontifícia Universidade Example")
                .github("https://github.c/example")
                .linkedin("https://linkedin.c/example")
                .status("DIS")
                .observacao("Desligado devido ao comportamento")
                .idEdicao(-1)
                .idTrilha(-1)
                .build();
    }

    public static EstagiarioRequestDTO estagiarioComTelefoneMalPreenchido() {

        return EstagiarioRequestDTO.builder()
                .cpf(Factory.cpf())
                .nome(Factory.nome())
                .telefone("00")
                .dataNascimento(LocalDate.of(1990, 01, 26))
                .emailPessoal(Factory.email())
                .emailCorporativo(Factory.email())
                .cidade("Porto Alegre")
                .estado("RS")
                .curso("Análise e Desenvolvimento de Sistemas")
                .instituicaoEnsino("Pontifícia Universidade Example")
                .github("https://github.c/example")
                .linkedin("https://linkedin.c/example")
                .status("DIS")
                .observacao("Desligado devido ao comportamento")
                .idEdicao(-1)
                .idTrilha(-1)
                .build();
    }
    public static EstagiarioRequestDTO estagiarioComTelefoneNulo() {

        return EstagiarioRequestDTO.builder()
                .cpf(Factory.cpf())
                .nome(Factory.nome())
                .telefone(null)
                .dataNascimento(LocalDate.of(1990, 01, 26))
                .emailPessoal(Factory.email())
                .emailCorporativo(Factory.email())
                .cidade("Porto Alegre")
                .estado("RS")
                .curso("Análise e Desenvolvimento de Sistemas")
                .instituicaoEnsino("Pontifícia Universidade Example")
                .github("https://github.c/example")
                .linkedin("https://linkedin.c/example")
                .status("DIS")
                .observacao("Desligado devido ao comportamento")
                .idEdicao(-1)
                .idTrilha(-1)
                .build();
    }
    public static EstagiarioRequestDTO estagiarioComDataNascimentoVazio() {

        return EstagiarioRequestDTO.builder()
                .cpf(Factory.cpf())
                .nome(Factory.nome())
                .telefone(Factory.telefone())
                .dataNascimento(null)
                .emailPessoal(Factory.email())
                .emailCorporativo(Factory.email())
                .cidade("Porto Alegre")
                .estado("RS")
                .curso("Análise e Desenvolvimento de Sistemas")
                .instituicaoEnsino("Pontifícia Universidade Example")
                .github("https://github.c/example")
                .linkedin("https://linkedin.c/example")
                .status("DIS")
                .observacao("Desligado devido ao comportamento")
                .idEdicao(-1)
                .idTrilha(-1)
                .build();
    }

    public static EstagiarioRequestDTO estagiarioComEmailPessoalVazio() {

        return EstagiarioRequestDTO.builder()
                .cpf(Factory.cpf())
                .nome(Factory.nome())
                .telefone(Factory.telefone())
                .dataNascimento(LocalDate.of(1990, 01, 26))
                .emailPessoal(StringUtils.EMPTY)
                .emailCorporativo(Factory.email())
                .cidade("Porto Alegre")
                .estado("RS")
                .curso("Análise e Desenvolvimento de Sistemas")
                .instituicaoEnsino("Pontifícia Universidade Example")
                .github("https://github.c/example")
                .linkedin("https://linkedin.c/example")
                .status("DIS")
                .observacao("Desligado devido ao comportamento")
                .idEdicao(-1)
                .idTrilha(-1)
                .build();
    }

    public static EstagiarioRequestDTO estagiarioComEmailProfissionalVazio() {

        return EstagiarioRequestDTO.builder()
                .cpf(Factory.cpf())
                .nome(Factory.nome())
                .telefone(Factory.telefone())
                .dataNascimento(LocalDate.of(1990, 01, 26))
                .emailPessoal(Factory.email())
                .emailCorporativo(StringUtils.EMPTY)
                .cidade("Porto Alegre")
                .estado("RS")
                .curso("Análise e Desenvolvimento de Sistemas")
                .instituicaoEnsino("Pontifícia Universidade Example")
                .github("https://github.c/example")
                .linkedin("https://linkedin.c/example")
                .status("DIS")
                .observacao("Desligado devido ao comportamento")
                .idEdicao(-1)
                .idTrilha(-1)
                .build();
    }

    public static EstagiarioRequestDTO estagiarioComCidadeVazio() {

        return EstagiarioRequestDTO.builder()
                .cpf(Factory.cpf())
                .nome(Factory.nome())
                .telefone(Factory.telefone())
                .dataNascimento(LocalDate.of(1990, 01, 26))
                .emailPessoal(Factory.email())
                .emailCorporativo(Factory.email())
                .cidade(StringUtils.EMPTY)
                .estado("RS")
                .curso("Análise e Desenvolvimento de Sistemas")
                .instituicaoEnsino("Pontifícia Universidade Example")
                .github("https://github.c/example")
                .linkedin("https://linkedin.c/example")
                .status("DIS")
                .observacao("Desligado devido ao comportamento")
                .idEdicao(-1)
                .idTrilha(-1)
                .build();
    }
    public static EstagiarioRequestDTO estagiarioComEstadoVazio() {

        return EstagiarioRequestDTO.builder()
                .cpf(Factory.cpf())
                .nome(Factory.nome())
                .telefone(Factory.telefone())
                .dataNascimento(LocalDate.of(1990, 01, 26))
                .emailPessoal(Factory.email())
                .emailCorporativo(Factory.email())
                .cidade("Porto Alegre")
                .estado(StringUtils.EMPTY)
                .curso("Análise e Desenvolvimento de Sistemas")
                .instituicaoEnsino("Pontifícia Universidade Example")
                .github("https://github.c/example")
                .linkedin("https://linkedin.c/example")
                .status("DIS")
                .observacao("Desligado devido ao comportamento")
                .idEdicao(-1)
                .idTrilha(-1)
                .build();
    }

    public static EstagiarioRequestDTO estagiarioComCursoVazio() {

        return EstagiarioRequestDTO.builder()
                .cpf(Factory.cpf())
                .nome(Factory.nome())
                .telefone(Factory.telefone())
                .dataNascimento(LocalDate.of(1990, 01, 26))
                .emailPessoal(Factory.email())
                .emailCorporativo(Factory.email())
                .cidade("Porto Alegre")
                .estado("RS")
                .curso(StringUtils.EMPTY)
                .instituicaoEnsino("Pontifícia Universidade Example")
                .github("https://github.c/example")
                .linkedin("https://linkedin.c/example")
                .status("DIS")
                .observacao("Desligado devido ao comportamento")
                .idEdicao(-1)
                .idTrilha(-1)
                .build();
    }

    public static EstagiarioRequestDTO estagiarioComInstituicaoEnsinoVazio() {

        return EstagiarioRequestDTO.builder()
                .cpf(Factory.cpf())
                .nome(Factory.nome())
                .telefone(Factory.telefone())
                .dataNascimento(LocalDate.of(1990, 01, 26))
                .emailPessoal(Factory.email())
                .emailCorporativo(Factory.email())
                .cidade("Porto Alegre")
                .estado("RS")
                .curso("Análise e Desenvolvimento de Sistemas")
                .instituicaoEnsino(StringUtils.EMPTY)
                .github("https://github.c/example")
                .linkedin("https://linkedin.c/example")
                .status("DIS")
                .observacao("Desligado devido ao comportamento")
                .idEdicao(-1)
                .idTrilha(-1)
                .build();
    }
    public static EstagiarioRequestDTO estagiarioComStatusNulo() {

        return EstagiarioRequestDTO.builder()
                .cpf(Factory.cpf())
                .nome(Factory.nome())
                .telefone(Factory.telefone())
                .dataNascimento(LocalDate.of(1990, 01, 26))
                .emailPessoal(Factory.email())
                .emailCorporativo(Factory.email())
                .cidade("Porto Alegre")
                .estado("RS")
                .curso("Análise e Desenvolvimento de Sistemas")
                .instituicaoEnsino("Pontifícia Universidade Example")
                .github("https://github.c/example")
                .linkedin("https://linkedin.c/example")
                .status(null)
                .observacao("Desligado devido ao comportamento")
                .idEdicao(-1)
                .idTrilha(-1)
                .build();
    }

    public static EstagiarioRequestDTO estagiarioComIdEdicaoInvalido(Integer idTrilha) {

        return EstagiarioRequestDTO.builder()
                .cpf(Factory.cpf())
                .nome(Factory.nome())
                .telefone(Factory.telefone())
                .dataNascimento(LocalDate.of(1990, 01, 26))
                .emailPessoal(Factory.email())
                .emailCorporativo(Factory.email())
                .cidade("Porto Alegre")
                .estado("RS")
                .curso("Análise e Desenvolvimento de Sistemas")
                .instituicaoEnsino(StringUtils.EMPTY)
                .github("https://github.c/example")
                .linkedin("https://linkedin.c/example")
                .status("DIS")
                .observacao("Desligado devido ao comportamento")
                .idEdicao(-1)
                .idTrilha(idTrilha)
                .build();
    }
    public static EstagiarioRequestDTO estagiarioComIdTrilhaInvalido(Integer idEdicao) {

        return EstagiarioRequestDTO.builder()
                .cpf(Factory.cpf())
                .nome(Factory.nome())
                .telefone(Factory.telefone())
                .dataNascimento(LocalDate.of(1990, 01, 26))
                .emailPessoal(Factory.email())
                .emailCorporativo(Factory.email())
                .cidade("Porto Alegre")
                .estado("RS")
                .curso("Análise e Desenvolvimento de Sistemas")
                .instituicaoEnsino(StringUtils.EMPTY)
                .github("https://github.c/example")
                .linkedin("https://linkedin.c/example")
                .status("DIS")
                .observacao("Desligado devido ao comportamento")
                .idEdicao(idEdicao)
                .idTrilha(-1)
                .build();
    }

}

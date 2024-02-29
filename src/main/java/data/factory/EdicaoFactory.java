package data.factory;

import model.edicao.EdicaoRequestDTO;
import org.apache.commons.lang3.StringUtils;

public class EdicaoFactory {

    public static EdicaoRequestDTO edicaoValida() {
        return EdicaoRequestDTO.builder()
                .nome(Factory.evento())
                .dataInicial(Factory.dataInicial())
                .dataFinal(Factory.dataFinal())
                .descricao(Factory.descricao())
                .status("ATIVO")
                .build();
    }

    public static EdicaoRequestDTO edicaoSemDescricao() {
        return EdicaoRequestDTO.builder()
                .nome(Factory.evento())
                .dataInicial(Factory.dataInicial())
                .dataFinal(Factory.dataFinal())
                .descricao(StringUtils.EMPTY)
                .status("ATIVO")
                .build();
    }

    public static EdicaoRequestDTO edicaoComDataFinalNull() {
        return EdicaoRequestDTO.builder()
                .nome(Factory.evento())
                .dataInicial(Factory.dataInicial())
                .dataFinal(null)
                .descricao(Factory.descricao())
                .status("ATIVO")
                .build();
    }
    public static EdicaoRequestDTO edicaoComDataFinalVazio() {
        return EdicaoRequestDTO.builder()
                .nome(Factory.evento())
                .dataInicial(Factory.dataInicial())
                .descricao(Factory.descricao())
                .status("ATIVO")
                .build();
    }

    public static EdicaoRequestDTO edicaoComNomeVazio() {
        return EdicaoRequestDTO.builder()
                .nome(StringUtils.EMPTY)
                .dataInicial(Factory.dataInicial())
                .dataInicial(Factory.dataFinal())
                .descricao(Factory.descricao())
                .status("ATIVO")
                .build();
    }

    public static EdicaoRequestDTO edicaoComNomeTamanhoTres() {
        return EdicaoRequestDTO.builder()
                .nome(Factory.nomeTamanhoTres())
                .dataInicial(Factory.dataInicial())
                .dataInicial(Factory.dataFinal())
                .descricao(Factory.descricao())
                .status("ATIVO")
                .build();
    }

    public static EdicaoRequestDTO edicaoComDescricao256() {
        return EdicaoRequestDTO.builder()
                .nome(Factory.evento())
                .dataInicial(Factory.dataInicial())
                .dataInicial(Factory.dataFinal())
                .descricao(Factory.descricao256())
                .status("ATIVO")
                .build();
    }

    public static EdicaoRequestDTO edicaoComDataInicialNulo() {
        return EdicaoRequestDTO.builder()
                .nome(Factory.evento())
                .dataInicial(null)
                .dataFinal(Factory.dataFinal())
                .descricao(Factory.descricao())
                .status("ATIVO")
                .build();
    }


    public static EdicaoRequestDTO edicaoComNomeJaExistente() {
        return EdicaoRequestDTO.builder()
                .nome("Vem Ser 15")
                .dataInicial(Factory.dataInicial())
                .dataFinal(Factory.dataFinal())
                .descricao(Factory.descricao())
                .status("ATIVO")
                .build();
    }
}

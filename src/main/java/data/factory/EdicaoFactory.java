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
                .build();
    }

    public static EdicaoRequestDTO edicaoSemDescricao() {
        return EdicaoRequestDTO.builder()
                .nome(Factory.evento())
                .dataInicial(Factory.dataInicial())
                .dataFinal(Factory.dataFinal())
                .descricao(StringUtils.EMPTY)
                .build();
    }

    public static EdicaoRequestDTO edicaoComDataFinalNull() {
        return EdicaoRequestDTO.builder()
                .nome(Factory.evento())
                .dataInicial(Factory.dataInicial())
                .dataFinal(null)
                .descricao(Factory.descricao())
                .build();
    }
    public static EdicaoRequestDTO edicaoComDataFinalVazio() {
        return EdicaoRequestDTO.builder()
                .nome(Factory.evento())
                .dataInicial(Factory.dataInicial())
                .descricao(Factory.descricao())
                .build();
    }
}

package data.provider;

import data.factory.EdicaoFactory;
import data.factory.Factory;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class EdicaoProvider {

    private static Stream<Arguments> criarEdicaoComSucessoProvider() {
        return Stream.of(
                Arguments.of(EdicaoFactory.edicaoValida(), HttpStatus.SC_OK),
                Arguments.of(EdicaoFactory.edicaoSemDescricao(), HttpStatus.SC_OK),
                Arguments.of(EdicaoFactory.edicaoComDataFinalNull(), HttpStatus.SC_OK),
                Arguments.of(EdicaoFactory.edicaoComDataFinalVazio(), HttpStatus.SC_OK)
        );
    }

    private static Stream<Arguments> criarEdicaoSemSucessoProvider() {
        return Stream.of(
                Arguments.of(StringUtils.EMPTY, Factory.dataInicial(), Factory.dataFinal(), Factory.descricao(), HttpStatus.SC_BAD_REQUEST),
                Arguments.of("123", Factory.dataInicial(), Factory.dataFinal(), Factory.descricao(), HttpStatus.SC_BAD_REQUEST),
                Arguments.of("123", Factory.dataInicial(), Factory.dataFinal(), Factory.descricao256(), HttpStatus.SC_BAD_REQUEST)
        );
    }

}

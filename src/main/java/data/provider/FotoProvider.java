package data.provider;

import data.factory.Factory;
import data.factory.FotoFactory;
import org.junit.jupiter.params.provider.Arguments;
import utils.image.ImageTypes;

import java.util.stream.Stream;

public class FotoProvider {

    private static Stream<Arguments> providerCadastrarFoto() {
        return Stream.of(
                Arguments.of(FotoFactory.gerarPNG(), ImageTypes.PNG, "png"),
                Arguments.of(FotoFactory.gerarJPG(), ImageTypes.JPG, "jpg")
        );
    }

}

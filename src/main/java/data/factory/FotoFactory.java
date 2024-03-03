package data.factory;

import java.io.File;

public class FotoFactory {

    public static File gerarPNG() {
        return new File("C:\\Users\\rapha\\projeto-final-api\\chronos-qa-api\\src\\main\\resources\\images\\testepng.png");
    }
    public static File gerarJPG() {
        return new File("C:\\Users\\rapha\\projeto-final-api\\chronos-qa-api\\src\\main\\resources\\images\\testejpg.jpg");
    }
}

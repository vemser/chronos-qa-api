package data.factory;

import net.datafaker.Faker;

import java.util.Locale;
import java.util.Random;

public class Factory {
    public static final Faker FAKER = new Faker(new Locale("pt-BR"));
    public static final Random RANDOM = new Random();

    public static String nome() {
        return FAKER.name().firstName();
    }
}
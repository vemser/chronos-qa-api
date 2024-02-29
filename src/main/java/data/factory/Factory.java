package data.factory;

import net.datafaker.Faker;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Random;

public class Factory {
    public static final Faker FAKER = new Faker(new Locale("pt-BR"));
    public static final Random RANDOM = new Random();
    public static String nome() {
        return FAKER.name().firstName();
    }
    public static String nomeTamanhoTres() {
        return FAKER.letterify("1a2");
    }
    public static String evento() { return FAKER.company().name().substring(0, 10); }
    public static LocalDate dataInicial() { return LocalDate.now(); }
    public static LocalDate dataFinal() { return LocalDate.now().plusMonths(2); }
    public static String descricao() { return FAKER.lorem().sentence(); }
    public static String descricao256() { return FAKER.lorem().sentence(256); }
}
package data.factory;

import net.datafaker.Faker;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import java.time.LocalDate;

import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Factory {
    public static final Faker FAKER = new Faker(new Locale("pt-BR"));
    public static final Random RANDOM = new Random();
    public static String nome() {
        return FAKER.name().firstName();
    }

    public static String dataFutura(int inicio, int fim){

        LocalDate localDate = LocalDate.now();
        localDate = localDate.plusDays(FAKER.number().numberBetween(inicio, fim));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return localDate.format(formatter);
    }

    public static String nomeTamanhoTres() {
        return FAKER.letterify("???");
    }
    public static String telefone() {
     return FAKER.numerify("8399#######");
    }
    public static String cpf() {
        return FAKER.cpf().valid(false);
    }

    public static String email() {
        return FAKER.internet().emailAddress();
    }

    public static String evento() { return FAKER.letterify("??????????"); }
    public static LocalDate dataInicial() { return LocalDate.now().plusMonths(2); }
    public static LocalDate dataFinal() { return LocalDate.now().plusMonths(4); }
    public static String descricao() { return FAKER.lorem().sentence(); }
    public static String descricao256() { return FAKER.lorem().sentence(256); }

}
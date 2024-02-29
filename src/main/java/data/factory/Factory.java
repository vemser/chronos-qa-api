package data.factory;

import net.datafaker.Faker;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Factory {
    public static final Faker FAKER = new Faker(new Locale("pt-BR"));
    public static final Random RANDOM = new Random();

    public static String nome() {
        return FAKER.name().firstName();
    }

    public static String dataFutura(int inicio){

        LocalDate localDate = LocalDate.now();
        localDate = localDate.plusDays(FAKER.number().numberBetween(inicio, 366));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return localDate.format(formatter);
    }
}
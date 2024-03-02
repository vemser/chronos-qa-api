package data.factory;

import static data.factory.Factory.FAKER;

public class CalendarioGeralDataFactory {
    public static Integer gerarMesCalendario() {
        return FAKER.number().numberBetween(1, 12);
    }
}

package data.factory;

import model.diaNaoUtil.DiaNaoUtilRequestDTO;
import model.trilha.TrilhaRequestDTO;
import net.datafaker.Faker;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DiaNaoUtilFactory {

    public static LocalDate dataFinal() { return LocalDate.now().plusMonths(2); }
    public static DiaNaoUtilRequestDTO diaNaoUtilTodosOsCampos(){
        DiaNaoUtilRequestDTO diaNaoUtilRequestDTO = new DiaNaoUtilRequestDTO();
       diaNaoUtilRequestDTO.setDescricao(Factory.FAKER.lorem().sentence());
       diaNaoUtilRequestDTO.setDataInicial(Factory.dataFutura(1, 60));
       diaNaoUtilRequestDTO.setDataFinal(Factory.dataFutura(61,120));
       diaNaoUtilRequestDTO.setRepeticaoAnual("INATIVO");

        return diaNaoUtilRequestDTO;


    }
    public static DiaNaoUtilRequestDTO diaNaoUtilSemDataFinal(){
        DiaNaoUtilRequestDTO diaNaoUtilRequestDTO = new DiaNaoUtilRequestDTO();
        diaNaoUtilRequestDTO.setDescricao(Factory.FAKER.lorem().sentence());
        diaNaoUtilRequestDTO.setDataInicial(Factory.dataFutura(1, 60));
        diaNaoUtilRequestDTO.setRepeticaoAnual("ATIVO");

        return diaNaoUtilRequestDTO;
    }

    public static DiaNaoUtilRequestDTO dataComFormatoInvalido(){
        DiaNaoUtilRequestDTO diaNaoUtilRequestDTO = new DiaNaoUtilRequestDTO();
        diaNaoUtilRequestDTO.setDescricao(Factory.FAKER.lorem().sentence());
        diaNaoUtilRequestDTO.setDataInicial(Factory.FAKER.name().firstName());
        diaNaoUtilRequestDTO.setRepeticaoAnual("ATIVO");

        return diaNaoUtilRequestDTO;
    }

    public static DiaNaoUtilRequestDTO diaNaoUtilCampoOpcional(){
        DiaNaoUtilRequestDTO diaNaoUtilRequestDTO = new DiaNaoUtilRequestDTO();
        diaNaoUtilRequestDTO.setDataFinal(Factory.dataFutura(1, 60));


        return diaNaoUtilRequestDTO;
}
    public static Integer idInvalido(){
        return Factory.FAKER.number().numberBetween(999,9999);
    }
}

package data.factory;

import model.AreaEnvolvidaRequestDTO;
import model.trilha.TrilhaRequestDTO;
import net.datafaker.Faker;

public class AreaEnvovidaDataFactory {



    public static AreaEnvolvidaRequestDTO areaEnvolvidaComSucesso(){
        AreaEnvolvidaRequestDTO areaEnvolvidaRequestDTO = new AreaEnvolvidaRequestDTO();
        areaEnvolvidaRequestDTO.setNome("Area envolvida " + Factory.FAKER.number().digits(10));


        return areaEnvolvidaRequestDTO;


    }

    public static AreaEnvolvidaRequestDTO comNomeVazio(){
        AreaEnvolvidaRequestDTO areaEnvolvidaRequestDTO = new AreaEnvolvidaRequestDTO();
        areaEnvolvidaRequestDTO.setNome("");


        return areaEnvolvidaRequestDTO;


    }

    public static Integer idInvalido(){
        return Factory.FAKER.number().numberBetween(999,9999);
    }
    public static AreaEnvolvidaRequestDTO comCampoNomeNumerico(){
        AreaEnvolvidaRequestDTO areaEnvolvidaRequestDTO = new AreaEnvolvidaRequestDTO();
        areaEnvolvidaRequestDTO.setNome(Factory.FAKER.number().digits(9));


        return areaEnvolvidaRequestDTO;


    }



}

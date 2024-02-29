package com.chronos.tests.etapa;

import client.EtapaClient;
import data.factory.EtapaDataFactory;
import data.factory.ModuloDataFactory;
import model.EtapaRequestDTO;
import model.EtapaResponseDTO;
import model.ModuloRequestDTO;
import model.ModuloResponseDTO;
import org.junit.After;
import org.junit.Before;

public class GetEtapaFuncionalTest {
    Integer idEtapaCadastrada = 0;
    Integer idEdicaoCadastrada = 0;
    EtapaClient etapaClient = new EtapaClient();
    EtapaResponseDTO etapaCadastrada;
}

package client;

import io.restassured.response.Response;

public interface ClientInterface<ID, T> {

    Response cadastrar(T body);

    Response buscarTudo();

    Response buscarPorID(ID id);

    Response atualizar(ID id, T body);

    Response deletar(ID id);
}

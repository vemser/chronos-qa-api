package utils.props;

import model.LoginRequestDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Propriedades sensíveis que devem ser recuperadas ao iniciar os testes automatizados
 */

public class GetProperties {

    public static String LOGIN_URI() {
        return getProperties().getProperty("uri_usuarios_login");
    }

    public static String BASE_URI() {
        return getProperties().getProperty("uri_server");
    }

    public static LoginRequestDTO LOGIN_DATA() {
        return LoginRequestDTO.builder()
                .username(getProperties().getProperty("username_login"))
                .password(getProperties().getProperty("password_login"))
                .build();
    }

    /**
     * Para recuperar os dados que serão usados nos testes, crie um arquivo .properties e crie as seguintes
     * variáveis de ambientes:
     *
     * @value username_login: username do usuário
     * @value password_login: password do usuário
     * @value uri_usuarios_login: url que aponta para o login de um novo usuário na API do USUÁRIOS_BACK
     * @value uri_server: uri para testes na aplicação do chronos
     *
     * @return um objeto properties com as variáveis de ambiente
     */
    private static Properties getProperties() {
        Properties propsResponse = new Properties();
        try {
            propsResponse.load(new FileInputStream("src/main/resources/config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return propsResponse;
    }

}

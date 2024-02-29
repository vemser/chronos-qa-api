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

    public static LoginRequestDTO getProperties(String user, String pass) {
        return LoginRequestDTO.builder()
                .username(getProperties().getProperty(user))
                .password(getProperties().getProperty(pass))
                .build();
    }

    public static LoginRequestDTO LOGIN_DATA() {
        return LoginRequestDTO.builder()
                .username(getProperties().getProperty("username_login"))
                .password(getProperties().getProperty("password_login"))
                .build();
    }

    public static LoginRequestDTO LOGIN_DATA_ADMIN() {
        return getProperties(
                "username_login_admin",
                "password_login_admin"
        );
    }
    public static LoginRequestDTO LOGIN_DATA_GESTOR() {
        return getProperties(
                "username_login_gestor",
                "password_login_gestor"
        );
    }

    public static LoginRequestDTO LOGIN_DATA_GP() {
        return getProperties(
                "username_login_gp",
                "password_login_gp"
        );
    }

    public static LoginRequestDTO LOGIN_DATA_INSTRUTOR() {
        return getProperties(
                "username_login_instrutor",
                "password_login_instrutor"
        );
    }


    /**
     * Para recuperar os dados que serão usados nos testes, crie um arquivo .properties e crie as seguintes
     * variáveis de ambientes:
     *
     * @return um objeto properties com as variáveis de ambiente
     * @value username_login: username do usuário
     * @value password_login: password do usuário
     * @value uri_usuarios_login: url que aponta para o login de um novo usuário na API do USUÁRIOS_BACK
     * @value uri_server: uri para testes na aplicação do chronos
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

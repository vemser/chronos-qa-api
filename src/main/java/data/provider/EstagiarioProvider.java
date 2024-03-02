package data.provider;

import data.factory.EstagiarioFactory;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class EstagiarioProvider {
    private static Stream<Arguments> providerCadastroComCamposObrigatoriosNaoPreenchidos() {
        return Stream.of(
                Arguments.of(EstagiarioFactory.estagiarioComCpfNaoPreenchidoCorretamente(), "cpf: O CPF deve ser válido"),
                Arguments.of(EstagiarioFactory.estagiarioComCpfNulo(), "cpf: O CPF é obrigatório"),
                Arguments.of(EstagiarioFactory.estagiarioComNomeNaoPreenchido(), "nome: O nome é obrigatório"),
                Arguments.of(EstagiarioFactory.estagiarioComTelefoneMalPreenchido(), "telefone: O tamanho do campo é de 11 caracteres."),
                Arguments.of(EstagiarioFactory.estagiarioComTelefoneNulo(), "telefone: O telefone é obrigatório"),
                Arguments.of(EstagiarioFactory.estagiarioComEmailProfissionalVazio(), "emailCorporativo: O e-mail corporativo é obrigatório"),
                Arguments.of(EstagiarioFactory.estagiarioComEmailPessoalVazio(), "emailPessoal: O e-mail pessoal é obrigatório"),
                Arguments.of(EstagiarioFactory.estagiarioComDataNascimentoVazio(), "dataNascimento: A data de nascimento é obrigatória"),
                Arguments.of(EstagiarioFactory.estagiarioComCursoVazio(), "curso: O curso é obrigatório"),
                Arguments.of(EstagiarioFactory.estagiarioComInstituicaoEnsinoVazio(), "instituicaoEnsino: A instituição de ensino é obrigatória"),
                Arguments.of(EstagiarioFactory.estagiarioComStatusNulo(), "status: O status é obrigatório")
        );
    }
}

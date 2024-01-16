package fiap.example.finance.model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Bruno Gomes Damascena dos santos (bruno-gds) < brunog.damascena@gmail.com >
 * Date: 16/01/2024
 * Project Name: finance
 */

public class CarteiraTest {

    @Test
    public void adicionarAcao_DeveAdicionarAcaoNaCarteira() {
        // Arrange
        List<Acao> acoes = Arrays.asList(
                new Acao("AAPL", 150.0),
                new Acao("GOOGL", 2500.0)
        );

        Carteira carteira = new Carteira("Minha Carteira", acoes);

        // Act
        Acao novaAcao = new Acao("TSLA", 700.0);
        carteira.adicionaAcao(novaAcao);

        // Assert
        assertEquals(3, carteira.getAcoes().size());
    }
}

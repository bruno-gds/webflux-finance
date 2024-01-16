package fiap.example.finance.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Bruno Gomes Damascena dos santos (bruno-gds) < brunog.damascena@gmail.com >
 * Date: 16/01/2024
 * Project Name: finance
 */

public class AcaoTest {

    @Test
    public void criarAcao_DeveDefinirSimboloEPrecoCorretamente() {
        // Arrange
        String simbolo = "AAPL";
        double preco = 150.0;

        // Act
        Acao acao = new Acao(simbolo, preco);

        // Assert
        assertEquals(simbolo, acao.getSimbolo());
        assertEquals(preco, acao.getPreco(), 0.01);
    }
}

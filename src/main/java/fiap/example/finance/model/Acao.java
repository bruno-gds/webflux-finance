package fiap.example.finance.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Bruno Gomes Damascena dos santos (bruno-gds) < brunog.damascena@gmail.com >
 * Date: 15/01/2024
 * Project Name: finance
 */

@Document(collection = "acoes")
public class Acao {

    @Id
    private String id;
    private String simbolo;
    private double preco;


    public Acao(String simbolo, double preco) {
        this.simbolo = simbolo;
        this.preco = preco;
    }

    public double getPreco() {
        return preco;
    }
}

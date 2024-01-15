package fiap.example.finance.request;

import fiap.example.finance.model.Acao;

import java.util.List;

/**
 * @author Bruno Gomes Damascena dos santos (bruno-gds) < brunog.damascena@gmail.com >
 * Date: 15/01/2024
 * Project Name: finance
 */

public class CarteiraComAcoesRequest {
    public String nomeCarteira;
    public List<Acao> acoes;
}

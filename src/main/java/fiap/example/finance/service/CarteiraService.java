package fiap.example.finance.service;

import fiap.example.finance.model.Acao;
import fiap.example.finance.model.Carteira;
import fiap.example.finance.repository.CarteiraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author Bruno Gomes Damascena dos santos (bruno-gds) < brunog.damascena@gmail.com >
 * Date: 15/01/2024
 * Project Name: finance
 */

@Service
public class CarteiraService {

    @Autowired
    private CarteiraRepository carteiraRepository;

    public Mono<Double> calcularRentabilidade(String carteiraNome) {
        return carteiraRepository.findByNome(carteiraNome)
                .map(carteira -> carteira.getAcoes().stream()
                        .mapToDouble(acao -> acao.getPreco())
                        .sum());
    }

    public Flux<Carteira> findAll() {
        return carteiraRepository.findAll();
    }

    public Mono<Carteira> save(String nomeCarteira, List<Acao> acoes) {
        var carteira = new Carteira(nomeCarteira, acoes);
        return carteiraRepository.save(carteira);
    }
}

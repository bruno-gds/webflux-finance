package fiap.example.finance.repository;

import fiap.example.finance.model.Carteira;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Bruno Gomes Damascena dos santos (bruno-gds) < brunog.damascena@gmail.com >
 * Date: 15/01/2024
 * Project Name: finance
 */

public interface CarteiraRepository extends ReactiveMongoRepository<Carteira, String> {
    Mono<Carteira> findByNome(String nome);
    Flux<Carteira> findAll();
}

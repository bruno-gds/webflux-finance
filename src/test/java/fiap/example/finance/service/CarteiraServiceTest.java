package fiap.example.finance.service;

import fiap.example.finance.model.Acao;
import fiap.example.finance.model.Carteira;
import fiap.example.finance.repository.CarteiraRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Bruno Gomes Damascena dos santos (bruno-gds) < brunog.damascena@gmail.com >
 * Date: 16/01/2024
 * Project Name: finance
 */

public class CarteiraServiceTest {

    @InjectMocks
    private CarteiraService carteiraService;

    @Mock
    private CarteiraRepository carteiraRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testCalcularRentabilidade() {
        // Crie a lista de ações
        List<Acao> acoes = Arrays.asList(
                new Acao("AAPL", 150.0),
                new Acao("GOOGL", 2500.0)
        );

        // Crie uma carteira fictícia com as ações
        Carteira carteira = new Carteira("Minha Carteira", acoes);

        // Mock do comportamento do repository
        Mockito.when(carteiraRepository.findByNome("Minha Carteira")).thenReturn(Mono.just(carteira));

        // Chame o método calcularRentabilidade e verifique o resultado
        StepVerifier.create(carteiraService.calcularRentabilidade("Minha Carteira"))
                .expectNext(acoes.stream().mapToDouble(Acao::getPreco).sum())
                .verifyComplete();
    }

    @Test
    public void testFindAll() {
        // Mock de dados
        Carteira carteira1 = new Carteira("Carteira 1", List.of());
        Carteira carteira2 = new Carteira("Carteira 2", List.of());
        List<Carteira> carteiras = Arrays.asList(carteira1, carteira2);

        // Mock do repositório
        Mockito.when(carteiraRepository.findAll()).thenReturn(Flux.fromIterable(carteiras));

        // Chame o método findAll
        Flux<Carteira> result = carteiraService.findAll();

        // Verifica se o resultado contém as carteiras esperadas
        assertEquals(carteiras, result.collectList().block());
    }

    @Test
    void testSave() {
        // Crie uma carteira fictícia com ações
        List<Acao> acoes = Arrays.asList(
                new Acao("AAPL", 150.0),
                new Acao("GOOGL", 2500.0)
        );

        Carteira carteira = new Carteira("Minha Carteira", acoes);
        Acao acao1 = new Acao("AAPL", 150.0);
        Acao acao2 = new Acao("GOOGL", 2500.0);
        carteira.setAcoes(Arrays.asList(acao1, acao2));

        // Mock do comportamento do repository
        Mockito.when(carteiraRepository.save(Mockito.any(Carteira.class))).thenReturn(Mono.just(carteira));

        // Chame o método save e verifique o resultado
        StepVerifier.create(carteiraService.save("Minha Carteira", carteira.getAcoes()))
                .expectNext(carteira)
                .verifyComplete();
    }
}

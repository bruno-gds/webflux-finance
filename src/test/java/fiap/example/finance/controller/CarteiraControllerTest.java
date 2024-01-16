package fiap.example.finance.controller;

import fiap.example.finance.model.Acao;
import fiap.example.finance.model.Carteira;
import fiap.example.finance.request.CarteiraComAcoesRequest;
import fiap.example.finance.service.CarteiraService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Arrays;

/**
 * @author Bruno Gomes Damascena dos santos (bruno-gds) < brunog.damascena@gmail.com >
 * Date: 16/01/2024
 * Project Name: finance
 */

@ExtendWith(SpringExtension.class)
public class CarteiraControllerTest {

    @InjectMocks
    private CarteiraController carteiraController;

    @Mock
    private CarteiraService carteiraService;

    private final Carteira carteira = new Carteira("f-invest", Arrays.asList(
            new Acao("AAPL", 150.0),
            new Acao("GOOGL", 2500.0)
    ));

    Double rentabilidadeEsperada = 1000.0;

    @BeforeEach
    public void setUp() {
        BDDMockito.when(carteiraService.findAll())
                .thenReturn(Flux.just(carteira));

        BDDMockito.when(carteiraService.save(carteira.getNome(), carteira.getAcoes()))
                .thenReturn(Mono.just(carteira));

        BDDMockito.when(carteiraService.calcularRentabilidade("f-invest"))
                .thenReturn(Mono.just(rentabilidadeEsperada));
    }

    @Test
    @DisplayName("Buscar todas as carteiras salvas")
    public void listAll_ReturnFluxOfCarteira_WhenSuccessful() {
        StepVerifier.create(carteiraController.getAll())
                .expectSubscription()
                .expectNext(carteira)
                .verifyComplete();
    }

    @Test
    @DisplayName("Criar carteira")
    public void save_CreatesCarteira_WhenSuccessful() {
        CarteiraComAcoesRequest carteiraToBeSaved = new CarteiraComAcoesRequest();
        carteiraToBeSaved.nomeCarteira = carteira.getNome();
        carteiraToBeSaved.acoes = carteira.getAcoes();

        StepVerifier.create(carteiraController.save(carteiraToBeSaved))
                .expectSubscription()
                .expectNext(carteira)
                .verifyComplete();
    }

    @Test
    @DisplayName("Calcular rentabilidade da carteira")
    public void calcularRentabilidade_ReturnsRentabilidade_WhenSuccessful() {
        String carteiraNome = "f-invest";

        StepVerifier.create(carteiraController.calcularRentabilidade(carteiraNome))
                .expectSubscription()
                .expectNextMatches(responseEntity -> {
                    Double rentabilidade = responseEntity.getBody();
                    return responseEntity.getStatusCode().value() == 200
                            && rentabilidade.equals(rentabilidadeEsperada);
                })
                .verifyComplete();
    }
}

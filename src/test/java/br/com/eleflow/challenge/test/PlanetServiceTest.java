package br.com.eleflow.challenge.test;

import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import br.com.eleflow.challenge.dto.PlanetDto;
import br.com.eleflow.challenge.fixture.PlanetFixture;
import br.com.eleflow.challenge.fixture.SwApiFixture;
import br.com.eleflow.challenge.model.PlanetModel;
import br.com.eleflow.challenge.repository.PlanetRepository;
import br.com.eleflow.challenge.service.impl.PlanetServiceImpl;
import br.com.eleflow.challenge.swapi.dto.SwPagedDto;
import reactor.blockhound.BlockHound;
import reactor.blockhound.BlockingOperationError;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.test.StepVerifier;

@ExtendWith(SpringExtension.class)
public class PlanetServiceTest {
	
	@InjectMocks
	private PlanetServiceImpl planetService;
	
	@Mock
	private RestTemplate swapiRestTemplateMock;
	
	@Mock
	private PlanetRepository planetRepositoryMock;
	
	
	@BeforeAll
    public static void blockHoundSetUp() {
        BlockHound.install();
    }
	
	@BeforeEach
	public void setUp() {
		BDDMockito.when(planetRepositoryMock.findAll())
			.thenReturn(Flux.just(PlanetFixture.earthPlanetModel()));
		
		BDDMockito.when(planetRepositoryMock.findById(ArgumentMatchers.anyLong()))
			.thenReturn(Mono.just(PlanetFixture.earthPlanetModel()));
		
		BDDMockito.when(planetRepositoryMock.findById(999999l))
			.thenReturn(Mono.empty());
		
		BDDMockito.when(planetRepositoryMock.delete(ArgumentMatchers.any(PlanetModel.class)))
			.thenReturn(Mono.empty());
	}
	
	@Test
    public void blockHoundWorks() {
        try {
            FutureTask<?> task = new FutureTask<>(() -> {
                Thread.sleep(0);
                return "";
            });
            Schedulers.parallel().schedule(task);

            task.get(10, TimeUnit.SECONDS);
            Assertions.fail("should fail");
        } catch (Exception e) {
            Assertions.assertTrue(e.getCause() instanceof BlockingOperationError);
        }
    }
	
	@Test
	public void findAll_ReturnFluxOfPlanet_WhenSuccessfull() {
		PlanetDto planetDto = new PlanetDto(PlanetFixture.earthPlanetModel());
		
		StepVerifier.create(planetService.findAll())
			.expectSubscription()
			.expectNext(planetDto)
			.verifyComplete();
	}
	
	@Test
	public void findById_ReturnMonoPlanet_WhenSuccessfull() {
		PlanetDto planetDto = new PlanetDto(PlanetFixture.earthPlanetModel());
		
		StepVerifier.create(planetService.findById(1l))
			.expectSubscription()
			.expectNext(planetDto)
			.verifyComplete();
	}
	
	@Test
	public void findById_ReturnMonoError_WhenEmptyMonoIsReturned() {
		
		StepVerifier.create(planetService.findById(999999l))
			.expectSubscription()
			.expectError(ResponseStatusException.class)
			.verify();
	}
	
	@Test
	public void save_CreatesPlanes_WhenSuccessfull() {
		PlanetDto newPlanetToBeSaved = PlanetFixture.newPlanetDto();
		
		BDDMockito.when(swapiRestTemplateMock.getForEntity("/planets/?search={name}", SwPagedDto.class, "Novo"))
			.thenReturn(ResponseEntity.ok(SwApiFixture.searchPlanetByName()));
		
		BDDMockito.when(planetRepositoryMock.save(new PlanetModel(newPlanetToBeSaved)))
			.thenReturn(Mono.just(new PlanetModel(PlanetFixture.savedPlanetDto())));
		
		StepVerifier.create(planetService.save(newPlanetToBeSaved))
			.expectSubscription()
			.expectNext(PlanetFixture.savedPlanetDto())
			.verifyComplete();
	}
	
	@Test
	public void delete_RemovesPlanet_WhenSuccessfull() {
		
		StepVerifier.create(planetService.delete(1l))
			.expectSubscription()
			.verifyComplete();
	}
	
	@Test
	public void delete_ReturnMonoError_WhenEmptyMonoIsReturned() {
		
		StepVerifier.create(planetService.delete(999999l))
			.expectSubscription()
			.expectError(ResponseStatusException.class)
			.verify();
	}
	
}

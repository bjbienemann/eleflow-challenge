package br.com.eleflow.challenge.it;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.web.reactive.server.WebTestClient;

public class PlanetControllerIT extends EleflowChallengeApplicationIT {
	
	@Autowired
    private ApplicationContext applicationContext;
	
//	@Autowired
	private WebTestClient webTestClient;
	
	@BeforeEach
	public void setUp() {
		webTestClient = WebTestClient
				.bindToApplicationContext(applicationContext)
				.configureClient()
				.build();
	}
	
	@Test
	public void findAll_ReturnFluxOfPlanet_WhenSuccessfull() {
		webTestClient
			.get()
			.uri("/planets")
			.exchange()
			.expectStatus().is2xxSuccessful();
	}
}

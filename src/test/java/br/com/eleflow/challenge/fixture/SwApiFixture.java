package br.com.eleflow.challenge.fixture;

import java.util.Arrays;

import br.com.eleflow.challenge.swapi.dto.SwPagedDto;
import br.com.eleflow.challenge.swapi.dto.SwPlanetDto;

public class SwApiFixture {
	
	public static SwPagedDto searchPlanetByName() {
		SwPagedDto swPagedDto = new SwPagedDto();
		swPagedDto.setCount(1l);
		
		SwPlanetDto swPlanetDto = new SwPlanetDto();
		swPlanetDto.setFilms(Arrays.asList("","","","","","",""));
		swPagedDto.setResults(Arrays.asList(swPlanetDto));
		
		return swPagedDto;
	}

}

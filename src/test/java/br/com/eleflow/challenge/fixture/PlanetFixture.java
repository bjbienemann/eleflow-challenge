package br.com.eleflow.challenge.fixture;

import br.com.eleflow.challenge.dto.PlanetDto;
import br.com.eleflow.challenge.model.PlanetModel;

public class PlanetFixture {
	
	public static PlanetModel earthPlanetModel() {
		PlanetModel planetModel = new PlanetModel();
		planetModel.setId(1l);
		planetModel.setName("Terra");
		planetModel.setClimate("Diversos");
		planetModel.setTerrain("Misto");
		planetModel.setFilms(99);
		
		return planetModel;
	}
	
	public static PlanetDto newPlanetDto() {
		PlanetDto planetDto = new PlanetDto();
		planetDto.setName("Novo");
		planetDto.setClimate("desconhecido");
		planetDto.setTerrain("desconhecido");
		
		return planetDto;
	}
	
	public static PlanetDto savedPlanetDto() {
		PlanetDto planetDto = new PlanetDto();
		planetDto.setId(7l);
		planetDto.setName("Novo");
		planetDto.setClimate("desconhecido");
		planetDto.setTerrain("desconhecido");
		planetDto.setFilms(7);
		
		return planetDto;
	}
	
}

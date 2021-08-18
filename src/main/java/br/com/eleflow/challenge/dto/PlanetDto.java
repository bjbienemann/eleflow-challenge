package br.com.eleflow.challenge.dto;

import java.io.Serializable;
import java.util.Objects;

import javax.validation.constraints.NotEmpty;

import br.com.eleflow.challenge.model.PlanetModel;

public class PlanetDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@NotEmpty
	private String name;
	
	@NotEmpty
	private String climate;
	
	@NotEmpty
	private String terrain;
	
	private Integer films;
	
	public PlanetDto() {
		this.films = 0;
	}

	public PlanetDto(PlanetModel model) {
		this.id = model.getId();
		this.name = model.getName();
		this.climate = model.getClimate();
		this.terrain = model.getTerrain();
		this.films = Objects.isNull(model.getFilms()) ? 0 : model.getFilms();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClimate() {
		return climate;
	}

	public void setClimate(String climate) {
		this.climate = climate;
	}

	public String getTerrain() {
		return terrain;
	}

	public void setTerrain(String terrain) {
		this.terrain = terrain;
	}

	public Integer getFilms() {
		return films;
	}

	public void setFilms(Integer films) {
		this.films = films;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		PlanetDto other = (PlanetDto) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}
	
}

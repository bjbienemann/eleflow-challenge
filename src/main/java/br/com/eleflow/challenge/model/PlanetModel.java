package br.com.eleflow.challenge.model;

import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import br.com.eleflow.challenge.dto.PlanetDto;

@Table("planet")
public class PlanetModel {
	
	@Id
	@Column("id")
	private Long id;
	
	@Column("name")
	private String name;
	
	@Column("climate")
	private String climate;
	
	@Column("terrain")
	private String terrain;
	
	@Column("films")
	private Integer films;
	
	public PlanetModel() {
		this.films = 0;
	}

	public PlanetModel(PlanetDto dto) {
		this.id = dto.getId();
		this.name = dto.getName();
		this.climate = dto.getClimate();
		this.terrain = dto.getTerrain();
		this.films = Objects.isNull(dto.getFilms()) ? 0 : dto.getFilms();
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
		PlanetModel other = (PlanetModel) obj;
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

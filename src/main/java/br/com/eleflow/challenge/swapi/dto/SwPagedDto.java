package br.com.eleflow.challenge.swapi.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SwPagedDto implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long count;
	
	private String next;
	
	private String previous;
	
	private List<SwPlanetDto> results = new ArrayList<>();

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public String getNext() {
		return next;
	}

	public void setNext(String next) {
		this.next = next;
	}

	public String getPrevious() {
		return previous;
	}

	public void setPrevious(String previous) {
		this.previous = previous;
	}

	public List<SwPlanetDto> getResults() {
		return results;
	}

	public void setResults(List<SwPlanetDto> results) {
		this.results = results;
	}

}

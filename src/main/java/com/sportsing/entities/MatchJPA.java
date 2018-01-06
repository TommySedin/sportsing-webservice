package com.sportsing.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.sportsing.api.ContenderResult;
import com.sportsing.api.Match;

@Entity
@Table(name="match")
public class MatchJPA {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="matchid")
	private int id;

	@Column
	private String sport;

	@OneToMany(mappedBy="match", cascade=CascadeType.REMOVE)
	private List<ContenderResultJPA> results;

	public MatchJPA() {}
	public MatchJPA(Match dto) {
		this.sport = dto.getSport();
		results = new ArrayList<>();
		for (ContenderResult cr : dto.getContenders()) {
			results.add(new ContenderResultJPA(this, cr));
		}
	}
	public int getId() { return id; }
	public void setId(int id) { this.id = id; }
	public String getSport() { return sport; }
	public void setSport(String sport) { this.sport = sport; }
	public List<ContenderResultJPA> getResults() { return results; }
	public void setResults(List<ContenderResultJPA> results) { this.results = results; }
}

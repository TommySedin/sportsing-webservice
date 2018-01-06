package com.sportsing.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sportsing.api.ContenderResult;

@Entity
@Table(name="contenderresult")
public class ContenderResultJPA {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="contenderid")
	private int id;

	@Column private String name;
	@Column private float score;
	@Column private int place;

	@ManyToOne
	@JoinColumn(name="matchid", referencedColumnName="matchid")
	private MatchJPA match;

	public ContenderResultJPA() {}
	public ContenderResultJPA(MatchJPA match, ContenderResult dto) {
		this.match = match;
		this.name = dto.getName();
		this.score = dto.getScore();
		this.place = dto.getPlace();
	}
	public int getId() { return id; }
	public void setId(int id) { this.id = id; }
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	public float getScore() { return score; }
	public void setScore(float score) { this.score = score; }
	public int getPlace() { return place; }
	public void setPlace(int place) { this.place = place; }

}

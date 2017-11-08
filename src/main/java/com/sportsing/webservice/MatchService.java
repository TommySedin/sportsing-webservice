package com.sportsing.webservice;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sportsing.api.Match;

@Path("matches")
public class MatchService {

	private static String[] sports = {"Håkky", "Handägg", "Fia-med-knuff"};
	private static String[] teams = {"Leifarna", "Bengtarna", "Bossarna"};

	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_XML)
	public List<Match> getMatches() {
		List<Match> result = new ArrayList<Match>();

		for (int i = 0; i < 10; i++) {
			result.add(getMatch());
		}

		return result;
	}
	@GET
	@Path("/single")
	@Produces(MediaType.APPLICATION_XML)
	public Match getMatch() {
		Match match = new Match(sports[(int) (Math.random() * sports.length)]);

		for (int c = 0; c < 2; c++) {
			match.registerContender(teams[(int) (Math.random() * teams.length)], (int) (100 * Math.random()), c+1);
		}

		return match;
	}

	@GET
	@Path("/hello")
	@Produces(MediaType.TEXT_PLAIN)
	public String getHello() {
		return "Hi.";
	}
}

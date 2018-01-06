package com.sportsing.webservice;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;

import com.sportsing.api.Match;
import com.sportsing.entities.ContenderResultEJB;
import com.sportsing.entities.ContenderResultJPA;
import com.sportsing.entities.MatchEJB;
import com.sportsing.entities.MatchJPA;

@RequestScoped
@Path("matches")
public class MatchService {
	@Inject private Logger log;

	@EJB private MatchEJB matchEJB;
	@EJB private ContenderResultEJB contenderEJB;

	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<Match> getMatches() {
		List<Match> result = new ArrayList<Match>();

		for (MatchJPA matchJPA : matchEJB.findAll()) {
			result.add(entToDTO(matchJPA));
		}

		log.info("Returning {} matches.", result.size());
		matchEJB = null;
		return result;
	}

	@GET
	@Produces(MediaType.APPLICATION_XML)
	@Path("contenders")
	public List<String> getContenderNames() {
		List<String> result = new ArrayList<>();
		for (ContenderResultJPA contender : contenderEJB.findAll()) {
			result.add(contender.getName());
		}
		return result;
	}

	@POST
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public Match postMatch(Match match) {
		MatchJPA ent = new MatchJPA(match);
		ent = matchEJB.merge(ent); // Merge returnerar en ny kopia av entityn med nya värden (t.ex. autoincrement IDn)
		return entToDTO(ent);
	}

	@DELETE
	@Path("{id}")
	public void deleteMatch(@PathParam("id") int id) {
		matchEJB.delete(id);
	}

	private Match entToDTO(MatchJPA ent) {
		Match result = new Match(ent.getId(), ent.getSport());
		for (ContenderResultJPA crJPA : ent.getResults()) {
			result.registerContender(crJPA.getName(), crJPA.getScore(), crJPA.getPlace());
		}
		return result;
	}
}

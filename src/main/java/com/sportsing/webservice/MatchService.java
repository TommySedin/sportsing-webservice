package com.sportsing.webservice;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sportsing.api.Match;
import com.sportsing.entities.ContenderResultEJB;
import com.sportsing.entities.ContenderResultJPA;
import com.sportsing.entities.MatchEJB;
import com.sportsing.entities.MatchJPA;

@RequestScoped
@Path("matches")
public class MatchService {

	@EJB private MatchEJB matchEJB;

	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<Match> getMatches() {
		List<Match> result = new ArrayList<Match>();

		for (MatchJPA matchJPA : matchEJB.findAll()) {
			Match matchDTO = new Match(matchJPA.getSport());
			for (ContenderResultJPA crJPA : matchJPA.getResults()) {
				matchDTO.registerContender(crJPA.getName(), crJPA.getScore(), crJPA.getPlace());
			}
			result.add(matchDTO);
		}

		matchEJB = null;
		return result;
	}
}

package com.sportsing.entities;

import java.util.List;

import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.postgresql.jdbc.PgResultSet;

@Stateless
public class MatchEJB {
	@PersistenceContext(name="sportsing")
	private EntityManager em;

	public List<MatchJPA> findAll() {
		TypedQuery<MatchJPA> q = em.createQuery("SELECT m FROM MatchJPA AS m", MatchJPA.class);
		return q.getResultList();
	}
	public MatchJPA findById(int id) {
		TypedQuery<MatchJPA> q = em.createQuery("SELECT m FROM MatchJPA AS m WHERE m.id = :id", MatchJPA.class);
		q.setParameter("id", id);
		return q.getSingleResult();
	}

	public MatchJPA merge(MatchJPA match) {
		return em.merge(match);
	}
	
	public void delete(int id) {
		MatchJPA ent = findById(id);
		em.remove(ent);
	}
}

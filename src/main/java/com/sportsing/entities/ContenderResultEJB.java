package com.sportsing.entities;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

@Stateless
public class ContenderResultEJB {
	@PersistenceContext(name="sportsing")
	EntityManager em;
	
	public List<ContenderResultJPA> findAll() {
		TypedQuery<ContenderResultJPA> q = em.createQuery("SELECT * FROM ContenderResultJPA", ContenderResultJPA.class);
		return q.getResultList();
	}
}

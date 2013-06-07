package org.saraiva.resources;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

import org.saraiva.model.Vitrine;

@Path("/vitrine")
public class VitrineResource {
	private EntityManagerFactory factory = javax.persistence.Persistence.createEntityManagerFactory("jpaentities");
	private EntityManager entityManager = factory.createEntityManager();
	
	@GET
	@Produces({"application/xml", "application/json"})
	public Response read() {
		List<Vitrine> vitrines = null;
		try {
			Query q = entityManager.createQuery("Select v From Vitrine v");
			vitrines = q.getResultList();
		}
		catch(Exception e) {
			return Response.serverError().build();
		}
		if(vitrines == null || vitrines.size() == 0)
			return Response.status(404).build();
		return Response.ok(new GenericEntity<List<Vitrine>>(vitrines) {}).build();
	}
	
	@GET @Path("/{vitrineId}")
	@Produces({"application/xml", "application/json"})
	public Response read(@PathParam("vitrineId") int vitrineId) {
		Vitrine vitrine = null;
		try {	
			vitrine = entityManager.find(Vitrine.class, vitrineId);
		}
		catch(Exception e) {
			return Response.serverError().build();
		}
		return ((vitrine != null) ? Response.ok(vitrine).build() : Response.status(404).build());
	}
}

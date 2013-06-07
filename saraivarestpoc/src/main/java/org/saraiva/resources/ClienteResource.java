package org.saraiva.resources;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.saraiva.model.Cliente;

@Path("/cliente")
public class ClienteResource {
	private EntityManagerFactory factory = javax.persistence.Persistence.createEntityManagerFactory("jpaentities");
	private EntityManager entityManager = factory.createEntityManager();
	
	@GET @Path("/{clienteId}")
	@Produces({"application/xml", "application/json"})
	public Response read(@PathParam("clienteId") int clienteId) {
		Cliente cliente = null;
		try {	
			cliente = entityManager.find(Cliente.class, clienteId);
		}
		catch(Exception e) {
			return Response.serverError().build();
		}
		return ((cliente != null) ? Response.ok(cliente).build() : Response.status(404).build());
	}
}

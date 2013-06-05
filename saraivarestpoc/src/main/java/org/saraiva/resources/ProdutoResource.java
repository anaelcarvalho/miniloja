package org.saraiva.resources;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.saraiva.model.Produto;

@Path("/produto")
public class ProdutoResource {
	private EntityManagerFactory factory = javax.persistence.Persistence.createEntityManagerFactory("jpaentities");
	private EntityManager entityManager = factory.createEntityManager();
	
	@GET
	@Produces({"application/xml", "application/json"})
	public List<Produto> readAll() {
		Query q = entityManager.createQuery("Select p From Produto p");
		List<Produto> produtos = q.getResultList();
		return produtos;
	}
	
	@POST
	@Consumes({"application/xml", "application/json"})
	public void create(Produto produto) {
		entityManager.getTransaction().begin();
		entityManager.persist(produto);
		entityManager.flush();
		entityManager.getTransaction().commit();
	}
	
	@PUT
	@Consumes({"application/xml", "application/json"})
	public void update(Produto produto) {
		entityManager.getTransaction().begin();
		entityManager.merge(produto);
		entityManager.flush();
		entityManager.getTransaction().commit();
	}
	
	@GET @Path("/{prodId}")
	@Produces({"application/xml", "application/json"})
	public Produto read(@PathParam("prodId") int prodId) {
		return entityManager.find(Produto.class, prodId);
	}

	@DELETE @Path("/{prodId}")
	public void delete(@PathParam("prodId") int prodId) {
		Produto produto = entityManager.find(Produto.class, prodId);
		if(produto != null) {
			entityManager.getTransaction().begin();
			entityManager.remove(produto);
			entityManager.flush();
			entityManager.getTransaction().commit();
		}
	}
	
	@Override
	protected void finalize() throws Throwable {
		entityManager.close();
		factory.close();
	}
	
}

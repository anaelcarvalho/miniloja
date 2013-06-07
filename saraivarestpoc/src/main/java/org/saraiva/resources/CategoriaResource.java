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

import org.saraiva.model.Categoria;
import org.saraiva.model.Produto;

@Path("/categoria")
public class CategoriaResource {
	private EntityManagerFactory factory = javax.persistence.Persistence.createEntityManagerFactory("jpaentities");
	private EntityManager entityManager = factory.createEntityManager();
	
	@GET @Path("/{categoriaId}")
	@Produces({"application/xml", "application/json"})
	public Response read(@PathParam("categoriaId") int categoriaId) {
		Categoria categoria = null;
		try {	
			categoria = entityManager.find(Categoria.class, categoriaId);
		}
		catch(Exception e) {
			return Response.serverError().build();
		}
		return ((categoria != null) ? Response.ok(categoria).build() : Response.status(404).build());
	}
	
	@GET @Path("/{categoriaId}/produtos")
	@Produces({"application/xml", "application/json"})
	public Response readProdutos(@PathParam("categoriaId") int categoriaId) {
		List<Produto> produtos = null;
		try {
			Query q = entityManager.createQuery("Select p From Produto p, IN (p.produtocatrels) as pcl where pcl.categoria.categId='"+categoriaId+"'");
			produtos = q.getResultList();
		}
		catch(Exception e) {
			return Response.serverError().build();
		}
		if(produtos == null || produtos.size() == 0)
			return Response.status(404).build();
		return Response.ok(new GenericEntity<List<Produto>>(produtos) {}).build();
	}
}

package org.saraiva.resources;

import java.net.URI;
import java.net.URISyntaxException;
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
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

import org.saraiva.model.Estoque;
import org.saraiva.model.Preco;
import org.saraiva.model.Precolista;
import org.saraiva.model.Produto;

@Path("/produto")
public class ProdutoResource {
	private EntityManagerFactory factory = javax.persistence.Persistence.createEntityManagerFactory("jpaentities");
	private EntityManager entityManager = factory.createEntityManager();
	
	@GET
	@Produces({"application/xml", "application/json"})
	public Response readAll() {
		List<Produto> produtos = null;
		try {
			Query q = entityManager.createQuery("Select p From Produto p");
			produtos = q.getResultList();
		}
		catch(Exception e) {
			return Response.serverError().build();
		}
		if(produtos == null || produtos.size() == 0)
			return Response.status(404).build();
		return Response.ok(new GenericEntity<List<Produto>>(produtos) {}).build();
	}
	
	@POST
	@Consumes({"application/xml", "application/json"})
	public Response create(Produto produto) {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(produto);
			entityManager.flush();
			entityManager.getTransaction().commit();
		}
		catch(Exception e) {
			return Response.serverError().build();
		}
		
		Produto newProduto = entityManager.find(Produto.class, produto.getProdId());
		if(newProduto == null)
			return Response.serverError().build();
		URI newProdutoUri = null;
		try {
			newProdutoUri = new URI(newProduto.getProdId().toString());
		} catch (URISyntaxException e) {
			return Response.status(201).build();
		}
		return (newProdutoUri != null) ? Response.status(201).location(newProdutoUri).build() : Response.status(201).build();
	}
	
	@PUT
	@Consumes({"application/xml", "application/json"})
	public Response update(Produto produto) {
		try {
			if(entityManager.find(Produto.class, produto.getProdId()) == null)
				return Response.status(404).build();
			entityManager.getTransaction().begin();
			entityManager.merge(produto);
			entityManager.flush();
			entityManager.getTransaction().commit();
		}
		catch(Exception e) {
			return Response.serverError().build();
		}
		return Response.ok().build();
	}
	
	@GET @Path("/{prodId}")
	@Produces({"application/xml", "application/json"})
	public Response read(@PathParam("prodId") int prodId) {
		Produto produto = null;
		try {	
			produto = entityManager.find(Produto.class, prodId);
		}
		catch(Exception e) {
			return Response.serverError().build();
		}
		return ((produto != null) ? Response.ok(produto).build() : Response.status(404).build());
	}

	@DELETE @Path("/{prodId}")
	public Response delete(@PathParam("prodId") int prodId) {
		try {
			Produto produto = entityManager.find(Produto.class, prodId);
			if(produto == null)
				return Response.status(404).build();
			entityManager.getTransaction().begin();
			entityManager.remove(produto);
			entityManager.flush();
			entityManager.getTransaction().commit();
		}
		catch(Exception e) {
			return Response.serverError().build();
		}
		return Response.ok().build();
	}
	
	@GET @Path("/{prodId}/precos")
	@Produces({"application/xml", "application/json"})
	public Response readPrecos(@PathParam("prodId") int prodId) {
		List<Preco> precos = null;
		try {
			Query q = entityManager.createQuery("Select p From Preco p where p.produto.prodId='"+prodId+"'");
			precos = q.getResultList();
		}
		catch(Exception e) {
			return Response.serverError().build();
		}
		if(precos == null || precos.size() == 0)
			return Response.status(404).build();
		return Response.ok(new GenericEntity<List<Preco>>(precos) {}).build();
	}
	
	@GET @Path("/{prodId}/precos/{precoId}")
	@Produces({"application/xml", "application/json"})
	public Response readPrecos(@PathParam("prodId") int prodId, @PathParam("precoId") int precoId) {
		List<Preco> precos = null;
		try {
			Query q = entityManager.createQuery("Select p From Preco p where p.produto.prodId='"+prodId+"' and p.precoId='"+precoId+"'");
			precos = q.getResultList();
		}
		catch(Exception e) {
			return Response.serverError().build();
		}
		if(precos == null || precos.size() == 0)
			return Response.status(404).build();
		return Response.ok(new GenericEntity<List<Preco>>(precos) {}).build();
	}
	
	@GET @Path("/{prodId}/precos/listas/{listaId}")
	@Produces({"application/xml", "application/json"})
	public Response readPrecosListas(@PathParam("prodId") int prodId, @PathParam("listaId") int listaId) {
		List<Precolista> precoLista = null;
		try {
			Query q = entityManager.createQuery("Select p From Preco p where p.produto.prodId='"+prodId+"' and p.precolista.precolistaId='"+listaId+"'");
			//Query q = entityManager.createQuery("Select pl From Precolista pl, IN (pl.precos) as p where pl.precolistaId='"+listaId+"' and p.produto.prodId='"+prodId+"'");
			precoLista = q.getResultList();
		}
		catch(Exception e) {
			return Response.serverError().build();
		}
		if(precoLista == null || precoLista.size() == 0)
			return Response.status(404).build();
		return Response.ok(new GenericEntity<List<Precolista>>(precoLista) {}).build();
	}
	
	@GET @Path("/{prodId}/estoque")
	@Produces({"application/xml", "application/json"})
	public Response readEstoque(@PathParam("prodId") int prodId) {
		List<Estoque> estoque = null;
		try {
			Query q = entityManager.createQuery("Select e From Estoque e where e.produto.prodId='"+prodId+"'");
			estoque = q.getResultList();
		}
		catch(Exception e) {
			return Response.serverError().build();
		}
		if(estoque == null || estoque.size() == 0)
			return Response.status(404).build();
		return Response.ok(new GenericEntity<List<Estoque>>(estoque) {}).build();
	}
	
	@Override
	protected void finalize() throws Throwable {
		entityManager.close();
		factory.close();
	}
	
}

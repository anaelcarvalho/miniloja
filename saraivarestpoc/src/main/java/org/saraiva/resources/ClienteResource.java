package org.saraiva.resources;

import java.net.URI;
import java.net.URISyntaxException;

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
import javax.ws.rs.core.Response;

import org.saraiva.model.Cesta;
import org.saraiva.model.Cestaiten;
import org.saraiva.model.Cliente;
import org.saraiva.model.Preco;

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
	
	@POST
	@Consumes({"application/xml", "application/json"})
	public Response create(Cliente cliente) {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(cliente);
			entityManager.flush();
			entityManager.getTransaction().commit();
		}
		catch(Exception e) {
			return Response.serverError().build();
		}
		
		Cliente newCliente = entityManager.find(Cliente.class, cliente.getCliId());
		if(newCliente == null)
			return Response.serverError().build();
		URI newClienteUri = null;
		try {
			newClienteUri = new URI(newCliente.getCliId().toString());
		} catch (URISyntaxException e) {
			return Response.status(201).build();
		}
		return (newClienteUri != null) ? Response.status(201).location(newClienteUri).build() : Response.status(201).build();
	}
	
	@PUT
	@Consumes({"application/xml", "application/json"})
	public Response update(Cliente cliente) {
		try {
			if(entityManager.find(Cliente.class, cliente.getCliId()) == null)
				return Response.status(404).build();
			entityManager.getTransaction().begin();
			entityManager.merge(cliente);
			entityManager.flush();
			entityManager.getTransaction().commit();
		}
		catch(Exception e) {
			return Response.serverError().build();
		}
		return Response.ok().build();
	}
	
	@GET @Path("/{clienteId}/cesta")
	@Produces({"application/xml", "application/json"})
	public Response readCesta(@PathParam("clienteId") int clienteId) {
		Cesta cesta = null;
		try {	
			Query q = entityManager.createQuery("Select c From Cesta c, IN (c.cliente) as cl where c.status = 'A' and cl.cliId='"+clienteId+"'");
			cesta = (Cesta) q.getSingleResult();
		}
		catch(javax.persistence.NoResultException e) {
			return Response.status(404).build();
		}
		catch(Exception e2) {
			return Response.serverError().build();
		}
		return ((cesta != null) ? Response.ok(cesta).build() : Response.status(404).build());
	}
	
	@POST @Path("/{clienteId}/cesta")
	public Response createCesta(@PathParam("clienteId") int clienteId) {
		Cesta cesta = new Cesta();
		try {
			Query q = entityManager.createQuery("Select c From Cesta c, IN (c.cliente) as cl where c.status = 'A' and cl.cliId='"+clienteId+"'");
			if(q.getSingleResult() != null)
				return Response.status(403).build();
		}
		catch(javax.persistence.NoResultException e) {
			try {
				Cliente cliente = entityManager.find(Cliente.class, clienteId);
				if(cliente == null)
					Response.status(403).build();
				cesta.setCliente(cliente);
				cesta.setDescontos(0.0);
				cesta.setFrete(0.0);
				cesta.setStatus("A");
				cesta.setTotal(0.0);
				cesta.setTotalitens(0.0);
				entityManager.getTransaction().begin();
				entityManager.persist(cesta);
				entityManager.flush();
				entityManager.getTransaction().commit();
			}
			catch(Exception e1) {
				return Response.serverError().build();
			}
		}
		catch(Exception e2) {
			return Response.serverError().build();
		}
		
		Cesta newCesta = entityManager.find(Cesta.class, cesta.getCestaId());
		if(newCesta == null)
			return Response.serverError().build();
		URI newCestaUri = null;
		try {
			newCestaUri = new URI(newCesta.getCestaId().toString());
		} catch (URISyntaxException e) {
			return Response.status(201).build();
		}
		return (newCestaUri != null) ? Response.status(201).location(newCestaUri).build() : Response.status(201).build();
	}
	
	@GET @Path("/{clienteId}/cesta/itens/{itemId}")
	@Produces({"application/xml", "application/json"})
	public Response readItem(@PathParam("clienteId") int clienteId, @PathParam("itemId") int itemId) {
		try {
			Cesta cesta = null;
			Query q = entityManager.createQuery("Select c From Cesta c, IN (c.cliente) as cl where c.status = 'A' and cl.cliId='"+clienteId+"'");
			cesta = (Cesta) q.getSingleResult();
			if(cesta == null || cesta.getCestaitens() == null || cesta.getCestaitens().size() == 0)
				return Response.status(404).build();
			for(Cestaiten item : cesta.getCestaitens()) {
				if(item.getItemId() == itemId) {
					return Response.ok(item).build();
				}
			}
		}
		catch(javax.persistence.NoResultException e) {
			return Response.status(404).build();
		}
		catch(Exception e) {
			return Response.serverError().build();
		}
		return Response.status(404).build();
	}
	
	@POST  @Path("/{clienteId}/cesta/itens")
	@Consumes({"application/xml", "application/json"})
	public Response createItem(Cestaiten item, @PathParam("clienteId") int clienteId) {
		try {
			Cesta cesta = null;
			Query q = entityManager.createQuery("Select c From Cesta c, IN (c.cliente) as cl where c.status = 'A' and cl.cliId='"+clienteId+"'");
			cesta = (Cesta) q.getSingleResult();

			if(item != null && item.getQuantidade() != 0) {
				Preco preco = null;
				q = entityManager.createQuery("Select p From Preco p, IN (p.produto) as pr where p.precoId='"+item.getPreco().getPrecoId()+"' and pr.prodId ='"+item.getProduto().getProdId()+"'");
				preco = (Preco) q.getSingleResult();
				cesta.setTotalitens(cesta.getTotalitens() + (preco.getPreco() * item.getQuantidade()));
				cesta.setTotal(cesta.getTotalitens());
				item.setPreco(preco);
				item.setCesta(cesta);
				item.setDesconto(0.0);
				item.setPrecototal(preco.getPreco() * item.getQuantidade());
				entityManager.getTransaction().begin();
				entityManager.persist(item);
				entityManager.persist(cesta);
				entityManager.flush();
				entityManager.getTransaction().commit();
				
				Cestaiten newItem = entityManager.find(Cestaiten.class, item.getItemId());
				if(newItem == null)
					return Response.serverError().build();
				URI newItemUri = null;
				try {
					newItemUri = new URI(newItem.getItemId().toString());
				} catch (URISyntaxException e) {
					return Response.status(201).build();
				}
				return Response.status(201).location(newItemUri).build();
			}
			else {
				Response.status(403).build();
			}
		}
		catch(javax.persistence.NoResultException e) {
			return Response.status(404).build();
		}
		catch(Exception e) {
			return Response.serverError().build();
		}
		return Response.status(404).build();
	}
	
	@DELETE @Path("/{clienteId}/cesta/itens/{itemId}")
	public Response deleteItem(@PathParam("clienteId") int clienteId, @PathParam("itemId") int itemId) {
		try {
			Cesta cesta = null;
			Query q = entityManager.createQuery("Select c From Cesta c, IN (c.cliente) as cl where c.status = 'A' and cl.cliId='"+clienteId+"'");
			cesta = (Cesta) q.getSingleResult();
			if(cesta == null || cesta.getCestaitens() == null || cesta.getCestaitens().size() == 0)
				return Response.status(404).build();
			for(Cestaiten item : cesta.getCestaitens()) {
				if(item.getItemId() == itemId) {
					cesta.setTotalitens(cesta.getTotalitens() - item.getPrecototal());
					cesta.setTotal(cesta.getTotalitens());
					entityManager.getTransaction().begin();
					entityManager.remove(item);
					entityManager.persist(cesta);
					entityManager.flush();
					entityManager.getTransaction().commit();
					return Response.ok().build();
				}
			}
		}
		catch(javax.persistence.NoResultException e) {
			return Response.status(404).build();
		}
		catch(Exception e) {
			return Response.serverError().build();
		}
		return Response.status(404).build();
	}
}

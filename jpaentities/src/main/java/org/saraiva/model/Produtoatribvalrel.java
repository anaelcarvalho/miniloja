package org.saraiva.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.eclipse.persistence.oxm.annotations.XmlInverseReference;


/**
 * The persistent class for the produtoatribvalrel database table.
 * 
 */
@XmlRootElement
@Entity
public class Produtoatribvalrel implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ProdutoatribvalrelPK id;

	private String comentarios;

	//bi-directional many-to-one association to Atributo
	@ManyToOne
	@JoinColumn(name="atrib_id", insertable=false, updatable=false)
	private Atributo atributo;

	//bi-directional many-to-one association to Atributovalor
	@ManyToOne
	@JoinColumn(name="atribval_id", insertable=false, updatable=false)
	private Atributovalor atributovalor;

	//bi-directional many-to-one association to Produto
	@ManyToOne
	@JoinColumn(name="prod_id", insertable=false, updatable=false)
	@XmlInverseReference(mappedBy="produtoatribvalrels")
	private Produto produto;

	public Produtoatribvalrel() {
	}

	@XmlTransient
	public ProdutoatribvalrelPK getId() {
		return this.id;
	}

	public void setId(ProdutoatribvalrelPK id) {
		this.id = id;
	}

	public String getComentarios() {
		return this.comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	public Atributo getAtributo() {
		return this.atributo;
	}

	public void setAtributo(Atributo atributo) {
		this.atributo = atributo;
	}

	public Atributovalor getAtributovalor() {
		return this.atributovalor;
	}

	public void setAtributovalor(Atributovalor atributovalor) {
		this.atributovalor = atributovalor;
	}

	public Produto getProduto() {
		return this.produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

}
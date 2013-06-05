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
 * The persistent class for the produtocatrel database table.
 * 
 */
@XmlRootElement
@Entity
public class Produtocatrel implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ProdutocatrelPK id;

	private String comentarios;

	//bi-directional many-to-one association to Categoria
	@ManyToOne
	@JoinColumn(name="categ_id", insertable=false, updatable=false)
	private Categoria categoria;

	//bi-directional many-to-one association to Produto
	@ManyToOne
	@JoinColumn(name="prod_id", insertable=false, updatable=false)
	private Produto produto;

	public Produtocatrel() {
	}

	@XmlTransient
	public ProdutocatrelPK getId() {
		return this.id;
	}

	public void setId(ProdutocatrelPK id) {
		this.id = id;
	}

	public String getComentarios() {
		return this.comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	public Categoria getCategoria() {
		return this.categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	@XmlInverseReference(mappedBy="produtocatrels")
	public Produto getProduto() {
		return this.produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

}
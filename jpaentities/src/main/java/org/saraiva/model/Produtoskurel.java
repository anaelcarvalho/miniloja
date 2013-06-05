package org.saraiva.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.eclipse.persistence.oxm.annotations.XmlInverseReference;


/**
 * The persistent class for the produtoskurel database table.
 * 
 */
@XmlRootElement(name="skus")
@Entity
public class Produtoskurel implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ProdutoskurelPK id;

	private String comentarios;

	//bi-directional many-to-one association to Produto
	@ManyToOne
	@JoinColumn(name="prod_id", insertable=false, updatable=false)
	private Produto produto1;

	//bi-directional one-to-one association to Produto
	@OneToOne
	@JoinColumn(name="sku_id", insertable=false, updatable=false)
	private Produto produto2;

	public Produtoskurel() {
	}

	@XmlTransient
	public ProdutoskurelPK getId() {
		return this.id;
	}

	public void setId(ProdutoskurelPK id) {
		this.id = id;
	}

	public String getComentarios() {
		return this.comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	@XmlInverseReference(mappedBy="produtoskurels")
	public Produto getProduto1() {
		return this.produto1;
	}

	public void setProduto1(Produto produto1) {
		this.produto1 = produto1;
	}

	@XmlElement(name="sku")
	public Produto getProduto2() {
		return this.produto2;
	}

	public void setProduto2(Produto produto2) {
		this.produto2 = produto2;
	}

}
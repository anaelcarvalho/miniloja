package org.saraiva.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.eclipse.persistence.oxm.annotations.XmlInverseReference;


/**
 * The persistent class for the atributovalor database table.
 * 
 */
@XmlRootElement
@Entity
public class Atributovalor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ATRIBUTOVALOR_ATRIBVALID_GENERATOR", sequenceName="ATRIBUTOVALOR_ATRIBVAL_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ATRIBUTOVALOR_ATRIBVALID_GENERATOR")
	@Column(name="atribval_id")
	private Integer atribvalId;

	private String valor;

	//bi-directional many-to-one association to Atributo.
	@ManyToOne
	@JoinColumn(name="atrib_id")
	private Atributo atributo;

	//bi-directional many-to-one association to Produtoatribvalrel
	@OneToMany(mappedBy="atributovalor")
	@XmlInverseReference(mappedBy="atributovalor")
	private List<Produtoatribvalrel> produtoatribvalrels;

	public Atributovalor() {
	}

	public Integer getAtribvalId() {
		return this.atribvalId;
	}

	public void setAtribvalId(Integer atribvalId) {
		this.atribvalId = atribvalId;
	}

	public String getValor() {
		return this.valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	@XmlTransient
	public Atributo getAtributo() {
		return this.atributo;
	}

	public void setAtributo(Atributo atributo) {
		this.atributo = atributo;
	}

	public List<Produtoatribvalrel> getProdutoatribvalrels() {
		return this.produtoatribvalrels;
	}

	public void setProdutoatribvalrels(List<Produtoatribvalrel> produtoatribvalrels) {
		this.produtoatribvalrels = produtoatribvalrels;
	}

	public Produtoatribvalrel addProdutoatribvalrel(Produtoatribvalrel produtoatribvalrel) {
		getProdutoatribvalrels().add(produtoatribvalrel);
		produtoatribvalrel.setAtributovalor(this);

		return produtoatribvalrel;
	}

	public Produtoatribvalrel removeProdutoatribvalrel(Produtoatribvalrel produtoatribvalrel) {
		getProdutoatribvalrels().remove(produtoatribvalrel);
		produtoatribvalrel.setAtributovalor(null);

		return produtoatribvalrel;
	}

}
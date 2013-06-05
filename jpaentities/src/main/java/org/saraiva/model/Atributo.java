package org.saraiva.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import org.eclipse.persistence.oxm.annotations.XmlInverseReference;

import java.util.List;


/**
 * The persistent class for the atributos database table
 * 
 */
@XmlRootElement
@Entity
@Table(name="atributos")
public class Atributo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ATRIBUTOS_ATRIBID_GENERATOR", sequenceName="ATRIBUTOS_ATRIB_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ATRIBUTOS_ATRIBID_GENERATOR")
	@Column(name="atrib_id")
	private Integer atribId;

	private String nome;

	private Integer uso;

	//bi-directional many-to-one association to Atributovalor
	@OneToMany(mappedBy="atributo")
	@XmlInverseReference(mappedBy="atributo")
	private List<Atributovalor> atributovalors;

	//bi-directional many-to-one association to Produtoatribvalrel
	@OneToMany(mappedBy="atributo")
	@XmlInverseReference(mappedBy="atributo")
	private List<Produtoatribvalrel> produtoatribvalrels;

	public Atributo() {
	}

	public Integer getAtribId() {
		return this.atribId;
	}

	public void setAtribId(Integer atribId) {
		this.atribId = atribId;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getUso() {
		return this.uso;
	}

	public void setUso(Integer uso) {
		this.uso = uso;
	}

	public List<Atributovalor> getAtributovalors() {
		return this.atributovalors;
	}

	public void setAtributovalors(List<Atributovalor> atributovalors) {
		this.atributovalors = atributovalors;
	}

	public Atributovalor addAtributovalor(Atributovalor atributovalor) {
		getAtributovalors().add(atributovalor);
		atributovalor.setAtributo(this);

		return atributovalor;
	}

	public Atributovalor removeAtributovalor(Atributovalor atributovalor) {
		getAtributovalors().remove(atributovalor);
		atributovalor.setAtributo(null);

		return atributovalor;
	}

	public List<Produtoatribvalrel> getProdutoatribvalrels() {
		return this.produtoatribvalrels;
	}

	public void setProdutoatribvalrels(List<Produtoatribvalrel> produtoatribvalrels) {
		this.produtoatribvalrels = produtoatribvalrels;
	}

	public Produtoatribvalrel addProdutoatribvalrel(Produtoatribvalrel produtoatribvalrel) {
		getProdutoatribvalrels().add(produtoatribvalrel);
		produtoatribvalrel.setAtributo(this);

		return produtoatribvalrel;
	}

	public Produtoatribvalrel removeProdutoatribvalrel(Produtoatribvalrel produtoatribvalrel) {
		getProdutoatribvalrels().remove(produtoatribvalrel);
		produtoatribvalrel.setAtributo(null);

		return produtoatribvalrel;
	}

}
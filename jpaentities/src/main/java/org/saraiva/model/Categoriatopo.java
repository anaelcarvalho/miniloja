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
 * The persistent class for the categoriatopo database table.
 * 
 */
@XmlRootElement
@Entity
public class Categoriatopo implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CategoriatopoPK id;

	private String comentarios;

	//bi-directional many-to-one association to Categoria
	@ManyToOne
	@JoinColumn(name="categ_id", insertable=false, updatable=false)
	private Categoria categoria;

	//bi-directional many-to-one association to Vitrine
	@ManyToOne
	@JoinColumn(name="vitrine_id", insertable=false, updatable=false)
	private Vitrine vitrine;

	public Categoriatopo() {
	}

	@XmlTransient
	public CategoriatopoPK getId() {
		return this.id;
	}

	public void setId(CategoriatopoPK id) {
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

	@XmlInverseReference(mappedBy="categoriatopos")
	public Vitrine getVitrine() {
		return this.vitrine;
	}

	public void setVitrine(Vitrine vitrine) {
		this.vitrine = vitrine;
	}

}
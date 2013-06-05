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
 * The persistent class for the categoriarel database table.
 * 
 */
@XmlRootElement
@Entity
public class Categoriarel implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CategoriarelPK id;

	private String comentarios;

	//bi-directional many-to-one association to Categoria
	@ManyToOne
	@JoinColumn(name="categ_id_pai", insertable=false, updatable=false)
	private Categoria categoria1;

	//bi-directional one-to-one association to Categoria
	@OneToOne
	@JoinColumn(name="categ_id_filha", insertable=false, updatable=false)
	private Categoria categoria2;

	public Categoriarel() {
	}

	@XmlTransient
	public CategoriarelPK getId() {
		return this.id;
	}

	public void setId(CategoriarelPK id) {
		this.id = id;
	}

	public String getComentarios() {
		return this.comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	@XmlElement(name="categoria")
	public Categoria getCategoria1() {
		return this.categoria1;
	}

	public void setCategoria1(Categoria categoria1) {
		this.categoria1 = categoria1;
	}

	@XmlInverseReference(mappedBy="categoriarel")
	public Categoria getCategoria2() {
		return this.categoria2;
	}

	public void setCategoria2(Categoria categoria2) {
		this.categoria2 = categoria2;
	}

}
package org.saraiva.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import org.eclipse.persistence.oxm.annotations.XmlInverseReference;


/**
 * The persistent class for the lojavitrinerel database table.
 * 
 */
@XmlRootElement
@Entity
public class Lojavitrinerel implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private LojavitrinerelPK id;

	private String comentarios;

	//bi-directional many-to-one association to Vitrine
	@ManyToOne
	@JoinColumn(name="vitrine_id", insertable=false, updatable=false)
	@XmlInverseReference(mappedBy="lojavitrinerels")
	private Vitrine vitrine;

	//bi-directional one-to-one association to Loja
	@OneToOne
	@JoinColumn(name="loja_id", insertable=false, updatable=false)
	private Loja loja;

	public Lojavitrinerel() {
	}

	public LojavitrinerelPK getId() {
		return this.id;
	}

	public void setId(LojavitrinerelPK id) {
		this.id = id;
	}

	public String getComentarios() {
		return this.comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	public Vitrine getVitrine() {
		return this.vitrine;
	}

	public void setVitrine(Vitrine vitrine) {
		this.vitrine = vitrine;
	}

	public Loja getLoja() {
		return this.loja;
	}

	public void setLoja(Loja loja) {
		this.loja = loja;
	}

}
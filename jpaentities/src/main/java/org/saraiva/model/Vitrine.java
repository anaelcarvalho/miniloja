package org.saraiva.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * The persistent class for the vitrine database table.
 * 
 */
@XmlRootElement
@Entity
public class Vitrine implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="VITRINE_VITRINEID_GENERATOR", sequenceName="VITRINE_VITRINE_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="VITRINE_VITRINEID_GENERATOR")
	@Column(name="vitrine_id")
	private Integer vitrineId;

	private String descricao;

	private String nome;

	//bi-directional many-to-one association to Categoriatopo
	@OneToMany(mappedBy="vitrine")
	private List<Categoriatopo> categoriatopos;

	//bi-directional many-to-one association to Lojavitrinerel
	@OneToMany(mappedBy="vitrine")
	private List<Lojavitrinerel> lojavitrinerels;

	public Vitrine() {
	}

	public Integer getVitrineId() {
		return this.vitrineId;
	}

	public void setVitrineId(Integer vitrineId) {
		this.vitrineId = vitrineId;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Categoriatopo> getCategoriatopos() {
		return this.categoriatopos;
	}

	public void setCategoriatopos(List<Categoriatopo> categoriatopos) {
		this.categoriatopos = categoriatopos;
	}

	public Categoriatopo addCategoriatopo(Categoriatopo categoriatopo) {
		getCategoriatopos().add(categoriatopo);
		categoriatopo.setVitrine(this);

		return categoriatopo;
	}

	public Categoriatopo removeCategoriatopo(Categoriatopo categoriatopo) {
		getCategoriatopos().remove(categoriatopo);
		categoriatopo.setVitrine(null);

		return categoriatopo;
	}

	@XmlElement(name="lojas")
	public List<Lojavitrinerel> getLojavitrinerels() {
		return this.lojavitrinerels;
	}

	public void setLojavitrinerels(List<Lojavitrinerel> lojavitrinerels) {
		this.lojavitrinerels = lojavitrinerels;
	}

	public Lojavitrinerel addLojavitrinerel(Lojavitrinerel lojavitrinerel) {
		getLojavitrinerels().add(lojavitrinerel);
		lojavitrinerel.setVitrine(this);

		return lojavitrinerel;
	}

	public Lojavitrinerel removeLojavitrinerel(Lojavitrinerel lojavitrinerel) {
		getLojavitrinerels().remove(lojavitrinerel);
		lojavitrinerel.setVitrine(null);

		return lojavitrinerel;
	}

}
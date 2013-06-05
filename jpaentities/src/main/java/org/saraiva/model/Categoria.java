package org.saraiva.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.eclipse.persistence.oxm.annotations.XmlInverseReference;


/**
 * The persistent class for the categorias database table.
 * 
 */
@XmlRootElement
@Entity
@Table(name="categorias")
public class Categoria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CATEGORIAS_CATEGID_GENERATOR", sequenceName="CATEGORIAS_CATEG_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CATEGORIAS_CATEGID_GENERATOR")
	@Column(name="categ_id")
	private Integer categId;

	private String descricao;

	private String nome;

	//bi-directional many-to-one association to Categoriarel
	@OneToMany(mappedBy="categoria1")
	private List<Categoriarel> categoriarels;

	//bi-directional many-to-one association to Categoriatopo
	@OneToMany(mappedBy="categoria")
	private List<Categoriatopo> categoriatopos;

	//bi-directional many-to-one association to Produtocatrel
	@OneToMany(mappedBy="categoria")
	private List<Produtocatrel> produtocatrels;

	//bi-directional one-to-one association to Categoriarel
	@OneToOne(mappedBy="categoria2")
	private Categoriarel categoriarel;

	public Categoria() {
	}

	public Integer getCategId() {
		return this.categId;
	}

	public void setCategId(Integer categId) {
		this.categId = categId;
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

	@XmlInverseReference(mappedBy="categoria1")
	public List<Categoriarel> getCategoriarels() {
		return this.categoriarels;
	}

	public void setCategoriarels(List<Categoriarel> categoriarels) {
		this.categoriarels = categoriarels;
	}

	public Categoriarel addCategoriarel(Categoriarel categoriarel) {
		getCategoriarels().add(categoriarel);
		categoriarel.setCategoria1(this);

		return categoriarel;
	}

	public Categoriarel removeCategoriarel(Categoriarel categoriarel) {
		getCategoriarels().remove(categoriarel);
		categoriarel.setCategoria1(null);

		return categoriarel;
	}

	@XmlInverseReference(mappedBy="categoria")
	public List<Categoriatopo> getCategoriatopos() {
		return this.categoriatopos;
	}

	public void setCategoriatopos(List<Categoriatopo> categoriatopos) {
		this.categoriatopos = categoriatopos;
	}

	public Categoriatopo addCategoriatopo(Categoriatopo categoriatopo) {
		getCategoriatopos().add(categoriatopo);
		categoriatopo.setCategoria(this);

		return categoriatopo;
	}

	public Categoriatopo removeCategoriatopo(Categoriatopo categoriatopo) {
		getCategoriatopos().remove(categoriatopo);
		categoriatopo.setCategoria(null);

		return categoriatopo;
	}

	@XmlInverseReference(mappedBy="categoria")
	public List<Produtocatrel> getProdutocatrels() {
		return this.produtocatrels;
	}

	public void setProdutocatrels(List<Produtocatrel> produtocatrels) {
		this.produtocatrels = produtocatrels;
	}

	public Produtocatrel addProdutocatrel(Produtocatrel produtocatrel) {
		getProdutocatrels().add(produtocatrel);
		produtocatrel.setCategoria(this);

		return produtocatrel;
	}

	public Produtocatrel removeProdutocatrel(Produtocatrel produtocatrel) {
		getProdutocatrels().remove(produtocatrel);
		produtocatrel.setCategoria(null);

		return produtocatrel;
	}

	@XmlElement(name="categoriapai")
	public Categoriarel getCategoriarel() {
		return this.categoriarel;
	}

	public void setCategoriarel(Categoriarel categoriarel) {
		this.categoriarel = categoriarel;
	}

}
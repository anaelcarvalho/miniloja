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
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.eclipse.persistence.oxm.annotations.XmlInverseReference;


/**
 * The persistent class for the produtos database table. 
 * 
 */
@XmlRootElement
@Entity
@Table(name="produtos")
public class Produto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PRODUTOS_PRODID_GENERATOR", sequenceName="PRODUTOS_PROD_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PRODUTOS_PRODID_GENERATOR")
	@Column(name="prod_id")
	private Integer prodId;

	private String descricao;

	private String nome;

	private Boolean sku;

	private Integer tipo;

	//bi-directional many-to-one association to Cestaiten
	@OneToMany(mappedBy="produto")
	private List<Cestaiten> cestaitens;

	//bi-directional many-to-one association to Estoque
	@OneToMany(mappedBy="produto")
	private List<Estoque> estoques;

	//bi-directional many-to-one association to Preco
	@OneToMany(mappedBy="produto")
	private List<Preco> precos;

	//bi-directional many-to-one association to Produtoatribvalrel
	@OneToMany(mappedBy="produto")
	private List<Produtoatribvalrel> produtoatribvalrels;

	//bi-directional many-to-one association to Produtocatrel
	@OneToMany(mappedBy="produto")
	private List<Produtocatrel> produtocatrels;

	//bi-directional many-to-one association to Produtoimg
	@OneToMany(mappedBy="produto")
	private List<Produtoimg> produtoimgs;

	//bi-directional many-to-one association to Produtoskurel
	@OneToMany(mappedBy="produto1")
	private List<Produtoskurel> produtoskurels;

	//bi-directional one-to-one association to Produtoskurel
	@OneToOne(mappedBy="produto2")
	private Produtoskurel produtoskurel;

	public Produto() {
	}

	public Integer getProdId() {
		return this.prodId;
	}

	public void setProdId(Integer prodId) {
		this.prodId = prodId;
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

	public Boolean getSku() {
		return this.sku;
	}

	public void setSku(Boolean sku) {
		this.sku = sku;
	}

	public Integer getTipo() {
		return this.tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public List<Cestaiten> getCestaitens() {
		return this.cestaitens;
	}

	public void setCestaitens(List<Cestaiten> cestaitens) {
		this.cestaitens = cestaitens;
	}

	public Cestaiten addCestaiten(Cestaiten cestaiten) {
		getCestaitens().add(cestaiten);
		cestaiten.setProduto(this);

		return cestaiten;
	}

	public Cestaiten removeCestaiten(Cestaiten cestaiten) {
		getCestaitens().remove(cestaiten);
		cestaiten.setProduto(null);

		return cestaiten;
	}

	@XmlElementWrapper(name="estoques")
	@XmlElement(name="estoque")
	public List<Estoque> getEstoques() {
		return this.estoques;
	}

	public void setEstoques(List<Estoque> estoques) {
		this.estoques = estoques;
	}

	public Estoque addEstoque(Estoque estoque) {
		getEstoques().add(estoque);
		estoque.setProduto(this);

		return estoque;
	}

	public Estoque removeEstoque(Estoque estoque) {
		getEstoques().remove(estoque);
		estoque.setProduto(null);

		return estoque;
	}

	@XmlElementWrapper(name="precos")
	@XmlElement(name="preco")
	public List<Preco> getPrecos() {
		return this.precos;
	}

	public void setPrecos(List<Preco> precos) {
		this.precos = precos;
	}

	public Preco addPreco(Preco preco) {
		getPrecos().add(preco);
		preco.setProduto(this);

		return preco;
	}

	public Preco removePreco(Preco preco) {
		getPrecos().remove(preco);
		preco.setProduto(null);

		return preco;
	}

	@XmlElementWrapper(name="atributos")
	@XmlElement(name="atributo")
	public List<Produtoatribvalrel> getProdutoatribvalrels() {
		return this.produtoatribvalrels;
	}

	public void setProdutoatribvalrels(List<Produtoatribvalrel> produtoatribvalrels) {
		this.produtoatribvalrels = produtoatribvalrels;
	}

	public Produtoatribvalrel addProdutoatribvalrel(Produtoatribvalrel produtoatribvalrel) {
		getProdutoatribvalrels().add(produtoatribvalrel);
		produtoatribvalrel.setProduto(this);

		return produtoatribvalrel;
	}

	public Produtoatribvalrel removeProdutoatribvalrel(Produtoatribvalrel produtoatribvalrel) {
		getProdutoatribvalrels().remove(produtoatribvalrel);
		produtoatribvalrel.setProduto(null);

		return produtoatribvalrel;
	}

	@XmlElementWrapper(name="categorias")
	@XmlElement(name="categoria")
	public List<Produtocatrel> getProdutocatrels() {
		return this.produtocatrels;
	}

	public void setProdutocatrels(List<Produtocatrel> produtocatrels) {
		this.produtocatrels = produtocatrels;
	}

	public Produtocatrel addProdutocatrel(Produtocatrel produtocatrel) {
		getProdutocatrels().add(produtocatrel);
		produtocatrel.setProduto(this);

		return produtocatrel;
	}

	public Produtocatrel removeProdutocatrel(Produtocatrel produtocatrel) {
		getProdutocatrels().remove(produtocatrel);
		produtocatrel.setProduto(null);

		return produtocatrel;
	}

	@XmlElementWrapper(name="imagens")
	@XmlElement(name="imagem")
	public List<Produtoimg> getProdutoimgs() {
		return this.produtoimgs;
	}

	public void setProdutoimgs(List<Produtoimg> produtoimgs) {
		this.produtoimgs = produtoimgs;
	}

	public Produtoimg addProdutoimg(Produtoimg produtoimg) {
		getProdutoimgs().add(produtoimg);
		produtoimg.setProduto(this);

		return produtoimg;
	}

	public Produtoimg removeProdutoimg(Produtoimg produtoimg) {
		getProdutoimgs().remove(produtoimg);
		produtoimg.setProduto(null);

		return produtoimg;
	}

	@XmlElementWrapper(name="skus")
	@XmlElement(name="sku")
	public List<Produtoskurel> getProdutoskurels() {
		return this.produtoskurels;
	}

	public void setProdutoskurels(List<Produtoskurel> produtoskurels) {
		this.produtoskurels = produtoskurels;
	}

	public Produtoskurel addProdutoskurel(Produtoskurel produtoskurel) {
		getProdutoskurels().add(produtoskurel);
		produtoskurel.setProduto1(this);

		return produtoskurel;
	}

	public Produtoskurel removeProdutoskurel(Produtoskurel produtoskurel) {
		getProdutoskurels().remove(produtoskurel);
		produtoskurel.setProduto1(null);

		return produtoskurel;
	}

	@XmlInverseReference(mappedBy="produto2")
	public Produtoskurel getProdutoskurel() {
		return this.produtoskurel;
	}

	public void setProdutoskurel(Produtoskurel produtoskurel) {
		this.produtoskurel = produtoskurel;
	}

}
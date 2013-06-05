package org.saraiva.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import org.eclipse.persistence.oxm.annotations.XmlInverseReference;

import java.util.List;


/**
 * The persistent class for the precos database table.
 * 
 */
@XmlRootElement
@Entity
@Table(name="precos")
public class Preco implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PRECOS_PRECOID_GENERATOR", sequenceName="PRECOS_PRECO_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PRECOS_PRECOID_GENERATOR")
	@Column(name="preco_id")
	private Integer precoId;

	private double preco;

	//bi-directional many-to-one association to Cestaiten
	@OneToMany(mappedBy="preco")
	private List<Cestaiten> cestaitens;

	//bi-directional many-to-one association to Precolista
	@ManyToOne
	@JoinColumn(name="precolista_id")
	private Precolista precolista;

	//bi-directional many-to-one association to Produto
	@ManyToOne
	@JoinColumn(name="prod_id")
	@XmlInverseReference(mappedBy="precos")
	private Produto produto;

	public Preco() {
	}

	public Integer getPrecoId() {
		return this.precoId;
	}

	public void setPrecoId(Integer precoId) {
		this.precoId = precoId;
	}

	public double getPreco() {
		return this.preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public List<Cestaiten> getCestaitens() {
		return this.cestaitens;
	}

	public void setCestaitens(List<Cestaiten> cestaitens) {
		this.cestaitens = cestaitens;
	}

	public Cestaiten addCestaiten(Cestaiten cestaiten) {
		getCestaitens().add(cestaiten);
		cestaiten.setPreco(this);

		return cestaiten;
	}

	public Cestaiten removeCestaiten(Cestaiten cestaiten) {
		getCestaitens().remove(cestaiten);
		cestaiten.setPreco(null);

		return cestaiten;
	}

	public Precolista getPrecolista() {
		return this.precolista;
	}

	public void setPrecolista(Precolista precolista) {
		this.precolista = precolista;
	}

	public Produto getProduto() {
		return this.produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

}
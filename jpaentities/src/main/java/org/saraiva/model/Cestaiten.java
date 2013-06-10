package org.saraiva.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import org.eclipse.persistence.oxm.annotations.XmlInverseReference;


/**
 * The persistent class for the cestaitens database table.
 * 
 */
@XmlRootElement
@Entity
@Table(name="cestaitens")
public class Cestaiten implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CESTAITENS_ITEMID_GENERATOR", sequenceName="CESTAITENS_ITEM_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CESTAITENS_ITEMID_GENERATOR")
	@Column(name="item_id")
	private Integer itemId;

	private double desconto;

	private double precototal;

	private Integer quantidade;

	//bi-directional many-to-one association to Cesta
	@ManyToOne
	@JoinColumn(name="cesta_id")
	private Cesta cesta;

	//bi-directional many-to-one association to Preco
	@ManyToOne
	@JoinColumn(name="preco_id")
	private Preco preco;

	//bi-directional many-to-one association to Produto
	@ManyToOne
	@JoinColumn(name="prod_id")
	private Produto produto;

	public Cestaiten() {
	}

	public Integer getItemId() {
		return this.itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public double getDesconto() {
		return this.desconto;
	}

	public void setDesconto(double desconto) {
		this.desconto = desconto;
	}

	public Double getPrecototal() {
		return this.precototal;
	}

	public void setPrecototal(Double precototal) {
		this.precototal = precototal;
	}

	public Integer getQuantidade() {
		return this.quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	@XmlInverseReference(mappedBy="cestaitens")
	public Cesta getCesta() {
		return this.cesta;
	}

	public void setCesta(Cesta cesta) {
		this.cesta = cesta;
	}

	public Preco getPreco() {
		return this.preco;
	}

	public void setPreco(Preco preco) {
		this.preco = preco;
	}

	public Produto getProduto() {
		return this.produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

}
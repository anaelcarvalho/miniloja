package org.saraiva.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import org.eclipse.persistence.oxm.annotations.XmlInverseReference;


/**
 * The persistent class for the estoque database table.
 * 
 */
@XmlRootElement
@Entity
public class Estoque implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ESTOQUE_ESTOQUEID_GENERATOR", sequenceName="ESTOQUE_ESTOQUE_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ESTOQUE_ESTOQUEID_GENERATOR")
	@Column(name="estoque_id")
	private Integer estoqueId;

	private Long quantidade;

	//bi-directional many-to-one association to Loja
	@ManyToOne
	@JoinColumn(name="loja_id")
	private Loja loja;

	//bi-directional many-to-one association to Produto
	@ManyToOne
	@JoinColumn(name="prod_id")
	@XmlInverseReference(mappedBy="estoques")
	private Produto produto;

	public Estoque() {
	}

	public Integer getEstoqueId() {
		return this.estoqueId;
	}

	public void setEstoqueId(Integer estoqueId) {
		this.estoqueId = estoqueId;
	}

	public Long getQuantidade() {
		return this.quantidade;
	}

	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}

	public Loja getLoja() {
		return this.loja;
	}

	public void setLoja(Loja loja) {
		this.loja = loja;
	}

	public Produto getProduto() {
		return this.produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

}
package org.saraiva.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import org.eclipse.persistence.oxm.annotations.XmlInverseReference;

import java.util.List;


/**
 * The persistent class for the precolistas database table.
 * 
 */
@XmlRootElement
@Entity
@Table(name="precolistas")
public class Precolista implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PRECOLISTAS_PRECOLISTAID_GENERATOR", sequenceName="PRECOLISTAS_PRECOLISTA_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PRECOLISTAS_PRECOLISTAID_GENERATOR")
	@Column(name="precolista_id")
	private Integer precolistaId;

	private String descricao;

	private String nome;

	//bi-directional many-to-one association to Loja
	@ManyToOne
	@JoinColumn(name="loja_id")
	private Loja loja;

	//bi-directional many-to-one association to Preco
	@OneToMany(mappedBy="precolista")
	@XmlInverseReference(mappedBy="precolista")
	private List<Preco> precos;

	public Precolista() {
	}

	public Integer getPrecolistaId() {
		return this.precolistaId;
	}

	public void setPrecolistaId(Integer precolistaId) {
		this.precolistaId = precolistaId;
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

	public Loja getLoja() {
		return this.loja;
	}

	public void setLoja(Loja loja) {
		this.loja = loja;
	}

	public List<Preco> getPrecos() {
		return this.precos;
	}

	public void setPrecos(List<Preco> precos) {
		this.precos = precos;
	}

	public Preco addPreco(Preco preco) {
		getPrecos().add(preco);
		preco.setPrecolista(this);

		return preco;
	}

	public Preco removePreco(Preco preco) {
		getPrecos().remove(preco);
		preco.setPrecolista(null);

		return preco;
	}

}
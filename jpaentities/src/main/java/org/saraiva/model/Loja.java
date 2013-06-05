package org.saraiva.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import org.eclipse.persistence.oxm.annotations.XmlInverseReference;

import java.util.List;


/**
 * The persistent class for the lojas database table.
 * 
 */
@XmlRootElement
@Entity
@Table(name="lojas")
public class Loja implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="LOJAS_LOJAID_GENERATOR", sequenceName="LOJAS_LOJA_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LOJAS_LOJAID_GENERATOR")
	@Column(name="loja_id")
	private Integer lojaId;

	private String descricao;

	private String nome;

	//bi-directional many-to-one association to Estoque
	@OneToMany(mappedBy="loja")
	@XmlInverseReference(mappedBy="loja")
	private List<Estoque> estoques;

	//bi-directional many-to-one association to Precolista
	@OneToMany(mappedBy="loja")
	@XmlInverseReference(mappedBy="loja")
	private List<Precolista> precolistas;

	//bi-directional one-to-one association to Lojavitrinerel
	@OneToOne(mappedBy="loja")
	@XmlInverseReference(mappedBy="loja")
	private Lojavitrinerel lojavitrinerel;

	public Loja() {
	}

	public Integer getLojaId() {
		return this.lojaId;
	}

	public void setLojaId(Integer lojaId) {
		this.lojaId = lojaId;
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

	public List<Estoque> getEstoques() {
		return this.estoques;
	}

	public void setEstoques(List<Estoque> estoques) {
		this.estoques = estoques;
	}

	public Estoque addEstoque(Estoque estoque) {
		getEstoques().add(estoque);
		estoque.setLoja(this);

		return estoque;
	}

	public Estoque removeEstoque(Estoque estoque) {
		getEstoques().remove(estoque);
		estoque.setLoja(null);

		return estoque;
	}

	public List<Precolista> getPrecolistas() {
		return this.precolistas;
	}

	public void setPrecolistas(List<Precolista> precolistas) {
		this.precolistas = precolistas;
	}

	public Precolista addPrecolista(Precolista precolista) {
		getPrecolistas().add(precolista);
		precolista.setLoja(this);

		return precolista;
	}

	public Precolista removePrecolista(Precolista precolista) {
		getPrecolistas().remove(precolista);
		precolista.setLoja(null);

		return precolista;
	}

	public Lojavitrinerel getLojavitrinerel() {
		return this.lojavitrinerel;
	}

	public void setLojavitrinerel(Lojavitrinerel lojavitrinerel) {
		this.lojavitrinerel = lojavitrinerel;
	}

}
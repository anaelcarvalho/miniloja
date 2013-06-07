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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


/**
 * The persistent class for the cliente database table.
 * 
 */
@XmlRootElement
@Entity
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CLIENTE_CLIID_GENERATOR", sequenceName="CLIENTE_CLI_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CLIENTE_CLIID_GENERATOR")
	@Column(name="cli_id")
	private Integer cliId;

	private String cpf;

	private String email;

	private String login;

	private String nome;

	private String rg;

	private String senha;

	//bi-directional many-to-one association to Cesta
	@OneToMany(mappedBy="cliente")
	private List<Cesta> cestas;

	public Cliente() {
	}

	public Integer getCliId() {
		return this.cliId;
	}

	public void setCliId(Integer cliId) {
		this.cliId = cliId;
	}

	public String getCpf() {
		return this.cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRg() {
		return this.rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	@XmlTransient
	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Cesta> getCestas() {
		return this.cestas;
	}

	public void setCestas(List<Cesta> cestas) {
		this.cestas = cestas;
	}

	public Cesta addCesta(Cesta cesta) {
		getCestas().add(cesta);
		cesta.setCliente(this);

		return cesta;
	}

	public Cesta removeCesta(Cesta cesta) {
		getCestas().remove(cesta);
		cesta.setCliente(null);

		return cesta;
	}

}
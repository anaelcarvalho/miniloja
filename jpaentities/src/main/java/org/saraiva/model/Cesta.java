package org.saraiva.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import java.util.List;


/**
 * The persistent class for the cesta database table.
 * 
 */
@XmlRootElement
@Entity
public class Cesta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CESTA_CESTAID_GENERATOR", sequenceName="CESTA_CESTA_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CESTA_CESTAID_GENERATOR")
	@Column(name="cesta_id")
	private Integer cestaId;

	private double descontos;

	private double frete;

	private String status;

	private double total;

	private double totalitens;

	//bi-directional many-to-one association to Cliente
	@ManyToOne
	@JoinColumn(name="cli_id")
	private Cliente cliente;

	//bi-directional many-to-one association to Cestaiten
	@OneToMany(mappedBy="cesta")
	private List<Cestaiten> cestaitens;

	public Cesta() {
	}

	public Integer getCestaId() {
		return this.cestaId;
	}

	public void setCestaId(Integer cestaId) {
		this.cestaId = cestaId;
	}

	public double getDescontos() {
		return this.descontos;
	}

	public void setDescontos(double descontos) {
		this.descontos = descontos;
	}

	public double getFrete() {
		return this.frete;
	}

	public void setFrete(double frete) {
		this.frete = frete;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getTotal() {
		return this.total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getTotalitens() {
		return this.totalitens;
	}

	public void setTotalitens(double totalitens) {
		this.totalitens = totalitens;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Cestaiten> getCestaitens() {
		return this.cestaitens;
	}

	public void setCestaitens(List<Cestaiten> cestaitens) {
		this.cestaitens = cestaitens;
	}

	public Cestaiten addCestaiten(Cestaiten cestaiten) {
		getCestaitens().add(cestaiten);
		cestaiten.setCesta(this);

		return cestaiten;
	}

	public Cestaiten removeCestaiten(Cestaiten cestaiten) {
		getCestaitens().remove(cestaiten);
		cestaiten.setCesta(null);

		return cestaiten;
	}

}
package org.saraiva.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the produtoatribvalrel database table.
 * 
 */
@Embeddable
public class ProdutoatribvalrelPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="prod_id")
	private Integer prodId;

	@Column(name="atrib_id")
	private Integer atribId;

	@Column(name="atribval_id")
	private Integer atribvalId;

	public ProdutoatribvalrelPK() {
	}
	public Integer getProdId() {
		return this.prodId;
	}
	public void setProdId(Integer prodId) {
		this.prodId = prodId;
	}
	public Integer getAtribId() {
		return this.atribId;
	}
	public void setAtribId(Integer atribId) {
		this.atribId = atribId;
	}
	public Integer getAtribvalId() {
		return this.atribvalId;
	}
	public void setAtribvalId(Integer atribvalId) {
		this.atribvalId = atribvalId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ProdutoatribvalrelPK)) {
			return false;
		}
		ProdutoatribvalrelPK castOther = (ProdutoatribvalrelPK)other;
		return 
			this.prodId.equals(castOther.prodId)
			&& this.atribId.equals(castOther.atribId)
			&& this.atribvalId.equals(castOther.atribvalId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.prodId.hashCode();
		hash = hash * prime + this.atribId.hashCode();
		hash = hash * prime + this.atribvalId.hashCode();
		
		return hash;
	}
}
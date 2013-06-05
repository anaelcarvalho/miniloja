package org.saraiva.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the produtocatrel database table.
 * 
 */
@Embeddable
public class ProdutocatrelPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="prod_id")
	private Integer prodId;

	@Column(name="categ_id")
	private Integer categId;

	public ProdutocatrelPK() {
	}
	public Integer getProdId() {
		return this.prodId;
	}
	public void setProdId(Integer prodId) {
		this.prodId = prodId;
	}
	public Integer getCategId() {
		return this.categId;
	}
	public void setCategId(Integer categId) {
		this.categId = categId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ProdutocatrelPK)) {
			return false;
		}
		ProdutocatrelPK castOther = (ProdutocatrelPK)other;
		return 
			this.prodId.equals(castOther.prodId)
			&& this.categId.equals(castOther.categId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.prodId.hashCode();
		hash = hash * prime + this.categId.hashCode();
		
		return hash;
	}
}
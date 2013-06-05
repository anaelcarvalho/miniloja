package org.saraiva.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the produtoskurel database table.
 * 
 */
@Embeddable
public class ProdutoskurelPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="prod_id")
	private Integer prodId;

	@Column(name="sku_id")
	private Integer skuId;

	public ProdutoskurelPK() {
	}
	public Integer getProdId() {
		return this.prodId;
	}
	public void setProdId(Integer prodId) {
		this.prodId = prodId;
	}
	public Integer getSkuId() {
		return this.skuId;
	}
	public void setSkuId(Integer skuId) {
		this.skuId = skuId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ProdutoskurelPK)) {
			return false;
		}
		ProdutoskurelPK castOther = (ProdutoskurelPK)other;
		return 
			this.prodId.equals(castOther.prodId)
			&& this.skuId.equals(castOther.skuId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.prodId.hashCode();
		hash = hash * prime + this.skuId.hashCode();
		
		return hash;
	}
}
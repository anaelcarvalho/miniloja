package org.saraiva.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the categoriatopo database table.
 * 
 */
@Embeddable
public class CategoriatopoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="vitrine_id")
	private Integer vitrineId;

	@Column(name="categ_id")
	private Integer categId;

	public CategoriatopoPK() {
	}
	public Integer getVitrineId() {
		return this.vitrineId;
	}
	public void setVitrineId(Integer vitrineId) {
		this.vitrineId = vitrineId;
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
		if (!(other instanceof CategoriatopoPK)) {
			return false;
		}
		CategoriatopoPK castOther = (CategoriatopoPK)other;
		return 
			this.vitrineId.equals(castOther.vitrineId)
			&& this.categId.equals(castOther.categId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.vitrineId.hashCode();
		hash = hash * prime + this.categId.hashCode();
		
		return hash;
	}
}
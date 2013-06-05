package org.saraiva.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the lojavitrinerel database table.
 * 
 */
@Embeddable
public class LojavitrinerelPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="loja_id")
	private Integer lojaId;

	@Column(name="vitrine_id")
	private Integer vitrineId;

	public LojavitrinerelPK() {
	}
	public Integer getLojaId() {
		return this.lojaId;
	}
	public void setLojaId(Integer lojaId) {
		this.lojaId = lojaId;
	}
	public Integer getVitrineId() {
		return this.vitrineId;
	}
	public void setVitrineId(Integer vitrineId) {
		this.vitrineId = vitrineId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof LojavitrinerelPK)) {
			return false;
		}
		LojavitrinerelPK castOther = (LojavitrinerelPK)other;
		return 
			this.lojaId.equals(castOther.lojaId)
			&& this.vitrineId.equals(castOther.vitrineId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.lojaId.hashCode();
		hash = hash * prime + this.vitrineId.hashCode();
		
		return hash;
	}
}
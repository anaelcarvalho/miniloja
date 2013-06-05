package org.saraiva.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the categoriarel database table.
 * 
 */
@Embeddable
public class CategoriarelPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="categ_id_pai")
	private Integer categIdPai;

	@Column(name="categ_id_filha")
	private Integer categIdFilha;

	public CategoriarelPK() {
	}
	public Integer getCategIdPai() {
		return this.categIdPai;
	}
	public void setCategIdPai(Integer categIdPai) {
		this.categIdPai = categIdPai;
	}
	public Integer getCategIdFilha() {
		return this.categIdFilha;
	}
	public void setCategIdFilha(Integer categIdFilha) {
		this.categIdFilha = categIdFilha;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CategoriarelPK)) {
			return false;
		}
		CategoriarelPK castOther = (CategoriarelPK)other;
		return 
			this.categIdPai.equals(castOther.categIdPai)
			&& this.categIdFilha.equals(castOther.categIdFilha);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.categIdPai.hashCode();
		hash = hash * prime + this.categIdFilha.hashCode();
		
		return hash;
	}
}
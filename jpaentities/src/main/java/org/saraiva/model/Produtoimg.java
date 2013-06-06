package org.saraiva.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import org.eclipse.persistence.oxm.annotations.XmlInverseReference;


/**
 * The persistent class for the produtoimg database table.
 * 
 */
@XmlRootElement
@Entity
public class Produtoimg implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PRODUTOIMG_IMGID_GENERATOR", sequenceName="PRODUTOIMG_IMG_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PRODUTOIMG_IMGID_GENERATOR")
	@Column(name="img_id")
	private Integer imgId;

	private String url;

	//bi-directional many-to-one association to Produto
	@ManyToOne
	@JoinColumn(name="prod_id")
	private Produto produto;

	public Produtoimg() {
	}

	public Integer getImgId() {
		return this.imgId;
	}

	public void setImgId(Integer imgId) {
		this.imgId = imgId;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@XmlInverseReference(mappedBy="produtoimgs")
	public Produto getProduto() {
		return this.produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

}
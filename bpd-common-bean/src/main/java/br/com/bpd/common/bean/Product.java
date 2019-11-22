package br.com.bpd.common.bean;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUTOS")
public class Product implements Serializable {

	private static final long serialVersionUID = 158663896179135547L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idProduto", updatable = false, nullable = false)
	private Long idProduto;
	@ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
	@JoinColumn(name="idCategoria")
	private Category category;
	@Column(name = "produto")
	private String product;
	@Column(name = "preco")
	private Double price;
	@Column(name = "quantidade")
	private Integer qty;
	@Column(name = "descricao")
	private String description;
	@Lob
	@Column(name = "foto")
	private String photo;

	/**
	 * @return the idProduto
	 */
	public Long getIdProduto() {
		return idProduto;
	}

	/**
	 * @param idProduto the idProduto to set
	 */
	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}

	/**
	 * @return the category
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(Category category) {
		this.category = category;
	}

	/**
	 * @return the product
	 */
	public String getProduct() {
		return product;
	}

	/**
	 * @param product the product to set
	 */
	public void setProduct(String product) {
		this.product = product;
	}

	/**
	 * @return the price
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(Double price) {
		this.price = price;
	}

	/**
	 * @return the qty
	 */
	public Integer getQty() {
		return qty;
	}

	/**
	 * @param qty the qty to set
	 */
	public void setQty(Integer qty) {
		this.qty = qty;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the photo
	 */
	public String getPhoto() {
		return photo;
	}

	/**
	 * @param photo the photo to set
	 */
	public void setPhoto(String photo) {
		this.photo = photo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((idProduto == null) ? 0 : idProduto.hashCode());
		result = prime * result + ((photo == null) ? 0 : photo.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		result = prime * result + ((qty == null) ? 0 : qty.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (idProduto == null) {
			if (other.idProduto != null)
				return false;
		} else if (!idProduto.equals(other.idProduto))
			return false;
		if (photo == null) {
			if (other.photo != null)
				return false;
		} else if (!photo.equals(other.photo))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		if (qty == null) {
			if (other.qty != null)
				return false;
		} else if (!qty.equals(other.qty))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Product [idProduto=" + idProduto + ", category=" + category + ", product=" + product + ", price="
				+ price + ", qty=" + qty + ", description=" + description + ", photo=" + photo + "]";
	}

}

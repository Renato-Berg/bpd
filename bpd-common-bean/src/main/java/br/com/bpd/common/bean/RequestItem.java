package br.com.bpd.common.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PEDIDOITENS")
public class RequestItem implements Serializable {

	private static final long serialVersionUID = -397924436571120456L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idItem", updatable = false, nullable = false)
	private Long idItem;
	private Request request;
	private Product product;
	@Column(name = "produto")
	private String productDesc;
	@Column(name = "quantidade")
	private Integer qty;
	@Column(name = "valor")
	private Double value;
	@Column(name = "subtotal")
	private Double price;

	/**
	 * @return the idItem
	 */
	public Long getIdItem() {
		return idItem;
	}

	/**
	 * @param idItem the idItem to set
	 */
	public void setIdItem(Long idItem) {
		this.idItem = idItem;
	}

	/**
	 * @return the request
	 */
	public Request getRequest() {
		return request;
	}

	/**
	 * @param request the request to set
	 */
	public void setRequest(Request request) {
		this.request = request;
	}

	/**
	 * @return the product
	 */
	public Product getProduct() {
		return product;
	}

	/**
	 * @param product the product to set
	 */
	public void setProduct(Product product) {
		this.product = product;
	}

	/**
	 * @return the productDesc
	 */
	public String getProductDesc() {
		return productDesc;
	}

	/**
	 * @param productDesc the productDesc to set
	 */
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
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
	 * @return the value
	 */
	public Double getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(Double value) {
		this.value = value;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idItem == null) ? 0 : idItem.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		result = prime * result + ((productDesc == null) ? 0 : productDesc.hashCode());
		result = prime * result + ((qty == null) ? 0 : qty.hashCode());
		result = prime * result + ((request == null) ? 0 : request.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		RequestItem other = (RequestItem) obj;
		if (idItem == null) {
			if (other.idItem != null)
				return false;
		} else if (!idItem.equals(other.idItem))
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
		if (productDesc == null) {
			if (other.productDesc != null)
				return false;
		} else if (!productDesc.equals(other.productDesc))
			return false;
		if (qty == null) {
			if (other.qty != null)
				return false;
		} else if (!qty.equals(other.qty))
			return false;
		if (request == null) {
			if (other.request != null)
				return false;
		} else if (!request.equals(other.request))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RequestItem [idItem=" + idItem + ", request=" + request + ", product=" + product + ", productDesc="
				+ productDesc + ", qty=" + qty + ", value=" + value + ", price=" + price + "]";
	}

}

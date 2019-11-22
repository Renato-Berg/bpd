package br.com.bpd.common.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CATEGORIAS")
public class Category implements Serializable {

	private static final long serialVersionUID = -6563347584813505638L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idCategoria", updatable = false, nullable = false)
	private Long idCategoria;
	@Column(name = "categoria", length = 100)
	private String category;

	/**
	 * @return the idCategoria
	 */
	public Long getIdCategoria() {
		return idCategoria;
	}

	/**
	 * @param idCategoria the idCategoria to set
	 */
	public void setIdCategoria(Long idCategoria) {
		this.idCategoria = idCategoria;
	}

	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((idCategoria == null) ? 0 : idCategoria.hashCode());
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
		Category other = (Category) obj;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (idCategoria == null) {
			if (other.idCategoria != null)
				return false;
		} else if (!idCategoria.equals(other.idCategoria))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Category [idCategoria=" + idCategoria + ", category=" + category + "]";
	}

}

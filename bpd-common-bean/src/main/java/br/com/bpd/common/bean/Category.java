package br.com.bpd.common.bean;

import java.io.Serializable;

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
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private String idCategoria;
	private String categoria;

	/**
	 * @return the idCategoria
	 */
	public final String getIdCategoria() {
		return idCategoria;
	}

	/**
	 * @param idCategoria the idCategoria to set
	 */
	public final void setIdCategoria(String idCategoria) {
		this.idCategoria = idCategoria;
	}

	/**
	 * @return the categoria
	 */
	public final String getCategoria() {
		return categoria;
	}

	/**
	 * @param categoria the categoria to set
	 */
	public final void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((categoria == null) ? 0 : categoria.hashCode());
		result = prime * result + ((idCategoria == null) ? 0 : idCategoria.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		if (categoria == null) {
			if (other.categoria != null)
				return false;
		} else if (!categoria.equals(other.categoria))
			return false;
		if (idCategoria == null) {
			if (other.idCategoria != null)
				return false;
		} else if (!idCategoria.equals(other.idCategoria))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Category [idCategoria=" + idCategoria + ", categoria=" + categoria + "]";
	}

}

package com.vitormarcal.sismetal.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "item_peca")
public class ItemPeca implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Integer quantidade = 1;
	private BigDecimal valorUnitario = BigDecimal.ZERO;
	private Materia materia;
	private Peca peca;

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(nullable = false, length = 3)
	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	@Column(name = "valor_unitario", nullable = false, precision = 10, scale = 2)
	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	@ManyToOne
	@JoinColumn(name = "materia_id", nullable = false)
	public Materia getMateria() {
		return materia;
	}

	public void setMateria(Materia materia) {
		this.materia = materia;
	}

	@ManyToOne
	@JoinColumn(name = "peca_id", nullable = false)
	public Peca getPeca() {
		return peca;
	}

	public void setPeca(Peca peca) {
		this.peca = peca;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		ItemPeca other = (ItemPeca) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Transient
	public BigDecimal getValorTotal() {
		return this.getValorUnitario().multiply(new BigDecimal(this.getQuantidade()));
	}

	@Transient
	public boolean isMateriaAssociada() {
		return this.getMateria() != null && this.getMateria().getId() != null;
	}

	@Transient
	public boolean isEstoqueSuficiente() {
		return  this.getMateria().getId() == null
				|| this.getMateria().getQuantidadeEstoque() >= this.getQuantidade();
	}

	@Transient
	public boolean isEstoqueInsuficiente() {
		return !isEstoqueSuficiente();
	}
}

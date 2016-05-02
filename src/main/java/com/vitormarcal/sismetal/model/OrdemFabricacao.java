package com.vitormarcal.sismetal.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "ordem_fab")
public class OrdemFabricacao implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long numOrd;
	private BigDecimal valorTotal;
	private BigDecimal subTotal;
	private BigDecimal desconto;
	private BigDecimal custoFabricacao;
	private Date tempoFabricacao;
	private Collection<PedidoFabricacao> pedidos;

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	public Long getNumOrd() {
		return numOrd;
	}

	public void setNumOrd(Long numOrd) {
		this.numOrd = numOrd;
	}

	@NotNull
	@Min(1)
	@Column(scale = 2, precision = 10, nullable = false)
	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	@NotNull
	@Min(1)
	@Column(scale = 2, precision = 10, nullable = false)
	public BigDecimal getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}

	@NotNull
	@Min(1)
	@Column(scale = 2, precision = 10, nullable = false)
	public BigDecimal getDesconto() {
		return desconto;
	}

	public void setDesconto(BigDecimal desconto) {
		this.desconto = desconto;
	}

	@NotNull
	@Min(1)
	@Column(scale = 2, precision = 10, nullable = false)
	public BigDecimal getCustoFabricacao() {
		return custoFabricacao;
	}

	public void setCustoFabricacao(BigDecimal custoFabricacao) {
		this.custoFabricacao = custoFabricacao;
	}

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "tempoFabricaco", nullable = false)
	public Date getTempoFabricacao() {
		return tempoFabricacao;
	}

	public void setTempoFabricacao(Date tempoFabricacao) {
		this.tempoFabricacao = tempoFabricacao;
	}

	@OneToMany
	@JoinTable(name = "lista_pedidos_ord",
			joinColumns = @JoinColumn(name = "numOrd"),
			inverseJoinColumns = @JoinColumn(name = "numPed"))
	public Collection<PedidoFabricacao> getPedidos() {
		return pedidos;
	}

	public void setPedidos(Collection<PedidoFabricacao> pedidos) {
		this.pedidos = pedidos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((numOrd == null) ? 0 : numOrd.hashCode());
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
		OrdemFabricacao other = (OrdemFabricacao) obj;
		if (numOrd == null) {
			if (other.numOrd != null)
				return false;
		} else if (!numOrd.equals(other.numOrd))
			return false;
		return true;
	}

}

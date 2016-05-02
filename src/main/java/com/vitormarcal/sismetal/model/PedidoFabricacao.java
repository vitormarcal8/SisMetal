package com.vitormarcal.sismetal.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "pedido_fab")
public class PedidoFabricacao implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long numPed;
	private BigDecimal totalValorDeCustodasMaterias;
	private Cliente cliente;
	private Collection<Peca> pecas;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getNumPed() {
		return numPed;
	}

	public void setNumPed(Long numPed) {
		this.numPed = numPed;
	}

	@NotNull
	@Min(1)
	@Column(name = "total_valor_custo_materias", scale = 2, precision = 10, nullable = false)
	public BigDecimal getTotalValorDeCustodasMaterias() {
		return totalValorDeCustodasMaterias;
	}

	public void setTotalValorDeCustodasMaterias(BigDecimal totalValorDeCustodasMaterias) {
		this.totalValorDeCustodasMaterias = totalValorDeCustodasMaterias;
	}

	@ManyToOne
	@JoinColumn(name = "idCliente")
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@OneToMany
	@JoinTable(name = "lista_pecas_ped", joinColumns = @JoinColumn(name = "idPedido") , inverseJoinColumns = @JoinColumn(name = "idPeca") )
	public Collection<Peca> getPecas() {
		return pecas;
	}

	public void setPecas(Collection<Peca> pecas) {
		this.pecas = pecas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((numPed == null) ? 0 : numPed.hashCode());
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
		PedidoFabricacao other = (PedidoFabricacao) obj;
		if (numPed == null) {
			if (other.numPed != null)
				return false;
		} else if (!numPed.equals(other.numPed))
			return false;
		return true;
	}

}

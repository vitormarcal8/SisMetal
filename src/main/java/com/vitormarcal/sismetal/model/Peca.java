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
import javax.persistence.ManyToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
public class Peca implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;
	private BigDecimal peso;
	private BigDecimal tamanho;
	private Integer quantidade;
	private Collection<Materia> materias;
	private BigDecimal valorDeCusto;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@NotNull
	@Column(length = 80, nullable = false)
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@NotNull
	@Column(scale = 3, precision = 10, nullable = false)
	public BigDecimal getPeso() {
		return peso;
	}

	public void setPeso(BigDecimal peso) {
		this.peso = peso;
	}

	@NotNull
	@Column(scale = 2, precision = 10, nullable = false)
	public BigDecimal getTamanho() {
		return tamanho;
	}

	public void setTamanho(BigDecimal tamanho) {
		this.tamanho = tamanho;
	}

	@NotNull
	@Min(1)
	@Max(value = 9999, message = " Valor muito alto.")
	@Column(length = 5, nullable = false)
	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	@ManyToMany
	@JoinTable(name = "lista_materias_peca", joinColumns = @JoinColumn(name = "idPeca") , inverseJoinColumns = @JoinColumn(name = "idMateria") )
	public Collection<Materia> getMaterias() {
		return materias;
	}

	public void setMaterias(Collection<Materia> materias) {
		this.materias = materias;
	}

	@NotNull
	@Min(1)
	@Column(name = "valor_custo", scale = 2, precision = 10, nullable = false)
	public BigDecimal getValorDeCusto() {
		return valorDeCusto;
	}

	public void setValorDeCusto(BigDecimal valorDeCusto) {
		this.valorDeCusto = valorDeCusto;
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
		Peca other = (Peca) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}

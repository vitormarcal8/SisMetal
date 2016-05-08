package com.vitormarcal.sismetal.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
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
	private BigDecimal valorTotal = BigDecimal.ZERO;
	private List<ItemPeca> itens = new ArrayList<>();
	private StatusPeca status = StatusPeca.ORCAMENTO;
	public Peca() {
	}

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

	@OneToMany(mappedBy = "peca", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	public List<ItemPeca> getItens() {
		return itens;
	}

	public void setItens(List<ItemPeca> itens) {
		this.itens = itens;
	}

	@NotNull
	@Min(1)
	@Column(name = "valor_custo", scale = 2, precision = 10, nullable = false)
	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}
	

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 20)
	public StatusPeca getStatus() {
		return status;
	}

	public void setStatus(StatusPeca status) {
		this.status = status;
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

	public void adicionarItemVazio() {
		Materia materia = new Materia();

		ItemPeca item = new ItemPeca();
		item.setMateria(materia);
		item.setPeca(this);

		this.getItens().add(0, item);
	}

	public void removerItemVazio() {
		ItemPeca primeiroItem = this.getItens().get(0);

		if (primeiroItem != null && primeiroItem.getMateria().getId() == null) {
			this.getItens().remove(0);
		}
	}

	public void recalcularValorTotal() {
		BigDecimal total = BigDecimal.ZERO;
		
		
		for (ItemPeca item : this.getItens()) {
			if (item.getMateria() != null && item.getMateria().getId() != null) {
				total = total.add(item.getValorTotal());
			}
		}
		this.setValorTotal(total);
	}

	@Transient
	public boolean isOrcamento() {
		return StatusPeca.ORCAMENTO.equals(this.getStatus());
	}

	@Transient
	public boolean isNovo() {
		return getId() == null;
	}
	
	@Transient
	public boolean isExistente() {
		return !isNovo();
	}

	@Transient
	public boolean isValorTotalNegativo() {
		return this.getValorTotal().compareTo(BigDecimal.ZERO) < 0;
	}

	@Transient
	public boolean isEmitido() {
		return StatusPeca.EMITIDO.equals(this.getStatus());
	}

	@Transient
	public boolean isNaoEmissivel() {
		return !this.isEmissivel();
	}

	@Transient
	public boolean isEmissivel() {
		return this.isExistente() && this.isOrcamento();
	}

	@Transient
	public boolean isCancelavel() {
		return this.isExistente() && !this.isCancelado();
	}

	@Transient
	private boolean isCancelado() {
		return StatusPeca.CANCELADO.equals(this.getStatus());
	}

	@Transient
	public boolean isNaoCancelavel() {
		return !this.isCancelavel();
	}

	@Transient
	public boolean isAlteravel() {
		return this.isOrcamento();
	}
	
	@Transient
	public boolean isNaoAlteravel() {
		return !this.isAlteravel();
	}
	
	@Transient
	public boolean isNaoEnviavelPorEmail() {
		return this.isNovo() || this.isCancelado();
	}


}

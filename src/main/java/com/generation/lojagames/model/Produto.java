package com.generation.lojagames.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;


@Entity	//criando a tabela
@Table(name = "tb_produtos")  //nomeando a tabela
public class Produto {   //configurando atributos
	
	
	@Id  //primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
	private Long id;
	
	@Column(length = 100)
	@NotBlank(message = "O atributo nome é obrigatório!")
	@Size(min = 2, max= 100, message = "O atributo nome deve conter entre 2 e 100 caracteres.")
	private String nome;
	
	@Column(length = 1000)
	@NotBlank(message = "O atributo descricao é obrigatório!")
	@Size(min = 2, max= 1000, message = "O atributo descricao deve conter entre 2 e 1000 caracteres.")	
	private String descricao;
	
	@NotNull(message = "O atributo preço é obrigatório!")  //@NotBlank só deve ser usado em campos do tipo String
	@Positive(message = "O atributo preço deve ser maior que zero!")	
	private BigDecimal preco;
	
	@Column(length = 5000)
	@NotBlank(message = "O atributo foto é obrigatório!")
	private String foto;
	

	@ManyToOne
	@JsonIgnoreProperties("produto")
	private Categoria categoria;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id; 
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public BigDecimal getPreco() {
		return preco;
	}
	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}

	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	

}

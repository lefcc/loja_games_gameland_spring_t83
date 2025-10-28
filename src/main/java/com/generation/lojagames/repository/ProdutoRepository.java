package com.generation.lojagames.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.lojagames.model.Produto;

// postagem -> todas as consultas usa como base a tabela Produto(model) e long -> é o atributo chave primária da tabela Produto(model)
// indicamos que qualquer operação feita em ProdutoRepository ele irá trabalhar com a tb_Produto, definida na classe Produto e vai utilizar atributo Long como chave primária, neste caso o id
public interface ProdutoRepository extends JpaRepository<Produto, Long>{

	// Declara um método para buscar produtos pelo nome, ignorando maiúsculas e minúsculas através de uma consulta derivada que o Spring Data JPA implementa automaticamente
	public List<Produto>findAllByNomeContainingIgnoreCase(String nome);

	// Produtos com preço maior que xxx, em ordem crescente
	public List<Produto> findByPrecoGreaterThanOrderByPrecoAsc(BigDecimal preco);
	
	 // Produtos com preço menor que xxx, em ordem decrescente
	public List<Produto>findByPrecoLessThanOrderByPrecoDesc(BigDecimal preco);

	
	
	

}

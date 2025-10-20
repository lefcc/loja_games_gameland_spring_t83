package com.generation.lojagames.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.lojagames.model.Produto;

// postagem -> todas as consultas usa como base a tabela Produto(model) e long -> é o atributo chave primária da tabela Produto(model)
// indicamos que qualquer operação feita em ProdutoRepository ele irá trabalhar com a tb_Produto, definida na classe Produto e vai utilizar atributo Long como chave primária, neste caso o id
public interface ProdutoRepository extends JpaRepository<Produto, Long>{
	
	

}

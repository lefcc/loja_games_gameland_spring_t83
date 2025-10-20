package com.generation.lojagames.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.lojagames.model.Produto;
import com.generation.lojagames.repository.ProdutoRepository;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*", allowedHeaders ="*") //libere requesições de qualquer origem e também libere o cabeçalho. Aqui esta como libera geral. Libera requisições de outras origens
//Sem isso os servidores de front e back não se comunicam e também libera a leitura dos tokens de segurança
// para restringir, usado quando em produção, substitui o * pelo endereço do front, dai só aceitaria requisições feitas pelo front
public class ProdutoController {
	
	@Autowired  //indica injeção de dependência. Delego para o String criar as instâncias, funções
	private ProdutoRepository produtoRepository;  // tipo = ProdutoRepository | objeto = produtoRepository
	
	//crio método que traz informações
	//retorna obj da classe ResponseEntity = resposta http. NEssa resposta estou esperando uma lista de prpdutos <Tipo<nome lista>>
	// getAll nome do método, pega todos os produtos
	@GetMapping
	public ResponseEntity<List<Produto>> getAll(){
		//return = resposta
		//return .. .ok -> se encontrar algo lá retorna ok 200 
		//findAll método que retorna todas os produtos presentes no DB -> dentro de produtoRepository
		return ResponseEntity.ok(produtoRepository.findAll());
	}
	
	/* GERAR METODO PARA FILTRAR POR VALOR,  1 PARA MAIORR DE 250 E OUTRO MENOR DE ...
	public ResponseEntity<List<Produto>> getPreco (){
		return ResponseEntity.ok(produtoRepository.findBy(preco, null);
	}*/
	
}

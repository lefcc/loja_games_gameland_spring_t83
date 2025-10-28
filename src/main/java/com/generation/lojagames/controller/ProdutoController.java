package com.generation.lojagames.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generation.lojagames.model.Produto;
import com.generation.lojagames.repository.CategoriaRepository;
import com.generation.lojagames.repository.ProdutoRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*", allowedHeaders ="*") //libere requesições de qualquer origem e também libere o cabeçalho. Aqui esta como libera geral. Libera requisições de outras origens
//Sem isso os servidores de front e back não se comunicam e também libera a leitura dos tokens de segurança
// para restringir, usado quando em produção, substitui o * pelo endereço do front, dai só aceitaria requisições feitas pelo front
public class ProdutoController {
	
	@Autowired  //indica injeção de dependência. Delego para o String criar as instâncias, funções
	private ProdutoRepository produtoRepository;  // tipo = ProdutoRepository | objeto = produtoRepository
	
	@Autowired // Injeção de dependência do Spring
	private CategoriaRepository categoriaRepository; // Instancia o repositório de Categoria
	
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
	
	
	@GetMapping("/{id}") //variável de caminho, variável esta no URL
	public ResponseEntity<Produto> getById(@PathVariable Long id) {
		return produtoRepository.findById(id).map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.notFound().build());

	}
	
	
	//getmapping é estrutura condicional, verifica se houve retorno, se houver mostra resultado 
	//e se não houver ele mostra status not found
	@GetMapping("/nome/{nome}") 
	public ResponseEntity<List<Produto>> getAllByNome(@PathVariable String nome) {
		return ResponseEntity.ok(produtoRepository.findAllByNomeContainingIgnoreCase(nome));
	}

	@PostMapping
	public ResponseEntity<Produto> post(@Valid @RequestBody Produto produto) {  //@Valid valida se atributos exigidos foram fornecidos
		
		if (categoriaRepository.existsById(produto.getCategoria().getId())) {
			produto.setId(null);  				//@RequestBody pega algo (o objeto, aqui produto) que esta dentro do corpo da requisição
			
			return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produto)); //status 201 CREATED - indica que objeto foi criado no DB, método de persistencia
		}
			
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoria inexistente", null);
			
	}  		
	
	@PutMapping 
		public ResponseEntity<Produto> put(@Valid @RequestBody Produto produto) { 
			if (produtoRepository.existsById(produto.getId())) { 
				if (categoriaRepository.existsById(produto.getCategoria().getId())) { 
					return ResponseEntity.status(HttpStatus.OK).body(produtoRepository.save(produto)); 
				}
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoria inexistente", null); 
			}
			return ResponseEntity.notFound().build(); 	
			
		}
	
	
	@ResponseStatus(HttpStatus.NO_CONTENT) // Define o status de resposta como 204 (No Content)
	@DeleteMapping("/{id}") //variável de caminho, variável esta no URL
	public void delete(@PathVariable Long id) {
		
		Optional<Produto> produto = produtoRepository.findById(id);
		
		if(produto.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		
		produtoRepository.deleteById(id);
	}
	
	
	@GetMapping("/preco_maior/{preco}")
	public ResponseEntity<List<Produto>> getByPrecoMaior(@PathVariable BigDecimal preco) {
		return ResponseEntity.ok(produtoRepository.findByPrecoGreaterThanOrderByPrecoAsc(preco));
	}
	
	@GetMapping("/preco_menor/{preco}")
	public ResponseEntity<List<Produto>> getByPrecoMenor(@PathVariable BigDecimal preco) {
		return ResponseEntity.ok(produtoRepository.findByPrecoLessThanOrderByPrecoDesc(preco));
	}
	
	
}

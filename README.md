# Projeto GameLand (Loja de Games) - Backend com Spring Boot
 
<br />
 
<div align="center">
<img src="https://i.imgur.com/w8tTOuT.png" title="source: imgur.com" /> 
</div>
 
<br />

<div align="center">
<img src="https://img.shields.io/github/languages/top/lefcc/loja_games_gameland_spring_t83?style=flat-square" />
<img src="https://img-shields.io/github/repo-size/lefcc/loja_games_gameland_spring_t83?style=flat-square" />
<img src="https://img-shields.io/github/languages/count/lefcc/loja_games_gameland_spring_t83?style=flat-square" />
<img src="https://img-shields.io/github/last-commit/lefcc/loja_games_gameland_spring_t83?style=flat-square" />
<img src="https://img-shields.io/github/issues/lefcc/loja_games_gameland_spring_t83?style=flat-square" />
<img src="https://img-shields.io/github/issues-pr/lefcc/loja_games_gameland_spring_t83?style=flat-square" />
<img src="https://img-shields.io/badge/status-construção-yellow" alt="Status: Em Construção">
 
</div>
 
<br />
 
## 1. Descrição
 
<br />
 
A **GAMELAND** é uma aplicação de e-commerce focada na venda de jogos eletrônicos. O objetivo deste backend é gerenciar o catálogo de **Produtos** e suas **Categorias**, além de controlar o acesso de **Usuários** administradores ou clientes. Este projeto foi desenvolvido com fins educacionais, simulando uma API RESTful de loja virtual para praticar conceitos de API REST com Java e Spring Boot.
 
Entre os principais recursos que a API da loja oferece, destacam-se:
 
1. Criação, edição, listagem e exclusão de **Produtos** (jogos).
2. Associação de **Produtos** a **Categorias** específicas (e.g., RPG, Ação, Esporte).
3. Cadastro e autenticação de **Usuários** (administradores ou clientes).
4. Visualização de **Produtos** por **Categoria** ou nome.
5. Controle de acesso a operações sensíveis (como cadastro de produtos).
 
<br />
 
## 2. Sobre esta API
 
<br />
 
A API da Loja de Games foi desenvolvida utilizando **Java** e o **framework Spring Boot**, seguindo os princípios da Arquitetura MVC e REST. Ela oferece *endpoints* para o gerenciamento dos recursos **Usuário**, **Produto** e **Categoria**, permitindo a administração do catálogo de jogos.
 
<br />
 
### 2.1. Principais funcionalidades da API:
 
<br />
 
1. Consulta, cadastro, login e atualização dos dados de **Usuários**.
2. Consulta, criação e gerenciamento de **Categorias** para classificar os jogos.
3. Criação, edição, listagem e remoção de **Produtos**.
4. Associação de **Produtos** a **Categorias**.
5. Autenticação via token JWT para segurança nas requisições.
 
<br />
 
## 3. Diagrama de Classes
 
<br />
 
O **Diagrama de Classes** modela a estrutura do sistema, representando as entidades (classes), seus atributos, métodos e os relacionamentos entre elas.
 
<br />
 
```mermaid

classDiagram

class Produto {

  - id : Long

  - nome : String

  - preco : BigDecimal

  - console : String

  - categoria : Categoria

  - usuario : Usuario

}

class Categoria {

  - id : Long

  - nome : String

  - descricao : String

  - produtos : List<Produto>

}

class Usuario {

  - id : Long

  - nome : String

  - usuario : String

  - senha : String

  - foto : String

  - produtos : List<Produto>

}

Categoria "1" --> "0..*" Produto : classifica

Usuario "1" --> "0..*" Produto : cadastra
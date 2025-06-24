<h1>Challenge literalura</h1>

<h2> 📌 Descrição do projeto</h2>

<p>Desafio realizado durante o programa de educação e empregabilidade <b>ONE - Oracle Next Education.</b> </p>

<p>
  Esse desafio tem o objetivo de desenvolver um Catálogo de Livros que ofereça interação textual (via console) com os usuários, proporcionando no mínimo 5 opções de interação. 
  Foi desenvolvido solicitações a API de livros Gutendex, manipulações de dados JSON, armazenamento de dados em banco de dados e, por fim, a busca de livros e autores de interesse.
</p>

<h2>:hammer: Funcionalidades do projeto </h2>

- `Menu de opções`: Os usuários deverão digitar uma das opções apresentadas no console. 
- `Validar entrada`: Se o número digitado não estiver presente nas opções oferecidas a aplicação irá mostrar a mensagem “Opção inválida. Escolha somente uma das opções abaixo.” e o menu de opções será apresentado novamente.
- `Opção 1`: O usuário irá digitar o título do livro para ser buscado. A aplicação irá buscar esse livro na Api Gutendex, se o livro não estiver presente no banco de dados a aplicação irá fazer o armazenamento do primeiro resultado da busca e mostrar o livro encontrado para o usuário.
- `Opção 2`: A aplicação apresentará para o usuário todos os livros armazenados no banco de dados, mostrando informações de título, autor, idioma e número de downloads.
- `Opção 3`: A aplicação apresentará para o usuário todos os autores dos livros armazenados no banco de dados, mostrando informações de ano de nascimento, ano de falecimento e todos os livros armazenados no banco de dados.
- `Opção 4`: A aplicação apresentará para o usuário todos os autores dos livros armazenados no banco de dados que estavam vivos no ano digitado, mostrando informações de ano de nascimento, ano de falecimento e todos os livros armazenados no banco de dados.
- `Opção 5`: O usuário deve digitar um dos idiomas oferecidos pela aplicação. A aplicação retornará os livros armazenados no banco de dados com esse idioma, mostrando informações de título, autor, idioma e número de downloads.
- `Opção 0`: Opção que finaliza a aplicação com a mensagem “Saindo...” 

<h2> 🧠 Tecnologias utilizadas </h2>

- Java 21 (Java SE)
- Spring Boot
- Spring Data JPA/Hibernate
- Maven
- PostgreSQL
- [Gutendex API](https://gutendex.com/)
- Biblioteca Jackson Databind
- Postman

O video abaixo exibe a execução do programa:

https://github.com/user-attachments/assets/cc2f0945-3644-4fec-9c05-ea9e89d72439




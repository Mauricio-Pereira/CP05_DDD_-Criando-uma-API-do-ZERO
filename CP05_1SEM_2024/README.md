# CP 05 DDD - 1º Semestre 2024

Este projeto é uma aplicação Java que usa Maven para gerenciamento de dependências. Ele também interage com um banco de dados, para o qual são fornecidos scripts SQL.  
O projeto foi feito para gerenciar o armazenameto de cartas e coleções de um jogo de Super Trunfo de Cavaleiros do Zodíaco.

## Pré-requisitos

- Java
- Maven
- Oracle SQL Server

## Começando

Siga estas etapas para colocar o projeto em funcionamento na sua máquina local:

1. **Configuração do Banco de Dados**
    - Execute o arquivo [script.sql](SQL/script.sql) no seu SQL Server para configurar o banco de dados.

2. **Configuração do Maven**
    - Navegue até o diretório que contém o arquivo [pom.xml](pom.xml).
    - Instale todas as dependencias do projeto.

4. **Configurando os dados de acesso ao Banco de dados**
    - Navegue até o diretório que contém o arquivo [DatabaseConnection.java](src/main/java/org/fiap/connections/DatabaseConnection.java).
    - Altere as variáveis `url`, `user` e `password` para os valores correspondentes ao seu banco de dados.
   
5. **Executando a classe FeedDatabase**
    - Execute a classe [FeedDatabase.java](src/test/java/org/fiap/FeedDatabase.java) para garantir a pré-configuração do banco de dados.

6. **Executando a Aplicação**
    - Execute a classe [Main.java](src/main/java/org/fiap/Main.java) para iniciar a aplicação.
   
Por favor, note que o script `script.sql` é necessário para configurar o banco de dados.  
Ele contém as tabelas `Carta` e `Colecao` e as relações entre elas.  
Em seguida é necessário executar a classe `Test.java` para popular o banco de dados com dados de teste.  
Finalmente, a classe `Main.java` é executada para iniciar a aplicação.


## Regras de Negócio
- Não é possível adicionar uma carta a uma coleção que já possui uma carta com o mesmo código de carta.
- Não é possível adicionar uma carta a uma coleção se a coleção não existir.
- Não é possível deletar uma coleção se ela possuir cartas.
- Não é possível adicionar mais de um super trunfo a uma coleção.
- As coleções podem possuir nomes iguais, mas serão diferenciadas pelo seu ID;

## POSTMAN
- O arquivo [CP05-DDD.postman_collection.json](POSTMAN/CP05.postman_collection.json) contém uma coleção de requisições para serem feitas no Postman.

## Construído Com
- [Java](https://www.java.com/)
- [Maven](https://maven.apache.org/)
- [SQL](https://www.microsoft.com/sql-server/)

## Autores

- Mauricio Pereira - [LinkedIn](https://www.linkedin.com/in/mauriciovpereira/) - [GitHub](https://github.com/Mauricio-Pereira)

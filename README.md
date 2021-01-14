## CITY

## Descrição do projeto

Esse projeto consiste na manutenção de dados das cidades. Podendo realizar ações de consulta e inserção de dados.

## Pré requisitos

- [Java 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- [MySQL](https://www.mysql.com/)
- [Lombok](https://projectlombok.org/)

## Dependências utilizadas

- Spring Boot Framework: Controlar o projeto e diminuir a necessidade de configurações.
- Apache commons: Facilitar a escrita do código com classes utilitarias
- MySQL: Integração com o SGBD
- Swagger: Documentação da API
- Flyway: Automatizar  a criação de scripts SQL 
- Junit: Criação de testes unitários
- Mockito: Criação de testes unitários
- Lombok: Evitar a repetição de código

## Para funcionar 

- Download do projeto

No terminal, clone o projeto:

```
git clone https://github.com/heltonalmeida/city.git
```

- Configurando SGBD

Visto a aplicação utilizar o MySQL, podemos obter seu instalador atraves do [Download](https://www.mysql.com/downloads/) 
ou tendo o [Docker](https://www.docker.com/) configurado, para obter uma instância execute o comando abaixo.  

```
docker run -it --name mysql -e MYSQL_ROOT_PASSWORD=root -p 3306:3306 -d mysql:8
```

Quanto a configuração das credencias para acesso ao banco, no arquivo application.yml já contém as configurações necessárias para a aplicação se conectar ao MySQL.

Na primeira vez que estiver executando o projeto, através do Flaway sera executado no banco os scripts contidos no caminho src\main\resources\db para criação de tabela e massa de dados. Para que isso aconteça é necessário já ter criado o database tb_city.

## Para apreciação

- Swagger 

```
http://localhost:5000/api/swagger-ui.html
```
- Postman

Na raiz do projeto esta contido o arquivo CITY_API.postman_collection.json





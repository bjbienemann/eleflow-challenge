# Eleflow Challenge
Para o desafio da API REST foi utilizado o spring webflux.

## Requisitos
* Java 11+
* MySQL 8+
* Docker 20+
* Docker-compose 1.29+

## Variáveis de ambiente
Segue as variáveis de configuração do banco de dados MySQL, caso não seja informada será considerado os valores por padrão.
```
MYSQL_HOST=localhost
MYSQL_PORT=3306
MYSQL_USERNAME=root
MYSQL_PASSWORD=m3e&tdmUmCqR
```

## Comandos
Os comandos devem ser executados na raiz da pasta deste repositório.

### Docker
Segue o comando de construção e execução:
```
docker-compose up
```
Para finalizar a execução e remover o volume com os dados do container docker:
```
docker-compose down -v
```

### Maven
Segue o comando para execução:
```
mvnw spring-boot:run
```

### Testes
Para executar os testes de integração precisamos do docker, estamos utilizando testcontainers para iniciar um container de testes com mysql que deve ser descartado ao fim da execução. Segue o comando:
```
mvnw verify
```

## Documetação
A documentação está disponível com OpenAPI e Swagger, segue o link para acesso local:
```
http://localhost:8080/swagger-ui.html
```

## Referência
* [Spring Guides](https://spring.io/guides/)
* [R2DBC Homepage](https://r2dbc.io)
* [Testcontainers](https://www.testcontainers.org/)
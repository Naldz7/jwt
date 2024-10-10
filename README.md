# JWT Validator

Aplicação que valida tokens JWT.

## Descrição

Projeto desenvolvido em Java versão 17 e Spring Boot como framework.

O endpoint criado é um GET que recebe no header um parametro chamado jwt

Ex.
```bash
curl --location 'http://localhost:8080/jwt/validate' \
--header 'jwt: eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiQWRtaW4iLCJTZWVkIjoiNzg0MSIsIk5hbWUiOiJUb25pbmhvIEFyYXVqbyJ9.QY05sIjtrcJnP533kQNk8QXcaleJ1Q01jWY_ZzIZuAg'
```


## Pré-requisitos

- Java 17 ou superior;
- IDE com suporte à linguagem Java (Eclipse, IntelliJ ou Visual Studio Code);
- Docker.

## Estrutura

![Estrutura](./docs/images/estrutura.png)

- config: Arquivos de configuração (Swagger);
- controllers: Chamadas da api (validação do token JWT);
- loggers: Abstração de logs das chamadas;
- services: Camada responsável pelo processamento das chamadas;
- utils: Arquivos que suportam toda a aplicação;
- validators: Arquivos responsáveis pelas validações do token JWT;

## Executando o projeto

1. Clone o repositório;
2. Execute o comando para iniciar a aplicação:
```bash
mvn clean install spring-boot:run
```
3. O serviço roda na porta: 8080.
```bash
curl --location 'http://localhost:8080/jwt/validate' \
--header 'jwt: eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiQWRtaW4iLCJTZWVkIjoiNzg0MSIsIk5hbWUiOiJUb25pbmhvIEFyYXVqbyJ9.QY05sIjtrcJnP533kQNk8QXcaleJ1Q01jWY_ZzIZuAg'
```
4. Documentação: http://localhost:8080/swagger-ui/index.html

![Swagger-01 Screenshot](./docs/images/swagger.png)

### Docker

#### Executando o projeto

1. Crie uma imagem docker executando o comando abaixo no diretório raiz:
```bash
docker build -t jwt .
```
2. Subir a aplicação:
```bash
docker run -t -p 9000:8080 jwt
```
3. Realizar chamadas considerando a porta 9000.
```bash
curl --location 'http://localhost:9000/jwt/validate' \
--header 'jwt: eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiQWRtaW4iLCJTZWVkIjoiNzg0MSIsIk5hbWUiOiJUb25pbmhvIEFyYXVqbyJ9.QY05sIjtrcJnP533kQNk8QXcaleJ1Q01jWY_ZzIZuAg'
```

## Resultado dos testes

_TODO: Implementar testes unitários e de integração (com os cenários de validação)_ 

Para validar os cenários pode-se utilizar cURL ou o próprio swagger alternando entre `Development - Local` ou `Development - Docker`

É possível conferir o log das requisições dentro da pasta `./logs/application_logback.log` sendo possível importar o arquivo em alguma estrutura ELK por exemplo.

Documentação: http://localhost:8080/swagger-ui/index.html

### Cenário 1
#### REQUEST
```bash
curl --location 'http://localhost:8080/jwt/validate' \
--header 'jwt: eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiQWRtaW4iLCJTZWVkIjoiNzg0MSIsIk5hbWUiOiJUb25pbmhvIEFyYXVqbyJ9.QY05sIjtrcJnP533kQNk8QXcaleJ1Q01jWY_ZzIZuAg'
```
#### RESPONSE 200 OK
```
true
```

### Cenário 2
#### REQUEST
```bash
curl --location 'http://localhost:8080/jwt/validate' \
--header 'jwt: eyJhbGciOiJzI1NiJ9.dfsdfsfryJSr2xrIjoiQWRtaW4iLCJTZrkIjoiNzg0MSIsIk5hbrUiOiJUb25pbmhvIEFyYXVqbyJ9.QY05fsdfsIjtrcJnP533kQNk8QXcaleJ1Q01jWY_ZzIZuAg'
```
#### RESPONSE 400 BAD REQUEST
```
false
```
### Cenário 3
#### REQUEST
```bash
curl --location 'http://localhost:8080/jwt/validate' \
--header 'jwt: eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiRXh0ZXJuYWwiLCJTZWVkIjoiODgwMzciLCJOYW1lIjoiTTRyaWEgT2xpdmlhIn0.6YD73XWZYQSSMDf6H0i3-kylz1-TY_Yt6h1cV2Ku-Qs' \
```
#### RESPONSE 400 BAD REQUEST
```
false
```
### Cenário 4
#### REQUEST
```bash
curl --location 'http://localhost:8080/jwt/validate' \
--header 'jwt: eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiTWVtYmVyIiwiT3JnIjoiQlIiLCJTZWVkIjoiMTQ2MjciLCJOYW1lIjoiVmFsZGlyIEFyYW5oYSJ9.cmrXV_Flm5mfdpfNUVopY_I2zeJUy4EZ4i3Fea98zvY'
```
#### RESPONSE 400 BAD REQUEST
```
false
```
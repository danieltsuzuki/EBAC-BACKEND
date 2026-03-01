### Instruções para rodar o projeto localmente

#### 1) Pré-requisitos
- **Java** (JDK) instalado (recomendado JDK 17+ ou o que seu projeto estiver configurado)
- **Maven**
- **PostgreSQL** instalado e rodando

#### 2) Criar o banco no PostgreSQL
Você precisa criar um banco para a aplicação. Exemplo via `psql`:

```sql
CREATE DATABASE streaming_service;
```

Opcionalmente, você pode criar um usuário próprio (caso não queira usar o `postgres`):

```sql
CREATE USER streaming_user WITH PASSWORD 'sua_senha';
GRANT ALL PRIVILEGES ON DATABASE streaming_service TO streaming_user;
```

#### 3) Configurar o `application.properties` (ou `application.yml`)
No arquivo `src/main/resources/application.properties`, configure a conexão com o PostgreSQL, informando **host**, **porta**, **nome do banco**, **usuário** e **senha**.

Exemplo:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/streaming_service
spring.datasource.username=streaming_user
spring.datasource.password=sua_senha

spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=true
```

> Importante: ajuste `streaming_service`, `streaming_user` e `sua_senha` para os valores que você criou no seu Postgres.

Se seu projeto usa migrations (Flyway/Liquibase), mantenha `spring.jpa.hibernate.ddl-auto=none` para não conflitar com as tabelas.

#### 4) Rodar as migrations
Se você estiver usando Flyway/Liquibase, as migrations devem rodar automaticamente ao subir a aplicação (dependendo da sua configuração).
Caso não use, você terá que executar manualmente o script SQL de criação das tabelas no banco.

#### 5) Subir a aplicação

##### Com Maven
Na raiz do projeto:

```bash
mvn spring-boot:run
```

Ou build + run:

```bash
mvn clean package
java -jar target/*.jar
```

#### 6) Acessos úteis
- Aplicação: `http://localhost:8080`
- Swagger UI (se habilitado): `http://localhost:8080/swagger-ui/index.html`
- OpenAPI JSON: `http://localhost:8080/v3/api-docs`

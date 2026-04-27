# Create Customer API 🚀

[![Java](https://img.shields.io/badge/Java-21-orange?style=flat-square&logo=java)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.7-green?style=flat-square&logo=spring)](https://spring.io/projects/spring-boot)
[![MySQL](https://img.shields.io/badge/MySQL-8.0-blue?style=flat-square&logo=mysql)](https://www.mysql.com/)
[![License](https://img.shields.io/badge/License-MIT-yellow?style=flat-square)](LICENSE)

Uma API REST robusta e profissional para gerenciamento de cadastro de clientes, desenvolvida com **arquitetura em
camadas** e **Clean Code**, seguindo as melhores práticas de desenvolvimento.

## 📋 Índice

- [Visão Geral](#visão-geral)
- [Requisitos](#requisitos)
- [Arquitetura](#arquitetura)
- [Instalação](#instalação)
- [Configuração](#configuração)
- [Endpoints](#endpoints)
- [Exemplos de Uso](#exemplos-de-uso)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Tecnologias](#tecnologias)
- [Testes](#testes)
- [Contribuição](#contribuição)

## 🎯 Visão Geral

A **Create Customer API** é uma solução completa para gerenciamento de dados de clientes, integrando serviços externos
de busca de endereço (ViaCEP) e oferecendo operações CRUD completas com tratamento robusto de erros e validação de
dados.

### Funcionalidades Principais

✅ Cadastro de novos clientes  
✅ Busca de clientes por CPF  
✅ Listagem de todos os clientes  
✅ Deleção de clientes  
✅ Integração com API ViaCEP para validação de CEP  
✅ Documentação automática com Swagger/OpenAPI  
✅ Tratamento centralizado de exceções  
✅ Logging estruturado com Logback  
✅ Testes automatizados com JUnit e Mockito

## 🔧 Requisitos

- **Java 21+** (JDK)
- **MySQL 8.0+**
- **Gradle 8.x+** ou **Maven 3.8.x+**
- **Git**

## 🏗️ Arquitetura

O projeto segue a arquitetura **Clean Architecture** com separação clara de responsabilidades:

```
create-customer-api/
│
├── domain                    # Camada de domínio
│   ├── model/               # Entidades (Cliente, ZipCode)
│   ├── exception/           # Exceções de negócio
│   └── parameters/          # DTOs de entrada (ClientRequest)
│
├── application              # Camada de aplicação
│   └── service/             # Casos de uso e orquestração de negócio
│
├── infrastructure           # Camada de infraestrutura
│   ├── customer/             # Integrações com APIs externas (ViaCEP)
│   └── repository/         # Implementações de persistência (JPA)
│
└── presentation            # Camada de apresentação
    └── controller/         # Endpoints REST
```

### Benefícios da Arquitetura

- **Independência de Frameworks**: Lógica de negócio desacoplada
- **Testabilidade**: Facilita testes unitários e de integração
- **Manutenibilidade**: Código organizado e previsível
- **Escalabilidade**: Fácil adicionar novas funcionalidades

## 💾 Instalação

### 1. Clone o Repositório

```bash
git clone https://github.com/seu-usuario/create-customer-api.git
cd create-customer-api
```

### 2. Configure o Banco de Dados

Crie o banco de dados no MySQL:

```sql
-- Criar schema
CREATE SCHEMA digibank;

-- Criar tabela de clientes
CREATE TABLE digibank.TB_CLIENTE (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    NAME VARCHAR(255),
    CPF VARCHAR(11) NOT NULL UNIQUE,
    RG VARCHAR(9),
    ADDRESS VARCHAR(255),
    CEP VARCHAR(8),
    EMAIL VARCHAR(100),
    TELEPHONE VARCHAR(15),
    DESCRIPTION VARCHAR(255),
    BIRTH_DATE DATE,
    CREATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UPDATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Criar índices para melhor performance
CREATE INDEX idx_cpf ON digibank.TB_CLIENTE(CPF);
CREATE INDEX idx_email ON digibank.TB_CLIENTE(EMAIL);
```

### 3. Configure as Propriedades

Edite `src/main/resources/application.properties`:

```properties
# Banco de Dados
spring.datasource.url=jdbc:mysql://localhost:3306/digibank
spring.datasource.username=root
spring.datasource.password=abcde123

# Servidor
server.port=8080
spring.mvc.pathmatch.matching-strategy=ant_path_matcher

# Feign Configuration - ViaCEP Client
feign.customer.config.default.connectTimeout=5000
feign.customer.config.default.readTimeout=5000
feign.customer.config.viacep-customer.connectTimeout=3000
feign.customer.config.viacep-customer.readTimeout=3000

# Logging
logging.level.com.create.customer=DEBUG
logging.level.feign=DEBUG
```

### 4. Compile e Execute

**Com Gradle:**

```bash
./gradlew build
./gradlew bootRun
```

**Com Maven:**

```bash
mvn clean install
mvn spring-boot:run
```

## ⚙️ Configuração

### Variáveis de Ambiente (Opcional)

```bash
# Banco de Dados
DB_URL=jdbc:mysql://localhost:3306/digibank
DB_USERNAME=root
DB_PASSWORD=abcde123

# Server
SERVER_PORT=8080
```

### Logging

O projeto utiliza **Logback** com configuração em `src/main/resources/logback-spring.xml`:

- **Console Output**: Logs em tempo real no console
- **File Output**: Arquivos de log em `logs/cadastro-clientes.log`
- **Error File**: Erros separados em `logs/cadastro-clientes-error.log`

## 📡 Endpoints

### 1. Registrar Novo Cliente

```http
POST /customer/register
Content-Type: application/json
```

**Request Body:**

```json
{
  "name": "João Silva",
  "cpf": "12345678901",
  "rg": "123456789",
  "email": "joao@example.com",
  "telephone": "11987654321",
  "birthDate": "1990-05-15",
  "cep": "06172006",
  "address": "Rua Exemplo, 123",
  "description": "Cliente VIP"
}
```

**Responses:**

| Status | Descrição                             |
|--------|---------------------------------------|
| 201    | Cliente registrado com sucesso        |
| 400    | Dados inválidos ou CEP não encontrado |
| 403    | Sem permissão                         |
| 500    | Erro interno                          |

---

### 2. Buscar Cliente por CPF

```http
GET /customer/{cpf}
```

**Exemplo:**

```bash
curl -X GET http://localhost:8080/customer/12345678901
```

**Response (200):**

```json
{
  "id": 1,
  "name": "João Silva",
  "cpf": "12345678901",
  "rg": "123456789",
  "email": "joao@example.com",
  "telephone": "11987654321",
  "birthDate": "1990-05-15",
  "cep": "06172006",
  "address": "Rua Exemplo, 123",
  "description": "Cliente VIP"
}
```

---

### 3. Listar Todos os Clientes

```http
GET /customer
```

**Response (200):**

```json
[
  {
    "id": 1,
    "name": "João Silva",
    "cpf": "12345678901",
    "email": "joao@example.com",
    "telephone": "11987654321"
  },
  {
    "id": 2,
    "name": "Maria Santos",
    "cpf": "98765432109",
    "email": "maria@example.com",
    "telephone": "11999998888"
  }
]
```

---

### 4. Deletar Cliente

```http
DELETE /customer/{cpf}
```

**Exemplo:**

```bash
curl -X DELETE http://localhost:8080/customer/12345678901
```

**Response:**

| Status | Descrição                    |
|--------|------------------------------|
| 204    | Cliente deletado com sucesso |
| 404    | Cliente não encontrado       |
| 400    | CPF inválido                 |
| 403    | Sem permissão                |

---

## 🧪 Exemplos de Uso

### Com cURL

```bash
# Registrar cliente
curl -X POST http://localhost:8080/customer/register \
  -H "Content-Type: application/json" \
  -d '{
    "name": "João Silva",
    "cpf": "12345678901",
    "rg": "123456789",
    "email": "joao@example.com",
    "telephone": "11987654321",
    "birthDate": "1990-05-15",
    "cep": "06172006",
    "description": "Cliente novo"
  }'

# Buscar cliente por CPF
curl -X GET http://localhost:8080/customer/12345678901

# Listar todos os clientes
curl -X GET http://localhost:8080/customer

# Deletar cliente
curl -X DELETE http://localhost:8080/customer/12345678901
```

### Com Postman

1. Abra o Postman
2. Importe as requisições ou crie manualmente:
    - **POST** - `http://localhost:8080/customer/register`
    - **GET** - `http://localhost:8080/customer/{cpf}`
    - **GET** - `http://localhost:8080/customer`
    - **DELETE** - `http://localhost:8080/customer/{cpf}`

### Com Swagger/OpenAPI

Acesse a documentação interativa em:

```
http://localhost:8080/swagger-ui.html
```

Todos os endpoints estão automaticamente documentados com exemplos e validações.

## 📁 Estrutura do Projeto

```
create-customer-api/
│
├── src/
│   ├── main/
│   │   ├── java/com/create/customer/
│   │   │   ├── Application.java                 # Entry point da aplicação
│   │   │   │
│   │   │   ├── domain/                          # Camada de Domínio
│   │   │   │   ├── model/                       # Entidades JPA
│   │   │   │   ├── exception/                   # Exceções customizadas
│   │   │   │   └── parameters/                  # DTOs (ClientRequest, etc)
│   │   │   │
│   │   │   ├── application/                     # Camada de Aplicação
│   │   │   │   └── service/                     # Lógica de negócio
│   │   │   │       ├── ClientRegistrationService
│   │   │   │       ├── ClientRegistrationUseCase
│   │   │   │       ├── ZipCodeService
│   │   │   │       └── impl/
│   │   │   │
│   │   │   ├── infrastructure/                  # Camada de Infraestrutura
│   │   │   │   ├── customer/                      # Clientes HTTP (Feign)
│   │   │   │   │   └── ViaCepClient
│   │   │   │   └── repository/                  # Persistência (JPA)
│   │   │   │       └── ClienteRepository
│   │   │   │
│   │   │   ├── presentation/                    # Camada de Apresentação
│   │   │   │   └── controller/                  # REST Controllers
│   │   │   │       ├── ClientRegistrationController
│   │   │   │       └── ClientRegistrationControllerImpl
│   │   │   │
│   │   │   └── utils/                           # Utilitários
│   │   │       └── queries/
│   │   │
│   │   └── resources/
│   │       ├── application.properties           # Configurações da aplicação
│   │       └── logback-spring.xml               # Configuração de logs
│   │
│   └── test/
│       ├── java/com/create/customer/
│       │   ├── repository/                      # Testes de repositório
│       │   ├── service/                         # Testes de serviço
│       │   └── controller/                      # Testes de controller
│       └── resources/
│           ├── features/                        # Testes BDD com Gherkin
│           └── mockito-extensions/
│
├── build/                                       # Artefatos compilados
├── logs/                                        # Arquivos de log
│
├── build.gradle                                 # Configuração Gradle
├── gradlew & gradlew.bat                        # Gradle Wrapper
├── mvnw & mvnw.cmd                              # Maven Wrapper (opcional)
├── schema.sql                                   # Script inicial de BD
├── test-api.ps1                                 # Script de testes (PowerShell)
├── test-api.sh                                  # Script de testes (Bash)
└── README.md                                    # Este arquivo
```

## 🛠️ Tecnologias

| Tecnologia                 | Versão   | Propósito                 |
|----------------------------|----------|---------------------------|
| **Java**                   | 21       | Linguagem de programação  |
| **Spring Boot**            | 3.3.7    | Framework web             |
| **Spring Data JPA**        | 3.3.7    | ORM e persistência        |
| **Spring Cloud OpenFeign** | 2023.0.1 | Cliente HTTP declarativo  |
| **MySQL**                  | 8.0+     | Banco de dados relacional |
| **Lombok**                 | 1.18.30  | Redução de boilerplate    |
| **Swagger/OpenAPI**        | 2.5.0    | Documentação interativa   |
| **JUnit 5**                | 5.10+    | Framework de testes       |
| **Mockito**                | 5.11.0   | Mocks para testes         |
| **Logback**                | 1.4+     | Framework de logging      |
| **Jacoco**                 | 0.8.8    | Cobertura de testes       |

## 🧪 Testes

### Executar Testes Unitários

```bash
# Com Gradle
./gradlew test

# Com Maven
mvn test
```

### Gerar Relatório de Cobertura

```bash
# Com Gradle
./gradlew jacocoTestReport

# Abrir relatório
# Windows: start build\reports\jacoco\test\html\index.html
# Linux/Mac: open build/reports/jacoco/test/html/index.html
```

### Tipos de Testes Implementados

- **Testes Unitários**: Testam componentes isolados
- **Testes de Integração**: Testam interação entre camadas
- **Testes BDD**: Testes em linguagem natural com Gherkin

## 🤝 Contribuição

Contribuições são bem-vindas! Por favor:

1. Faça um Fork do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

## 📝 Licença

Este projeto está licenciado sob a Licença MIT - veja o arquivo [LICENSE](LICENSE) para detalhes.

## 👨‍💻 Autor

Desenvolvido com ❤️ por sua equipe de desenvolvimento.

---

## ❓ FAQ

**P: Como validar um CPF?**  
R: O CPF é validado automaticamente na camada de negócio. Aceita 11 dígitos numéricos e verifica sua validade.

**P: O CEP é obrigatório?**  
R: Sim, a API faz integração com ViaCEP para validar e completar o endereço automaticamente.

**P: Como são tratadas as exceções?**  
R: As exceções são tratadas pelo `HandlerControllerImpl` que retorna respostas padronizadas com mensagens claras.

**P: Qual é a taxa de requisições máxima?**  
R: Atualmente não há rate limiting implementado. Configure conforme necessário em produção.

**P: A API está pronta para produção?**  
R: O código segue boas práticas, mas antes de produção, implemente autenticação, HTTPS e rate limiting.

## 🔗 Links Úteis

- [Documentação Spring Boot](https://spring.io/projects/spring-boot)
- [API ViaCEP](https://viacep.com.br/)
- [Swagger/OpenAPI 3.0](https://swagger.io/)
- [MySQL Documentation](https://dev.mysql.com/doc/)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)

---

**Última atualização**: Abril de 2026  
**Status**: ✅ Em Desenvolvimento  
**Versão**: 0.0.1-SNAPSHOT

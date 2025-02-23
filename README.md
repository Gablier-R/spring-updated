# Projeto Spring Boot com Docker

Este é um projeto Spring Boot configurado para ser executado localmente ou em contêineres Docker. Ele também pode ser facilmente puxado do Docker Hub.

## Tecnologias Utilizadas

- **Java 17**: A versão do Java usada no projeto.
- **Spring Boot 3.2.5**: Framework principal para o desenvolvimento da aplicação.
- **Spring Cloud OpenFeign**: Usado para realizar requisições HTTP de forma fácil e declarativa para a **DummyJSON API**.
- **Docker**: Utilizado para containerizar a aplicação, facilitando a execução em diferentes ambientes.
- **Docker Compose**: Ferramenta que orquestra múltiplos containers, permitindo rodar a aplicação e seus serviços de forma simplificada.
- **DummyJSON API**: API externa utilizada para simular dados de produtos.

## Como o OpenFeign é Usado

O **OpenFeign** é uma ferramenta poderosa do Spring Cloud que permite criar clientes HTTP de forma declarativa. Ele facilita a integração com APIs externas, como a **DummyJSON**, sem precisar escrever o código manualmente para fazer as requisições.

No projeto, o **OpenFeign** é configurado para realizar chamadas GET para a **DummyJSON API** e buscar informações sobre os produtos. A configuração do Feign Client no código seria algo como:


## Passos para Executar o Projeto

### Pré-requisitos

- **Java 17**
- **Maven 3.8.x**
- **Docker**

### Executar a Aplicação

1. Clone o repositório:

    ```bash
    git clone https://github.com/Gablier-R/spring-updated.git
    cd dummyjson-client
    ```

2. Compile e execute o projeto:

    ```bash
    mvn clean install
    mvn spring-boot:run
    ```

3. Acesse o serviço:

    O serviço estará disponível em `http://localhost:8080`.

### Executar Testes

Para executar os testes unitários:

```bash
mvn clean test
```


#### 2. Rodando o projeto atraves do compose

Execute os comandos docker em relação ao compose para ser buildado e executado. Temos a opção de rodar em 2 ambientes, tanto em yaml e tambem em dev.

```bash
docker-compose -f docker-compose-dev.yaml up --build
docker-compose -f docker-compose-dev.dev up --build
```

#### 3. Rodando uma imagem do dockerhub
```bash
docker pull gablier/spring-app:latest
docker run -p 8080:8080 gablier/spring-app:latest
```

Importe a imagem para o local, e depois execute ela no docker. A porte que vai rodar na sua maquina fica a sua escolha. Mas a porta necessária para acessar o container precisa ser a 8080.

## Estrutura do Projeto

```bash
dummyjson-client
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com.example.dummyjsonclient
│   │   │       ├── DummyJsonClientApplication.java
│   │   │       ├── controller
│   │   │       │   └── ProductController.java
│   │   │       │   └── Health.java
│   │   │       ├── dto
│   │   │       │   └── Product.java
│   │   │       │   └── ResponseDTO.java
│   │   │       ├── service
│   │   │       │   └── ClientProductsService.java
│   │   │       │   └── ProductService.java
│   │   └── resources
│   │       └── application.yaml
│   └── test
│       ├── java
│       │   └── com.example.dummyjsonclient
│       │       └── controller
│       │       │   └── ProductControllerTestIT.java
│       │       │   └── ProductControllerTestUNIT.java
│       │       ├── dto
│       │       │   └── ProductTest.java
│       │       │   └── ResponseDTOTest.java
│       │       └── service
│       │           └── ClientProductsServiceTest.java
│       │           └── ProductServiceTest.java
│       └── resources
└── pom.xml
```

# Descrição dos Recursos da API

Abaixo estão os detalhes sobre os recursos disponíveis na API, incluindo os endpoints que você pode acessar para interagir com a aplicação.

## Endpoints de Produtos 🛒

- http://localhost:8080/**local**/api/v1/products
- http://localhost:8080/**hml**/api/v1/products
- http://localhost:8080/**dev**/api/v1/products

### 1. **Obter todos os produtos**

- **Endpoint**: `/local/api/v1/products`
- **Método**: `GET`
- **Descrição**: Retorna uma lista com todos os produtos cadastrados na aplicação.
- **Resposta Esperada**:
  - **Código HTTP**: `200 OK`
  - **Corpo**:
    ```json
    [
    {
      "id": "1",
      "title": "Produto 1",
      "description": "Descrição do produto 1",
      "price": 100.0
    },
    {
      "id": "2",
      "title": "Produto 1",
      "description": "Descrição do produto 1",
      "price": 100.0
    }
    ]
    ```

### 2. **Obter um produto específico**

- **Endpoint**: `/local/api/v1/products/{id}`
- **Método**: `GET`
- **Descrição**: Retorna os detalhes de um produto específico, identificando-o pelo ID fornecido na URL.
- **Parâmetros**:
  - `id`: O ID do produto que você deseja obter.
- **Resposta Esperada**:
  - **Código HTTP**: `200 OK`
  - **Corpo**:
    ```json
    {
      "id": "1",
      "title": "Produto 1",
      "description": "Descrição do produto 1",
      "price": 100.0
    }
    ```

### 3. **Verificar a saúde da aplicação**

- **Endpoint**: `local/api/v1/health`
- **Método**: `GET`
- **Descrição**: Verifica se a aplicação está rodando corretamente.
- **Resposta Esperada**:
  - **Código HTTP**: `200 OK`
  - **Corpo**:
    ```json
    {
      Service run
    }
    ```

---

## Como Interagir com a API 🔧

### Local

Para fazer chamadas à API localmente, você deve usar a seguinte URL de base:

```bash
http://localhost:8080/local/api/v1/products

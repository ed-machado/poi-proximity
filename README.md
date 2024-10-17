# POI Proximity API

## Descrição

A **POI Proximity API** é uma aplicação desenvolvida em **Java** com **Spring Boot** que permite aos usuários cadastrar, listar e encontrar pontos de interesse (POI) próximos a uma localização específica. Utilizando um banco de dados H2 em memória, a API simula pontos de interesse, permitindo a consulta pela distância a partir das coordenadas fornecidas.

## Funcionalidades

A API possui as seguintes funcionalidades:

1. **Cadastrar Ponto de Interesse (POI)**
   - Endpoint: `POST /poi`
   - Descrição: Permite cadastrar um novo ponto de interesse no banco de dados.
   - Corpo da requisição:
     ```json
     {
       "x": 27,
       "y": 12,
       "name": "Lanchonete"
     }
     ```

2. **Listar Pontos de Interesse**
   - Endpoint: `GET /poi`
   - Descrição: Lista todos os pontos de interesse cadastrados com suporte à paginação.
   - Parâmetros:
     - `page`: Número da página (padrão: 0)
     - `pageSize`: Tamanho da página (padrão: 10)

3. **Buscar Pontos de Interesse Próximos**
   - Endpoint: `GET /poi/near-me`
   - Descrição: Retorna todos os pontos de interesse que estão dentro de uma distância máxima em relação às coordenadas fornecidas.
   - Parâmetros:
     - `x`: Coordenada X de referência
     - `y`: Coordenada Y de referência
     - `max`: Distância máxima a partir das coordenadas fornecidas
   - Exemplo de chamada:
     ```
     GET /poi/near-me?x=20&y=10&max=10
     ```

## Estrutura do Projeto

A API foi construída com as seguintes camadas:

1. **Controller**: Responsável por receber as requisições HTTP e retornar as respostas. A lógica de negócios foi delegada para o Service.
   - `PointOfInterestController`: Contém os endpoints para listar, cadastrar e buscar POIs próximos.

2. **Service**: Camada intermediária responsável por implementar a lógica de negócios, como o cálculo da distância e a busca por POIs dentro do raio especificado.
   - `PointOfInterestService`: Contém métodos para salvar, listar e calcular a distância entre coordenadas.

3. **Repository**: Responsável por realizar operações no banco de dados H2.
   - `PointOfInterestRepository`: Extende `JpaRepository` para facilitar a comunicação com o banco de dados.

4. **Entity**: Representa a entidade `PointOfInterest`, que contém os campos `x`, `y` (coordenadas) e `name` (nome do ponto de interesse).

5. **DTO (Data Transfer Object)**: Utilizado para transferir dados do corpo da requisição para a camada de serviço.
   - `CreatePointOfInterest`: DTO que contém as informações necessárias para criar um novo POI.

## Inicialização

Para rodar a aplicação localmente, siga os passos abaixo:

1. Clone o repositório:
   ```bash
   git clone https://github.com/ed-machado/poi-proximity.git
   ```
   
2. Acesse o diretório do projeto:
   ```bash
   cd poi-proximity
   ```
   
3. Execute o projeto usando Maven:
   ```bash
   ./mvnw spring-boot:run
   ```
   
4. A API estará disponível em `http://localhost:8080.`

## Exemplos de Uso

### Cadastrar um Ponto de Interesse

```bash
curl -X POST http://localhost:8080/poi \
-H "Content-Type: application/json" \
-d '{
  "x": 31,
  "y": 18,
  "name": "Posto"
}'
```

### Listar Pontos de Interesse

```bash
curl http://localhost:8080/poi?page=0&pageSize=5
```

### Buscar Pontos Próximos

```bash
curl http://localhost:8080/poi/near-me?x=20&y=10&max=10
```

## Banco de Dados

A aplicação usa o banco de dados em memória H2. Ao iniciar, alguns pontos de interesse são carregados automaticamente no banco para facilitar a simulação e os testes.

Pontos de interesse cadastrados inicialmente:

- Lanchonete (27, 12)
- Posto (31, 18)
- Joalheria (15, 12)
- Floricultura (19, 31)
- Pub (12, 8)
- Supermercado (23, 6)
- Churrascaria (28, 2)

## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot**
- **H2 Database**
- **Maven**

## Como Contribuir

1. Faça um fork do projeto.
2. Crie uma nova branch: `git checkout -b minha-nova-feature`.
3. Faça as suas alterações e commit: `git commit -m 'Minha nova feature'`.
4. Envie para o repositório remoto: `git push origin minha-nova-feature`.
5. Crie um Pull Request.

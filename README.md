# Treino API

API REST em Java e Spring Boot para cadastrar alunos, registrar treinos e sugerir a próxima carga de um exercício com base no desempenho registrado.

O destaque do projeto é uma regra simples de progressão: quando o aluno alcança a meta de repetições, a API aumenta a carga em 5% e arredonda o resultado para o múltiplo de 0,25 kg mais próximo. Se a meta não for alcançada, mantém a carga anterior.

## Tecnologias

- Java 21
- Spring Boot 4.1.0, Spring Web MVC e Spring Data JPA
- H2 em memória
- Springdoc OpenAPI para documentação interativa
- Maven

## Como rodar

Tenha o **Java 21** instalado. Na raiz do projeto, execute:

```bash
./mvnw spring-boot:run
```

No Windows, use `mvnw.cmd spring-boot:run`. A API estará disponível em `http://localhost:8080`.

Com a aplicação em execução, acesse:

- **Documentação interativa:** [Swagger UI](http://localhost:8080/swagger-ui.html)
- **Console do H2:** [localhost:8080/h2-console](http://localhost:8080/h2-console)

Para entrar no H2, use a URL JDBC `jdbc:h2:mem:treinodb`, usuário `sa` e senha em branco. O banco é mantido em memória; os registros são perdidos quando a aplicação para.

## Endpoints

| Método | Rota | O que faz |
| --- | --- | --- |
| `POST` | `/alunos` | Cadastra um aluno |
| `GET` | `/alunos` | Lista os alunos |
| `GET` | `/alunos/{id}` | Busca um aluno pelo ID |
| `POST` | `/alunos/{alunoId}/treinos` | Registra um treino com exercícios para o aluno |
| `GET` | `/exercicios/{exercicioTreinoId}/progressao?repeticoesAlvo=10` | Sugere a próxima carga para um exercício registrado |

### Exemplo de uso

**1. Cadastre um aluno:**

```bash
curl -X POST http://localhost:8080/alunos \
  -H 'Content-Type: application/json' \
  -d '{"nome":"Ana Silva","email":"ana@example.com","pesoKg":68.5}'
```

Guarde o `id` retornado. Nos exemplos abaixo, ele é `1`.

**2. Registre um treino:**

```bash
curl -X POST http://localhost:8080/alunos/1/treinos \
  -H 'Content-Type: application/json' \
  -d '{
    "nome": "Treino A",
    "data": "2026-09-19",
    "exercicios": [
      {
        "nomeExercicio": "Agachamento",
        "tipo": "MUSCULACAO",
        "series": 3,
        "repeticoes": 10,
        "cargaKg": 40.0
      }
    ]
  }'
```

Os tipos aceitos são `MUSCULACAO`, `CARDIO` e `MOBILIDADE`. Guarde o `id` do exercício retornado no treino.

**3. Consulte a sugestão de progressão:**

```bash
curl 'http://localhost:8080/exercicios/1/progressao?repeticoesAlvo=10'
```

Para um exercício com 10 repetições e 40 kg, a sugestão é **42 kg**. Se o registro tiver menos de 10 repetições, a sugestão permanece em **40 kg**. A resposta inclui `exercicio`, `cargaAnterior` e `cargaSugerida`.

## Organização do código

```text
src/main/java/com/hugolini/treino_api/
├── controller/   # rotas HTTP
├── service/      # regras de negócio
├── repository/   # acesso aos dados
├── model/        # entidades persistidas
├── exception/    # tratamento de erros
└── utils/        # tipos auxiliares
```

Projeto desenvolvido para fins de estudo de APIs REST, persistência com JPA e organização em camadas.

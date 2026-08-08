# 📄 CONTEXT.md - Guia do Projeto & Comportamento da IA

## 👤 Perfil do Desenvolvedor
- **Experiência:** Desenvolvedor Sênior com 13+ anos de bagagem em C#, .NET, ASP.NET Core e Azure.
- **Objetivo Atual:** Transição de carreira/tecnologia para a stack Java 21 + Spring Boot 3 + AWS no Itaú.
- **Conhecimento Técnico:** Já domina DDD, Clean Architecture, SOLID, Design Patterns, ORM e APIs RESTful, além de ter forte base em POO.
- **Necessidade:** Aprender as convenções, anotações, sintaxe e peculiaridades do ecossistema Java/Spring de forma prática. Não precisa de explicações conceituais teóricas de arquitetura ou lógica de programação; o foco é o de-para C# → Java.
- **Estilo de Aprendizado:** Quer escrever o código ele mesmo, com passo a passo guiado, snippets curtos e explicações diretas sobre diferenças sintáticas e semânticas entre as linguagens.

---

## 🤖 Regras de Comportamento e Atuação da IA
1. **Papel:** Atuar como Mentora e Instrutora Técnica Sênior em Java, com foco em transição de carreira C# → Java.
2. **Modo Didático:** NUNCA escreva o código completo diretamente nos arquivos e NUNCA execute comandos no terminal pelo usuário. O usuário deve escrever o código para aprender de verdade.
3. **Fluxo de Trabalho:**
   - Forneça o passo a passo com o raciocínio e a sintaxe recomendada.
   - Mostre snippets curtos e exemplos para o usuário digitar/colar.
   - Explique diferenças reais entre C# e Java, como: `record` vs classe, `sealed`/`readonly` vs `final`, `var` vs inferência em Java, `using`/`namespace` vs `package`, construtores, anotações, dependency injection, `List<T>` e coleções, `Optional`, `null` e exceções.
   - Se o usuário encontrar um erro, analise o log, identifique a causa raiz e explique onde e por que a correção deve ser feita.
4. **Comunicação Direct-to-Point:** Use comparações diretas C# → Java, mantendo o ensino objetivo, prático e alinhado ao contexto do projeto.
5. **Foco de Ensino:** Priorizar sintaxe, convenções e conceitos Java que não são idênticos ao C#; evitar explicações excessivamente teóricas quando o objetivo é aprender pela prática.

---

## 🚀 Objetivo do Projeto: Company Registry API
Aplicação de rampa de aceleração para praticar Java 21, Spring Boot 3, Lombok e Spring Data JPA em um CRUD simples de cadastro de empresas PJ.

---

## 📂 Estrutura de Pastas e Arquivos Reais
A inspeção do disco mostrou a seguinte estrutura atual do projeto:

```text
company-registry/
├── src/main/java/com/itau/company_registry/
│   ├── CompanyRegistryApplication.java
│   ├── controller/
│   │   └── PingController.java
│   ├── dto/
│   │   ├── CompanyResponse.java
│   │   └── CreateCompanyRequest.java
│   ├── model/
│   │   └── Company.java
│   └── service/
│       └── CompanyService.java
├── src/main/resources/
│   └── application.properties
├── src/test/java/com/itau/company_registry/
│   ├── CompanyRegistryApplicationTests.java
│   └── controller/
└── pom.xml
```

### Arquivos reais atualmente presentes
- `src/main/java/com/itau/company_registry/CompanyRegistryApplication.java`
  - Classe bootstrap do Spring Boot.
- `src/main/java/com/itau/company_registry/controller/PingController.java`
  - Controller REST com endpoints para readiness e companies.
- `src/main/java/com/itau/company_registry/service/CompanyService.java`
  - Camada de serviço com lógica de criação e leitura em memória.
- `src/main/java/com/itau/company_registry/dto/CreateCompanyRequest.java`
  - DTO de entrada para criar empresa.
- `src/main/java/com/itau/company_registry/dto/CompanyResponse.java`
  - DTO de saída para retornar dados da empresa.
- `src/main/java/com/itau/company_registry/model/Company.java`
  - Modelo simples com `id` e `name`.
- `src/main/resources/application.properties`
  - Configuração atual do Spring e do banco.
- `src/test/java/com/itau/company_registry/CompanyRegistryApplicationTests.java`
  - Teste básico de contexto do Spring.

---

## 📌 Status do Código e Persistência
### Como os dados estão sendo manipulados hoje
O código atual ainda não usa banco de dados relacional para persistência. A lógica de armazenamento está em memória, mas já foi isolada na camada de serviço.

#### Comportamento atual
- A camada `CompanyService` mantém uma lista em memória com `List<Company>`.
- O método `POST /companies` cria uma nova instância de `Company`, define um `id` incremental e adiciona o objeto à lista em memória.
- O método `GET /companies` devolve essa lista a partir do serviço.
- O controller ficou mais fino e delega a responsabilidade para o service.
- Ainda não existe `@Entity`, `JpaRepository` ou integração real com JPA.
- O modelo `Company` é um POJO simples, sem anotações JPA.

#### Persistência real hoje
- A persistência é temporária e volátil.
- Os dados somem quando a aplicação reinicia.
- O arquivo `application.properties` já traz configuração para H2 e Hibernate, mas isso ainda não está sendo utilizado pela implementação atual, porque não existe integração real com JPA/Repository.

### Endpoints HTTP atualmente respondendo
Os endpoints já implementados e respondendo a partir da lista em memória são:
- `GET /ready`
- `POST /companies`
- `GET /companies`

---

## 🟡 Plano de Evolução e Próximos Passos


### Checklist de transição da memória para a infraestrutura real (07/08/2026)
- [x] Separação de responsabilidades
  - Criar um `CompanyController` dedicado para os endpoints REST.
  - Remover a lógica de armazenamento do controller e isolá-la em uma camada apropriada.
  - Separar DTOs do modelo de domínio.

- [x] Provisionamento do banco PostgreSQL
  - Criar e subir uma instância do PostgreSQL via Docker ou `docker-compose`.
  - Definir um ambiente local consistente para o desenvolvimento.

- [x] Configuração das credenciais reais no `application.properties`
  - Substituir as configurações genéricas/temporárias por credenciais reais do ambiente local ou de desenvolvimento.
  - Ajustar a URL, usuário, senha e driver do banco.

- [x] Implementação do Spring Data JPA
  - Criar `CompanyRepository extends JpaRepository<Company, Long>`.
  - Transformar a entidade `Company` em uma entidade JPA real com `@Entity` e mapeamento de colunas.
  - Substituir a lista em memória pelo repositório.

- [x] Criação da camada de serviço
  - Criar `CompanyService` para concentrar as regras de negócio.
  - O controller deve depender do serviço, e não mais da lista em memória.


### 🟡 Próximos Passos (08/08/2026)
- [x] Migração para JPA/PostgreSQL.
- [] **Robustez (Validação):** Implementar `@Valid` (em progresso) e criar `@ControllerAdvice` para tratamento centralizado de erros.
- [ ] **Testes de Integração:** Implementar testes utilizando o banco real via `@DataJpaTest` ou `@SpringBootTest`.
- [ ] **Evolução do CRUD:** Implementar métodos `GET /{id}`, `PUT /{id}` e `DELETE /{id}`.


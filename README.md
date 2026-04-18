# Talento em Linha

> ⚠️ **Atenção:** Este projeto está em fase inicial de desenvolvimento.

O Talento em linha é uma aplicação que tem por objetivo creditar pontos a funcionários com base em algumas categorias de performance, Tais pontos poderão ser trocados por prêmios junto a empresa.

![Status](https://img.shields.io/badge/Status-em%20desenvolvimento-yellow)
![Versão](https://img.shields.io/badge/Versão-0.0.1--SNAPSHOT-blue)
![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.5-brightgreen)
---

## Sobre o Projeto

O Talento em Linha é uma aplicação para gerenciar programas de pontos e bonificação de colaboradores no setor industrial.

O problema que ele resolve é simples: algumas empresas controlam esse processo na mão, via planilhas, anotações ou mesmo cartilhas, o que gera erros e dor de cabeça na hora de escalar.
Com o Talento em Linha, colaboradores acumulam pontos com base em indicadores do dia a dia e podem trocar esses pontos por brindes em um catálogo, sem precisar passar pelo gestor a cada resgate, reservando-os diretamento pela aplicação.




---

## Backlog do Produto
 - [RF01] Como gestor, quero cadastrar colaboradores para que eles possam participar do programa de pontos
 - [RF02] Como gestor, quero cadastrar indicadores (metas, segurança, qualidade, etc.) para definir como os pontos são gerados
 - [RF03] Como gestor, quero lançar pontos para um ou vários colaboradores com base em um indicador
 - [RF04] Como gestor, quero visualizar o saldo de pontos de cada colaborador
 - [RF05] Como gestor, quero cadastrar brindes no catálogo com nome, descrição e custo em pontos
 - [RF06] Como colaborador, quero visualizar o catálogo de brindes disponíveis
 - [RF07] Como colaborador, quero reservar e resgatar um brinde usando meus pontos acumulados
 - [RF08] Como colaborador, quero visualizar o histórico de resgates por periodo
 - [RF09] Como gestor, quero gerar um relatório de pontuação por período


---

## Estrutura do Projeto

```
talentoemlinha/
├── src/
│   ├── main/
│   │   ├── java/com/talentoemlinha/
│   │   │   ├── controller/      
│   │   │   ├── service/         
│   │   │   ├── repository/      
│   │   │   ├── model/           
│   │   │   └── dto/             
│   │   └── resources/
│   │       ├── db/migration/
│   │       ├── static/     
│   │       └── application.properties
│   └── test/
├── pom.xml
└── README.md
```

> A estrutura pode mudar conforme o projeto evolui.

---

## Pré-requisitos

- [Git]()
- [Java 21+]()
- [Docker]()

---

## Como Executar



### Executando a aplicação

```bash
# Clone o repositório
git clone https://github.com/marcelo-belotto/talento-em-linha.git
cd talento-em-linha

# Compile e execute
./mvnw spring-boot:run
```

As migrations do Flyway serão aplicadas automaticamente na inicialização.

A API estará disponível em `http://localhost:8080/api/`.

A documentação Swagger estará em `http://localhost:8080/swagger-ui.html`.

---

## Roadmap

### MVP (em andamento)
- [ ] Estrutura base do projeto
- [ ] Modelagem e migrations iniciais
- [ ] CRUD dos recursos principais
- [ ] Autenticação e autorização
- [ ] Interface web

---

## Como Contribuir

1. Faça um **fork** do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/minha-feature`)
3. Commit seguindo [Conventional Commits](https://www.conventionalcommits.org/pt-br/) (`git commit -m 'feat: adiciona minha feature'`)
4. Push para a branch (`git push origin feature/minha-feature`)
5. Abra um **Pull Request**

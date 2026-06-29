# 📚 Sistema de Gestão de Biblioteca

![Java](https://img.shields.io/badge/Java-17+-orange?style=flat-square&logo=java)
![Status](https://img.shields.io/badge/Status-Concluído-green?style=flat-square)
![Gson](https://img.shields.io/badge/Gson-2.10.1-blue?style=flat-square)

Sistema de gerenciamento de biblioteca desenvolvido em Java puro, com persistência de dados em JSON. Permite cadastrar livros e usuários, realizar empréstimos e devoluções, e consultar o acervo de diferentes formas.

---

## 🎯 Objetivo

Consolidar na prática os conceitos fundamentais de Java aprendidos ao longo dos estudos — orientação a objetos, coleções, Streams, Optional, tratamento de exceções e manipulação de arquivos — através de um projeto completo com estrutura próxima ao que é usado no mercado.

---

## ✨ Funcionalidades

### 📖 Gestão de Livros
- Cadastro de livros com ISBN, título, autor, ano de publicação e gênero
- Busca por título, autor, gênero ou ano de publicação
- Listagem de todos os livros ou apenas os disponíveis
- Controle de disponibilidade atualizado automaticamente a cada empréstimo e devolução

### 👤 Gestão de Usuários
- Cadastro de usuários com validação de e-mail duplicado
- Identificação única gerada automaticamente (UUID)

### 🔄 Empréstimos e Devoluções
- Registro de empréstimos com data automática e prazo de 14 dias
- Controle de status: `ATIVO` ou `DEVOLVIDO`
- Devolução com preenchimento automático da data real
- Listagem de empréstimos ativos e em atraso

### 📊 Relatórios
- Agrupamento do acervo por gênero com contagem de livros

### 💾 Persistência
- Todos os dados são salvos automaticamente em arquivos `.json`
- Os dados são carregados ao iniciar o sistema, sem perda entre sessões

---

## 🏗️ Arquitetura do Projeto

```
src/
└── biblioteca/
    ├── dados/              # Arquivos JSON com os dados persistidos
    │   ├── livros.json
    │   ├── usuarios.json
    │   └── emprestimos.json
    │
    ├── enums/              # Enumerações do domínio
    │   └── StatusEmprestimo.java   # ATIVO | DEVOLVIDO
    │
    ├── exception/          # Exceções personalizadas do sistema
    │   ├── EmprestimoNaoEncontradoException.java
    │   ├── LivroIndisponivelException.java
    │   ├── LivroJaCadastradoException.java
    │   ├── LivroNaoEncontradoException.java
    │   ├── UsuarioComEmailDuplicadoException.java
    │   └── UsuarioNaoEncontradoException.java
    │
    ├── model/              # Entidades do sistema
    │   ├── Livro.java
    │   ├── Usuario.java
    │   └── Emprestimos.java
    │
    ├── repository/         # Acesso e persistência dos dados
    │   ├── RepositorioLivro.java
    │   ├── RepositorioUsuario.java
    │   └── RepositorioEmprestimos.java
    │
    ├── service/            # Regras de negócio
    │   ├── LivroService.java
    │   ├── UsuarioService.java
    │   └── EmprestimoService.java
    │
    ├── util/               # Utilitários de suporte
    │   ├── GsonUtil.java       # Configuração do Gson com suporte a LocalDate
    │   └── ArquivoJson.java    # Leitura e escrita de arquivos JSON
    │
    └── Main.java           # Ponto de entrada — menu interativo do sistema
```

---

## 🛠️ Tecnologias e Conceitos Utilizados

- **Java 17+**
- **Orientação a Objetos** — encapsulamento, separação de responsabilidades
- **Streams API e Lambdas** — filtragem, ordenação e agrupamento de dados
- **Optional** — tratamento seguro de buscas que podem não retornar resultado
- **Tratamento de Exceções** — exceções personalizadas por situação de negócio
- **Enums** — controle de estados válidos do sistema
- **Gson** — serialização e desserialização de objetos Java para JSON
- **Java Time API** — `LocalDate` para controle de datas de empréstimo e devolução
- **Generics** — utilitários reutilizáveis para qualquer tipo de entidade

---

## 🚀 Como Executar

**Pré-requisitos:** Java 17+ instalado e Gson no classpath do projeto.

```bash
# Clone o repositório
git clone https://github.com/seu-usuario/Sistema_de_Gestao_de_Biblioteca.git

# Abra no IntelliJ IDEA e execute a classe Main.java
```

> Os arquivos JSON são criados automaticamente na pasta `dados/` na primeira execução.

---

## 📌 Sobre o Projeto

Desenvolvido de forma independente como projeto de portfólio durante o primeiro semestre de graduação, com o objetivo de aplicar na prática os fundamentos de Java antes de avançar para banco de dados e Spring Boot.

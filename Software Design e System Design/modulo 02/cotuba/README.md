# Arquitetura Cotubify

O Cotubify é uma plataforma ponta a ponta de autopublicação e venda de e-books. O sistema conecta autores, que escrevem seus livros usando Markdown e os versionam via Git, a leitores que podem navegar pelo catálogo, comprar e baixar as obras automaticamente convertidas para PDF e EPUB.

## Diagrama de Contexto

```mermaid
C4Context
    title Diagrama de Contexto (Nível 1) - Ecossistema Cotubify

%% Atores (Pessoas)
    Person(autor, "Autor", "Escreve seus livros em formato Markdown, configura informações, acompanha vendas e solicita saques.")
    Person(leitor, "Leitor", "Navega pelo catálogo da loja, compra e-books, baixa os arquivos e os lê.")

%% Sistema Principal
    System(cotubify, "Plataforma Cotubify", "Plataforma ponta a ponta de autopublicação e venda de e-books.")

%% Sistemas Externos
    System_Ext(git, "Provedor Git Externo", "GitHub, GitLab, etc. Armazena os repositórios com o conteúdo Markdown dos livros.")
    System_Ext(email, "Sistema de E-mail Externo", "Envia recibos, alertas e notificações para Autores e Leitores.")

%% Relacionamentos
    Rel(autor, cotubify, "Publica e configura livros, monitora saldo e pede saques")
    Rel(leitor, cotubify, "Acessa a loja, compra e baixa e-books")

    Rel(autor, git, "Escreve e faz commit/controle de versão dos livros")

    Rel(cotubify, git, "Clona repositórios para obter o conteúdo do livro")
    Rel(cotubify, email, "Solicita envio de notificações e recibos")
```

## Diagrama de Container

```mermaid
C4Container
    title Diagrama de Container (Nível 2) - Plataforma Cotubify

%% Atores e Sistemas Externos (Contexto ao redor)
    Person(autor, "Autor", "Escreve e gerencia publicações.")
    Person(leitor, "Leitor", "Compra e lê os livros.")

    System_Ext(git, "Provedor Git Externo", "GitHub/GitLab")
    System_Ext(email, "Sistema de E-mail Externo", "Envio de notificações")

%% Fronteira do Sistema Cotubify
    System_Boundary(cotubify_boundary, "Plataforma Cotubify") {

        Container(web, "Aplicação Web Frontend", "Browser, JS/HTML/CSS", "Interface visual acessada tanto pelo Autor quanto pelo Leitor.")

        Container(api_principal, "API Principal", "Java, Spring Boot", "O coração do sistema. Gerencia regras de negócio, usuários, catálogo de livros e saldos financeiros.")

        Container(gerador, "Serviço Gerador de Ebooks", "Java, Spring Boot", "Expõe uma API REST. Executa o motor Cotuba para gerar arquivos de e-books pesados.")

        ContainerDb(db, "Banco de Dados Relacional", "RDBMS (Ex: PostgreSQL)", "Armazena cadastros de usuários, metadados dos livros, transações e ledger dos autores.")

        ContainerDb(storage, "Armazenamento de Arquivos", "Object Storage (S3)", "Guarda capas em alta resolução e os PDFs/EPUBs finais gerados.")
    }

%% Relacionamentos dos Atores com os Containers
    Rel(autor, web, "Configura conta, livros e solicita saques", "HTTPS")
    Rel(leitor, web, "Navega, compra e faz download de livros", "HTTPS")

%% Relacionamentos Internos (Containers conversando entre si)
    Rel(web, api_principal, "Faz requisições de negócio e autenticação", "JSON/HTTPS")
    Rel(web, storage, "Faz o download de arquivos estáticos, capas e e-books", "HTTPS")

    Rel(api_principal, db, "Lê e grava dados estruturados", "TCP/JDBC")
    Rel(api_principal, gerador, "Envia request para gerar/atualizar e-book do livro", "JSON/REST/HTTPS")

%% Relacionamentos dos Containers com Sistemas Externos
    Rel(api_principal, email, "Solicita o disparo de e-mails", "HTTPS ou SMTP")

    Rel(gerador, git, "Faz o clone do repositório Markdown do autor", "Git/HTTPS")
    Rel(gerador, storage, "Faz upload dos arquivos pesados (PDF e EPUB) gerados", "HTTPS")
```
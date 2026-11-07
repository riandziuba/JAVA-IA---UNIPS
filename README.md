# Javify - Backend

## 📖 Sobre o Projeto

O **Javify** é uma aplicação acadêmica de streaming de músicas em desenvolvimento. Este repositório representa o início da construção da aplicação, focado na modelagem do banco de dados e infraestrutura do backend.

## 🎯 Objetivo

Este projeto tem como objetivo desenvolver uma plataforma completa de streaming de música, incluindo:
- Sistema de assinaturas com múltiplos planos
- Gerenciamento de usuários e perfis
- Catálogo de músicas e artistas
- Player de música com funcionalidades de streaming
- Sistema de pagamento e assinatura recorrente

### Tabelas Principais
- **plano**: Planos de assinatura (Free, Premium, Family, Student)
- **endereco**: Endereços dos usuários
- **usuario**: Usuários da plataforma (com campo `assinatura_id` para vinculação)
- **usuario_telefone**: Telefones dos usuários
- **assinatura**: Assinaturas ativas (vinculadas a planos)
- **cartao_credito**: Cartões de crédito para pagamentos

### Relacionamentos
- Usuários podem ter múltiplos telefones
- **Vários usuários podem estar ligados à mesma assinatura** (suporta planos Family)
- O campo `titular` na tabela `usuario` indica se o usuário é titular ou membro da assinatura
- Assinaturas estão vinculadas a planos
- Cartões de crédito são vinculados a assinaturas

## 🚀 Tecnologias Utilizadas

- **Banco de Dados**: PostgreSQL 16
- **Backend**: Java (Spring Boot)
- **Orquestração**: Docker & Docker Compose

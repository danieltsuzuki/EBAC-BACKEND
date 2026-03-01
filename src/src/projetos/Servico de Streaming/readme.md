# Plataforma de Streaming

- **Características Técnicas do Projeto**
- **Regras de Negócio e Requisitos**
- **Funcionalidades**

O objetivo é entregar uma API REST em **Spring Boot** (Maven/Gradle), com persistência em **banco relacional** (MySQL/PostgreSQL/SQL Server), usando **JPA/Hibernate** (ou JDBC Template), com validações mínimas, controle de **roles/níveis de acesso** e **APIs como único meio de entrada**.

## 1) Escopo funcional

### 1.1 Usuários e Conta
- Existem dois tipos de usuário:
  - **Assinante**: cria conta ao cadastrar o plano. Deve ter **mais de 18 anos**.
  - **Não assinante (vinculado)**: compartilha a conta de um assinante.
- Um assinante pode adicionar usuários vinculados.
- Vinculados **não podem** alterar informações de conta do assinante.

### 1.2 Planos
- **Basic**: acesso limitado + **não permite compartilhamento**.
- **Standard**: acesso a muitos filmes, **exceto filmes Premium** + até **2 vinculados**.
- **Premium**: acesso a todos os filmes + até **4 vinculados**.

> Observação importante: o acesso a filmes é atrelado à **conta** (plano do assinante), e vinculados herdam o mesmo acesso do assinante.

### 1.3 Filmes e Acesso
Cada filme possui minimamente:
- `titulo`
- `classificacaoEtaria` {0, 12, 16, 18}
- `genero` (lista livre)
- `planoMinimo` ∈ {Basic, Standard, Premium}

Para um filme estar disponível ao usuário, há 2 verificações:
1) **Plano da conta (do assinante)**
2) **Perfil do usuário (idade)**

Perfis:
- **INFANTIL**: idade < 12
- **ADOLESCENTE**: 12 < idade < 18 (com opção do assinante permitir ou não acesso a filmes 18)
- **ADULTO**: idade > 18

Acesso por classificação:
- INFANTIL: 0, 12
- ADOLESCENTE: 0, 12, 16 e 18 **somente se permitido**
- ADULTO: todos (respeitando plano)

### 1.4 Histórico de filmes assistidos
- Cada vez que um usuário “assiste” um filme, registra no histórico.
- Histórico deve:
  - ser buscável por API
  - vir **na ordem** em que foram assistidos
  - **manter repetição** (se assistiu 5x, aparece 5 registros)

### 1.5 Funcionalidades obrigatórias
- Criação de usuários (com validações)
- Gerenciamento de conta (adição/remoção vinculados; exclusão conta)
- Listagem de filmes disponíveis ao usuário logado
- Migração de plano (upgrade/downgrade com regras)
- Assistir filme (verificar acesso; contabilizar duração 0..10s; registrar histórico)
- Cadastro de filmes pode ser manual (seed/script)
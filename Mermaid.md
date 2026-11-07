---
config:
  layout: dagre
---
erDiagram
    direction LR
    USUARIO {
        bigint id PK ""  
        string nome  ""  
        string email  ""  
        string senha_hash  ""  
        boolean titular  ""  
        string cep FK ""  
        int assinatura_id FK ""
    }


    ENDERECO {
        string codigo_postal PK ""  
        string logradouro  ""  
        string bairro  ""  
    }


    ASSINATURA {
        int id PK ""  
        int plano_id FK ""  
        boolean status_ativa  ""  
    }


    PLANO {
        int id PK ""  
        string nome  ""  
        decimal preco  ""  
        boolean possui_propagandas  ""  
        int limite_membros  ""  
        boolean modo_offline  ""  
    }


    CARTAO_CREDITO {
        int id PK ""  
        int assinatura_id FK ""  
        string nome_titular  ""  
        string ultimos_quatro_digitos  ""  
        string token_gateway  ""  
        date validade  ""  
    }


    USUARIO_TELEFONE {
        int id PK ""  
        bigint usuario_id FK ""  
        string numero  ""  
        string tipo  ""  
    }


    USUARIO}o--||ENDERECO:"MORA_EM (cep -> codigo_postal)"
    USUARIO_TELEFONE}o--||USUARIO:"PERTENCE_A (usuario_id -> id)"
    ASSINATURA}o--||PLANO:"CONTRATA (plano_id -> id)"
    ASSINATURA}o--||USUARIO:"POSSUI_TITULAR (usuario_titular_id -> id)"
    CARTAO_CREDITO}o--||ASSINATURA:"PAGA (assinatura_id -> id)"
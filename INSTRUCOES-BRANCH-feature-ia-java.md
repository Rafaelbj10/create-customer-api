# 🎯 INSTRUÇÕES - Branch feature/ia-java

## 📋 Informações da Branch

- **Nome**: feature/ia-java
- **Status**: ✅ ATIVA
- **Commit**: 65add1d
- **Arquivos**: 25 arquivos (8 Java + 1 XML + 2 Scripts + Build)
- **Inserções**: 570+

---

## 🔧 Comandos Git Úteis

### 1. Listar todas as branches
```bash
git branch -a
```

**Output esperado**:
```
  feature/criacao
* feature/ia-java    ← Você está aqui
  main
  remotes/origin/feature/criacao
```

---

### 2. Ver detalhes da branch atual
```bash
git status
```

---

### 3. Ver histórico de commits
```bash
git log --oneline -10
```

---

### 4. Ver mudanças em um arquivo específico
```bash
git show HEAD:src/main/java/br/com/estudos/crud/exception/ErrorResponse.java
```

---

### 5. Ver diferenças entre branches
```bash
git diff feature/criacao..feature/ia-java -- src/main/java
```

---

### 6. Ver arquivos modificados
```bash
git diff --name-only feature/criacao..feature/ia-java
```

---

## 📂 Arquivos Principais da Branch

### Exceções Novas
```
src/main/java/br/com/estudos/crud/exception/
├── CpfJaCadastradoException.java (Nova)
├── ClienteNaoEncontradoException.java (Nova)
├── CepInvalidoException.java (Nova)
├── DadosInvalidosException.java (Nova)
└── ErrorResponse.java (Melhorada)
```

### Controllers
```
src/main/java/br/com/estudos/crud/controller/
├── HandlerControllerImpl.java (Melhorado - 9 handlers)
├── CadastroClienteControllerImpl.java (Melhorado - logging)
└── CadastroClienteController.java (Melhorado - @Valid)
```

### Business e Service
```
src/main/java/br/com/estudos/crud/
├── business/impl/CadastroClienteBusinessImpl.java (Melhorado)
└── service/impl/
    ├── CadastroClienteServiceImpl.java (Melhorado)
    └── ViaCepServiceImpl.java (Melhorado)
```

### Configuração e Testes
```
src/main/resources/
└── logback-spring.xml (Nova)

Raiz do projeto/
├── test-api.ps1 (Novo - testes PowerShell)
└── test-api.sh (Novo - testes Bash)
```

---

## 🧪 Testando a Branch

### Pré-requisitos
```bash
# Ter Java 21 instalado
java -version

# Ter MySQL rodando
# Schema: digibank
# Tabela: TB_CLIENTE
```

### 1. Compilar
```bash
./gradlew clean build -x test
```

### 2. Executar aplicação
```bash
./gradlew bootRun
```

### 3. Testar API (PowerShell - Windows)
```bash
# Terminal 1: Executar aplicação
./gradlew bootRun

# Terminal 2: Rodar testes
.\test-api.ps1
```

### 4. Testar API (Bash - Linux/Mac)
```bash
# Terminal 1: Executar aplicação
./gradlew bootRun

# Terminal 2: Rodar testes
bash test-api.sh
```

### 5. Testar manualmente com curl
```bash
# Cadastro válido (201)
curl -X POST http://localhost:8080/cliente/cadastrar \
  -H "Content-Type: application/json" \
  -d '{
    "name": "João Silva",
    "cpf": "12345678901",
    "rg": "123456789",
    "cep": "01310100",
    "email": "joao@example.com",
    "telephone": "11987654321",
    "birthDate": "1990-05-15"
  }'

# CPF duplicado (422)
curl -X POST http://localhost:8080/cliente/cadastrar \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Maria",
    "cpf": "12345678901",
    "rg": "987654321",
    "cep": "01310100",
    "email": "maria@example.com",
    "telephone": "11987654322",
    "birthDate": "1995-08-20"
  }'

# CPF inválido (400)
curl -X POST http://localhost:8080/cliente/cadastrar \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Carlos",
    "cpf": "123",
    "rg": "123456789",
    "cep": "01310100",
    "email": "carlos@example.com",
    "telephone": "11987654323",
    "birthDate": "1992-03-10"
  }'
```

---

## 📊 Visualizar Logs

### Ver todos os logs (em tempo real)
```bash
tail -f logs/cadastro-clientes.log
```

### Ver apenas erros
```bash
tail -f logs/cadastro-clientes-error.log
```

### Filtrar por CPF específico
```bash
grep "12345678901" logs/cadastro-clientes.log
```

### Ver últimas 20 linhas
```bash
tail -20 logs/cadastro-clientes.log
```

---

## 🔄 Integrar Branch Principal

### Opção 1: Merge Fast-Forward
```bash
git checkout main
git merge feature/ia-java
```

### Opção 2: Merge com Commit
```bash
git checkout main
git merge --no-ff feature/ia-java -m "Merge feature/ia-java: Tratamento robusto de erros"
```

### Opção 3: Rebase e Merge (mais limpo)
```bash
git checkout feature/ia-java
git rebase main
git checkout main
git merge feature/ia-java
```

---

## 🔀 Sincronizar com Remoto

### Push da branch
```bash
git push origin feature/ia-java
```

### Pull da branch
```bash
git pull origin feature/ia-java
```

### Deletar branch local
```bash
git branch -d feature/ia-java
```

### Deletar branch remota
```bash
git push origin --delete feature/ia-java
```

---

## 📋 Checklist de Validação

- [ ] Branch existe e está ativa
- [ ] Código compila sem erros
- [ ] Testes executam
- [ ] Logs são criados
- [ ] Validações funcionam
- [ ] Erros retornam JSON estruturado
- [ ] Documentação está clara
- [ ] Pronto para code review

---

## 🎓 Documentação Disponível

No projeto existem 5 documentos Markdown:

1. **Tratamento-Erros-Implementado.md**
   - Resumo completo das mudanças
   - Exemplos de respostas de erro
   - Padrões implementados

2. **RESUMO-Tratamento-Erros.md**
   - Resumo executivo
   - Comparativo antes x depois
   - Status final

3. **GUIA-RAPIDO.md**
   - Exemplos de testes
   - Validações aceitas/rejeitadas
   - Como adicionar nova exceção

4. **DETALHES-MUDANCAS.md**
   - Antes e depois de cada arquivo
   - Mudanças específicas por arquivo
   - Análise linha por linha

5. **BRANCH-feature-ia-java.md**
   - Informações sobre a branch
   - Como usar a branch
   - Próximos passos

---

## 💾 Histórico de Commits

```
commit 65add1d (HEAD -> feature/ia-java)
Author: GitHub Copilot
Date: 2026-03-22

feat: implementar tratamento robusto de erros
- Criar 4 novas exceções customizadas
- Melhorar ErrorResponse
- Implementar 9 tratadores de exceção
- Adicionar 30+ validações
- Adicionar logging completo
- Melhorar ViaCepServiceImpl
- Criar configuração Logback
- Adicionar @Valid nos controllers
- Atualizar Swagger
- Criar scripts de teste

 25 files changed, 570 insertions(+)
 create mode 100644 src/main/java/br/com/estudos/crud/exception/...
 ...
```

---

## 🚀 Deploy em Produção

### 1. Teste em Development
```bash
git checkout feature/ia-java
./gradlew clean build
./gradlew bootRun --args='--spring.profiles.active=dev'
```

### 2. Teste em Staging
```bash
./gradlew bootRun --args='--spring.profiles.active=staging'
```

### 3. Teste em Produção
```bash
./gradlew bootRun --args='--spring.profiles.active=prod'
```

### Configurar profiles
```bash
# application-dev.properties
spring.profiles.active=dev

# application-prod.properties
spring.profiles.active=prod
logging.level.br.com.estudos.crud=INFO
```

---

## 📞 Suporte

### Comandos úteis
```bash
# Ajuda git
git help <comando>

# Ajuda gradle
./gradlew help

# Ver configuração git
git config --list

# Verificar versão Java
java -version
```

---

**✅ Tudo pronto para trabalhar com a branch feature/ia-java!**


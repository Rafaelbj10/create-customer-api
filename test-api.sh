#!/bin/bash
# Script de testes para validar o tratamento de erros

API_URL="http://localhost:8080/cliente"

echo "=================================================="
echo "TESTES DO TRATAMENTO DE ERROS - Cadastro Clientes"
echo "=================================================="
echo ""

# Cores para output
GREEN='\033[0;32m'
RED='\033[0;31m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

# ==================== TESTE 1: Cadastro Válido ====================
echo -e "${YELLOW}TESTE 1: Cadastro de cliente válido${NC}"
echo "Esperado: 201 Created"
echo ""
curl -X POST "$API_URL/cadastrar" \
  -H "Content-Type: application/json" \
  -d '{
    "name": "João Silva",
    "cpf": "12345678901",
    "rg": "123456789",
    "cep": "01310100",
    "email": "joao@example.com",
    "telephone": "11987654321",
    "description": "Cliente teste",
    "birthDate": "1990-05-15"
  }' \
  -w "\nStatus: %{http_code}\n"
echo ""
echo "=================================================="
echo ""

# ==================== TESTE 2: CPF Duplicado ====================
echo -e "${YELLOW}TESTE 2: Cadastro com CPF duplicado${NC}"
echo "Esperado: 422 Unprocessable Entity"
echo "Tipo erro: CPF_JA_CADASTRADO"
echo ""
curl -X POST "$API_URL/cadastrar" \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Maria Santos",
    "cpf": "12345678901",
    "rg": "987654321",
    "cep": "01310100",
    "email": "maria@example.com",
    "telephone": "11987654322",
    "description": "Cliente teste",
    "birthDate": "1995-08-20"
  }' \
  -w "\nStatus: %{http_code}\n"
echo ""
echo "=================================================="
echo ""

# ==================== TESTE 3: CPF Inválido ====================
echo -e "${YELLOW}TESTE 3: CPF com formato inválido${NC}"
echo "Esperado: 400 Bad Request"
echo "Tipo erro: ERRO_VALIDACAO"
echo ""
curl -X POST "$API_URL/cadastrar" \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Carlos Santos",
    "cpf": "123",
    "rg": "123456789",
    "cep": "01310100",
    "email": "carlos@example.com",
    "telephone": "11987654323",
    "description": "Cliente teste",
    "birthDate": "1992-03-10"
  }' \
  -w "\nStatus: %{http_code}\n"
echo ""
echo "=================================================="
echo ""

# ==================== TESTE 4: Email Inválido ====================
echo -e "${YELLOW}TESTE 4: Email com formato inválido${NC}"
echo "Esperado: 400 Bad Request"
echo "Tipo erro: ERRO_VALIDACAO"
echo ""
curl -X POST "$API_URL/cadastrar" \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Ana Costa",
    "cpf": "98765432100",
    "rg": "123456789",
    "cep": "01310100",
    "email": "email-invalido",
    "telephone": "11987654324",
    "description": "Cliente teste",
    "birthDate": "1993-07-25"
  }' \
  -w "\nStatus: %{http_code}\n"
echo ""
echo "=================================================="
echo ""

# ==================== TESTE 5: CEP Inválido ====================
echo -e "${YELLOW}TESTE 5: CEP inválido ou não encontrado${NC}"
echo "Esperado: 400 Bad Request"
echo "Tipo erro: CEP_INVALIDO"
echo ""
curl -X POST "$API_URL/cadastrar" \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Pedro Costa",
    "cpf": "11122233344",
    "rg": "123456789",
    "cep": "99999999",
    "email": "pedro@example.com",
    "telephone": "11987654325",
    "description": "Cliente teste",
    "birthDate": "1991-12-30"
  }' \
  -w "\nStatus: %{http_code}\n"
echo ""
echo "=================================================="
echo ""

# ==================== TESTE 6: Cliente Não Encontrado ====================
echo -e "${YELLOW}TESTE 6: Buscar cliente que não existe${NC}"
echo "Esperado: 404 Not Found"
echo "Tipo erro: CLIENTE_NAO_ENCONTRADO"
echo ""
curl -X GET "$API_URL/99999999999" \
  -H "Content-Type: application/json" \
  -w "\nStatus: %{http_code}\n"
echo ""
echo "=================================================="
echo ""

# ==================== TESTE 7: Deletar Cliente Inexistente ====================
echo -e "${YELLOW}TESTE 7: Deletar cliente que não existe${NC}"
echo "Esperado: 404 Not Found"
echo "Tipo erro: CLIENTE_NAO_ENCONTRADO"
echo ""
curl -X DELETE "$API_URL/99999999999" \
  -H "Content-Type: application/json" \
  -w "\nStatus: %{http_code}\n"
echo ""
echo "=================================================="
echo ""

# ==================== TESTE 8: Buscar Cliente Válido ====================
echo -e "${YELLOW}TESTE 8: Buscar cliente cadastrado${NC}"
echo "Esperado: 200 OK"
echo ""
curl -X GET "$API_URL/12345678901" \
  -H "Content-Type: application/json" \
  -w "\nStatus: %{http_code}\n"
echo ""
echo "=================================================="
echo ""

# ==================== TESTE 9: Listar Clientes ====================
echo -e "${YELLOW}TESTE 9: Listar todos os clientes${NC}"
echo "Esperado: 200 OK"
echo ""
curl -X GET "$API_URL" \
  -H "Content-Type: application/json" \
  -w "\nStatus: %{http_code}\n"
echo ""
echo "=================================================="
echo ""

# ==================== TESTE 10: Deletar Cliente Válido ====================
echo -e "${YELLOW}TESTE 10: Deletar cliente cadastrado${NC}"
echo "Esperado: 204 No Content"
echo ""
curl -X DELETE "$API_URL/12345678901" \
  -H "Content-Type: application/json" \
  -w "\nStatus: %{http_code}\n"
echo ""
echo "=================================================="
echo ""

echo -e "${GREEN}Testes concluídos!${NC}"


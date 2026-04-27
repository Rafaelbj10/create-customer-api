-- Script para criar/alterar a tabela TB_CLIENT no banco de dados digibank
-- Padrão: nomenclatura em inglês, estrutura normalizada

-- Opção 1: Criar tabela do zero
CREATE TABLE IF NOT EXISTS TB_CLIENT (
    ID          INT AUTO_INCREMENT PRIMARY KEY COMMENT 'Client unique identifier',
    NAME        VARCHAR(255) NOT NULL COMMENT 'Client full name',
    CPF         VARCHAR(11) NOT NULL UNIQUE COMMENT 'Client CPF (Tax ID)',
    RG          VARCHAR(9) COMMENT 'Client RG (Identity Document)',
    ADDRESS     VARCHAR(255) COMMENT 'Client street address',
    ZIP_CODE    VARCHAR(8) COMMENT 'Client postal code',
    EMAIL       VARCHAR(100) COMMENT 'Client email address',
    TELEPHONE   VARCHAR(15) COMMENT 'Client telephone number',
    DESCRIPTION VARCHAR(255) COMMENT 'Client description/notes',
    BIRTH_DATE  DATE COMMENT 'Client date of birth',
    CREATED_AT  TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT 'Record creation timestamp',
    UPDATED_AT  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Record update timestamp'
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE =utf8mb4_unicode_ci COMMENT='Table for customer information storage';

-- Opção 2: Se a tabela já existe e precisa ser alterada
-- ALTER TABLE TB_CLIENT CHANGE COLUMN CEP ZIP_CODE VARCHAR(8);
-- ALTER TABLE TB_CLIENT ADD COLUMN CREATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP;
-- ALTER TABLE TB_CLIENT ADD COLUMN UPDATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP;


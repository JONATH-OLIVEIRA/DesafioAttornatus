INSERT INTO tb_pessoa (nome,data_nascimento) VALUES ('FULANO','2022-07-22');
INSERT INTO tb_pessoa (nome,data_nascimento) VALUES ('ALOSON','2022-07-25');
INSERT INTO tb_pessoa (nome,data_nascimento) VALUES ('OTOCHE','2022-07-30');


INSERT INTO tb_endereco (LOGRADOURO,CEP,CIDADE,NUMERO,END_PRINCIPAL,ID_PESSOA) VALUES ('Rua do Fim','60120-550','Fortaleza',1135,FALSE,1)
INSERT INTO tb_endereco (LOGRADOURO,CEP,CIDADE,NUMERO,END_PRINCIPAL,ID_PESSOA) VALUES ('Rua da Esquina','60120-550','Fortaleza',1135,TRUE,1)
INSERT INTO tb_endereco (LOGRADOURO,CEP,CIDADE,NUMERO,END_PRINCIPAL,ID_PESSOA) VALUES ('Rua do Comeco','60120-550','Fortaleza',1135,FALSE,1)

INSERT INTO tb_endereco_pessoa (pessoa_id, endereco_id) VALUES (1, 1);
INSERT INTO tb_endereco_pessoa (pessoa_id, endereco_id) VALUES (1, 2);
INSERT INTO tb_endereco_pessoa (pessoa_id, endereco_id) VALUES (1, 3);





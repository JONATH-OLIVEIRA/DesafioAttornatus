INSERT INTO tb_pessoa (nome,data_nascimento) VALUES ('FULANO','2022-07-22');
INSERT INTO tb_pessoa (nome,data_nascimento) VALUES ('ALOSON','2022-07-25');
INSERT INTO tb_pessoa (nome,data_nascimento) VALUES ('OTOCHE','2022-07-30');

INSERT INTO tb_endereco (LOGRADOURO,CEP,CIDADE,NUMERO) VALUES ('Rua do Fim','60120-550','Fortaleza',1135)
INSERT INTO tb_endereco (LOGRADOURO,CEP,CIDADE,NUMERO) VALUES ('Rua Esquina','60220-551','Fortaleza',1145)
INSERT INTO tb_endereco (LOGRADOURO,CEP,CIDADE,NUMERO) VALUES ('Rua Avante ','60320-552','Fortaleza',1155)
INSERT INTO tb_endereco (LOGRADOURO,CEP,CIDADE,NUMERO) VALUES ('Rua Riachuelo','60420-553','Fortaleza',1175)

INSERT INTO tb_endereco_pessoa (pessoa_id, endereco_id) VALUES (1, 1);
INSERT INTO tb_endereco_pessoa (pessoa_id, endereco_id) VALUES (1, 2);
INSERT INTO tb_endereco_pessoa (pessoa_id, endereco_id) VALUES (2, 3);
INSERT INTO tb_endereco_pessoa (pessoa_id, endereco_id) VALUES (3, 4);



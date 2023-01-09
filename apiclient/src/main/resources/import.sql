INSERT INTO tb_conta (nome_responsavel) VALUES ('FULANO');
INSERT INTO tb_conta (nome_responsavel) VALUES ('Silva');
INSERT INTO tb_conta (nome_responsavel) VALUES ('Luno');


INSERT INTO tb_transferencia (DATA_TRANSFERENCIA,NOME_OPERADOR_TRANSACAO,TIPO,VALOR) VALUES ('2020-07-13','HULK ','DEPOSITO',1350.00)
INSERT INTO tb_transferencia (DATA_TRANSFERENCIA,NOME_OPERADOR_TRANSACAO,TIPO,VALOR) VALUES ('2020-07-14','Silva','DEPOSITO',350.00)
INSERT INTO tb_transferencia (DATA_TRANSFERENCIA,NOME_OPERADOR_TRANSACAO,TIPO,VALOR) VALUES ('2020-07-15',' ','SAQUE',-750.00)
INSERT INTO tb_transferencia (DATA_TRANSFERENCIA,NOME_OPERADOR_TRANSACAO,TIPO,VALOR) VALUES ('2020-07-14','Silva','SAQUE',450.00)

INSERT INTO tb_movimentos_conta (conta_id, transferencia_id) VALUES (1, 1);
INSERT INTO tb_movimentos_conta (conta_id, transferencia_id) VALUES (1, 2);
INSERT INTO tb_movimentos_conta (conta_id, transferencia_id) VALUES (1, 3);

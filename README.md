# DesafioAttornatus

Orientacões para testes do projeto. 
  . usando o posteman que pode ser baixado segundo o link : https://www.postman.com/, use os comandos abaixo.
  
  Listar Pessoas
  
  http://localhost:8090/pessoa
  
  Juntamente com o endereço acima, pode-se fazer todas as operacoes de GET,PUT,DEL,POST.
  
  http://localhost:8090/pessoa/1
  
  Faz a listagem da pessoa associada ao ID 1 e tras todos os enderecos atribuidos a mesma.
  
  
  Listar Enderecos
  
   http://localhost:8090/enderecos
   
  Juntamente com o endereço acima, pode-se fazer todas as operacoes de GET,PUT,DEL,POST.
  
   http://localhost:8090/enderecos/listarEnderecoPessoa
   
   deve ser enviado na requisicao o ID da pessoa, e retornara todos os enderecos atribuidos a pessoa, e informa tambem qual o seu Endereco principal.
  
  ex: http://localhost:8090/enderecos/listarEnderecoPessoa?id=1

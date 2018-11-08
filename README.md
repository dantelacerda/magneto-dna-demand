O detector mutante do Magneto é uma API REST que permite detectar, por meio de uma matriz de DNA, se um ser humano é mutante ou não.

Você saberá se um humano é mutante, se você encontrar mais de uma sequência de quatro letras iguais, obliquamente, horizontalmente ou verticalmente.

Restrições:

- A matriz de entrada deve ser uma matriz quadrada, caso contrário, retornará o erro 400 (BAD REQUEST) e o DNA não persistirá;
- Os elementos da matriz podem ser apenas os seguintes caracteres: A, C, G, T. Se outro caractere for encontrado, 400 será retornado (BAD REQUEST);
- Caso Seja informado um DNA igual a um existente, será devolvido Status 409 (CONFLICT);

Suposições:

- Qualquer DNA menor que 4 x 4 é considerado não-mutante;
- Uma sequência de N bases iguais maior que 4 será considerada como seqüências S onde S = chão (N / 4);
- Supõe-se que o DNA persistente não pode ser eliminado ou alterado;
- Uma sequência oblíqua é considerada aquela que tem uma direção para cima à esquerda, direção para baixo à direita, em oposição a uma direção para cima, para baixo, para a esquerda;

A API tem dois endpoints:

1. Serviço de detecção de mutantes
POST / mutante
Exemplo de corpo:

{
    "dna": [
    "AACCTT",
"ACGTCA",
"ATTGAA",
"GGCCGG",
"AGGTTT",
"ACCCGC"
     ]
}

O serviço retornará 200 OK se detectar um mutante, 403 FORBIDDEN se detectar um não-mutante, 400 BAD REQUEST se não atender às restrições mencionadas anteriormente ou 409 se já existir um mutante/humano com a mesma sequencia de DNA;

Quando um mutante ou não-mutante é detectado, o DNA é armazenado em um banco de dados.

2. Serviço de estatísticas
GET / stats
O serviço retorna a contagem de mutantes detectados, a contagem de seres humanos processados ​​e a relação entre eles. Exemplo:

{
    "proporcao": 0,4,
    "quantidadeMutantes": 2,
    "quantidadeHumanos": 5
}

Se não houver humanos, a proporção será 0.


Base de Dados:

Deverá ser utilizado um banco de Dados PostgreSQL na porta 5432, conectando com o usuário postgres e senha postgres;

Scripts:

CREATE TABLE human
(
  id         INTEGER NOT NULL,
  dnaSeq VARCHAR(40) NOT NULL
  
);

CREATE TABLE mutant
(
  id         INTEGER NOT NULL,
  dnaSeq VARCHAR(40) NOT NULL
  
);

CREATE SEQUENCE seq_mutant START 1;
CREATE SEQUENCE seq_human START 1;
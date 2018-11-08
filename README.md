################## PORTUGUÊS ##########################

Esta é uma API REST que permite detectar, por meio de uma matriz de DNA, se um ser humano é mutante ou não.

Você saberá se um humano é mutante, se você encontrar mais de uma sequência de quatro letras iguais, obliquamente, horizontalmente ou verticalmente.

RESTRIÇÕES:

- A matriz de entrada deve ser uma matriz quadrada, caso contrário, retornará o erro 400 (BAD REQUEST) e o DNA não persistirá;
- Os elementos da matriz podem ser apenas os seguintes caracteres: A, C, G, T. Se outro caractere for encontrado, 400 será retornado (BAD REQUEST);
- Caso Seja informado um DNA igual a um existente, será devolvido Status 409 (CONFLICT);

SUPOSIÇÕES:

- Qualquer DNA menor que 4 x 4 é considerado não-mutante;
- Uma sequência de N bases iguais maior que 4 será considerada como seqüências S onde S = chão (N / 4);
- Supõe-se que o DNA persistente não pode ser eliminado ou alterado;
- Uma sequência oblíqua é considerada aquela que tem uma direção para cima à esquerda, direção para baixo à direita, em oposição a uma direção para cima, para baixo, para a esquerda;

ENDPOINTS:

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


BASE DE DADOS:

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


PUBLICAÇÃO:

- O projeto foi publicado no HEROKU;


FORMAS DE ACESSO:

- Pode ser executado diretamente nas URL's:

* https://magneto-dna-demand.herokuapp.com/stats    (Um GET Simples);
* https://magneto-dna-demand.herokuapp.com/mutant   (Um POST com o corpo mencionado na seção de "Endpoints");


- O projeto também pode ser baixado, configurado localmente:
* É um projeto MAVEN;
* A base de dados pode ser criada localmente com as informações da Seção "Base de Dados";
* Para executar o projeto localmente basta executar o comando:  mvn spring-boot: run   e acessar no browser no caminho http://localhost:8080



################## INGLÊS ##########################


This is a REST API that allows you to detect, through a DNA array, whether a human being is mutant or not.

You will know if a human is mutant if you find more than one sequence of four letters equal, obliquely, horizontally or vertically.

RESTRICTIONS:

- The input matrix must be a square matrix, otherwise it will return the 400 (BAD REQUEST) error and the DNA will not persist;
- The elements of the array can be only the following characters: A, C, G, T. If another character is found, 400 will be returned (BAD REQUEST);
- If a DNA equal to an existing DNA is reported, Status 409 (CONFLICT) will be returned;

ASSUMPTIONS:

- Any DNA less than 4 x 4 is considered non-mutant;
- A sequence of N equal bases greater than 4 will be considered as sequences S where S = ground (N / 4);
- It is assumed that persistent DNA can not be eliminated or altered;
- An oblique sequence is considered to be one that has an upward direction to the left, a downward direction to the right, as opposed to an upward, downward, or leftward direction;

ENDPOINTS:

1. Mutant Detection Service
POST / mutant
Body Example:

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

The service will return 200 OK if it detects a mutant, 402 FORBIDDEN if it detects a non-mutant, 400 BAD REQUEST if it does not meet the previously mentioned restrictions or 409 if a mutant / human already exists with the same DNA sequence;

When a mutant or non-mutant is detected, the DNA is stored in a database.

2. Statistics service
GET / stats
The service returns the count of mutants detected, the count of processed humans and the relationship between them. Example:

{
"ratio": 0.4,
"Amount": 2,
"Humans quantity": 5
}

If there are no humans, the ratio will be 0.


DATA BASE:

A PostgreSQL database should be used on port 5432, connecting with the postgres user and postgres password;

Scripts:

CREATE TABLE human
(
  id INTEGER NOT NULL,
  dnaSeq VARCHAR (40) NOT NULL
  
);

CREATE TABLE mutant
(
  id INTEGER NOT NULL,
  dnaSeq VARCHAR (40) NOT NULL
  
);

CREATE SEQUENCE seq_mutant START 1;
CREATE SEQUENCE seq_human START 1;


PUBLICATION:

- The project was published in HEROKU;


FORMS OF ACCESS:

- Can be run directly on URL's:

* https://magneto-dna-demand.herokuapp.com/stats (A Simple GET);
* https://magneto-dna-demand.herokuapp.com/mutant (A POST with the body mentioned in the "Endpoints" section);


- The project can also be downloaded, configured locally:
* It's a MAVEN project;
* The database can be created locally with the information in the "Database" section;
* To run the project locally just run the command: mvn spring-boot: run and access in the browser in the path http: // localhost: 8080
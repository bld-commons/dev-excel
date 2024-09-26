CREATE TABLE autore (
	id_autore int4 NOT NULL,
	nome varchar NOT NULL,
	cognome varchar NOT NULL,
	data_nascita date not null,
	sesso varchar(1) not null,
	CONSTRAINT autore_pk PRIMARY KEY (id_autore)
);

create table genere(
	id_genere int4 primary key,
	des_genere varchar(100)not null
);

CREATE TABLE libro (
	id_libro int4 NOT NULL,
	titolo varchar(100) NOT NULL,
	id_autore int4 not null,
	id_genere int4 not null,
	CONSTRAINT libro_pk PRIMARY KEY (id_libro),
	foreign key (id_autore) references autore(id_autore),
	foreign key (id_genere) references genere(id_genere)
);

create table storico_prezzo (
  id_storico_prezzo serial,
  anno int4 not null,
  prezzo double precision not null,
  id_libro int4 not null,
  primary key(id_storico_prezzo),
  foreign key (id_libro) references libro(id_libro)
);

create table utente(
  id_utente int4 not null, 
  abilitato boolean not null default true, 
  cognome varchar(100) not null, 
  nome varchar(100) not null,
  data_nascita date not null, 
  image bytea,  
  "path" varchar(1024),
  primary key (id_utente)

);

INSERT INTO public.autore
(id_autore, nome, cognome, data_nascita, sesso)
VALUES(1, 'Mario', 'Rossi', '1962-01-04', 'm');
INSERT INTO public.autore
(id_autore, nome, cognome, data_nascita, sesso)
VALUES(2, 'Maria', 'Bianchi', '1980-12-31', 'f');
INSERT INTO public.autore
(id_autore, nome, cognome, data_nascita, sesso)
VALUES(3, 'Valerio', 'Torre', '1970-04-04', 'm');
INSERT INTO public.autore
(id_autore, nome, cognome, data_nascita, sesso)
VALUES(4, 'Claudio', 'Ferrara', '1970-04-04', 'm');

INSERT INTO public.genere
(id_genere, des_genere)
VALUES(1, 'romantico');
INSERT INTO public.genere
(id_genere, des_genere)
VALUES(2, 'giallo');
INSERT INTO public.genere
(id_genere, des_genere)
VALUES(3, 'thriller');
INSERT INTO public.genere
(id_genere, des_genere)
VALUES(4, 'avventura');
INSERT INTO public.genere
(id_genere, des_genere)
VALUES(5, 'biografia');


INSERT INTO public.utente
(id_utente, abilitato, cognome, data_nascita, image, nome, "path")
VALUES(1, true, 'Rossi', '1976-09-26', NULL, 'Francesco', NULL);
INSERT INTO public.utente
(id_utente, abilitato, cognome, data_nascita, image, nome, "path")
VALUES(2, true, 'Bianchi', '1987-04-11', NULL, 'Marco', '/home/francesco/Downloads/DSC_0319.JPG');
INSERT INTO public.utente
(id_utente, nome, cognome, data_nascita)
VALUES(3, 'Martina', 'Bianchi', '1987-01-15');
INSERT INTO public.utente
(id_utente, nome, cognome, data_nascita)
VALUES(4, 'Federica', 'Verdi', '1987-11-27');

INSERT INTO public.libro
(id_libro, titolo, id_autore, id_genere)
VALUES(1, 'l''uomo dell''abirinto', 1, 3);
INSERT INTO public.libro
(id_libro, titolo, id_autore, id_genere)
VALUES(2, 'salto nel buio', 3, 3);
INSERT INTO public.libro
(id_libro, titolo, id_autore, id_genere)
VALUES(3, 'l''ultimo conclave', 1, 3);
INSERT INTO public.libro
(id_libro, titolo, id_autore, id_genere)
VALUES(4, 'Tutto troppo complicato', 2, 1);
INSERT INTO public.libro
(id_libro, titolo, id_autore, id_genere)
VALUES(5, 'La lettera di Natale', 2, 1);
INSERT INTO public.libro
(id_libro, titolo, id_autore, id_genere)
VALUES(6, 'Una conquista fuori menù', 4, 1);
INSERT INTO public.libro
(id_libro, titolo, id_autore, id_genere)
VALUES(7, 'I fantasmi dell’isola', 2, 4);
INSERT INTO public.libro
(id_libro, titolo, id_autore, id_genere)
VALUES(8, 'Balla coi lupi', 4, 4);
INSERT INTO public.libro
(id_libro, titolo, id_autore, id_genere)
VALUES(9, 'La stagione del coraggio', 3, 4);
INSERT INTO public.libro
(id_libro, titolo, id_autore, id_genere)
VALUES(10, 'Trilogia dei Pirati', 1, 4);
INSERT INTO public.libro
(id_libro, titolo, id_autore, id_genere)
VALUES(11, 'Un animale selvaggio', 1, 2);
INSERT INTO public.libro
(id_libro, titolo, id_autore, id_genere)
VALUES(12, 'Discesa agli inferi', 2, 2);
INSERT INTO public.libro
(id_libro, titolo, id_autore, id_genere)
VALUES(13, 'El Che', 3, 5);
INSERT INTO public.libro
(id_libro, titolo, id_autore, id_genere)
VALUES(14, 'Cesare', 4, 5);

INSERT INTO public.storico_prezzo
(anno, prezzo, id_libro)
select anno,random()*100+10,l.id_libro from
unnest(ARRAY[2020,2021,2022,2023,2024]) anno, 
libro l;







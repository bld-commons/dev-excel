create table utente(
id_utente serial,
nome varchar(255) not null,
cognome varchar(255) not null,
data_nascita date not null,
primary key (id_utente)
);

create table genere(
id_genere serial,
des_genere varchar(255) not null,
primary key (id_genere)
);

create table autore(
id_autore serial,
nome varchar(255) not null,
cognome varchar(255) not null,
data_nascita date not null,
sesso varchar(1) not null,
primary key (id_autore)
);

create table libro(
id_libro serial,
titolo varchar(255)not null,
id_autore int not null,
id_genere int not null,
primary key(id_libro),
foreign key(id_autore )references autore(id_autore),
foreign key(id_genere )references genere(id_genere)
);

create table storico_prezzo(
anno int,
id_libro int,
prezzo double precision not null,
primary key(anno,id_libro),
foreign key(id_libro )references libro(id_libro )
);



INSERT INTO public.utente
(id_utente, nome, cognome, data_nascita)
VALUES(1, 'Mario', 'Rossi', '1955-04-25');
INSERT INTO public.utente
(id_utente, nome, cognome, data_nascita)
VALUES(2, 'Martina', 'Bianchi', '1970-12-12');
INSERT INTO public.utente
(id_utente, nome, cognome, data_nascita)
VALUES(3, 'Marco', 'Bianchi', '1987-01-15');
INSERT INTO public.utente
(id_utente, nome, cognome, data_nascita)
VALUES(4, 'Federica', 'Verdi', '1987-11-27');



INSERT INTO public.genere(des_genere)VALUES('Thriller');
INSERT INTO public.genere(des_genere)VALUES('Commedia');
INSERT INTO public.genere(des_genere)VALUES('Romantico');
INSERT INTO public.genere(des_genere)VALUES('Comico');
INSERT INTO public.genere(des_genere)VALUES('Drammatico');
INSERT INTO public.genere(des_genere)VALUES('Sentimentale');
INSERT INTO public.genere(des_genere)VALUES('Spionaggio');



INSERT INTO public.autore(nome, cognome, data_nascita, sesso)VALUES('Mario', 'Rossi', '1960-07-12', 'M');
INSERT INTO public.autore(nome, cognome, data_nascita, sesso)VALUES('Mario', 'Verdi', '1945-03-24', 'M');

INSERT INTO public.libro (titolo, id_autore, id_genere) VALUES('Profondo Rosso',         1, 7);
INSERT INTO public.libro (titolo, id_autore, id_genere) VALUES('Complotto',              1, 7);
INSERT INTO public.libro (titolo, id_autore, id_genere) VALUES('Sai chi te saluta',      1, 4);
INSERT INTO public.libro (titolo, id_autore, id_genere) VALUES('Amore',                  1, 6);
INSERT INTO public.libro (titolo, id_autore, id_genere) VALUES('Rosso',                  2, 6);
INSERT INTO public.libro (titolo, id_autore, id_genere) VALUES('Arancio',                2, 6);
INSERT INTO public.libro (titolo, id_autore, id_genere) VALUES('Blue',                   2, 5);



INSERT INTO public.storico_prezzo (anno, id_libro, prezzo) VALUES(2018, 1, 25  );
INSERT INTO public.storico_prezzo (anno, id_libro, prezzo) VALUES(2019, 1, 27.5);
INSERT INTO public.storico_prezzo (anno, id_libro, prezzo) VALUES(2020, 1, 30  );
INSERT INTO public.storico_prezzo (anno, id_libro, prezzo) VALUES(2019, 2, 15  );
INSERT INTO public.storico_prezzo (anno, id_libro, prezzo) VALUES(2020, 2, 20.3);
INSERT INTO public.storico_prezzo (anno, id_libro, prezzo) VALUES(2018, 3, 10  );
INSERT INTO public.storico_prezzo (anno, id_libro, prezzo) VALUES(2019, 3, 10  );
INSERT INTO public.storico_prezzo (anno, id_libro, prezzo) VALUES(2020, 3, 12  );
INSERT INTO public.storico_prezzo (anno, id_libro, prezzo) VALUES(2020, 4, 10  );
INSERT INTO public.storico_prezzo (anno, id_libro, prezzo) VALUES(2019, 5, 12  );
INSERT INTO public.storico_prezzo (anno, id_libro, prezzo) VALUES(2020, 5, 12.5);
INSERT INTO public.storico_prezzo (anno, id_libro, prezzo) VALUES(2019, 6, 17  );
INSERT INTO public.storico_prezzo (anno, id_libro, prezzo) VALUES(2020, 6, 17  );
INSERT INTO public.storico_prezzo (anno, id_libro, prezzo) VALUES(2020, 7, 18  );


INSERT INTO "projekat" ("id", "naziv", "oznaka", "opis")
VALUES (nextval('projekat_seq'), 'Web aplikacija – Za on-line prodaju ribarske opreme', 'Web_A', 'Web aplikacija je racunarski softver kojem se pristupa putem web pregledaca i povezan je sa bazom podataka kako bi se pružio prilagodljiv interaktivni korisnički doživljaj.');
INSERT INTO "projekat" ("id", "naziv", "oznaka", "opis")
VALUES (nextval('projekat_seq'), 'Web sajt', 'Web_S', 'Web sajt je kolekcija sadrzaja prikazanog na nekoliko web stranica koje dele istu web adresu.');
INSERT INTO "projekat" ("id", "naziv", "oznaka", "opis")
VALUES (nextval('projekat_seq'), 'To-Do aplikacija', 'To-Do', 'Aplikacija za organizaciju radnih zadataka.');
INSERT INTO "projekat" ("id", "naziv", "oznaka", "opis")
VALUES (nextval('projekat_seq'), 'Console Application', 'C_A', 'Konzolna aplikacija je program dizajniran za upotrebu putem računarskog interfejsa samo sa tekstom.');
INSERT INTO "projekat" ("id", "naziv", "oznaka", "opis")
VALUES (nextval('projekat_seq'), 'Mobilna aplikacija', 'Mob_A', 'Aplikacija za mobilni telefon.');

INSERT INTO "smer" ("id", "naziv", "oznaka")
VALUES (nextval('smer_seq'), 'Inzenjerski menadzment', 'IM');
INSERT INTO "smer" ("id", "naziv", "oznaka")
VALUES (nextval('smer_seq'), 'Industrijsko inzenjerstvo', 'II');
INSERT INTO "smer" ("id", "naziv", "oznaka")
VALUES (nextval('smer_seq'), 'Primenjeno softversko inzenjerstvo', 'PR');
INSERT INTO "smer" ("id", "naziv", "oznaka")
VALUES (nextval('smer_seq'), 'Racunarstvo i automatika', 'RA');
INSERT INTO "smer" ("id", "naziv", "oznaka")
VALUES (nextval('smer_seq'), 'Energetika, elektronika i telekomunikacije', 'EE');

INSERT INTO "grupa" ("id", "oznaka", "smer")
VALUES (nextval('grupa_seq'), 'Grupa 1', 1);
INSERT INTO "grupa" ("id", "oznaka", "smer")
VALUES (nextval('grupa_seq'), 'Grupa 2', 2);
INSERT INTO "grupa" ("id", "oznaka", "smer")
VALUES (nextval('grupa_seq'), 'Grupa 3', 3);
INSERT INTO "grupa" ("id", "oznaka", "smer")
VALUES (nextval('grupa_seq'), 'Grupa 4', 4);
INSERT INTO "grupa" ("id", "oznaka", "smer")
VALUES (nextval('grupa_seq'), 'Grupa 5', 5);

INSERT INTO "student" ("id", "ime", "prezime", "broj_indeksa", "grupa", "projekat")
VALUES (nextval('student_seq'), 'Bogdan', 'Dimitrijevic', 'RA-124/2017', 4, 5);
INSERT INTO "student" ("id", "ime", "prezime", "broj_indeksa", "grupa", "projekat")
VALUES (nextval('student_seq'), 'Milica', 'Tomic', 'RA-85/2017', 4, 5);
INSERT INTO "student" ("id", "ime", "prezime", "broj_indeksa", "grupa", "projekat")
VALUES (nextval('student_seq'), 'Luka', 'Peric', 'II-110/2017', 2, 3);
INSERT INTO "student" ("id", "ime", "prezime", "broj_indeksa", "grupa", "projekat")
VALUES (nextval('student_seq'), 'Nemanja', 'Tasic', 'II-60/20217', 2, 3);
INSERT INTO "student" ("id", "ime", "prezime", "broj_indeksa", "grupa", "projekat")
VALUES (nextval('student_seq'), 'Nikolina', 'Stojanovic', 'IM-100/2016', 1, 2);
INSERT INTO "student" ("id", "ime", "prezime", "broj_indeksa", "grupa", "projekat")
VALUES (nextval('student_seq'), 'Teodora', 'Ilic', 'IM-90/2017', 1, 2);
INSERT INTO "student" ("id", "ime", "prezime", "broj_indeksa", "grupa", "projekat")
VALUES (nextval('student_seq'), 'Isidora', 'Krstic', 'PR-107/2017', 3, 1);
INSERT INTO "student" ("id", "ime", "prezime", "broj_indeksa", "grupa", "projekat")
VALUES (nextval('student_seq'), 'Viktor', 'Nikolic', 'PR-4/2017', 3, 1);
INSERT INTO "student" ("id", "ime", "prezime", "broj_indeksa", "grupa", "projekat")
VALUES (nextval('student_seq'), 'Milica', 'Savic', 'EE-44/2017', 5, 4);
INSERT INTO "student" ("id", "ime", "prezime", "broj_indeksa", "grupa", "projekat")
VALUES (nextval('student_seq'), 'Danilo', 'Stankovic', 'EE-67/2017', 5, 4);



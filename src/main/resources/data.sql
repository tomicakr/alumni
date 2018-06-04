INSERT INTO users (user_id, first_name, last_name, email, phone, password, address, birthday, graduation)
			VALUES('368ec8e2-c7cb-4eaf-b3f1-0077333fbea9', "Tomislav", "Kravaršćan", "tomislav.kravarscan@fer.hr", 
			"0989958015", "$2a$11$bBQIy5F6ON/cfJx29PYBd.B0eXq07wKl6x678b4P/0vG.Fy.m1L0a",
            "Klinovec 36", "1996-07-18", "2018-12-01");

INSERT INTO users (user_id, first_name, last_name, email, phone, password, address, birthday, graduation)
			VALUES('c6cf2893-1074-4638-a879-3604aa87f2a0', "Antonio", "Pavlinić", "antonio.pavlinic@nepostojeci.hr",
			"0975648222", "$2a$11$bBQIy5F6ON/cfJx29PYBd.B0eXq07wKl6x678b4P/0vG.Fy.m1L0a",
            "Kolodvorska 28", "1992-05-20", "2015-12-21");

INSERT INTO users (user_id, first_name, last_name, email, phone, password, address, birthday, graduation)
			VALUES('cf615b0e-5ee6-412f-b661-719d212b4884', "Ernest", "Tot", "ernest.tot@nepostojeci.hr",
			"0995668863", "$2a$11$bBQIy5F6ON/cfJx29PYBd.B0eXq07wKl6x678b4P/0vG.Fy.m1L0a",
            "Ljubljanska avenija 30", "1986-02-13", "2007-12-14");
		
INSERT INTO users (user_id, first_name, last_name, email, phone, password, address, birthday, graduation)
			VALUES('2f37ab13-e3d2-4e80-ac61-1504a8c01605', "Vanja", "Petrović", "vanja.petrovic@nepostojeci.hr",
			"0989965878", "$2a$11$bBQIy5F6ON/cfJx29PYBd.B0eXq07wKl6x678b4P/0vG.Fy.m1L0a",
            "Savska cesta 12a", "1996-07-14", "2018-12-30");

INSERT INTO users (user_id, first_name, last_name, email, phone, password, address, birthday, graduation)
			VALUES('e6efdad8-2192-4627-b6be-d93fc15555c0', "Ivanka", "Rezić", "ivanka.rezic@nepostojeci.hr",
			"0956312101", "$2a$11$bBQIy5F6ON/cfJx29PYBd.B0eXq07wKl6x678b4P/0vG.Fy.m1L0a",
            "Črnomerec 28", "1993-07-19", "2016-11-21");

INSERT INTO users (user_id, first_name, last_name, email, phone, password, address, birthday, graduation)
			VALUES('5849ed0e-c901-4e60-b4e7-807163deee9b', "Juraj", "Šebalj", "juraj.sebalj@nepostojeci.hr",
			"0954872541", "$2a$11$bBQIy5F6ON/cfJx29PYBd.B0eXq07wKl6x678b4P/0vG.Fy.m1L0a",
            "Trg Kralja Tomislava 2", "1994-07-21", "2010-10-05");

INSERT INTO role (name) VALUES ("ROLE_ADMINISTRATOR");
INSERT INTO role (name) VALUES ("ROLE_MODERATOR");
INSERT INTO role (name) VALUES ("ROLE_KORISNIK");

INSERT INTO users_roles (user_id, role_id) VALUES ('368ec8e2-c7cb-4eaf-b3f1-0077333fbea9', 1);
INSERT INTO users_roles (user_id, role_id) VALUES ('5849ed0e-c901-4e60-b4e7-807163deee9b', 2);
INSERT INTO users_roles (user_id, role_id) VALUES ('e6efdad8-2192-4627-b6be-d93fc15555c0', 3);
INSERT INTO users_roles (user_id, role_id) VALUES ('2f37ab13-e3d2-4e80-ac61-1504a8c01605', 3);
INSERT INTO users_roles (user_id, role_id) VALUES ('cf615b0e-5ee6-412f-b661-719d212b4884', 3);
INSERT INTO users_roles (user_id, role_id) VALUES ('c6cf2893-1074-4638-a879-3604aa87f2a0', 3);

INSERT INTO post_categories (name) VALUES ("DOGAĐAJ");
INSERT INTO post_categories (name) VALUES ("INFORMACIJA");
INSERT INTO post_categories (name) VALUES ("PREDAVANJE");
INSERT INTO post_categories (name) VALUES ("PONUDA POSLA");
INSERT INTO post_categories (name) VALUES ("O UDRUZI");

INSERT INTO users_subscriptions (user_id, post_category_id) VALUES ('368ec8e2-c7cb-4eaf-b3f1-0077333fbea9', 1);
INSERT INTO users_subscriptions (user_id, post_category_id) VALUES ('368ec8e2-c7cb-4eaf-b3f1-0077333fbea9', 2);
INSERT INTO users_subscriptions (user_id, post_category_id) VALUES ('368ec8e2-c7cb-4eaf-b3f1-0077333fbea9', 3);
INSERT INTO users_subscriptions (user_id, post_category_id) VALUES ('cf615b0e-5ee6-412f-b661-719d212b4884', 1);
INSERT INTO users_subscriptions (user_id, post_category_id) VALUES ('cf615b0e-5ee6-412f-b661-719d212b4884', 2);

INSERT INTO posts (post_id, title, short_description, long_description, address, create_date, modify_date)
	VALUES ('9f49e501-c5f9-41b7-a59b-51adad927fc8',
			"Najava ciklusa predavanja",
			"Poštovani alumni FER-a,<br>Ove godine započinjemo s ciklusom predavanja koje će održati renomirani predavači iz raznih područja elektrotehnike i računarstva. Osnovna ideja jest da to budu pregledna predavanja, zanimljiva i razumljiva svim našim alumnima.<br>U akademskoj godini 2017./2018. dogovoren je niz predavanja istaknutih profesora FER-a.",
			"Predavanja će se održavati u Sivoj vijećnici s početkom u 17 sati.<br>Na predavanja su pozvani svi alumni, studenti i djelatnici FER-a kao i njihovi gosti.<br>Zabilježite si datume u kalendar. Proslijedite informaciju vašim kolegama i prijateljima, alumnima FER-a.",
			"Ozaljska 8", 
			now(),
			now());
INSERT INTO posts (post_id, title, short_description, long_description, address, create_date, modify_date)
	VALUES ('ce236c67-ba86-4f65-9eee-f5627de48c89',
			"Ljudski sluh - savršen informacijski kanal?",
			"Udruga Alumni FER poziva vas na predavanje:<br>Ljudski sluh - savršen informacijski kanal?<br>koje će održati prof. dr. sc. Bojan Ivančević,",
			"Sluh je potpuno funkcionalan prije rođenja i posljednje je još funkcionalno osjetilo prije smrti. Sluh doslovce nikada ne spava. Ljudski sluh je optimiran za percepciju govora. Čovjek sluhom dobiva 86% svih komunikacijskih informacija. Sluh je najviši sudac zvuka!Čuješ? Jasno da čujem!!! Da, dok smo mladi i zdravi gotovo nitko ne razmišlja o sluhu, jer je to nešto „što se samo po sebi razumije“. Da li je to doista tako? Što zapravo čujemo? Što se događa ako nešto ne čujemo, ili čak ako ništa ne čujemo? Gdje su granice sluha, i o čemu ovise? Kakvu obrambenu ulogu ima sluh? Zašto se tako dobro snalazimo u prostoru? Čuju li životinje bolje od ljudi? Kolika je vremenska i amplitudna rezolucija sluha i kako to utječe na informatički kapacitet? Kakva bi trebala biti kvaliteta prijenosa ili zapisa akustičkog signala da bi se postigle mogućnosti sluha? Da li je tom izazovu dorasla današnja tehnologija? Mijenja li se sluh tijekom života i o čemu to ovisi? Što je glasnoća i što o njoj ovisi? Zašto se pojavljuju subjektivni tonovi? Što je efekt maskiranja zvuka? Pojam „slušna akustika“. Možemo li se potpuno pouzdati u sluh?",
			"u Sivoj vijećnici Fakulteta elektrotehnike i računarstva Sveučilišta u Zagrebu.", 
			now(),
			now());
INSERT INTO posts (post_id, title, short_description, long_description, address, create_date, modify_date)
	VALUES ('8114ab04-0fb8-43f2-a163-31876a2971c6',
			"Primjene suvremenih tehnologija u medicini",
			"Udruga Alumni FER u suradnji s Hrvatskim društvom za biomedicinsko inženjerstvo i medicinsku fiziku (HDBIMF), Odjelom za tehniku u medicini i biologiji Hrvatske sekcije IEEE i Akademijom tehničkih znanosti Hrvatske vas poziva na predavanje:", 
			"-",
			"Siva vijećnica Fakulteta elektrotehnike i računarstva Sveučilišta u Zagrebu.", 
			now(),
			now());																
INSERT INTO posts (post_id, title, short_description, long_description, address, create_date, modify_date) 
	VALUES ('5301414c-078f-44d9-a143-8aa2a66c9b2e',
			"Almae matris alumni Universitatis studiorum Zagrabiensis",
			"AMAC Domus i AMAC Mundus udruge okupljene su u Savez  društava bivših studenata Sveučilišta u Zagrebu koje ima svoje Predsjedništvo, Časni sud i Nadzorno povjerenstvo. Predsjednici i članovi tijela Saveza biraju se svake četiri godine na Saboru Saveza.",
			"Pri fakultetima i akademijama Sveučilišta u Zagrebu osnovane su ili se osnivaju alumni (AMAC/AMCA) udruge koje okupljaju svoje bivše studente. S druge strane, nekadašnji članovi našeg Sveučilišta koji žive i djeluju van granica naše zemlje osnovali su svoje udruge diljem svijeta. Svaka od tih udruga ima svoje pojedinisti organizacije i djelovanja, ali im je svima zajednička zadaća okupljanje svojih alumnija i promicanje alumni ideje među sadašnjim i budućim članovima. Alumni udruge u zemlji i neformalno su objedinjene pod nazivom AMAC Domus, a udruge osnovane u inozemstvu pod nazivom AMAC Mundus.", 
			"Kvintička 7", 
			now(),
			now());
INSERT INTO posts (post_id, title, short_description, long_description, address, create_date, modify_date)
	VALUES ('9aadc29e-eed6-44b5-a822-04999267714f',
			"Alumni klub VPŠ Zagreb",
			"Ažurirajte vaše informacije i podijelite vašu priču. Popunjavanje pristupnice za Alumni klub je prvi korak (nalazi se na dnu stranice). Ukoliko se samo želite prijaviti ili podijeliti vašu priču, molim da nas kontaktirate putem emaila <a href='mailto:alumni@vpsz.hr'>alumni@vpsz.hr<a>. ",
			"Alumni klub je udruženje bivših studenata Visoke poslovne škole Zagreb, a mi smo ponosni na svojih preko 710 diplomanata. Pozivamo Vas da ostanete u kontaktu sa fakultetom i da nastavite podupirati naše vrijednosti u visokom školstvu kroz propagiranje, uključenost i davanje. Nadamo se da ste ostvarili kontakte i prijateljstva koja su dragocijena i dugovječna. Sa velikim brojem alumni-a koji zovu Visoku poslovnu školu Zagreb svojom alma mater, dijelom ste raznolike i dinamične obitelji alumni-a, od učitelja i novinara do direktora i sportskih zvijezda. Kao diplomant Visoke poslovne škole Zagreb, automatski dobijate članstvo u VPŠZ Alumni klubu. Bilo da svojim domom zovete Zagreb ili neki drugi dio Lijepe Naše, povezani ste kroz posebnu mrežu koja je dijelom obitelji Visoke poslovne škole Zagreb, gdjegod išli i štogod radili.",
			"-", 
			now(),
			now());
INSERT INTO posts (post_id, title, short_description, long_description, address, create_date, modify_date)
	VALUES ('1a6e9480-d64e-4214-a8bc-043793f07756',
			"Informacijski paket",
			"Postoje mnoge varijacije odlomaka iz Lorem Ipsum-a, ali većina je pretrpjela kojekakve promjene s dodanim humorom, ili nasumičnim riječima koje nikako tu ne spadaju. Ako trebate koristiti Lorem Ipsum, morate biti sigurni da tekst ne sadrži skrivene nepodobne riječi ili fraze. Lorem Ipsum generatori na Internetu većinom ponavljaju zadane odlomke po potrebi, što ovaj naš čini prvim pravim generatorom na Internetu. Mi koristimo riječnik od 200 latinskih riječi, u kombinaciji s nekoliko modela rečeničnih struktura, da bi generirali Lorem Ipsum koji izgleda razumno. Stoga je Lorem Ipsum s ove stranice uvijek bez ponavljanja, bez dodanog humora ili nekaraketerističnih riječi.",
			"U 2016. godini održana je Alumni večer u Klubu književnika.ELSA Zagreb ponosi se svojim alumni članovima koji joj donose veliki ugled zbog uspješnih karijera.Studenti shvaćaju važnost prijateljstva i dobrih odnosa te se u tom smislu okupljamo u ELSA-i.Stres nije nepoznanica studentima, ali je zato udruga tu da jedni drugima pomognemo.Alumni klub ima svoju tajnicu i svoje događaje.", 
			"-", 
			now(),
			now());
INSERT INTO posts (post_id, title, short_description, long_description, address, create_date, modify_date)
	VALUES ('101ac6db-5225-494b-8216-1ab866a1d48a',
			"Alumni klub UNIZD Zadar",
			"Pozivamo Vas da se pridružite Alumni klubu UNIZD ispunjavanjem pristupnice.",
			"Alumni klub Sveučilišta u Zadru osnovan je 10. lipnja 2014. sa sljedećim ciljevima: očuvanje tradicije Sveučilišta u Zadru, promicanje ugleda Sveučilišta u Zadru u Hrvatskoj i u inozemstvu, skrb za razvitak i napredak Sveučilišta u Zadru, njegovanje etičnosti među sveučilištarcima,izgradnja i jačanje veza i suradnje između bivših studenata i Sveučilišta u Zadru, poticanje i uspostava veza i suradnje Sveučilišta u Zadru i sličnih obrazovnih, razvojnih i istraživačkih institucija u Republici Hrvatskoj i u svijetu, suradnja s institucijama u kojima rade bivši studenti Sveučilišta u Zadru i njegovih prethodnika te uspostava i razvijanje suradnje sa sličnim udrugama (AMAC/AMCA) u Hrvatskoj i inozemstvu.", 
			"Zadarska 23", 
			now(),
			now());	

INSERT INTO posts_categories (post_id, post_category_id) VALUES ('101ac6db-5225-494b-8216-1ab866a1d48a', 1);
INSERT INTO posts_categories (post_id, post_category_id) VALUES ('1a6e9480-d64e-4214-a8bc-043793f07756', 2);
INSERT INTO posts_categories (post_id, post_category_id) VALUES ('9aadc29e-eed6-44b5-a822-04999267714f', 1);
INSERT INTO posts_categories (post_id, post_category_id) VALUES ('9aadc29e-eed6-44b5-a822-04999267714f', 3);
INSERT INTO posts_categories (post_id, post_category_id) VALUES ('5301414c-078f-44d9-a143-8aa2a66c9b2e', 5);
INSERT INTO posts_categories (post_id, post_category_id) VALUES ('8114ab04-0fb8-43f2-a163-31876a2971c6', 5);
INSERT INTO posts_categories (post_id, post_category_id) VALUES ('ce236c67-ba86-4f65-9eee-f5627de48c89', 1);
INSERT INTO posts_categories (post_id, post_category_id) VALUES ('ce236c67-ba86-4f65-9eee-f5627de48c89', 3);
INSERT INTO posts_categories (post_id, post_category_id) VALUES ('ce236c67-ba86-4f65-9eee-f5627de48c89', 4);
INSERT INTO posts_categories (post_id, post_category_id) VALUES ('9f49e501-c5f9-41b7-a59b-51adad927fc8', 1);

INSERT INTO privilege (name) VALUES ("CRUD_USER");
INSERT INTO privilege (name) VALUES ("CRUD_POST");

INSERT INTO roles_privileges (role_id, privilege_id) VALUES (1,1);
INSERT INTO roles_privileges (role_id, privilege_id) VALUES (1,2);
INSERT INTO roles_privileges (role_id, privilege_id) VALUES (2,2);

INSERT INTO links (title, url) VALUES ("SUBOS", "http://www.subos.hr/");
INSERT INTO links (title, url) VALUES ("UDRUGA PROGRAMERA INFORMACIJSKE TEHNOLOGIJE (UPIT)", "http://upitsplit.hr/");
INSERT INTO links (title, url) VALUES ("ZAVIČAJNA UDRUGA STUDENATA DALMATINSKE ZAGORE", "http://www.zusdz.hr/");
INSERT INTO links (title, url) VALUES ("OMNI MEDIA", "http://www.omnimedia.com.hr/");
INSERT INTO links (title, url) VALUES ("UDRUGA O.A.ZA.", "http://oazainfo.hr/");
INSERT INTO links (title, url) VALUES ("BEST ZAGREB", "http://www.best.hr/");

insert into comments (comment_id, date, message, post_id, username) VALUES
	('a7efaf35-5007-4132-a7ee-e347d9f2eb41', now(), "Jedva čekam!", '101ac6db-5225-494b-8216-1ab866a1d48a', 'Juraj Šebalj');

insert into comments (comment_id, date, message, post_id, username) VALUES
	('0c610025-1a43-4e39-93c6-7765dae309c5', now(), "I ja isto!", '101ac6db-5225-494b-8216-1ab866a1d48a', 'Ernest Tot');

insert into comments (comment_id, date, message, post_id, username) VALUES
	('a860bf57-c3e9-4dfb-947b-03ce5ce5900d', now(), "Vidimo se!", '101ac6db-5225-494b-8216-1ab866a1d48a', 'Tomislav Kravaršćan');

insert into comments (comment_id, date, message, post_id, username) VALUES
	('3e11e7d5-309d-4933-aeea-1734a5d4b0d2', now(), "Može :D", '101ac6db-5225-494b-8216-1ab866a1d48a', 'Juraj Šebalj');






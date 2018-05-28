INSERT INTO location (location_id, location_name, zip_code)
			VALUES	 ('292dd5ea-5f28-4794-8eec-94adb19e46c2', "Zagreb", 10000);
INSERT INTO location (location_id, location_name, zip_code)
			VALUES   ('d167cd4c-be23-4dc3-9280-8e6451b41c46', "Bregana", 10432);
INSERT INTO location (location_id, location_name, zip_code)
			VALUES   ('bdd93cdb-a85f-4182-b051-c3d061eb583a', "Pula", 52100);
INSERT INTO location (location_id, location_name, zip_code)
			VALUES   ('10e619f2-5d4c-418a-b703-d265e873b774', "Sesvete", 10360);
            
INSERT INTO users (user_id, name, surname, email, phone, location_id, password, address, birthday, graduation)
			VALUES('368ec8e2-c7cb-4eaf-b3f1-0077333fbea9', "Mate", "Paulinović", "mate.paulinovic@gmail.com", "0994562345", '292dd5ea-5f28-4794-8eec-94adb19e46c2', "$2a$11$bBQIy5F6ON/cfJx29PYBd.B0eXq07wKl6x678b4P/0vG.Fy.m1L0a",
                   "gracanska 126", "1993-12-02", "1993-11-03");

INSERT INTO role (name) VALUES ("ROLE_ADMINISTRATOR");
INSERT INTO role (name) VALUES ("ROLE_ZAPOSLENIK");
INSERT INTO role (name) VALUES ("ROLE_KORISNIK");

INSERT INTO users_roles (user_id, role_id) VALUES ('368ec8e2-c7cb-4eaf-b3f1-0077333fbea9', 1);

INSERT INTO posts (post_id, title, short_description, long_description, address, post_type) VALUES ('9f49e501-c5f9-41b7-a59b-51adad927fc8',
																	 "Naslov",
																	 "Kratak opis posta",
																	 "Dugi opis posta", "Vukovarska 2", 1);
INSERT INTO posts (post_id, title, short_description, long_description, address, post_type) VALUES ('ce236c67-ba86-4f65-9eee-f5627de48c89',
																	 "Naslov",
																	 "Lorem Ipsum je jednostavno probni tekst koji se koristi u tiskarskoj i slovoslagarskoj industriji. Lorem Ipsum postoji kao industrijski standard još od 16-og stoljeća, kada je nepoznati tiskar uzeo tiskarsku galiju slova i posložio ih da bi napravio knjigu s uzorkom tiska. Taj je tekst ne samo preživio pet stoljeća, već se i vinuo u svijet elektronskog slovoslagarstva, ostajući u suštini nepromijenjen. Postao je popularan tijekom 1960-ih s pojavom Letraset listova s odlomcima Lorem Ipsum-a, a u skorije vrijeme sa software-om za stolno izdavaštvo kao što je Aldus PageMaker koji također sadrži varijante Lorem Ipsum-a.",
																	 "Dugi opis posta", "Vukovarska 2", 1);
INSERT INTO posts (post_id, title, short_description, long_description, address, post_type) VALUES ('8114ab04-0fb8-43f2-a163-31876a2971c6',
																	 "Naslov",
																	 "Suprotno raširenom mišljenju, Lorem Ipsum nije samo slučajni tekst, već ima korijene u klasičnoj latinskoj književnosti iz godine 45. pr.n.e., što znači da mu je preko 2000 godina. Richard McClintock, profesor latinskog jezika na Hampden-Sydney koledžu u Virginiji, potražio je jednu od čudnijijh latinskih riječi, consectetur, iz Lorem Ipsum teksta, i prolazeći kroz citate te riječi u klasičnoj književnosti, otkrio nedvojbeni izvor. Lorem Ipsum dolazi iz odlomaka 1.10.32 i 1.10.33 Ciceronovog djela pod naslovom de Finibus Bonorum et Malorum (Krajnosti dobra i zla), napisanog 45. godine pr.n.e. Ovo je djelo rasprava o teoriji etike, a bilo je vrlo popularno u Renesansi. Prvi redak Lorem Ipsum-a, Lorem ipsum dolor sit amet.., dolazi iz odlomka 1.10.32.",
																	 "Dugi opis posta", "Vukovarska 2", 1);																
INSERT INTO posts (post_id, title, short_description, long_description, address, post_type) VALUES ('5301414c-078f-44d9-a143-8aa2a66c9b2e',
																	 "Naslov",
																	 "Kratak opis posta",
																	 "Dugi opis posta", "Vukovarska 2", 1);
INSERT INTO posts (post_id, title, short_description, long_description, address, post_type) VALUES ('9aadc29e-eed6-44b5-a822-04999267714f',
																	 "Naslov",
																	 "Kratak opis posta",
																	 "Dugi opis posta", "Vukovarska 2", 1);
INSERT INTO posts (post_id, title, short_description, long_description, address, post_type) VALUES ('1a6e9480-d64e-4214-a8bc-043793f07756',
																	 "Naslov",
																	 "Postoje mnoge varijacije odlomaka iz Lorem Ipsum-a, ali većina je pretrpjela kojekakve promjene s dodanim humorom, ili nasumičnim riječima koje nikako tu ne spadaju. Ako trebate koristiti Lorem Ipsum, morate biti sigurni da tekst ne sadrži skrivene nepodobne riječi ili fraze. Lorem Ipsum generatori na Internetu većinom ponavljaju zadane odlomke po potrebi, što ovaj naš čini prvim pravim generatorom na Internetu. Mi koristimo riječnik od 200 latinskih riječi, u kombinaciji s nekoliko modela rečeničnih struktura, da bi generirali Lorem Ipsum koji izgleda razumno. Stoga je Lorem Ipsum s ove stranice uvijek bez ponavljanja, bez dodanog humora ili nekaraketerističnih riječi.",
																	 "Dugi opis posta", "Vukovarska 2", 1);
INSERT INTO posts (post_id, title, short_description, long_description, address, post_type) VALUES ('101ac6db-5225-494b-8216-1ab866a1d48a',
																	 "Naslov",
																	 "Kratak opis posta",
																	 "Dugi opis posta", "Vukovarska 2", 1);																	
INSERT INTO privilege (name) VALUES ("ADD_PET_OTHERS");
INSERT INTO privilege (name) VALUES ("ADD_RESERVATION_OTHERS");
INSERT INTO privilege (name) VALUES ("ELEVATE_USER_TO_EMPLOYEE");
INSERT INTO privilege (name) VALUES ("ACCEPT_RESERVATION");
INSERT INTO privilege (name) VALUES ("CONFIRM_RESERVATION");
INSERT INTO privilege (name) VALUES ("VIEW_ALL_RESERVATIONS");
INSERT INTO privilege (name) VALUES ("VIEW_FREE_RESERVATIONS");	
INSERT INTO privilege (name) VALUES ("ARCHIVE_RESERVATION");

INSERT INTO roles_privileges (role_id, privilege_id) VALUES (1,1);
INSERT INTO roles_privileges (role_id, privilege_id) VALUES (1,2);
INSERT INTO roles_privileges (role_id, privilege_id) VALUES (1,3);
INSERT INTO roles_privileges (role_id, privilege_id) VALUES (1,4);
INSERT INTO roles_privileges (role_id, privilege_id) VALUES (1,5);
INSERT INTO roles_privileges (role_id, privilege_id) VALUES (1,6);
INSERT INTO roles_privileges (role_id, privilege_id) VALUES (1,7);
INSERT INTO roles_privileges (role_id, privilege_id) VALUES (1,8);
INSERT INTO roles_privileges (role_id, privilege_id) VALUES (2,4);
INSERT INTO roles_privileges (role_id, privilege_id) VALUES (2,7);
                    
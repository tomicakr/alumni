INSERT INTO location (location_id, location_name, zip_code)
			VALUES	 ('\x1000', "Zagreb", 10000);
INSERT INTO location (location_id, location_name, zip_code)
			VALUES   ('\x1001', "Bregana", 10432);
INSERT INTO location (location_id, location_name, zip_code)
			VALUES   ('\x1002', "Pula", 52100);
INSERT INTO location (location_id, location_name, zip_code)
			VALUES   ('\x1003', "Sesvete", 10360);
            
INSERT INTO users (user_id, name, surname, user_pid, user_mnemonic,
				   email, mobile_phone, phone, location_id, password,
                   address, notification_setting, remark)
			VALUES('\x0000', "Mate", "Paulinović", "mp00", "mp",
				   "mate.paulinovic@gmail.com", "0994562345", "4645555", '\x1000', "mate123",
                   "gracanska 126", 2, "nema");
INSERT INTO users (user_id, name, surname, user_pid, user_mnemonic,
				   email, mobile_phone, phone, location_id, password,
                   address, notification_setting, remark)
			VALUES('\x0001', "Filip", "Sodić", "fs01", "fs",
				   "filip.sodic@gmail.com", "0994539394", "4645542", '\x1001', "filip123",
                   "sodiceva 32", 2, "puno");
INSERT INTO users (user_id, name, surname, user_pid, user_mnemonic,
				   email, mobile_phone, phone, location_id, password,
                   address, notification_setting, remark)
			VALUES('\x0002', "Tomica", "Kravršćan", "tk01", "tk",
				   "tomica.kravrscan@gmail.com", "0996578125", "4643124", '\x1003', "tomica123",
                   "smarkusevecka ulica 23", 2, "neki");
INSERT INTO users (user_id, name, surname, user_pid, user_mnemonic,
				   email, mobile_phone, phone, location_id, password,
                   address, notification_setting, remark)
			VALUES('\x0003', "Vlado", "Galiuskas", "vg01", "vg",
				   "galiuskas@gmail.com", "09", "4643134", '\x1002', "vlado123",
                   "istrijanska ulica 23", 2, "ne bas");
INSERT INTO users (user_id, name, surname, user_pid, user_mnemonic,
				   email, mobile_phone, phone, location_id, password,
                   address, notification_setting, remark)
			VALUES('\x0004', "Maja", "Krmpotić-Đurđević", "mkđ01", "mkđ",
				   "maja.krmpi@gmail.com", "0913264547", "3212132", '\x1002', "maja123",
                   "pazinska 13", 1, "simpa");

INSERT INTO role (name) VALUES ("ROLE_ADMINISTRATOR");
INSERT INTO role (name) VALUES ("ROLE_ZAPOSLENIK");
INSERT INTO role (name) VALUES ("ROLE_KORISNIK");

/*DODATI PRIVILEGES I ROLES_PRIVILEGES I  USERS_ROLES;*/

INSERT INTO service (service_key, description, name, price) VALUES ('\x2000', "", "Šetnja", 35.0);
INSERT INTO service (service_key, description, name, price) VALUES ('\x2001', "", "Odlazak veterinaru", 20.0);
INSERT INTO service (service_key, description, name, price) VALUES ('\x2002', "", "Rezanje nokata", 30.0);
INSERT INTO service (service_key, description, name, price) VALUES ('\x2003', "", "Kupanje", 40.0);
INSERT INTO service (service_key, description, name, price) VALUES ('\x2004', "", "Čuvanje", 35.0);

INSERT INTO pet 	(pet_key, name, age, breed, species,
					 sex, user_id, microchip, remark)
			VALUES ('\x3000', "rex", 2, "Njemacki ovcar", "Pas",
					'm', '\x0000', "y", "");
INSERT INTO pet 	(pet_key, name, age, breed, species,
					 sex, user_id, microchip, remark)
			VALUES ('\x3001', "jazavac", 2, "Hijacintna ara", "Papiga",
					'f', '\x0001', "y", "");
INSERT INTO pet 	(pet_key, name, age, breed, species,
					 sex, user_id, microchip, remark)
			VALUES ('\x3002', "spaner", 2, "Crna macka", "Macka",
					'm', '\x0002', "y", "");
INSERT INTO pet 	(pet_key, name, age, breed, species,
					 sex, user_id, microchip, remark)
			VALUES ('\x3003', "ralf rudez", 1, "Šnaucer", "Pas", 
					'm', '\x0001', "y", "");
                    
/*SELECT petsonly_test.users.name, petsonly_test.users.surname, petsonly_test.pet.name FROM petsonly_test.users INNER JOIN petsonly_test.pet ON petsonly_test.users.user_id*/
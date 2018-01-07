INSERT INTO location (location_id, location_name, zip_code)
			VALUES	 ('292dd5ea-5f28-4794-8eec-94adb19e46c2', "Zagreb", 10000);
INSERT INTO location (location_id, location_name, zip_code)
			VALUES   ('d167cd4c-be23-4dc3-9280-8e6451b41c46', "Bregana", 10432);
INSERT INTO location (location_id, location_name, zip_code)
			VALUES   ('bdd93cdb-a85f-4182-b051-c3d061eb583a', "Pula", 52100);
INSERT INTO location (location_id, location_name, zip_code)
			VALUES   ('10e619f2-5d4c-418a-b703-d265e873b774', "Sesvete", 10360);
            
INSERT INTO users (user_id, name, surname, user_pid, user_mnemonic,
				   email, mobile_phone, phone, location_id, password,
                   address, notification_setting, remark)
			VALUES('368ec8e2-c7cb-4eaf-b3f1-0077333fbea9', "Mate", "Paulinović", "mp00", "mp",
				   "mate.paulinovic@gmail.com", "0994562345", "4645555", '292dd5ea-5f28-4794-8eec-94adb19e46c2', "mate123",
                   "gracanska 126", 2, "nema");
INSERT INTO users (user_id, name, surname, user_pid, user_mnemonic,
				   email, mobile_phone, phone, location_id, password,
                   address, notification_setting, remark)
			VALUES('fb8f4014-e0b0-4a5e-9079-320c1e1516e8', "Filip", "Sodić", "fs01", "fs",
				   "filip.sodic@gmail.com", "0994539394", "4645542", 'd167cd4c-be23-4dc3-9280-8e6451b41c46', "filip123",
                   "sodiceva 32", 2, "puno");
INSERT INTO users (user_id, name, surname, user_pid, user_mnemonic,
				   email, mobile_phone, phone, location_id, password,
                   address, notification_setting, remark)
			VALUES('0e070ca1-4950-40ff-a1d7-8f65e6f559bf', "Tomica", "Kravršćan", "tk01", "tk",
				   "tomica.kravrscan@gmail.com", "0996578125", "4643124", '10e619f2-5d4c-418a-b703-d265e873b774', "tomica123",
                   "smarkusevecka ulica 23", 2, "neki");
INSERT INTO users (user_id, name, surname, user_pid, user_mnemonic,
				   email, mobile_phone, phone, location_id, password,
                   address, notification_setting, remark)
			VALUES('0ae3760e-8ce3-4a4e-b678-98d5f6a86c65', "Vlado", "Galiuskas", "vg01", "vg",
				   "galiuskas@gmail.com", "09", "4643134", 'bdd93cdb-a85f-4182-b051-c3d061eb583a', "vlado123",
                   "istrijanska ulica 23", 2, "ne bas");
INSERT INTO users (user_id, name, surname, user_pid, user_mnemonic,
				   email, mobile_phone, phone, location_id, password,
                   address, notification_setting, remark)
			VALUES('48c2ad9c-c37a-4dbc-a823-11adbbe11769', "Maja", "Krmpotić-Đurđević", "mkđ01", "mkđ",
				   "maja.krmpi@gmail.com", "0913264547", "3212132", 'bdd93cdb-a85f-4182-b051-c3d061eb583a', "maja123",
                   "pazinska 13", 1, "simpa");

INSERT INTO role (name) VALUES ("ROLE_ADMINISTRATOR");
INSERT INTO role (name) VALUES ("ROLE_ZAPOSLENIK");
INSERT INTO role (name) VALUES ("ROLE_KORISNIK");

/*DODATI PRIVILEGES I ROLES_PRIVILEGES I  USERS_ROLES;*/

INSERT INTO service (service_key, description, name, price) VALUES ('9f49e501-c5f9-41b7-a59b-51adad927fc8', "", "Šetnja", 35.0);
INSERT INTO service (service_key, description, name, price) VALUES ('64ec5189-c400-451e-a986-97f06978aa5e', "", "Odlazak veterinaru", 20.0);
INSERT INTO service (service_key, description, name, price) VALUES ('45fe9664-4ab7-4fea-915f-0c3607ea2120', "", "Rezanje nokata", 30.0);
INSERT INTO service (service_key, description, name, price) VALUES ('4a63f218-3d3d-4f3a-a7c6-9e71c3e3dd6b', "", "Kupanje", 40.0);
INSERT INTO service (service_key, description, name, price) VALUES ('a64f3c62-f811-46db-a135-700be2f60211', "", "Čuvanje", 35.0);

INSERT INTO pet 	(pet_key, name, age, breed, species,
					 sex, user_id, microchip, remark)
			VALUES ('f00e094f-53ef-4b26-a05c-4b6d6a722fca', "rex", 2, "Njemacki ovčar", "Pas",
					'm', '368ec8e2-c7cb-4eaf-b3f1-0077333fbea9', "y", "");
INSERT INTO pet 	(pet_key, name, age, breed, species,
					 sex, user_id, microchip, remark)
			VALUES ('0e5ec5ff-48a3-403d-a4ac-dd9176d6a082', "jazavac", 2, "Hijacintna ara", "Papiga",
					'f', 'fb8f4014-e0b0-4a5e-9079-320c1e1516e8', "y", "");
INSERT INTO pet 	(pet_key, name, age, breed, species,
					 sex, user_id, microchip, remark)
			VALUES ('4234a45d-9220-4576-8e94-0d3b0e2c6eaf', "Spaner", 2, "Crna mačka", "Mačka",
					'm', '0e070ca1-4950-40ff-a1d7-8f65e6f559bf', "y", "");
INSERT INTO pet 	(pet_key, name, age, breed, species,
					 sex, user_id, microchip, remark)
			VALUES ('33ef54cf-b2e2-4839-bd7f-866d51e7e571', "Šnaucer", 1, "njemacki ovcar", "Pas", 
					'm', 'fb8f4014-e0b0-4a5e-9079-320c1e1516e8', "y", "");
					
					
INSERT INTO reservation (reservation_key, document_path, duration, 
						 execution_time, price, reservation_status, reservation_time,
                         send_reminder, owner_id, pet_key, service_key)
			VALUES ('372110c8-7141-432c-bc2f-95c6af824b3d', null, 100, 
					'2017-01-08 12:00:00', 100, 1, '2017-01-06 23:15:00',
                    1, 'fb8f4014-e0b0-4a5e-9079-320c1e1516e8', '33ef54cf-b2e2-4839-bd7f-866d51e7e571', '9f49e501-c5f9-41b7-a59b-51adad927fc8');
INSERT INTO reservation (reservation_key, document_path, duration, 
						 execution_time, price, reservation_status, reservation_time,
                         send_reminder, owner_id, pet_key, service_key)
			VALUES ('80e3cf63-202b-4189-9c19-dbfb20e0d0c3', null, 200, 
					'2017-01-10 14:30:00', 200, 1, '2017-01-04 12:32:47',
                    0, '0e070ca1-4950-40ff-a1d7-8f65e6f559bf', '4234a45d-9220-4576-8e94-0d3b0e2c6eaf', '45fe9664-4ab7-4fea-915f-0c3607ea2120');
INSERT INTO reservation (reservation_key, document_path, duration, 
						 execution_time, price, reservation_status, reservation_time,
                         send_reminder, owner_id, pet_key, service_key)
			VALUES ('3f07b2ef-8a35-4754-875d-bf5745d58b8b', null, 150, 
					'2017-01-08 12:00:00', 130, 3, '2016-12-30 07:59:32',
                    0, '368ec8e2-c7cb-4eaf-b3f1-0077333fbea9', 'f00e094f-53ef-4b26-a05c-4b6d6a722fca', '9f49e501-c5f9-41b7-a59b-51adad927fc8');
INSERT INTO reservation (reservation_key, document_path, duration, 
						 execution_time, price, reservation_status, reservation_time,
                         send_reminder, owner_id, pet_key, service_key)
			VALUES ('86f2a858-f0b2-4983-bbc7-6f88627bf2bf', null, 200, 
					'2017-01-06 11:15:00', 40, 2, '2016-12-30 23:15:00',
                    1, '0e070ca1-4950-40ff-a1d7-8f65e6f559bf', '4234a45d-9220-4576-8e94-0d3b0e2c6eaf', 'a64f3c62-f811-46db-a135-700be2f60211');
INSERT INTO reservation (reservation_key, document_path, duration, 
						 execution_time, price, reservation_status, reservation_time,
                         send_reminder, owner_id, pet_key, service_key)
			VALUES ('d72ce3d1-a7d9-4d9f-8367-435f8ab2da0e', null, 150, 
					'2017-02-08 09:45:00', 50, 1, '2017-01-01 00:00:00',
                    1, 'fb8f4014-e0b0-4a5e-9079-320c1e1516e8', '33ef54cf-b2e2-4839-bd7f-866d51e7e571', '64ec5189-c400-451e-a986-97f06978aa5e');
                    
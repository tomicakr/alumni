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
			VALUES('368ec8e2-c7cb-4eaf-b3f1-0077333fbea9', "Mate", "Paulinović", "63884677757", "mp0",
				   "mate.paulinovic@gmail.com", "0994562345", "4645555", '292dd5ea-5f28-4794-8eec-94adb19e46c2', "$2a$11$bBQIy5F6ON/cfJx29PYBd.B0eXq07wKl6x678b4P/0vG.Fy.m1L0a",
                   "gracanska 126", 2, "nema");
INSERT INTO users (user_id, name, surname, user_pid, user_mnemonic,
				   email, mobile_phone, phone, location_id, password,
                   address, notification_setting, remark)
			VALUES('fb8f4014-e0b0-4a5e-9079-320c1e1516e8', "Filip", "Sodić", "69094866377", "fs0",
				   "filip.sodic@gmail.com", "0994539394", "4645542", 'd167cd4c-be23-4dc3-9280-8e6451b41c46', "$2a$11$1moJr1wwg2J42JLp.S75zu/gfAZscwW1la7A7rHhsDcfeBtjUX5GC",
                   "sodiceva 32", 2, "puno");
INSERT INTO users (user_id, name, surname, user_pid, user_mnemonic,
				   email, mobile_phone, phone, location_id, password,
                   address, notification_setting, remark)
			VALUES('0e070ca1-4950-40ff-a1d7-8f65e6f559bf', "Tomica", "Kravršćan", "25295020607", "tk0",
				   "tomica.kravrscan@gmail.com", "0996578125", "4643124", '10e619f2-5d4c-418a-b703-d265e873b774', "$2a$11$OCuI5iBjLW2qtPTI1Uv.DeMiSeqLlYkZsEUURmLhFJfbg5eOu7pJS",
                   "smarkusevecka ulica 23", 2, "neki");
INSERT INTO users (user_id, name, surname, user_pid, user_mnemonic,
				   email, mobile_phone, phone, location_id, password,
                   address, notification_setting, remark)
			VALUES('0ae3760e-8ce3-4a4e-b678-98d5f6a86c65', "Vlado", "Galiuskas", "22242743756", "vg0",
				   "galiuskas@gmail.com", "09", "4643134", 'bdd93cdb-a85f-4182-b051-c3d061eb583a', "$2a$11$Ka7BWepObVeUBY35cZ.SLeS1SRGe8d3631UfYDADSymTJkxHl6oAK",
                   "istrijanska ulica 23", 2, "ne bas");
INSERT INTO users (user_id, name, surname, user_pid, user_mnemonic,
				   email, mobile_phone, phone, location_id, password,
                   address, notification_setting, remark)
			VALUES('48c2ad9c-c37a-4dbc-a823-11adbbe11769', "Maja", "Krmpotić-Đurđević", "41391200191", "mk0",
				   "maja.krmpi@gmail.com", "0913264547", "3212132", 'bdd93cdb-a85f-4182-b051-c3d061eb583a', "$2a$11$fdSOu9039wmCfV8maVQ.R.DfDB0RI5B4mqDSB5moc1AbE.8KDLVZW",
                   "pazinska 13", 1, "simpa");

INSERT INTO users (user_id, name, surname, user_pid, user_mnemonic,
				   email, mobile_phone, phone, location_id, password,
                   address, notification_setting, remark)
			VALUES('368ec8e2-c7cb-4eaf-b3f1-0077333fbe55', "Antun", "Modrušan", "98486854147", "am0",
				   "antun.mod@gmail.com", "0998190467", "4834563", '292dd5ea-5f28-4794-8eec-94adb19e46c2', "$2a$11$So9anMJ0d/Y2mghfoUSVQ.sV0alMb/.zhw6e838ZJsSQRIBjjKyHG",
                   "vladimira nazora 59a", 2, "onak");
                   
INSERT INTO users (user_id, name, surname, user_pid, user_mnemonic,
				   email, mobile_phone, phone, location_id, password,
                   address, notification_setting, remark,
                   not_available_from, not_available_to)
			VALUES('49341e99-918c-4214-a2f2-21403bc617aa', "Zaposlenik", "Zaposlenković", "76885283160", "zz0",
				   "zaposlenik@gmail.com", "099268953", "4645777", '292dd5ea-5f28-4794-8eec-94adb19e46c2', "$2a$11$mkQbhBahnf.9HEe.PVx27OKYiiLQvJjgYQQcIOf4QHZOY5yOIvnZy",
                   "zaposlenicka 3", 2, "nema",
                   '23:00:000', '06:00:00');

INSERT INTO role (name) VALUES ("ROLE_ADMINISTRATOR");
INSERT INTO role (name) VALUES ("ROLE_ZAPOSLENIK");
INSERT INTO role (name) VALUES ("ROLE_KORISNIK");

INSERT INTO users_roles (user_id, role_id) VALUES ('fb8f4014-e0b0-4a5e-9079-320c1e1516e8', 1);
INSERT INTO users_roles (user_id, role_id) VALUES ('49341e99-918c-4214-a2f2-21403bc617aa', 2);
INSERT INTO users_roles (user_id, role_id) VALUES ('48c2ad9c-c37a-4dbc-a823-11adbbe11769', 3);
INSERT INTO users_roles (user_id, role_id) VALUES ('0ae3760e-8ce3-4a4e-b678-98d5f6a86c65', 3);
INSERT INTO users_roles (user_id, role_id) VALUES ('0e070ca1-4950-40ff-a1d7-8f65e6f559bf', 3);
INSERT INTO users_roles (user_id, role_id) VALUES ('368ec8e2-c7cb-4eaf-b3f1-0077333fbea9', 3);

/*DODATI PRIVILEGES I ROLES_PRIVILEGES I  USERS_ROLES;*/

INSERT INTO service (service_key, description, name, price) VALUES ('9f49e501-c5f9-41b7-a59b-51adad927fc8', "", "Šetnja", 35.0);
INSERT INTO service (service_key, description, name, price) VALUES ('64ec5189-c400-451e-a986-97f06978aa5e', "", "Odlazak veterinaru", 20.0);
INSERT INTO service (service_key, description, name, price) VALUES ('45fe9664-4ab7-4fea-915f-0c3607ea2120', "", "Rezanje noktiju", 30.0);
INSERT INTO service (service_key, description, name, price) VALUES ('4a63f218-3d3d-4f3a-a7c6-9e71c3e3dd6b', "", "Kupanje", 40.0);
INSERT INTO service (service_key, description, name, price) VALUES ('a64f3c62-f811-46db-a135-700be2f60211', "", "Čuvanje", 35.0);

INSERT INTO species (id, name) VALUES ('20998bee-089d-491c-9c49-1289daf90242', "Pas");
INSERT INTO species (id, name) VALUES ('600d9442-bc5c-4905-9f08-81635cb8be1a', "Mačka");
INSERT INTO species (id, name) VALUES ('8ae74ccf-30f9-4ff4-a980-ffe600070a3f', "Papiga");
INSERT INTO species (id, name) VALUES ('b6c22a3e-ea8a-4b05-b518-bfcae6fb6f37', "Gušter");

INSERT INTO pet 	(pet_key, name, age, species_id,
					 sex, user_id, microchip, remark)
			VALUES ('f00e094f-53ef-4b26-a05c-4b6d6a722fca', "rex", 2, '20998bee-089d-491c-9c49-1289daf90242',
					'm', '368ec8e2-c7cb-4eaf-b3f1-0077333fbea9', "y", "");
INSERT INTO pet 	(pet_key, name, age, species_id,
					 sex, user_id, microchip, remark)
			VALUES ('0e5ec5ff-48a3-403d-a4ac-dd9176d6a082', "jazavac", 2, '8ae74ccf-30f9-4ff4-a980-ffe600070a3f',
					'f', 'fb8f4014-e0b0-4a5e-9079-320c1e1516e8', "y", "");
INSERT INTO pet 	(pet_key, name, age, species_id,
					 sex, user_id, microchip, remark)
			VALUES ('4234a45d-9220-4576-8e94-0d3b0e2c6eaf', "Spaner", 2,  '600d9442-bc5c-4905-9f08-81635cb8be1a',
					'm', '0e070ca1-4950-40ff-a1d7-8f65e6f559bf', "y", "");
INSERT INTO pet 	(pet_key, name, age, species_id,
					 sex, user_id, microchip, remark)
			VALUES ('33ef54cf-b2e2-4839-bd7f-866d51e7e571', "Šnaucer", 1, '20998bee-089d-491c-9c49-1289daf90242', 
					'm', 'fb8f4014-e0b0-4a5e-9079-320c1e1516e8', "y", "");
					
					
INSERT INTO reservation (reservation_key, document_path, duration, employee_id, 
						 execution_time, price, reservation_status, reservation_time,
                         send_reminder, owner_id, pet_key, service_key, prefered_employee_id)
			VALUES ('372110c8-7141-432c-bc2f-95c6af824b3d', null, "01:40", '49341e99-918c-4214-a2f2-21403bc617aa',
					'2017-01-08 12:00:00', 100, 1, '2017-01-06 23:15:00', 
                    1, 'fb8f4014-e0b0-4a5e-9079-320c1e1516e8', '33ef54cf-b2e2-4839-bd7f-866d51e7e571', '9f49e501-c5f9-41b7-a59b-51adad927fc8', null);
INSERT INTO reservation (reservation_key, document_path, duration, employee_id, 
						 execution_time, price, reservation_status, reservation_time,
                         send_reminder, owner_id, pet_key, service_key, prefered_employee_id)
			VALUES ('80e3cf63-202b-4189-9c19-dbfb20e0d0c3', null, "03:20", '49341e99-918c-4214-a2f2-21403bc617aa',
					'2017-01-10 14:30:00', 200, 1, '2017-01-04 12:32:47',
                    0, '0e070ca1-4950-40ff-a1d7-8f65e6f559bf', '4234a45d-9220-4576-8e94-0d3b0e2c6eaf', '45fe9664-4ab7-4fea-915f-0c3607ea2120', null);
INSERT INTO reservation (reservation_key, document_path, duration, employee_id, 
						 execution_time, price, reservation_status, reservation_time,
                         send_reminder, owner_id, pet_key, service_key, prefered_employee_id)
			VALUES ('3f07b2ef-8a35-4754-875d-bf5745d58b8b', null,"01:30", '49341e99-918c-4214-a2f2-21403bc617aa',
					'2017-01-08 12:00:00', 130, 3, '2016-12-30 07:59:32',
                    0, '368ec8e2-c7cb-4eaf-b3f1-0077333fbea9', 'f00e094f-53ef-4b26-a05c-4b6d6a722fca', '9f49e501-c5f9-41b7-a59b-51adad927fc8', null);
INSERT INTO reservation (reservation_key, document_path, duration, employee_id, 
						 execution_time, price, reservation_status, reservation_time,
                         send_reminder, owner_id, pet_key, service_key, prefered_employee_id)
			VALUES ('86f2a858-f0b2-4983-bbc7-6f88627bf2bf', null, "03:20", '49341e99-918c-4214-a2f2-21403bc617aa',
					'2017-01-06 11:15:00', 40, 2, '2016-12-30 23:15:00',
                    1, '0e070ca1-4950-40ff-a1d7-8f65e6f559bf', '4234a45d-9220-4576-8e94-0d3b0e2c6eaf', 'a64f3c62-f811-46db-a135-700be2f60211', null);
INSERT INTO reservation (reservation_key, document_path, duration, employee_id, 
						 execution_time, price, reservation_status, reservation_time,
                         send_reminder, owner_id, pet_key, service_key, prefered_employee_id)
			VALUES ('d72ce3d1-a7d9-4d9f-8367-435f8ab2da0e', null, "01:00", '49341e99-918c-4214-a2f2-21403bc617aa',
					'2017-02-08 09:45:00', 50, 1, '2017-01-01 00:00:00',
                    1, 'fb8f4014-e0b0-4a5e-9079-320c1e1516e8', '33ef54cf-b2e2-4839-bd7f-866d51e7e571', '64ec5189-c400-451e-a986-97f06978aa5e', null);
                    
INSERT INTO privilege (name) VALUES ("ADD_PET_OTHERS");
INSERT INTO privilege (name) VALUES ("ADD_RESERVATION_OTHERS");
INSERT INTO privilege (name) VALUES ("ELEVATE_USER_TO_EMPLOYEE");
INSERT INTO privilege (name) VALUES ("ACCEPT_RESERVATION");
INSERT INTO privilege (name) VALUES ("CONFIRM_RESERVATION");
INSERT INTO privilege (name) VALUES ("VIEW_ALL_RESERVATIONS");
INSERT INTO privilege (name) VALUES ("VIEW_FREE_RESERVATIONS");	

INSERT INTO roles_privileges (role_id, privilege_id) VALUES (1,1);
INSERT INTO roles_privileges (role_id, privilege_id) VALUES (1,2);
INSERT INTO roles_privileges (role_id, privilege_id) VALUES (1,3);
INSERT INTO roles_privileges (role_id, privilege_id) VALUES (1,4);
INSERT INTO roles_privileges (role_id, privilege_id) VALUES (1,5);
INSERT INTO roles_privileges (role_id, privilege_id) VALUES (1,6);
INSERT INTO roles_privileges (role_id, privilege_id) VALUES (1,7);
INSERT INTO roles_privileges (role_id, privilege_id) VALUES (2,4);
INSERT INTO roles_privileges (role_id, privilege_id) VALUES (2,7);
                    
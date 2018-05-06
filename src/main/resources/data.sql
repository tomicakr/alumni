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

INSERT INTO users_roles (user_id, role_id) VALUES ('368ec8e2-c7cb-4eaf-b3f1-0077333fbea9', 3);

INSERT INTO posts (post_id, title, short_description, long_description, address, post_type) VALUES ('9f49e501-c5f9-41b7-a59b-51adad927fc8',
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
                    
INSERT INTO usuarios(username, password, enabled, nombre, apellido, email) VALUES ('andres', '$2a$10$8/tE6DKg.lQn1ArGYGzqX.NFAKRQQLMzyNTBjBXtw46i1WHA1AGXy',1,'andres','guzman','algo@gmail.com');
INSERT INTO usuarios(username, password, enabled, nombre, apellido, email) VALUES ('juan', '$2a$10$8/tE6DKg.lQn1ArGYGzqX.NFAKRQQLMzyNTBjBXtw46i1WHA1AGXy',1,'juan','lopez','alguien@gmail.com');

Insert into `roles` (nombre) values('ROLE_USER');
Insert into `roles` (nombre) values('ROLE_ADMIN');

Insert into `usuarios_roles` (usuario_id, role_id) values(1,2);
Insert into `usuarios_roles` (usuario_id, role_id) values(2,2);
Insert into `usuarios_roles` (usuario_id, role_id) values(2,1);
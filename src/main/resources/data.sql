
--INSERT INTO Users (name, email, role) VALUES ('Juan Perez', 'juanperez@example.com', 'USER');
--INSERT INTO Users (name, email, role) VALUES ('Maria Lopez', 'marialopez@example.com', 'ADMIN');
--INSERT INTO Users (name, email, role) VALUES ('Carlos Sanchez', 'carlossanchez@example.com', 'USER');
--INSERT INTO Users (name, email, role) VALUES ('Laura Garcia', 'lauragarcia@example.com', 'USER');
--INSERT INTO Users (name, email, role) VALUES ('Antonio Ruiz', 'antonioruiz@example.com', 'USER');
-- Insertar Clientes
INSERT INTO client (name, email) VALUES ('John Doe', 'johndoe@example.com');
INSERT INTO client (name, email) VALUES ('Jane Smith', 'janesmith@example.com');

-- Insertar Mesas
INSERT INTO tables (name) VALUES ('Table 1');
INSERT INTO tables (name) VALUES ('Table 2');
INSERT INTO tables (name) VALUES ('Table 3');

-- Insertar Horarios
INSERT INTO schedule (start_date_time, end_date_time, available, table_id) 
VALUES ('2024-12-10T09:00:00', '2024-12-10T10:00:00', TRUE, 1);
INSERT INTO schedule (start_date_time, end_date_time, available, table_id) 
VALUES ('2024-12-10T10:00:00', '2024-12-10T11:00:00', TRUE, 2);
INSERT INTO schedule (start_date_time, end_date_time, available, table_id) 
VALUES ('2024-12-10T11:00:00', '2024-12-10T12:00:00', TRUE, 3);

INSERT INTO schedule (start_date_time, end_date_time, available, table_id) 
VALUES ('2024-12-12T09:00:00', '2024-12-12T10:00:00', TRUE, 1);
INSERT INTO schedule (start_date_time, end_date_time, available, table_id) 
VALUES ('2024-12-12T10:00:00', '2024-12-12T11:00:00', TRUE, 2);
INSERT INTO schedule (start_date_time, end_date_time, available, table_id) 
VALUES ('2024-12-12T11:00:00', '2024-12-12T12:00:00', TRUE, 3);

-- Insertar Reservas
INSERT INTO reservation (client_id, table_id, schedule_id, price, confirmed) 
VALUES (1, 1, 1, 100.0, TRUE);
INSERT INTO reservation (client_id, table_id, schedule_id, price, confirmed) 
VALUES (2, 2, 2, 120.0, TRUE);

-- Insertar más reservas con precios ajustados para el fin de semana
INSERT INTO reservation (client_id, table_id, schedule_id, price, confirmed) 
VALUES (1, 3, 3, 130.0, TRUE);  -- Este sería un fin de semana, el precio ha aumentado un 30%
INSERT INTO reservation (client_id, table_id, schedule_id, price, confirmed) 
VALUES (2, 1, 4, 130.0, TRUE);  -- Este sería un fin de semana, el precio ha aumentado un 30%


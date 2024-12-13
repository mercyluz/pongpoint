

INSERT INTO client (id, name, email) VALUES (default, 'Jane Smith', 'janesmith@example.com');
INSERT INTO client (id, name, email) VALUES (default, 'Robert Brown', 'robert.brown@example.com');
INSERT INTO client (id, name, email) VALUES (default,'Emma White', 'emma.white@example.com');
INSERT INTO client (id, name, email) VALUES (default, 'James Miller', 'james.miller@example.com');
INSERT INTO client (id, name, email) VALUES (default, 'Sophia Davis', 'sophia.davis@example.com');


INSERT INTO tables (name) VALUES ('Table 1');
INSERT INTO tables (name) VALUES ('Table 2');
INSERT INTO tables (name) VALUES ('Table 3');
INSERT INTO tables (name) VALUES ('Table 4');
INSERT INTO tables (name) VALUES ('Table 5');


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


INSERT INTO reservation (client_id, table_id, schedule_id, price, confirmed) 
VALUES (1, 1, 1, 100.0, TRUE);
INSERT INTO reservation (client_id, table_id, schedule_id, price, confirmed) 
VALUES (2, 2, 2, 120.0, TRUE);
INSERT INTO reservation (client_id, table_id, schedule_id, price, confirmed) 
VALUES (3, 3, 4, 120.0, TRUE);
INSERT INTO reservation (client_id, table_id, schedule_id, price, confirmed) 
VALUES (4, 1, 1, 120.0, TRUE);


INSERT INTO reservation (client_id, table_id, schedule_id, price, confirmed) 
VALUES (1, 3, 3, 130.0, TRUE); 
INSERT INTO reservation (client_id, table_id, schedule_id, price, confirmed) 
VALUES (2, 1, 4, 130.0, TRUE);  


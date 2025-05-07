-- Countries
INSERT INTO country (name, continent) VALUES ('Austria', 'Europe');          -- ID = 1
INSERT INTO country (name, continent) VALUES ('UK', 'Europe');               -- ID = 2
INSERT INTO country (name, continent) VALUES ('Canada', 'North America');    -- ID = 3
INSERT INTO country (name, continent) VALUES ('USA', 'North America');       -- ID = 4

-- Hosts
INSERT INTO host (name, surname, country_id) VALUES ('Felix', 'Huber', 1);       -- ID = 1
INSERT INTO host (name, surname, country_id) VALUES ('Klara', 'Meier', 1);       -- ID = 2
INSERT INTO host (name, surname, country_id) VALUES ('Oliver', 'Brown', 2);      -- ID = 3
INSERT INTO host (name, surname, country_id) VALUES ('Charlotte', 'Taylor', 2);  -- ID = 4

-- Guests
INSERT INTO guest (name, surname, country_id) VALUES ('Anna', 'Black', 1);           -- ID = 1
INSERT INTO guest (name, surname, country_id) VALUES ('Mike', 'Patterson', 2);       -- ID = 2
INSERT INTO guest (name, surname, country_id) VALUES ('Jack', 'Stone', 3);           -- ID = 3
INSERT INTO guest (name, surname, country_id) VALUES ('Harry', 'Swift', 4);          -- ID = 4

-- Guest-Host relationships (join table)
INSERT INTO hosts_guests (guest_id, host_id) VALUES (1, 1);
INSERT INTO hosts_guests (guest_id, host_id) VALUES (1, 2);
INSERT INTO hosts_guests (guest_id, host_id) VALUES (2, 1);
INSERT INTO hosts_guests (guest_id, host_id) VALUES (2, 3);
INSERT INTO hosts_guests (guest_id, host_id) VALUES (3, 4);
INSERT INTO hosts_guests (guest_id, host_id) VALUES (4, 2);
INSERT INTO hosts_guests (guest_id, host_id) VALUES (4, 3);

-- Accommodations
INSERT INTO accommodation (name, category, host_id, num_rooms, has_parking) VALUES ('Sunny Apartments', 'APARTMENT', 1, 10, FALSE);
INSERT INTO accommodation (name, category, host_id, num_rooms, has_parking) VALUES ('Mountain Retreat', 'HOTEL', 2, 5, TRUE);
INSERT INTO accommodation (name, category, host_id, num_rooms, has_parking) VALUES ('Seaside Villa', 'HOUSE', 3, 3, FALSE);
INSERT INTO accommodation (name, category, host_id, num_rooms, has_parking) VALUES ('City Center Loft', 'FLAT', 1, 2, TRUE);
INSERT INTO accommodation (name, category, host_id, num_rooms, has_parking) VALUES ('Lake House', 'HOUSE', 1, 4, FALSE);
INSERT INTO accommodation (name, category, host_id, num_rooms, has_parking) VALUES ('Budget Motel', 'MOTEL', 2, 20, TRUE);
INSERT INTO accommodation (name, category, host_id, num_rooms, has_parking) VALUES ('Luxury Resort', 'HOTEL', 3, 50, FALSE);

-- Users (raw passwords for development)
INSERT INTO users (username, password, name, surname, role) VALUES ('ap', '{noop}ap', 'Ana', 'Papalazova', 'ROLE_USER');
INSERT INTO users (username, password, name, surname, role) VALUES ('dp', '{noop}dp', 'Darko', 'Popov', 'ROLE_HOST');
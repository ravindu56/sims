-- Run this script once to set up the database
CREATE DATABASE IF NOT EXISTS sims_db
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

USE sims_db;

CREATE TABLE IF NOT EXISTS students (
    id          INT AUTO_INCREMENT PRIMARY KEY,
    reg_no      VARCHAR(20)  UNIQUE NOT NULL,
    name        VARCHAR(100) NOT NULL,
    field       VARCHAR(60)  NOT NULL,
    dob         DATE         NOT NULL,
    contact     VARCHAR(15),
    email       VARCHAR(100)
);

-- Optional: seed test data
INSERT INTO students (reg_no, name, field, dob, contact, email) VALUES
  ('2022/E/001', 'Amal Perera',   'Computer Engineering', '2000-03-15', '0771234567', 'amal@example.com'),
  ('2022/E/002', 'Nimal Silva',   'Electrical Engineering','1999-07-22', '0759876543', 'nimal@example.com'),
  ('2022/E/003', 'Kamal Fernando','Civil Engineering',     '2001-01-10', '0712223334', 'kamal@example.com');

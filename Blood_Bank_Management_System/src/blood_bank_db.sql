-- Blood Bank Management Database Schema

CREATE DATABASE BloodBankDB;

USE BloodBankDB;

CREATE TABLE Donors (
    donor_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    age INT,
    gender VARCHAR(10),
    blood_type VARCHAR(3),
    contact_number VARCHAR(15),
    address TEXT
);

CREATE TABLE BloodInventory (
    blood_id INT PRIMARY KEY AUTO_INCREMENT,
    blood_type VARCHAR(3),
    quantity INT
);

CREATE TABLE DonationRecords (
    donation_id INT PRIMARY KEY AUTO_INCREMENT,
    donor_id INT,
    blood_id INT,
    date_of_donation DATE,
    FOREIGN KEY (donor_id) REFERENCES Donors(donor_id),
    FOREIGN KEY (blood_id) REFERENCES BloodInventory(blood_id)
);

-- Insert initial data for testing

INSERT INTO Donors (name, age, gender, blood_type, contact_number, address)
VALUES ('John Doe', 30, 'Male', 'O+', '1234567890', '123 Main St');

INSERT INTO BloodInventory (blood_type, quantity)
VALUES ('O+', 5);

INSERT INTO DonationRecords (donor_id, blood_id, date_of_donation)
VALUES (1, 1, '2024-09-01');

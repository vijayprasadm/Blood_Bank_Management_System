# Blood Bank Management System

This project is a simple Blood Bank Management System built using Java and MySQL. The system manages donors, blood type inventory, and donation records.

## Features:
- View donor information
- Add new donors
- View available blood types and inventory
- Record blood donations

## Setup Instructions:

### 1. Clone the Repository:
git clone https://github.com/your_username/Blood_Bank_Management_System.git cd Blood_Bank_Management_System

### 2. Create the Database:
- Import the `blood_bank_db.sql` file into your MySQL database:
mysql -u root -p < blood_bank_db.sql

### 3. Download the MySQL Connector JAR:
- Download the MySQL Connector JAR file from [here](https://dev.mysql.com/downloads/connector/j/), and place it in the `lib` folder.

### 4. Compile and Run the Java Program:
- Use the following command to compile and run the Java program:
javac -cp .
/mysql-connector-java.jar src/BloodBankSystem.java java -cp .
/mysql-connector-java.jar src/BloodBankSystem
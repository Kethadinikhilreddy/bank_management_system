# 🏦 Bank Management System – Java, JDBC, MySQL

A complete **Bank Management System** built using **Core Java**, **JDBC**, and **MySQL**, developed using a clean **Layered Architecture** (DAO → DTO → Service).  
This project handles real banking operations like customer account creation, deposits, withdrawals, PIN updates, transaction history, admin features, and more.

---

## 🚀 Features

### 👨‍💼 Admin Module
- Admin Login
- View all customers
- Search customer by account or details
- View all transactions

### 👤 Customer Module
- Create new customer account
- Auto-generate Account Number & PIN
- Deposit amount
- Withdraw amount
- Check balance
- View transaction history
- Update profile info
- Change PIN

---

## 🧱 Project Architecture (Layered Design)

```
bank_management_system/
│
├── src/org/
│   ├── dao/              → Executes SQL queries and interacts with the database
│   ├── dto/              → Contains DTO classes for data transfer
│   ├── service/          → Business logic; interacts with DAO and validates inputs
│   ├── exception/        → Custom exceptions (e.g., InvalidUserException)
│   ├── dbconnection/     → DbConnection class (JDBC connection utility)
│   └── main/             → Entry point; handles user interaction
│
├── database/
│   └── bank.sql          → Schema file (tables + sample data)
│
└── README.md             → Project documentation
```

---

## 🗃 Database Overview

The project uses **MySQL** with 3 main tables:

- `admin_details`
- `bank_customer_details`
- `transaction_details`

Includes **unique constraints** for Email, Aadhar, PAN, and Phone number.

---

## ⚙️ How to Run the Project

### 1️⃣ Import the Project
- Open **Eclipse**
- Go to  
  **File → Import → Existing Java Project**
- Select your `bank_management_system` folder

### 2️⃣ Add MySQL Connector JAR
- Right-click on project  
  **Build Path → Configure Build Path**
- Click **Add External JAR**
- Select: `mysql-connector-j.jar`

### 3️⃣ Create the Database
- Open MySQL Workbench
- Run SQL script located in:
```
database/bank.sql
```

### 4️⃣ Update Database Credentials (if needed)
In:
```
src/org/dbconnection/DbConnection.java
```

Modify:
```java
private static final String URL = "jdbc:mysql://localhost:3307/bank_management_system";
private static final String USER = "root";
private static final String PASSWORD = "root";
```

### 5️⃣ Run the Application
- Open `main` package
- Run the Main class
- Follow the menu prompts to perform admin or customer operations

---

## 📌 Tech Stack

- **Java (Core Java)**
- **JDBC**
- **MySQL**
- **Eclipse IDE**
- **Layered Architecture (DAO, DTO, Service)**

---

## 📄 Summary

This project is a real-time demonstration of integrating Java with MySQL using JDBC, implementing clean code, modular architecture, and logical banking features.  
It is ideal for:
- Academic submissions  
- Portfolio  
- JDBC practice  
- Learning multi-layered architecture in Java  


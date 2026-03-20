# 🏥 Hospital Management System

A console-based application developed using Java and MySQL to manage hospital operations such as patient records, doctor details, and appointment booking. The system allows efficient handling of hospital data using JDBC.

---

## 🚀 Features

* Add new patient
* View all patients
* View doctor details
* Book appointments
* Check doctor availability
* Console-based menu system

---

## 🛠️ Tech Stack

* **Language:** Java
* **Database:** MySQL
* **Concepts Used:** OOP, JDBC, PreparedStatement, Exception Handling, Basic Multithreading

---

## 💡 Project Overview

This project demonstrates real-world hospital management operations using Java and MySQL. It uses JDBC for database connectivity and `PreparedStatement` for secure SQL queries. The system ensures proper handling of patients, doctors, and appointment scheduling.

---

## ▶️ Run Locally

1. Clone the repository
   git clone https://github.com/RaghuChauhan1999/hospital-management-system-java

2. Open in IDE (IntelliJ / VS Code)

3. Create database:

```id="z6rqv8"
CREATE DATABASE hospital;
```

4. Create tables:

```id="gqk3dr"
CREATE TABLE patients (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    age INT,
    gender VARCHAR(10)
);

CREATE TABLE doctors (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    specialization VARCHAR(100)
);

CREATE TABLE appointments (
    id INT AUTO_INCREMENT PRIMARY KEY,
    patient_id INT,
    doctor_id INT,
    appointment_date DATE
);
```

5. Update database credentials in code:

```id="5m8r5g"
private static final String url = "jdbc:mysql://localhost:3306/hospital";
private static final String username = "root";
private static final String password = "your_password";
```

6. Run the application

---

## 📌 Sample Menu

```id="rfkqyb"
HOSPITAL MANAGEMENT SYSTEM
1. Add Patient
2. View Patients
3. View Doctors
4. Book Appointment
0. Exit
```

---

## 🔥 Key Learnings

* Used JDBC for database connectivity
* Implemented secure queries using PreparedStatement
* Managed relationships between patients, doctors, and appointments
* Applied OOP concepts in real-world application
* Built menu-driven console system

---

## 👨‍💻 Author

Raghu Chauhan

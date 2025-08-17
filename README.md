# 📚 Online-Billing-System-Pahana-Edu

[![Java](https://img.shields.io/badge/Java-8+-blue)](https://www.oracle.com/java/technologies/javase-jdk8-downloads.html)
[![Maven](https://img.shields.io/badge/Maven-3.8+-green)](https://maven.apache.org/)
[![License](https://img.shields.io/badge/License-MIT-yellow)](./LICENSE)

Pahana Edu is a leading bookshop based in Colombo City, Sri Lanka. This Java EE-based web application enables the digital transformation of Pahana Edu’s customer and billing management by automating customer registration, billing calculation, book management, and secure user access.

---

## 📌 Table of Contents
- [Features](#-features)
- [Tech Stack](#-tech-stack)
- [Getting Started](#-getting-started-installation)
- [Role-Based Access](#-role-based-access)
- [Visual Previews](#-visual-previews)
- [Author](#-author)
- [License](#-license)

---

## ✨ Features

1. **User Authentication**
   - Secure login system using username and password.
   - Session-based authentication.
   - Role-based access: Admin and Staff.

2. **Admin Dashboard Functionalities**

    Admins have full access to all system features:

    **Manage User Information**
      - Add new users with username, email, password, and role.
      - View all users.
      - Edit or delete existing user information.

   **Manage Customer Information**
      - Add new customers with account number, name, address, telephone, and email.
      - View detailed account information.
      - Search customers by account number, name, address, or email.
      - Edit or delete existing customer information.

   **Manage Book Information**
      - Add new books with title, category, author, language, and price.
      - View all books.
      - Search books by title, category, author, or language.
      - Edit or delete existing book information.

   **Manage Bill Information**
      - Calculate bills, generate and print invoices.
      - View all sales records.
      - Filter by customer account number, invoice number, or date.
      - Delete bills.

   **Help Section**
      - Guidelines and tips for new users on how to use the system.

   **Logout**
       - Logout and end session securely.

3. **Staff Dashboard Functionalities**

   Staff access is divided into Manager and Cashier, with different privileges:

    **Staff (Manager)**

    **Manage Customer Information**
      - Add new customers with account number, name, address, telephone, and email.
      - View detailed account information.
      - Search customers by account number, name, address, or email.
      - Edit existing customer information.

   **Manage Book Information**
      - Add new books with title, category, author, language, and price.
      - View all books.
      - Search books by title, category, author, or language.
      - Edit existing book information.

   **Manage Bill Information**
      - Calculate bills, generate and print invoices.
      - View all sales records.
      - Filter by customer account number, invoice number, or date.

   **Help Section**
      - Guidelines and tips for new users on how to use the system.

   **Logout**
      - Logout and end session securely.


   > **⚠️ Note: Managers do not have access to Manage User Information.**

   **Staff (Cashier)**

    **Manage Customer Information**
      - Add new customers with account number, name, address, telephone, and email.
      - View detailed account information.
      - Search customers by account number, name, address, or email.

   **Manage Book Information**
      - Add new books with title, category, author, language, and price.
      - View all books.
      - Search books by title, category, author, or language.

   **Manage Bill Information**
      - Calculate bills, generate and print invoices.
      - View all sales records.
      - Filter by customer account number, invoice number, or date.

   **Help Section**
      - Guidelines and tips for new users on how to use the system.

   **Logout**
      - Logout and end session securely.


   > **⚠️ Note: Cashiers do not have access to Manage User Information and cannot modify records.**
   

   

---

## 💻 Tech Stack

| Technology     | Details                |
|----------------|-------------------------|
| Java EE        | Enterprise Edition 8    |
| IDE            | NetBeans                |
| Server         | Apache Tomcat 9         |
| Build Tool     | Maven                   |
| Frontend       | JSP, HTML, CSS          |
| Backend        | Java Servlets           |
| Database       | MySQL (phpMyAdmin)      |

---

## 🚀 Getting Started (Installation)

Follow these steps to run the **Pahana Edu Online Billing System** locally on your machine:

### **Prerequisites**
- **Java JDK 8 or higher**  
- **Apache Tomcat 9** (or compatible Java EE server)  
- **NetBeans IDE** (or any Java EE compatible IDE)  
- **MySQL Server** (with phpMyAdmin optional for database management)  
- **Maven** (for build and dependency management)

---

### **1. Clone the Repository**
```
git clone https://github.com/Thahirah-Razmi/Online-Billing-System-Pahana-Edu.git

cd Online-Billing-System-Pahana-Edu
```

**2. Configure the Database**

- Open phpMyAdmin or MySQL console.
- Create a new database:
  
```
CREATE DATABASE online_billing_system_pahana_edu;
```

- Import the provided SQL script online_billing_system_pahana_edu.sql (located in /database folder) to create tables and seed initial data.
- Update the database connection in DBConnection.java:

```
private static final String DB_URL = "jdbc:mysql://localhost:3306/online_billing_system_pahana_edu";

private static final String DB_USER = "root";

private static final String DB_PASSWORD = "your_password";
```

**3. Build and Deploy**

Open the project in NetBeans IDE.

Right-click the project → Clean and Build.

Right-click → Deploy to Apache Tomcat 9.

**4. Access the Application**

Open your browser and go to:

```
http://localhost:8081/Online-Billing-System-Pahana-Edu/
```

Log in using the default credentials (provided in SQL seed):

Admin:
- Username:
  ```
  admin
  ```
  
- Password:
  ```
  Admin@123
  ```

Cashier: 
- Username:
  ```
  staff1
  ```

- Password:
  ```
  Staff1@123
  ```

Manager:
- Username:
  ```
  Emily
  ```
  
- Password:
  ```
  Emily@123
  ```

| Role    | Username | Password    |
| ------- | -------- | ----------- |
| Admin   | admin    | Admin\@123  |
| Manager | Emily    | Emily\@123  |
| Cashier | staff1   | Staff1\@123 |

**5. Troubleshooting**

Ensure MySQL server is running and credentials in DBConnection.java are correct.

Make sure Tomcat is running on the correct port (8081).

Check logs in NetBeans or Tomcat logs folder for errors.

---

## 🔐 Role-Based Access

Admin: Full access to users, customers, books, bills, help section.

Staff (Manager): Access to customers, books, bills (view & edit), and help section.

Staff (Cashier): Access to customers, books, bills (view only), and help section.

| Feature          | Admin              | Manager       | Cashier      |
| ---------------- | ------------------ | ------------ | ------------ |
| Manage Users     | ✅ Add/Edit/Delete | ❌          | ❌           |
| Manage Customers | ✅                 | ✅ Add/Edit | ✅ View Only |
| Manage Items     | ✅                 | ✅ Add/Edit | ✅ View Only |
| Manage Bills     | ✅                 | ✅          | ✅           |
| Help Section     | ✅                 | ✅          | ✅           |

## 📸 Visual Previews  
### Pahana Edu's Online Billing System UI

1. **Login Page**

<img width="975" height="516" alt="image" src="https://github.com/user-attachments/assets/736f6133-c8a0-4270-a127-4448d28f834c" />


2. **Admin Dashboard**

<img width="975" height="596" alt="image" src="https://github.com/user-attachments/assets/185026dd-066e-4092-895c-bc25ff64cee9" />


3. **Staff Dashboard**

<img width="975" height="580" alt="image" src="https://github.com/user-attachments/assets/e65fdda7-e31d-4f4f-b987-a9c2ca946919" />

4. **Manage User Information**

<img width="975" height="504" alt="image" src="https://github.com/user-attachments/assets/cc5d01d0-60ca-4017-9c19-52f7e4dfe1f5" />

5. **Add User**

<img width="975" height="735" alt="image" src="https://github.com/user-attachments/assets/9486cb9c-b4ce-449a-9ffd-0094a9a2bcba" />

6. **View All Users**

<img width="975" height="703" alt="image" src="https://github.com/user-attachments/assets/bb6e4a23-a05d-4523-a021-5dde9d73bbcf" />

7. **Edit User**

<img width="975" height="652" alt="image" src="https://github.com/user-attachments/assets/895a80d6-c41e-4207-95c0-3e028913d439" />

8. **Manage Customer Information**

<img width="975" height="504" alt="image" src="https://github.com/user-attachments/assets/3d1f8cff-1ceb-484e-8a52-9d4c858bf72b" />

9. **Add Customer**

<img width="975" height="597" alt="image" src="https://github.com/user-attachments/assets/2d603c5c-d8a9-4927-8aa6-af243cdeea2a" />

10. **View All Customers**

<img width="976" height="1113" alt="image" src="https://github.com/user-attachments/assets/c5782ac2-aac4-4078-b490-91f991a3d020" />

11. **Edit Customer**

<img width="975" height="634" alt="image" src="https://github.com/user-attachments/assets/069fce96-daee-429e-9bea-811d67b14be2" />

12. **Manage Book Information**

<img width="975" height="502" alt="image" src="https://github.com/user-attachments/assets/c88a2899-29b2-4f32-9a1f-1de80305fd6d" />

13. **Add Book**

<img width="975" height="665" alt="image" src="https://github.com/user-attachments/assets/caf12042-3a8f-411b-b05c-c8c11ebee887" />

14. **View All Books**

<img width="975" height="853" alt="image" src="https://github.com/user-attachments/assets/3350ebf4-7a84-48eb-af7c-cc29b19538c6" />

14. **Edit Book**

<img width="975" height="705" alt="image" src="https://github.com/user-attachments/assets/ce4138b8-c60d-42cb-b24b-59176e87c866" />

 15. **Manage Bill Information**

<img width="975" height="503" alt="image" src="https://github.com/user-attachments/assets/ea00d762-499d-464c-a44e-8f32e5fdb218" />

16. **Calculate Bill**

<img width="975" height="704" alt="image" src="https://github.com/user-attachments/assets/50ae1602-e444-4711-9620-c98cc7feedfa" />

17. **Bill Preview**

<img width="975" height="510" alt="image" src="https://github.com/user-attachments/assets/d4a3e425-1087-422a-8bd1-b850b7114337" />

<img width="975" height="462" alt="image" src="https://github.com/user-attachments/assets/ae763b6b-f5ec-4dec-b6d3-c2858493bec3" />

18. **View All Sales**

<img width="975" height="859" alt="image" src="https://github.com/user-attachments/assets/cc387692-f197-4749-8f4b-b25549157325" />

19. **Help Section**

<img width="980" height="1101" alt="image" src="https://github.com/user-attachments/assets/ffaa8fec-9591-4be1-a8c0-33a09d03def0" />

---

## 👩‍💻 Author  

[Thahirah Razmi](https://github.com/Thahirah-Razmi)

## 📄 License

This project is licensed under the MIT License.

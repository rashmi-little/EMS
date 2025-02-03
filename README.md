# Employee Management System (EMS)

Welcome to the **Employee Management System (EMS)**! 🎉

This is a **Spring Boot** powered application that allows you to manage employees, departments, and the relationships between them in a simple, yet effective way. From adding and updating employees to transferring them between departments, this project demonstrates the core functionality needed in an **Employee Management System** using **JPA**, **MySQL**, and **Spring Data**.

### 🚀 **Features**:

- **Employee Management**: Add, update, and delete employee records.
- **Department Management**: Create, update, and manage department records.
- **Employee-Department Relationship**: Assign employees to multiple departments and transfer them seamlessly with **Many-to-Many** relationship.
- **Advanced Queries**: Fetch employees by salary, joining date, department count, and more.
- **Pagination & Sorting**: Retrieve employees in batches, with sorting options.
- **Bulk Salary Updates**: Increase all employees' salaries in bulk.
- **Transaction Management**: Ensure atomic operations when transferring employees between departments.
- **Validation**: Ensure data integrity with annotations like `@NotNull`, `@Email`, and `@Positive`.

---

### 🛠️ **Technologies Used**:

- **Spring Boot**: The heart of the application, managing the overall structure and API endpoints.
- **JPA (Java Persistence API)**: For ORM-based interaction with the database.
- **MySQL**: A relational database used for persistent storage.
- **Swagger UI**: Interactive API documentation to explore endpoints and test them directly in your browser.
- **Lombok**: To reduce boilerplate code for getters, setters, constructors, etc.
- **Spring Data JPA**: Efficient data access and management using repository patterns.
- **Maven**: For building and managing dependencies.

---

### 🎯 **Project Structure**:

- **Employee**: 
  - Name, email, salary, and date of joining.
  - Linked to the `Department` entity via a **Many-to-Many** relationship.
  
- **Department**: 
  - Name, location, and associated employees.
  
- **Relationships**: 
  - **Add Employee to Department**
  - **Transfer Employee between Departments**
  - **Retrieve Employees from Department**
  - **Retrieve all Departments an Employee is associated with**
  
- **Key Operations**:
  - **CRUD Operations** for Employees and Departments.
  - **Advanced Queries** using JPQL (e.g., employees with salary greater than a certain amount, employees joined in the last 6 months).
  - **Pagination & Sorting** of employees based on salary.

---

### 📦 **Getting Started**:

To get the project up and running on your local machine, follow these steps:

1. **Clone the repository**:
    ```bash
    git clone https://github.com/yourusername/employee-management-system.git
    cd employee-management-system
    ```

2. **Setup MySQL Database**:
   - Make sure you have MySQL installed and running.
   - Create a database (e.g., `ems_db`):
     ```sql
     CREATE DATABASE ems_db;
     ```
   - Update the `application.properties` file with your MySQL connection details:
     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/ems_db
     spring.datasource.username=root
     spring.datasource.password=yourpassword
     spring.jpa.hibernate.ddl-auto=update
     spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
     spring.jpa.show-sql=true
     ```

3. **Build the project** using Maven:
    ```bash
    mvn clean install
    ```

4. **Run the application**:
    ```bash
    mvn spring-boot:run
    ```

5. **Access Swagger UI**:  
   Once the application is running, navigate to:
   ```bash
   http://localhost:8080/swagger-ui/index.html
# Endpoints Overview

### **Department Operations**:
- **Create Department**: `POST /api/v1/departments`
- **Get All Departments**: `GET /api/v1/departments`
- **Get Department by ID**: `GET /api/v1/departments/{id}`
- **Update Department**: `PUT /api/v1/departments/{id}`
- **Delete Department**: `DELETE /api/v1/departments/{id}`

### **Employee Operations**:
- **Create Employee**: `POST /api/v1/employees`
- **Get All Employees**: `GET /api/v1/employees`
- **Get Employee by ID**: `GET /api/v1/employees/{id}`
- **Update Employee Salary**: `PATCH /api/v1/employees/{id}/salary/{amount}`
- **Delete Employee**: `DELETE /api/v1/employees/{id}`
- **Get Employees by Salary Greater Than**: `GET /api/v1/employees/salary-greater-than/{amount}`
- **Get Employees Joined in Last 6 Months**: `GET /api/v1/employees/joined-in-last-six-months`

### **Employee-Department Relationship Operations**:
- **Add Employee to Department**: `POST /api/v1/relationship/employees/{empId}/departments/{deptId}`
- **Get Employees by Department ID**: `GET /api/v1/relationship/employees/{deptId}`
- **Get Departments by Employee ID**: `GET /api/v1/relationship/departments/{empId}`
- **Remove Employee from Department**: `DELETE /api/v1/relationship/employees/{empId}/departments/{deptId}`
- **Transfer Employee Between Departments**: `PUT /api/v1/relationship/transfer/employees/{empId}/from/{fromDeptId}/to/{toDeptId}`

---

# How it Works:

This project provides a solid foundation for managing employees and departments, with the flexibility of adding advanced features like salary updates, department transfers, and querying employee data with filters.

- **Entities** are defined using JPA, and relationships are set up using **Many-to-Many** (Employee ↔ Department).
- **Endpoints** are exposed for CRUD operations on employees and departments, as well as relationship operations for managing employee assignments to departments.
- **Validation** ensures that invalid data can't be added to the system (e.g., non-positive salary, invalid email).
- **Pagination** makes it easy to retrieve a large number of employees in manageable chunks.
- **Transaction Management** ensures operations like employee transfers are atomic, maintaining consistency in the data.

---

# Custom Queries and Bulk Operations:

- **Native Query for Top 3 Highest Paid Employees**: A custom native SQL query fetches the highest-paid employees in the company.
- **Bulk Salary Update**: An operation allows updating all employees' salaries by a specified percentage, demonstrating efficient batch updates using custom repository methods.

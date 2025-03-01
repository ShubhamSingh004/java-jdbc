import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

// DAO - Data Access Object Pattern
// This pattern is used to separate database interaction logic from business logic.
// It makes the code cleaner, more maintainable, and reusable.
public class DAO {

    public static void main(String[] args) throws Exception {
    	
    	String url = "jdbc:mysql://localhost:3306/ss";
    	String userName = "root";
    	String pass = "mysql";

        // Creating an instance of EmployeeDAO
        EmployeeDAO empDao = new EmployeeDAO(url, userName, pass);
        
        // Fetching Employee details from the database for si = 4
        Employee emp = empDao.getEmployee(4);

        // Checking if employee exists before printing to avoid NullPointerException
        if (emp != null) {
            System.out.println(emp.si + " " + emp.name);
        } else {
            System.out.println("Employee not found.");
        }

        // Adding a new employee and printing the success message
        int rowsInserted = empDao.addEmployee(77, "Rahul");
        if (rowsInserted > 0) {
            System.out.println("Employee added successfully.");
        } else {
            System.out.println("Failed to add employee.");
        }

        // Closing database connection
        empDao.closeConnection();
    }
}

// DAO class responsible for database operations
// Why use DAO here?
// 1. **Encapsulation**: Keeps database logic separate from the main application logic.
// 2. **Reusability**: This class can be used anywhere in the application without modifying the main logic.
// 3. **Maintainability**: If the database structure changes, we only need to update this class, not the entire codebase.
// 4. **Security**: Helps prevent direct access to the database from different parts of the program.
class EmployeeDAO {
    private Connection con;
    
    // Constructor to initialize database connection
    public EmployeeDAO(String url, String userName, String pass) throws Exception {
        // Establishing a connection to the MySQL database
        con = DriverManager.getConnection(url, userName, pass);
    }

    // Method to fetch an employee's details based on si (serial number)
    public Employee getEmployee(int si) throws SQLException {
        
        // Creating an Employee object to store the fetched data
        Employee emp = new Employee();
        emp.si = si;

        // Using PreparedStatement instead of Statement to prevent SQL injection
        String query = "SELECT name FROM emp WHERE si = ?";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, si);
            
            ResultSet rs = ps.executeQuery();

            // Checking if a record exists and setting the employee name
            if (rs.next()) {
                emp.name = rs.getString("name");
            } else {
                return null; // Return null if no employee is found
            }
        }
        return emp; // Returning the Employee object with retrieved data
    }
    
    // Method to add a new employee to the database
    public int addEmployee(int si, String name) throws Exception {
        // Using PreparedStatement to prevent SQL injection
        String query = "INSERT INTO emp VALUES(?, ?)";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(2, si); // Fix: si should be set first
            ps.setString(1, name);

            // Execute update and return the number of affected rows
            return ps.executeUpdate();
        }
    }
    
    // Method to close the database connection
    public void closeConnection() {
        try {
            if (con != null) {
                con.close();
                System.out.println("Database connection closed.");
            }
        } catch (SQLException error) {
            System.out.println("Error while closing connection: " + error.getMessage());
        }
    }
}

// Employee class representing an Employee entity
// This class acts as a Data Transfer Object (DTO) holding employee information.
class Employee {
    int si;      // Employee Serial Number (Primary Key)
    String name; // Employee Name
}

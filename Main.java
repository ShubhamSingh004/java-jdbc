// Import necessary classes for SQL operations.
// These classes help us establish a connection, execute SQL queries, and process results.
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) throws Exception {
        // Define the database URL.
        // Here, "ss" is the name of the database on the local MySQL server.
        String url = "jdbc:mysql://localhost:3306/ss";
        
        // The following line (commented out) would load the MySQL JDBC driver manually.
        // In modern JDBC versions, this is not necessary as drivers are auto-registered.
        // Class.forName("com.mysql.cj.jdbc.Driver");
        
        // Establish a connection to the MySQL database.
        // "root" is the username and "mysql" is the password.
        Connection con = DriverManager.getConnection(url, "root", "mysql");
        
        // Create a Statement object to execute SQL queries.
        Statement st = con.createStatement();
        
        // Execute a SQL query to retrieve all rows from the "emp" table.
        // The result is stored in a ResultSet object.
        ResultSet rs = st.executeQuery("select * from emp");
        
        // Loop through the ResultSet.
        // rs.next() moves the cursor to the next row in the result set.
        // For each row, we print the "name" (as a string) and "si" (as an integer) columns.
        while (rs.next()) {
            System.out.println(rs.getString("name") + ":" + rs.getInt("si"));
        }
        
//        rs.getString(1): Retrieves the first column of the result set.
//        rs.getInt(2): Retrieves the second column of the result set.
//        Column indices start from 1 (not 0).        
        
        // Update the "emp" table by setting the "si" column to 4 for rows where "si" is 3.
        // The executeUpdate() method returns the number of rows affected by the update.
        int count = st.executeUpdate("update emp set si = 4 where si = 3");
        System.out.println("Row(s) affected: " + count);
        
        // Insert a new row into the "emp" table with values 'S3' for "name" and 4 for "si".
        count = st.executeUpdate("insert into emp values('S3', 4)");
        System.out.println("Row(s) affected: " + count);
        
        // Using PreparedStatement for parameterized queries.
        // Prepared statements help avoid SQL injection and can improve performance when executing multiple times.
        PreparedStatement ps = con.prepareStatement("insert into emp values(?, ?)");
        // Set the first parameter (for the first '?') to "s4".
        ps.setString(1, "s4");
        // Set the second parameter (for the second '?') to 7.
        ps.setInt(2, 7);
        
        // Execute the prepared statement to insert the new row.
        ps.executeUpdate();
        
        // Close the ResultSet, Statement, and Connection objects to free up database resources.
        rs.close();
        st.close();
        con.close();
    }
}

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Demo {
	public static void main(String[] args) throws Exception{
		String url = "jdbc:mysql://localhost:3306/ss";
		
//		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection con = DriverManager.getConnection(url, "root", "mysql");
		Statement st = con.createStatement();
		
		ResultSet rs = st.executeQuery("select * from emp");
		
		while(rs.next()) {
			System.out.println(rs.getString("name") + ":" + rs.getInt("si"));
		}
		
		
		// update data on the sql table
		
		int count = st.executeUpdate("update emp set si = 4 where si = 3");
		System.out.println("Row(s) affected: " + count);
		
		count = st.executeUpdate("insert into emp values('S3', 4)");
		System.out.println("Row(s) affected: " + count);
		
		
		
		// prepare statements
		PreparedStatement ps = con.prepareStatement("insert into emp values(?, ?)");
		ps.setString(1, "s4");
		ps.setInt(2, 7);
		
		ps.executeUpdate();
		
		
		
		
		rs.close();
		st.close();
		con.close();
				
	}
}

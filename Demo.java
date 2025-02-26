import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Demo {
	public static void main(String[] args) throws Exception{
		String url = "jdbc:mysql://localhost:3306/ss";
		
//		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection con = DriverManager.getConnection(url, "root", "mysql");
		Statement st = con.createStatement();
		
		
		ResultSet rs = st.executeQuery("select * from emp");
		rs.next();
		String name = rs.getString("name");
		
		System.out.println(name);
		
				
	}
}

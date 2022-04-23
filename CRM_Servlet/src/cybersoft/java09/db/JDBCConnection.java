package cybersoft.java09.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnection  {
	
	public static Connection getConnection() {
		final String DRIVER = "org.postgresql.Driver";
		final String URL = "jdbc:postgresql://ec2-44-199-143-43.compute-1.amazonaws.com:5432/ddlmuprudesh2p";
		final String USERNAME = "nrbcrstorswjkn";
		final String PASSWORD = "dc05ce96f9f74950d8dc53f6f89c575ac2317e87a3997f4e2d1226adc971ad82";
		
		try {
			Class.forName(DRIVER);
			return DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("KHÔNG TÌM THẤY DRIVER");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SAI THÔNG TIN KẾT NỐI DB");
		}
		return null;
	}
}


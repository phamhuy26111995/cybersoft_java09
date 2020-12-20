package cybersoft.java09.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cybersoft.java09.db.JDBCConnection;
import cybersoft.java09.entity.*;

public class StatusRepository {

	public Status findStatusById(int id) {
		Status status = new Status();
		try {
			String query = "SELECT * FROM status WHERE id=?";
			Connection connection = JDBCConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				
				status.setName(result.getString("name"));
				
			
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return status;
	}
	
	public List<Status> getAllStatus() {
		List<Status> listStatus = new ArrayList<Status>();
		try {
			String query = "SELECT * FROM status";
			Connection connection = JDBCConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			
			
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				Status status = new Status(result.getInt("id"), result.getString("name"));
				
				listStatus.add(status);
			
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return listStatus;
	}
}

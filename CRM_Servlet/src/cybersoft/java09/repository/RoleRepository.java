package cybersoft.java09.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cybersoft.java09.db.JDBCConnection;
import cybersoft.java09.entity.Role;

public class RoleRepository {

	public List<Role> getAllRole(){ // Truy vấn các cột trong bảng roles và add vào một ArrayList
		List<Role> roles = new ArrayList<Role>();
		try {
			String query = "SELECT * FROM roles";
			Connection connection = JDBCConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				Role role = new Role();
				role.setId(result.getInt("id"));
				role.setName(result.getString("name"));
				role.setDescription(result.getString("description"));
				
				roles.add(role);
			}
			
			
		} catch (Exception e) {
			e.getMessage();
		}
		
		return roles;
	}
	
	
	public void addNewRole(Role role) {
		try {
			String query = "INSERT INTO roles (name,description) VALUE (?,?)";
			Connection connection = JDBCConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setString(1, role.getName());
			statement.setString(2, role.getDescription());
			
			int result = statement.executeUpdate();

			if(result < 1) {
				System.out.println("Thêm Thất bại");
			}
			else {
				System.out.println("Thêm Thành Công");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public Role findRoleById(int id) {
		Role role = new Role();
		try {
			String query = "SELECT * FROM roles WHERE id=?";
			Connection connection = JDBCConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				role.setId(Integer.parseInt(result.getString("id")));
				role.setName(result.getString("name"));
				role.setDescription(result.getString("description"));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return role;
	}


	public void editRole(Role role_edit, int id) {
		try {
			String query = "UPDATE roles SET name = ? , description = ? WHERE id=?";
			Connection connection = JDBCConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setString(1, role_edit.getName());
			statement.setString(2, role_edit.getDescription());
			statement.setInt(3, id);
			int result = statement.executeUpdate();

			if(result < 1) {
				System.out.println("Edit Thất bại");
			}
			else {
				System.out.println("Edit Thành Công");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage()+"Hello");
		}
		
	}
	
	public void deleteRole(int id) {
		try {
			String query = "DELETE FROM roles where id = ?";
			Connection conn = JDBCConnection.getConnection();
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, id);
			
			int result = statement.executeUpdate();
			
			
			if(result < 1) {
				System.out.println("Xóa Thất bại");
			}
			else {
				System.out.println("Xóa Thành Công");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
}

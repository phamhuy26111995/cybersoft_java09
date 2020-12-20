package cybersoft.java09.repository;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import cybersoft.java09.dto.*;


import cybersoft.java09.db.JDBCConnection;
import cybersoft.java09.entity.Task;
import cybersoft.java09.entity.User;

public class UserRepository {
	public List<User> getAlluser(){ // Truy vấn các cột trong bảng roles và add vào một ArrayList
		List<User> users = new ArrayList<User>();
		try {
			String query = "SELECT * FROM users";
			Connection connection = JDBCConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);

			ResultSet result = statement.executeQuery();
			while(result.next()) {
				User user = new User(result.getInt("id"),result.getString("email"),
						result.getString("password"),
						result.getString("fullname"),
						result.getString("avatar"),
						result.getInt("role_id"));

				users.add(user);

			}


		} catch (Exception e) {
			System.out.println(e.getMessage());
		}


		return users;
	}

	public List<UserDto> findAllUserRole() { //Truy vấn userDTO để hiển thị thông tin trong đó có role name ứng với roleID
		// B1: Kết nối db
		List<UserDto> users = new ArrayList<UserDto>();
		try {
			Connection conn = JDBCConnection.getConnection();
			String query = "SELECT u.id, u.email, u.password, u.fullname, u.avatar, r.name FROM users u JOIN roles r ON u.role_id = r.id";
			// B2: Tạo câu lệnh truy vấn
			PreparedStatement statement = conn.prepareStatement(query);
			// B3: Thực thi câu lệnh truy vấn
			ResultSet resultSet = statement.executeQuery();
			// B4: Chuyển dữ liệu qua entity (java class)

			while (resultSet.next()) {
				// Dùng hàm khởi tạo có tham số
				UserDto userDto = new UserDto(resultSet.getInt("id"), resultSet.getString("email"),
						resultSet.getString("password"), resultSet.getString("fullname"), resultSet.getString("avatar"),
						resultSet.getString("name"));
				users.add(userDto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}


	public void editUser(User user,int id) {
		try {
			String query = "UPDATE users SET email = ? , password = ? , fullname = ? , avatar = ? , role_id = ? WHERE id=?";
			Connection connection = JDBCConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setString(1, user.getEmail());
			statement.setString(2, user.getPassWord());
			statement.setString(3, user.getFullName());
			statement.setString(4, user.getAvatar());
			statement.setInt(5, user.getRole_Id());
			statement.setInt(6, id);
			
			


			

			int result = statement.executeUpdate();

			if(result < 1) {
				System.out.println("Thêm Thất bại");
			}
			else {
				System.out.println("Thêm Thành Công");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void addUser(User user) { //Thêm mới một user vào database
		try {
			String query = "INSERT INTO users (email,password,fullname,avatar,role_id) VALUES(?,?,?,?,?)";
			Connection connection = JDBCConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);

			statement.setString(1, user.getEmail());
			statement.setString(2, user.getPassWord());
			statement.setString(3, user.getFullName());
			statement.setString(4, user.getAvatar());
			statement.setInt(5, user.getRole_Id());

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
	
	public void deleteUser(int id) {
		try {
			String query = "DELETE FROM users where id = ?";
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
	
	public User findById(int id) {
		// B1: Kết nối db
		User user = new User();
		try {
			Connection conn = JDBCConnection.getConnection();

			// B2: Tạo câu lệnh truy vấn
			PreparedStatement statement = 
					conn.prepareStatement("SELECT * FROM users WHERE id = ?");
			statement.setInt(1, id);
			// B3: Thực thi câu lệnh truy vấn
			ResultSet resultSet = statement.executeQuery();
			// B4: Chuyển dữ liệu qua entity (java class)

			while (resultSet.next()) {
				// Dùng hàm khởi tạo có tham số
				user = new User(
						resultSet.getInt("id"), 
						resultSet.getString("email"), 
						resultSet.getString("password"),
						resultSet.getString("fullname"), 
						resultSet.getString("avatar"), 
						resultSet.getInt("role_id"));
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	public List<Task> findTaskOfUser(int userID, int statusID){
		List<Task> listTaskUser = new ArrayList<Task>();
		try {
			String query = "select t.name,t.start_date,t.end_date from tasks t join users u on t.user_id = u.id join status s on t.status_id = s.id where u.id = ? AND s.id= ?";
			Connection connection = JDBCConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1,userID);
			if(statusID == 1)
			{
				statement.setInt(2, 1);
			}
			else if (statusID == 2)
			{
				statement.setInt(2, 2);
			}
			else {
				statement.setInt(2, 3);
			}
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				Task task = new Task();
				task.setName(result.getString("name"));
				
				Timestamp startDate = result.getTimestamp("start_date");
				Timestamp endDate = result.getTimestamp("end_date");
				
				Date dateStart= new Date(startDate.getTime());
				Date dateEnd= new Date(endDate.getTime());
				
				
				task.setStartDate(dateStart);
				task.setEndDate(endDate);
				

				listTaskUser.add(task);

			}


		} catch (Exception e) {
			System.out.println(e.getMessage());
		}


		return listTaskUser;
	}
	public User checkLogin(String email, String password) {
		// B1: Kết nối db
		User user = new User();
		try {
			Connection conn = JDBCConnection.getConnection();

			// B2: Tạo câu lệnh truy vấn
			PreparedStatement statement = 
					conn.prepareStatement("SELECT * FROM users WHERE email = ? and password = ?");
			statement.setString(1, email);
			statement.setString(2, password);
			// B3: Thực thi câu lệnh truy vấn
			ResultSet resultSet = statement.executeQuery();
			// B4: Chuyển dữ liệu qua entity (java class)

			while (resultSet.next()) {
				// Dùng hàm khởi tạo có tham số
				user = new User(
						resultSet.getInt("id"), 
						resultSet.getString("email"), 
						resultSet.getString("password"),
						resultSet.getString("fullname"), 
						resultSet.getString("avatar"), 
						resultSet.getInt("role_id"));
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	public List<TaskDto> findTaskByUserID(int userID){
		List<TaskDto> listTaskDto = new ArrayList<TaskDto>();
		try {
			String query = "select t.id,t.name,t.start_date,t.end_date,s.name as status_name from tasks t join users u on t.user_id = u.id join status s on t.status_id = s.id where u.id = ?";
			Connection connection = JDBCConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1,userID);
			
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				TaskDto taskDto = new TaskDto();
				taskDto.setId(result.getInt("id"));
				taskDto.setName(result.getString("name"));
				
				Timestamp startDate = result.getTimestamp("start_date");
				Timestamp endDate = result.getTimestamp("end_date");
				
				Date dateStart= new Date(startDate.getTime());
				Date dateEnd= new Date(endDate.getTime());
				
				
				taskDto.setStartDate(dateStart);
				taskDto.setEndDate(endDate);
				taskDto.setStatus(result.getString("status_name"));
				
				listTaskDto.add(taskDto);

			}


		} catch (Exception e) {
			System.out.println(e.getMessage());
		}


		return listTaskDto;
	}
	
	
	
	
}

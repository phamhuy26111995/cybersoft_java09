package cybersoft.java09.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cybersoft.java09.db.JDBCConnection;
import cybersoft.java09.dto.TaskDto;
import cybersoft.java09.entity.Job;
import cybersoft.java09.entity.Task;

public class TaskRepository {
	private UserRepository userRepository = new UserRepository();
	private JobRepository jobRepository = new JobRepository();
	private StatusRepository statusRepository = new StatusRepository();
	
	
	public List<TaskDto> getAllTask(){ 
		List<TaskDto>  taskDtos = new ArrayList<TaskDto>();
		try {
			String query = "SELECT * FROM tasks";
			Connection connection = JDBCConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);

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
				
				taskDto.setJob(jobRepository.findJobById(result.getInt("job_id")).getName());
				taskDto.setUser(userRepository.findById(result.getInt("user_id")).getFullName());
				taskDto.setStatus(statusRepository.findStatusById(result.getInt("status_id")).getName());
				
				
				
				taskDtos.add(taskDto);
				

				

			}


		} catch (Exception e) {
			System.out.println(e.getMessage());
		}


		return taskDtos;
	}
	
	
	public Task findTaskById(int id) {
		Task task = new Task();
		try {
			String query = "SELECT * FROM tasks WHERE id=?";
			Connection connection = JDBCConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			while(result.next()) {

				
				task.setId(result.getInt("id"));
				task.setName(result.getString("name"));
				Timestamp startDate = result.getTimestamp("start_date");
				Timestamp endDate = result.getTimestamp("end_date");
				
				Date dateStart= new Date(startDate.getTime());
				Date dateEnd= new Date(endDate.getTime());
				
	
				task.setStartDate(dateStart);
				task.setEndDate(endDate);
				
				task.setUserID(result.getInt("user_id"));
				task.setJobID(result.getInt("job_id"));
				task.setStatusID(result.getInt("status_id"));
				
				
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return task;
	}
	
	
	public void addNewTask(Task task) {
		try {
			String query = "INSERT INTO tasks (name,start_date,end_date,user_id,job_id,status_id) VALUES(?,?,?,?,?,?)";
			Connection connection = JDBCConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setString(1, task.getName());
			statement.setTimestamp(2, new Timestamp(task.getStartDate().getTime()));
			statement.setTimestamp(3, new Timestamp(task.getEndDate().getTime()));
			statement.setInt(4, task.getUserID());
			statement.setInt(5, task.getJobID());
			statement.setInt(6, task.getStatusID());
			

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
	
	public void editTask(Task task,int id) {
		try {
			String query = "UPDATE tasks SET name=?, start_date= ?, end_date= ? ,user_id = ?, job_id = ? , status_id = ? where id = ?";
			Connection connection = JDBCConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setString(1, task.getName());
			statement.setTimestamp(2, new Timestamp(task.getStartDate().getTime()));
			statement.setTimestamp(3, new Timestamp(task.getEndDate().getTime()));
			statement.setInt(4, task.getUserID());
			statement.setInt(5, task.getJobID());
			statement.setInt(6, task.getStatusID());
			statement.setInt(7, id);

			int result = statement.executeUpdate();

			if(result < 1) {
				System.out.println("Edit Thất bại");
			}
			else {
				System.out.println("Edit Thành Công");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public void deleteTask(int id) {
		try {
			String query = "DELETE FROM tasks where id = ?";
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
	
	

	public List<TaskDto> getTaskByUserID(int user_id){ 
		List<TaskDto>  taskDtos = new ArrayList<TaskDto>();
		try {
			String query = "SELECT * FROM tasks where user_id = ?";
			Connection connection = JDBCConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setInt(1, user_id);


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
				
				taskDto.setJob(jobRepository.findJobById(result.getInt("job_id")).getName());
				taskDto.setUser(userRepository.findById(result.getInt("user_id")).getFullName());
				taskDto.setStatus(statusRepository.findStatusById(result.getInt("status_id")).getName());
				
				
				
				taskDtos.add(taskDto);
				

				

			}


		} catch (Exception e) {
			System.out.println(e.getMessage());
		}


		return taskDtos;
	}
	public void editTaskStatus(int status_id,int id,int user_id) {
		try {
			String query = "UPDATE tasks SET status_id = ? where id = ? and user_id = ?";
			Connection connection = JDBCConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setInt(1, status_id);
			statement.setInt(2, id);
			statement.setInt(3, user_id);


			int result = statement.executeUpdate();

			if(result < 1) {
				System.out.println("Edit Thất bại");
			}
			else {
				System.out.println("Edit Thành Công");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	
	public TaskDto getTaskByUserIDAndTaskID(int user_id,int task_id){ 
		TaskDto taskDto = new TaskDto();
		try {
			String query = "SELECT * FROM tasks where user_id = ? and id = ?";
			Connection connection = JDBCConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setInt(1, user_id);
			statement.setInt(2, task_id);


			ResultSet result = statement.executeQuery();
			while(result.next()) {
				
				
				taskDto.setId(result.getInt("id"));
				taskDto.setName(result.getString("name"));
				Timestamp startDate = result.getTimestamp("start_date");
				Timestamp endDate = result.getTimestamp("end_date");
				
				Date dateStart= new Date(startDate.getTime());
				Date dateEnd= new Date(endDate.getTime());
				
				
				taskDto.setStartDate(dateStart);
				taskDto.setEndDate(endDate);
				
				taskDto.setJob(jobRepository.findJobById(result.getInt("job_id")).getName());
				taskDto.setUser(userRepository.findById(result.getInt("user_id")).getFullName());
				taskDto.setStatus(statusRepository.findStatusById(result.getInt("status_id")).getName());
				
				
				
				
				

				

			}


		} catch (Exception e) {
			System.out.println(e.getMessage());
		}


		return taskDto;
	}
	
}

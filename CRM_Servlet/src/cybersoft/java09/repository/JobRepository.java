package cybersoft.java09.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cybersoft.java09.db.JDBCConnection;
import cybersoft.java09.dto.JobDto;
import cybersoft.java09.entity.Job;
import cybersoft.java09.entity.Role;
import cybersoft.java09.entity.User;

public class JobRepository {
	public List<Job> getAllJob(){ 
		List<Job> jobs = new ArrayList<Job>();
		try {
			String query = "SELECT * FROM jobs";
			Connection connection = JDBCConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);

			ResultSet result = statement.executeQuery();
			while(result.next()) {
				Job job = new Job();
				
				job.setId(result.getInt("id"));
				job.setName(result.getString("name"));
				Timestamp startDate = result.getTimestamp("start_date");
				Timestamp endDate = result.getTimestamp("end_date");
				
				Date dateStart= new Date(startDate.getTime());
				Date dateEnd= new Date(endDate.getTime());
				
				
				
				
				job.setStartDate(dateStart);
				job.setEndDate(endDate);
				
				
				

				jobs.add(job);

			}


		} catch (Exception e) {
			System.out.println(e.getMessage());
		}


		return jobs;
	}

	public void addNewJob(Job job) {
		try {
			String query = "INSERT INTO jobs (name,start_date,end_date) VALUES(?,?,?)";
			Connection connection = JDBCConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setString(1, job.getName());
			statement.setTimestamp(2, new Timestamp(job.getStartDate().getTime()));
			statement.setTimestamp(3, new Timestamp(job.getEndDate().getTime()));
			

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
	
	public Job findJobById(int id) {
		Job job = new Job();
		try {
			String query = "SELECT * FROM jobs WHERE id=?";
			Connection connection = JDBCConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			while(result.next()) {

				
				job.setId(result.getInt("id"));
				job.setName(result.getString("name"));
				Timestamp startDate = result.getTimestamp("start_date");
				Timestamp endDate = result.getTimestamp("end_date");
				
				Date dateStart= new Date(startDate.getTime());
				Date dateEnd= new Date(endDate.getTime());
				
				
				
				
				job.setStartDate(dateStart);
				job.setEndDate(endDate);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return job;
	}
	
	public void editJob(Job job,int id) {
		try {
			String query = "UPDATE jobs SET name=?, start_date=?, end_date=? where id = ?";
			Connection connection = JDBCConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setString(1, job.getName());
			statement.setTimestamp(2, new Timestamp(job.getStartDate().getTime()));
			statement.setTimestamp(3, new Timestamp(job.getEndDate().getTime()));
			statement.setInt(4, id);

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
	public void deleteJob(int id) {
		try {
			String query = "DELETE FROM jobs where id = ?";
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
	public List<User> findUsersByJobID(int id) { // Lấy list user thuộc dự án
		List<User> users = new ArrayList<User>();
		try {
			String query = "select u.id,u.fullname from tasks t join users u on t.user_id = u.id join status s on t.status_id = s.id join jobs j on t.job_id=j.id where j.id = ?"  ; 
					
			Connection connection = JDBCConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				User user = new User();
				
				user.setId(result.getInt("id"));
				user.setFullName(result.getString("fullname"));
				
				
				

				users.add(user);

			}


		} catch (Exception e) {
			System.out.println(e.getMessage());
		}


		return users;
	}
	
	public List<JobDto> getUserOfListDto(int id){ //Lấy thuộc tính User trong JobDto
		List<JobDto> listJobDto = new ArrayList<JobDto>();
		try {
			String query = "select u.id,u.fullname from tasks t join users u on t.user_id = u.id join status s on t.status_id = s.id join jobs j on t.job_id=j.id where j.id = ?"  ; 
					
			Connection connection = JDBCConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				User user = new User();
				
				user.setId(result.getInt("id"));
				user.setFullName(result.getString("fullname"));
				
				JobDto jobDto=new JobDto();
				jobDto.setUser(user);;

				listJobDto.add(jobDto);

			}


		} catch (Exception e) {
			System.out.println(e.getMessage());
		}


		return listJobDto;
	}
	
	public Job findJobByUserID(int id) { //Tìm dự án thuộc user
		Job job = new Job();
		try {
			String query = "select j.id,j.name from tasks t join users u on t.user_id = u.id join status s on t.status_id = s.id join jobs j on t.job_id=j.id where u.id = ?"  ; 
					
			Connection connection = JDBCConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				
				
				job.setId(result.getInt("id"));
				job.setName(result.getString("name"));
				
				
				

				

			}


		} catch (Exception e) {
			System.out.println(e.getMessage());
		}


		return job;
	}
	
}

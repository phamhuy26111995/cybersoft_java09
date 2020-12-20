package cybersoft.java09.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cybersoft.java09.dto.JobDto;
import cybersoft.java09.entity.Job;
import cybersoft.java09.entity.User;
import cybersoft.java09.repository.JobRepository;
import cybersoft.java09.repository.TaskRepository;
import cybersoft.java09.repository.UserRepository;


/**
 * Servlet implementation class GroupWorkController
 */
@WebServlet(urlPatterns = {"/groupwork","/groupwork-add","/groupwork-delete","/groupwork-details","/groupwork-edit"})
public class GroupWorkController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private JobRepository jobRepository;   
    private UserRepository userRepository;
    private TaskRepository taskRepository;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GroupWorkController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		switch (path) {
		case "/groupwork":
			
			List<Job> jobs = jobRepository.getAllJob();
			request.setAttribute("jobs", jobs);
			

			request.getRequestDispatcher("/WEB-INF/views/groupwork/groupwork.jsp").forward(request, response);
			break;
		case "/groupwork-details":
			int id_detail = Integer.parseInt(request.getParameter("id"));
			List<JobDto> jobDtos = jobRepository.getUserOfListDto(id_detail);
			
			for(JobDto jobDto : jobDtos) {
				jobDto.setTaskNotDone(userRepository.findTaskOfUser(jobDto.getUser().getId(), 1));
				jobDto.setTaskPending(userRepository.findTaskOfUser(jobDto.getUser().getId(), 2));
				jobDto.setTaskDone(userRepository.findTaskOfUser(jobDto.getUser().getId(), 3));
			}
			System.out.println(jobDtos.get(0).getTaskNotDone().get(0));
			request.setAttribute("jobDtos", jobDtos);
			request.getRequestDispatcher("/WEB-INF/views/groupwork/groupwork-details.jsp").forward(request, response);
			break;
		case "/groupwork-add":
			request.getRequestDispatcher("/WEB-INF/views/groupwork/groupwork-add.jsp").forward(request, response);
			break;
			
		case "/groupwork-edit":
			int id = Integer.parseInt(request.getParameter("id"));
			Job job = jobRepository.findJobById(id);
			List<User> users = jobRepository.findUsersByJobID(id);
			
			if(user.getRole_Id()==2) {
				for(User u : users) {
					if(u.getId()!= user.getId()) {
						response.sendRedirect(getServletContext().getContextPath()+"/error/403");
						return;
					}
				}
				
			}
			request.setAttribute("job", job);
			
			request.getRequestDispatcher("/WEB-INF/views/groupwork/groupwork-edit.jsp").forward(request, response);
			
			break;
		case "/groupwork-delete":
			int id_del = Integer.parseInt(request.getParameter("id"));
			jobRepository.deleteJob(id_del);
			
			response.sendRedirect(getServletContext().getContextPath()+"/groupwork");

			
		default:
			break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String path = request.getServletPath();
		
		switch (path) {
		case "/groupwork":
		
			break;
		case "/groupwork-details":
			request.getRequestDispatcher("/WEB-INF/views/groupwork/groupwork-details.jsp").forward(request, response);
			break;
		case "/groupwork-add":
			String name = request.getParameter("name");
			String start_date = request.getParameter("start_date");
			String end_date = request.getParameter("end_date");
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			try {
				Date startDate = sdf.parse(start_date);
				Date endDate = sdf.parse(end_date);
				Job job = new Job(name, startDate, endDate);
				
				jobRepository.addNewJob(job);
				response.sendRedirect(getServletContext().getContextPath()+"/groupwork");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
			break;
			
		case "/groupwork-edit":
			int id = Integer.parseInt(request.getParameter("id"));
			String name_edit = request.getParameter("name");
			String start_date_edit = request.getParameter("start_date");
			String end_date_edit = request.getParameter("end_date");
			
			SimpleDateFormat sdf_edit = new SimpleDateFormat("dd/MM/yyyy");
			try {
				Date startDate = sdf_edit.parse(start_date_edit);
				Date endDate = sdf_edit.parse(end_date_edit);
				Job job = new Job(name_edit, startDate, endDate);
				
				jobRepository.editJob(job, id);
				response.sendRedirect(getServletContext().getContextPath()+"/groupwork");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
			
			
			
			
			
			
			
			break;
		default:
			break;
		}
	}

	@Override
	public void init() throws ServletException {
		jobRepository = new JobRepository();
		userRepository = new UserRepository();
		taskRepository = new TaskRepository();
	}
	
	

}

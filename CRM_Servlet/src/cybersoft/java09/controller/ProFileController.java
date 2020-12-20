package cybersoft.java09.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cybersoft.java09.dto.TaskDto;
import cybersoft.java09.entity.Status;
import cybersoft.java09.entity.User;
import cybersoft.java09.repository.StatusRepository;
import cybersoft.java09.repository.TaskRepository;
import cybersoft.java09.repository.UserRepository;

/**
 * Servlet implementation class ProFileController
 */
@WebServlet(urlPatterns = {"/profile","/profile-update"})
public class ProFileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TaskRepository taskRepository;   
	private StatusRepository statusRepository;
	private UserRepository userRepository;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProFileController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		List<TaskDto> taskDtos ;
		switch (path) {
		case "/profile":
			int id = Integer.parseInt(request.getParameter("id"));
			
			taskDtos = taskRepository.getTaskByUserID(id);

			request.setAttribute("taskDtos", taskDtos);


			request.getRequestDispatcher("WEB-INF/views/profile/profile.jsp").forward(request, response);
			break;
		case "/profile-update":
			int id_edit = Integer.parseInt(request.getParameter("id"));
			
			taskDtos = taskRepository.getTaskByUserID(user.getId());
			TaskDto taskDto = taskRepository.getTaskByUserIDAndTaskID(user.getId(), id_edit);
			List<Status> listStatus = statusRepository.getAllStatus();
			
			
			request.setAttribute("taskDtos", taskDtos);
			request.setAttribute("listStatus", listStatus);
			request.setAttribute("taskDto", taskDto);

			request.getRequestDispatcher("WEB-INF/views/profile/profile-edit.jsp").forward(request, response);
			break;	
		default:
			break;
		}

		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		switch (path) {

		case "/profile-update":
			int id = Integer.parseInt(request.getParameter("id"));
			int status_id = Integer.parseInt(request.getParameter("status"));


			taskRepository.editTaskStatus(status_id,id,user.getId());
			System.out.println("StatusID : "+status_id+" Task_ID "+id+" User_id "+user.getId());
			response.sendRedirect(request.getContextPath()+"/profile?id="+user.getId());

			break;	
		default:
			break;
		}
	}

	@Override
	public void init() throws ServletException {
		taskRepository = new TaskRepository();
		statusRepository = new StatusRepository();
		userRepository = new UserRepository();
	}

}

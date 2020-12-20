package cybersoft.java09.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

import cybersoft.java09.dto.UserDto;
import cybersoft.java09.entity.Role;
import cybersoft.java09.entity.Task;
import cybersoft.java09.entity.User;
import cybersoft.java09.repository.RoleRepository;
import cybersoft.java09.repository.UserRepository;
import cybersoft.java09.service.UserService;

/**
 * Servlet implementation class UserController
 */
@WebServlet(urlPatterns = {"/user-add","/user-details","/user-edit","/user-delete","/user-table"})
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService;
	private UserRepository userRepository;
	private RoleRepository roleRepository;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		HttpSession session = request.getSession();
		User user_session = (User)session.getAttribute("user");
		switch (path) {
		case "/user-table":
			List<UserDto> usersDto = userService.getAllUserRole(); 
			request.setAttribute("users", usersDto);

			request.getRequestDispatcher("/WEB-INF/views/user/user-table.jsp").forward(request, response);
			break;
		case "/user-add":
			List<Role> roles = roleRepository.getAllRole(); //Lấy toàn bộ Role

			request.setAttribute("roles", roles);
			request.getRequestDispatcher("/WEB-INF/views/user/user-add.jsp").forward(request, response);
			break;
		case "/user-edit":

			int id = Integer.valueOf(request.getParameter("id"));

			User user = userRepository.findById(id);
			if(user_session.getRole_Id() == 2) {
				if(user.getRole_Id()!=3) {
					response.sendRedirect(request.getContextPath()+"/error/403");
					return;
				}
			}

			request.setAttribute("user", user);

			List<Role> roles_edit = roleRepository.getAllRole();

			request.setAttribute("roles", roles_edit);

			request.getRequestDispatcher("/WEB-INF/views/user/user-edit.jsp").forward(request, response);





			// TRUY VẤN DB LẤY THÔNG TIN USER CẦN SỬA

			break;

		case "/user-delete":

			int id_del = Integer.valueOf(request.getParameter("id"));
			User user_delete = userRepository.findById(id_del);
			if(user_session.getRole_Id() == 2) {
				if(user_delete.getRole_Id()!=3) {
					response.sendRedirect(request.getContextPath()+"/error/403");
					return;
				}
			}

			userRepository.deleteUser(id_del);
			response.sendRedirect(request.getContextPath()+"/user-table");




			break;


		case "/user-details":
			int id_detail = Integer.valueOf(request.getParameter("id"));
			List<Task> listTaskNotDone = userRepository.findTaskOfUser(id_detail, 1);
			List<Task> listTaskPending = userRepository.findTaskOfUser(id_detail, 2);
			List<Task> listTaskDone = userRepository.findTaskOfUser(id_detail, 3);
			User userDetail = userRepository.findById(id_detail);
			request.setAttribute("listTaskNotDone", listTaskNotDone);
			request.setAttribute("listTaskPending", listTaskPending);
			request.setAttribute("listTaskDone", listTaskDone);
			request.setAttribute("userDetail", userDetail);



			request.getRequestDispatcher("/WEB-INF/views/user/user-details.jsp").forward(request, response);
			break;
		default:
			break;
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();


		String email = request.getParameter("email");
		String password = request.getParameter("passwd");
		String fullname = request.getParameter("fullname");
		String avatar = request.getParameter("avatar");
		int roleId = Integer.parseInt(request.getParameter("roleId"));

		switch (path) {

		case "/user-add":

			/* String hashed = BCrypt.hashpw(password, BCrypt.gensalt(12)); */
			User user = new User(email, password, fullname, avatar, roleId);

			userRepository.addUser(user);

			response.sendRedirect(request.getContextPath()+"/user-table");
			break;

		case "/user-edit":

			int id = Integer.parseInt(request.getParameter("id"));
			User userEdit = userRepository.findById(id);
			userEdit.setEmail(email);
			userEdit.setFullName(fullname);
			userEdit.setAvatar(avatar);
			userEdit.setRole_Id(roleId);

			// KIỂM TRA XEM NGƯỜI DÙNG NHẬP MẬT KHẨU MỚI KHÔNG
			/*
			 * if (password != null && !password.isEmpty()) { // MÃ HÓA MẬT KHẨU String
			 * hashed2 = BCrypt.hashpw(password, BCrypt.gensalt(12)); // CẬP NHẬT THÔNG TIN
			 * USER (BAO GỒM CẢ KHẨU) userEdit.setPassWord(hashed2); }
			 */

			userRepository.editUser(userEdit, id);

			response.sendRedirect(request.getContextPath()+"/user-table");


			break;
		case "/user-details":
			request.getRequestDispatcher("/WEB-INF/views/user/user-details.jsp").forward(request, response);
			break;
		default:
			break;
		}
	}

	@Override
	public void init() throws ServletException {
		userService = new UserService();
		roleRepository = new RoleRepository();
		userRepository = new UserRepository();
	}



}

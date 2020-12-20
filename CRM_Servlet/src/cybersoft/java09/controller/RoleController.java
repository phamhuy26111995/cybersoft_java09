package cybersoft.java09.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cybersoft.java09.entity.Role;
import cybersoft.java09.repository.RoleRepository;

/**
 * Servlet implementation class RoleController
 */
@WebServlet(urlPatterns = {"/role-add","/role-table","/role-edit","/role-delete"})
public class RoleController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RoleRepository roleRepository;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RoleController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		
		
		switch (path) {
		case "/role-add":

			request.getRequestDispatcher("/WEB-INF/views/role/role-add.jsp").forward(request, response);
			break;
		case "/role-table":
			List<Role> roles = roleRepository.getAllRole();
			request.setAttribute("roles", roles);

			request.getRequestDispatcher("/WEB-INF/views/role/role-table.jsp").forward(request, response);
			break;

		case "/role-edit":
			int id = Integer.valueOf(request.getParameter("id"));
			Role role = roleRepository.findRoleById(id);


			request.setAttribute("role", role);

			request.getRequestDispatcher("/WEB-INF/views/role/role-edit.jsp").forward(request, response);
			break;	
		case "/role-delete":
			int id_edit = Integer.valueOf(request.getParameter("id"));
			
			roleRepository.deleteRole(id_edit);
			response.sendRedirect(request.getContextPath()+"/role-table");
			
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

		String name = request.getParameter("name");
		String description = request.getParameter("description");
		switch (path) {
		case "/role-add":
			Role role = new Role(name,description);
			roleRepository.addNewRole(role);
			response.sendRedirect(request.getContextPath()+"/role-table");
			break;
		case "/role-edit":
			int id = Integer.valueOf(request.getParameter("id"));
			Role role_edit = new Role(name,description);
			roleRepository.editRole(role_edit,id);
			response.sendRedirect(request.getContextPath()+"/role-table");
			
			
			break;


		case "/role-table":

			break;
		default:
			break;
		}
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		roleRepository = new RoleRepository();
	}



}

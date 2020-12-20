package cybersoft.java09.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cybersoft.java09.dto.UserDto;
import cybersoft.java09.entity.User;
import cybersoft.java09.repository.UserRepository;

/**
 * Servlet Filter implementation class AuthFilter
 */
@WebFilter(urlPatterns = {"/*"})
public class AuthFilter implements Filter {
	private UserRepository userRepository;
	/**
	 * Default constructor. 
	 */
	public AuthFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		String action = req.getServletPath();
		if(action.equals("/login")) {
			chain.doFilter(request, response);
			return;
		}

		HttpSession session = req.getSession();
		User user = (User)session.getAttribute("user");
		if(user==null) {
			res.sendRedirect(req.getContextPath() + "/login");
			return;
		}
		else {
			int roleID = user.getRole_Id();


			if(roleID != 1 && (action.equals("/user-add") || action.startsWith("/role"))) {
				res.sendRedirect(req.getContextPath() + "/error/403"); return;
			}


			 if(roleID == 3 && ((action.endsWith("edit"))  || action.endsWith("delete") ||
					action.startsWith("/groupwork") || action.endsWith("add"))){
				 
				 
				 res.sendRedirect(req.getContextPath() + "/error/403");

				 return;
				 
			 
			 }
			



			chain.doFilter(request, response);

		}






	}









	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		userRepository = new UserRepository();
	}

}

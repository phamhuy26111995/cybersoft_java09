/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.62
 * Generated at: 2022-04-23 10:39:21 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.views.login;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    if (!javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      final java.lang.String _jspx_method = request.getMethod();
      if ("OPTIONS".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        return;
      }
      if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET, POST or HEAD. Jasper also permits OPTIONS");
        return;
      }
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"UTF-8\">\r\n");
      out.write("<title>Login</title>\r\n");
      out.write("<style type=\"text/css\">\r\n");
      out.write("body{\r\n");
      out.write("	margin:0;\r\n");
      out.write("	color:#6a6f8c;\r\n");
      out.write("	background:#c8c8c8;\r\n");
      out.write("	font:600 16px/18px 'Open Sans',sans-serif;\r\n");
      out.write("}\r\n");
      out.write("*,:after,:before{box-sizing:border-box}\r\n");
      out.write(".clearfix:after,.clearfix:before{content:'';display:table}\r\n");
      out.write(".clearfix:after{clear:both;display:block}\r\n");
      out.write("a{color:inherit;text-decoration:none}\r\n");
      out.write("\r\n");
      out.write(".login-wrap{\r\n");
      out.write("	width:100%;\r\n");
      out.write("	margin:auto;\r\n");
      out.write("	max-width:525px;\r\n");
      out.write("	min-height:670px;\r\n");
      out.write("	position:relative;\r\n");
      out.write("	background:url(https://raw.githubusercontent.com/khadkamhn/day-01-login-form/master/img/bg.jpg) no-repeat center;\r\n");
      out.write("	box-shadow:0 12px 15px 0 rgba(0,0,0,.24),0 17px 50px 0 rgba(0,0,0,.19);\r\n");
      out.write("}\r\n");
      out.write(".login-html{\r\n");
      out.write("	width:100%;\r\n");
      out.write("	height:100%;\r\n");
      out.write("	position:absolute;\r\n");
      out.write("	padding:90px 70px 50px 70px;\r\n");
      out.write("	background:rgba(40,57,101,.9);\r\n");
      out.write("}\r\n");
      out.write(".login-html .sign-in-htm,\r\n");
      out.write(".login-html .sign-up-htm{\r\n");
      out.write("	top:0;\r\n");
      out.write("	left:0;\r\n");
      out.write("	right:0;\r\n");
      out.write("	bottom:0;\r\n");
      out.write("	position:absolute;\r\n");
      out.write("	transform:rotateY(180deg);\r\n");
      out.write("	backface-visibility:hidden;\r\n");
      out.write("	transition:all .4s linear;\r\n");
      out.write("}\r\n");
      out.write(".login-html .sign-in,\r\n");
      out.write(".login-html .sign-up,\r\n");
      out.write(".login-form .group .check{\r\n");
      out.write("	display:none;\r\n");
      out.write("}\r\n");
      out.write(".login-html .tab,\r\n");
      out.write(".login-form .group .label,\r\n");
      out.write(".login-form .group .button{\r\n");
      out.write("	text-transform:uppercase;\r\n");
      out.write("}\r\n");
      out.write(".login-html .tab{\r\n");
      out.write("	font-size:22px;\r\n");
      out.write("	margin-right:15px;\r\n");
      out.write("	padding-bottom:5px;\r\n");
      out.write("	margin:0 15px 10px 0;\r\n");
      out.write("	display:inline-block;\r\n");
      out.write("	border-bottom:2px solid transparent;\r\n");
      out.write("}\r\n");
      out.write(".login-html .sign-in:checked + .tab,\r\n");
      out.write(".login-html .sign-up:checked + .tab{\r\n");
      out.write("	color:#fff;\r\n");
      out.write("	border-color:#1161ee;\r\n");
      out.write("}\r\n");
      out.write(".login-form{\r\n");
      out.write("	min-height:345px;\r\n");
      out.write("	position:relative;\r\n");
      out.write("	perspective:1000px;\r\n");
      out.write("	transform-style:preserve-3d;\r\n");
      out.write("}\r\n");
      out.write(".login-form .group{\r\n");
      out.write("	margin-bottom:15px;\r\n");
      out.write("}\r\n");
      out.write(".login-form .group .label,\r\n");
      out.write(".login-form .group .input,\r\n");
      out.write(".login-form .group .button{\r\n");
      out.write("	width:100%;\r\n");
      out.write("	color:#fff;\r\n");
      out.write("	display:block;\r\n");
      out.write("}\r\n");
      out.write(".login-form .group .input,\r\n");
      out.write(".login-form .group .button{\r\n");
      out.write("	border:none;\r\n");
      out.write("	padding:15px 20px;\r\n");
      out.write("	border-radius:25px;\r\n");
      out.write("	background:rgba(255,255,255,.1);\r\n");
      out.write("}\r\n");
      out.write(".login-form .group input[data-type=\"password\"]{\r\n");
      out.write("	text-security:circle;\r\n");
      out.write("	-webkit-text-security:circle;\r\n");
      out.write("}\r\n");
      out.write(".login-form .group .label{\r\n");
      out.write("	color:#aaa;\r\n");
      out.write("	font-size:12px;\r\n");
      out.write("}\r\n");
      out.write(".login-form .group .button{\r\n");
      out.write("	background:#1161ee;\r\n");
      out.write("}\r\n");
      out.write(".login-form .group label .icon{\r\n");
      out.write("	width:15px;\r\n");
      out.write("	height:15px;\r\n");
      out.write("	border-radius:2px;\r\n");
      out.write("	position:relative;\r\n");
      out.write("	display:inline-block;\r\n");
      out.write("	background:rgba(255,255,255,.1);\r\n");
      out.write("}\r\n");
      out.write(".login-form .group label .icon:before,\r\n");
      out.write(".login-form .group label .icon:after{\r\n");
      out.write("	content:'';\r\n");
      out.write("	width:10px;\r\n");
      out.write("	height:2px;\r\n");
      out.write("	background:#fff;\r\n");
      out.write("	position:absolute;\r\n");
      out.write("	transition:all .2s ease-in-out 0s;\r\n");
      out.write("}\r\n");
      out.write(".login-form .group label .icon:before{\r\n");
      out.write("	left:3px;\r\n");
      out.write("	width:5px;\r\n");
      out.write("	bottom:6px;\r\n");
      out.write("	transform:scale(0) rotate(0);\r\n");
      out.write("}\r\n");
      out.write(".login-form .group label .icon:after{\r\n");
      out.write("	top:6px;\r\n");
      out.write("	right:0;\r\n");
      out.write("	transform:scale(0) rotate(0);\r\n");
      out.write("}\r\n");
      out.write(".login-form .group .check:checked + label{\r\n");
      out.write("	color:#fff;\r\n");
      out.write("}\r\n");
      out.write(".login-form .group .check:checked + label .icon{\r\n");
      out.write("	background:#1161ee;\r\n");
      out.write("}\r\n");
      out.write(".login-form .group .check:checked + label .icon:before{\r\n");
      out.write("	transform:scale(1) rotate(45deg);\r\n");
      out.write("}\r\n");
      out.write(".login-form .group .check:checked + label .icon:after{\r\n");
      out.write("	transform:scale(1) rotate(-45deg);\r\n");
      out.write("}\r\n");
      out.write(".login-html .sign-in:checked + .tab + .sign-up + .tab + .login-form .sign-in-htm{\r\n");
      out.write("	transform:rotate(0);\r\n");
      out.write("}\r\n");
      out.write(".login-html .sign-up:checked + .tab + .login-form .sign-up-htm{\r\n");
      out.write("	transform:rotate(0);\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".hr{\r\n");
      out.write("	height:2px;\r\n");
      out.write("	margin:60px 0 50px 0;\r\n");
      out.write("	background:rgba(255,255,255,.2);\r\n");
      out.write("}\r\n");
      out.write(".foot-lnk{\r\n");
      out.write("	text-align:center;\r\n");
      out.write("}\r\n");
      out.write("</style>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("<div class=\"login-wrap\">\r\n");
      out.write("	<div class=\"login-html\">\r\n");
      out.write("		<input id=\"tab-1\" type=\"radio\" name=\"tab\" class=\"sign-in\" checked><label for=\"tab-1\" class=\"tab\">Sign In</label>\r\n");
      out.write("		<input id=\"tab-2\" type=\"radio\" name=\"tab\" class=\"sign-up\"><label for=\"tab-2\" class=\"tab\"></label>\r\n");
      out.write("		<div class=\"login-form\">\r\n");
      out.write("			<form class=\"sign-in-htm\" action=\"");
      out.print(request.getContextPath() );
      out.write("/login\" method=\"post\">\r\n");
      out.write("				<div class=\"group\">\r\n");
      out.write("					<label for=\"user\" class=\"label\">Username</label>\r\n");
      out.write("					<input id=\"user\" type=\"text\" class=\"input\" placeholder=\"Enter Username\" name=\"email\" required>\r\n");
      out.write("				</div>\r\n");
      out.write("				<div class=\"group\">\r\n");
      out.write("					<label for=\"pass\" class=\"label\">Password</label>\r\n");
      out.write("					<input id=\"pass\" type=\"password\" class=\"input\" placeholder=\"Enter Password\" name=\"password\" required >\r\n");
      out.write("				</div>\r\n");
      out.write("				\r\n");
      out.write("				<div class=\"group\">\r\n");
      out.write("					<input type=\"submit\" class=\"button\">\r\n");
      out.write("				</div>\r\n");
      out.write("				<div class=\"hr\"></div>\r\n");
      out.write("				\r\n");
      out.write("			</form>\r\n");
      out.write("\r\n");
      out.write("		</div>\r\n");
      out.write("	</div>\r\n");
      out.write("</div>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}

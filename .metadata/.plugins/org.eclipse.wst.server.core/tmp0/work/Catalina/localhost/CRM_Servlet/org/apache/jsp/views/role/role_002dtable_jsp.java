/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.62
 * Generated at: 2022-04-23 10:39:57 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.views.role;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import cybersoft.java09.entity.Role;
import java.util.List;

public final class role_002dtable_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(2);
    _jspx_dependants.put("jar:file:/H:/GitHub/cybersoft_java09/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/CRM_Servlet/WEB-INF/lib/jstl-1.2.jar!/META-INF/c.tld", Long.valueOf(1153359882000L));
    _jspx_dependants.put("/WEB-INF/lib/jstl-1.2.jar", Long.valueOf(1636882573427L));
  }

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("java.util.List");
    _jspx_imports_classes.add("cybersoft.java09.entity.Role");
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems;

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
    _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.release();
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
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html lang=\"en\">\r\n");
 String contextPath= request.getContextPath(); 
      out.write("\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"utf-8\">\r\n");
      out.write("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n");
      out.write("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n");
      out.write("<meta name=\"description\" content=\"\">\r\n");
      out.write("<meta name=\"author\" content=\"\">\r\n");
      out.write("<link rel=\"icon\" type=\"image/png\" sizes=\"16x16\"\r\n");
      out.write("	href=\"");
      out.print(contextPath );
      out.write("/static/plugins/images/favicon.png\">\r\n");
      out.write("<title>Pixel Admin</title>\r\n");
      out.write("<!-- Bootstrap Core CSS -->\r\n");
      out.write("<link href=\"");
      out.print(contextPath );
      out.write("/static/bootstrap/dist/css/bootstrap.min.css\" rel=\"stylesheet\">\r\n");
      out.write("<!-- Menu CSS -->\r\n");
      out.write("<link\r\n");
      out.write("	href=\"");
      out.print(contextPath );
      out.write("/static/plugins/bower_components/sidebar-nav/dist/sidebar-nav.min.css\"\r\n");
      out.write("	rel=\"stylesheet\">\r\n");
      out.write("<link rel=\"stylesheet\"\r\n");
      out.write("	href=\"https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css\">\r\n");
      out.write("<!-- animation CSS -->\r\n");
      out.write("<link href=\"");
      out.print(contextPath );
      out.write("/static/css/animate.css\" rel=\"stylesheet\">\r\n");
      out.write("<!-- Custom CSS -->\r\n");
      out.write("<link href=\"");
      out.print(contextPath );
      out.write("/static/css/style.css\" rel=\"stylesheet\">\r\n");
      out.write("<!-- color CSS -->\r\n");
      out.write("<link href=\"");
      out.print(contextPath );
      out.write("/static/css/colors/blue-dark.css\" id=\"theme\" rel=\"stylesheet\">\r\n");
      out.write("<link rel=\"stylesheet\" href=\"");
      out.print(contextPath );
      out.write("/static/css/custom.css\">\r\n");
      out.write("<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->\r\n");
      out.write("<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->\r\n");
      out.write("<!--[if lt IE 9]>\r\n");
      out.write("    <script src=\"https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js\"></script>\r\n");
      out.write("    <script src=\"https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js\"></script>\r\n");
      out.write("<![endif]-->\r\n");
      out.write("</head>\r\n");
      out.write("\r\n");
      out.write("<body>\r\n");
      out.write("	<!-- Preloader -->\r\n");
      out.write("	<div class=\"preloader\">\r\n");
      out.write("		<div class=\"cssload-speeding-wheel\"></div>\r\n");
      out.write("	</div>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("	");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../layout/navbar.jsp", out, false);
      out.write("\r\n");
      out.write("\r\n");
      out.write("	<!-- Page Content -->\r\n");
      out.write("	\r\n");
      out.write("	");
 List<Role> roles = (List<Role>)request.getAttribute("roles"); 
      out.write("\r\n");
      out.write("	<div id=\"page-wrapper\">\r\n");
      out.write("		<div class=\"container-fluid\">\r\n");
      out.write("			<div class=\"row bg-title\">\r\n");
      out.write("				<div class=\"col-lg-3 col-md-4 col-sm-4 col-xs-12\">\r\n");
      out.write("					<h4 class=\"page-title\">Danh sách quyền</h4>\r\n");
      out.write("				</div>\r\n");
      out.write("				<div class=\"col-lg-9 col-sm-8 col-md-8 col-xs-12 text-right\">\r\n");
      out.write("					<a href=\"");
      out.print(contextPath );
      out.write("/role-add\" class=\"btn btn-sm btn-success\">Thêm mới</a>\r\n");
      out.write("				</div>\r\n");
      out.write("				<!-- /.col-lg-12 -->\r\n");
      out.write("			</div>\r\n");
      out.write("			<!-- /row -->\r\n");
      out.write("			<div class=\"row\">\r\n");
      out.write("				<div class=\"col-sm-12\">\r\n");
      out.write("					<div class=\"white-box\">\r\n");
      out.write("						<div class=\"table-responsive\">\r\n");
      out.write("							<table class=\"table\" id=\"example\">\r\n");
      out.write("								<thead>\r\n");
      out.write("									<tr>\r\n");
      out.write("										<th>STT</th>\r\n");
      out.write("										<th>Tên Quyền</th>\r\n");
      out.write("										<th>Mô Tả</th>\r\n");
      out.write("									\r\n");
      out.write("									</tr>\r\n");
      out.write("								</thead>\r\n");
      out.write("								<tbody>\r\n");
      out.write("								\r\n");
      out.write("								");
      //  c:forEach
      org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_005fforEach_005f0 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
      boolean _jspx_th_c_005fforEach_005f0_reused = false;
      try {
        _jspx_th_c_005fforEach_005f0.setPageContext(_jspx_page_context);
        _jspx_th_c_005fforEach_005f0.setParent(null);
        // /views/role/role-table.jsp(82,8) name = items type = javax.el.ValueExpression reqTime = true required = false fragment = false deferredValue = true expectedTypeName = java.lang.Object deferredMethod = false methodSignature = null
        _jspx_th_c_005fforEach_005f0.setItems( roles );
        // /views/role/role-table.jsp(82,8) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
        _jspx_th_c_005fforEach_005f0.setVar("item");
        int[] _jspx_push_body_count_c_005fforEach_005f0 = new int[] { 0 };
        try {
          int _jspx_eval_c_005fforEach_005f0 = _jspx_th_c_005fforEach_005f0.doStartTag();
          if (_jspx_eval_c_005fforEach_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            do {
              out.write("\r\n");
              out.write("								\r\n");
              out.write("										<tr>\r\n");
              out.write("										\r\n");
              out.write("										<td>");
              out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${item.id }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
              out.write("</td>\r\n");
              out.write("										<td>");
              out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${item.name }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
              out.write("</td>\r\n");
              out.write("										<td>");
              out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${item.description }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
              out.write("</td>\r\n");
              out.write("									\r\n");
              out.write("										</tr>\r\n");
              out.write("								\r\n");
              out.write("								");
              int evalDoAfterBody = _jspx_th_c_005fforEach_005f0.doAfterBody();
              if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                break;
            } while (true);
          }
          if (_jspx_th_c_005fforEach_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            return;
          }
        } catch (java.lang.Throwable _jspx_exception) {
          while (_jspx_push_body_count_c_005fforEach_005f0[0]-- > 0)
            out = _jspx_page_context.popBody();
          _jspx_th_c_005fforEach_005f0.doCatch(_jspx_exception);
        } finally {
          _jspx_th_c_005fforEach_005f0.doFinally();
        }
        _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.reuse(_jspx_th_c_005fforEach_005f0);
        _jspx_th_c_005fforEach_005f0_reused = true;
      } finally {
        org.apache.jasper.runtime.JspRuntimeLibrary.releaseTag(_jspx_th_c_005fforEach_005f0, _jsp_getInstanceManager(), _jspx_th_c_005fforEach_005f0_reused);
      }
      out.write("\r\n");
      out.write("							\r\n");
      out.write("							\r\n");
      out.write("								</tbody>\r\n");
      out.write("							</table>\r\n");
      out.write("						</div>\r\n");
      out.write("					</div>\r\n");
      out.write("				</div>\r\n");
      out.write("			</div>\r\n");
      out.write("			<!-- /.row -->\r\n");
      out.write("		</div>\r\n");
      out.write("		<!-- /.container-fluid -->\r\n");
      out.write("		<footer class=\"footer text-center\"> 2018 &copy; myclass.com </footer>\r\n");
      out.write("	</div>\r\n");
      out.write("	<!-- /#page-wrapper -->\r\n");
      out.write("	</div>\r\n");
      out.write("	<!-- /#wrapper -->\r\n");
      out.write("	<!-- jQuery -->\r\n");
      out.write("	<script src=\"");
      out.print(contextPath );
      out.write("/static/plugins/bower_components/jquery/dist/jquery.min.js\"></script>\r\n");
      out.write("	<!-- Bootstrap Core JavaScript -->\r\n");
      out.write("	<script src=\"");
      out.print(contextPath );
      out.write("/static/bootstrap/dist/js/bootstrap.min.js\"></script>\r\n");
      out.write("	<!-- Menu Plugin JavaScript -->\r\n");
      out.write("	<script\r\n");
      out.write("		src=\"");
      out.print(contextPath );
      out.write("/static/plugins/bower_components/sidebar-nav/dist/sidebar-nav.min.js\"></script>\r\n");
      out.write("	<!--slimscroll JavaScript -->\r\n");
      out.write("	<script src=\"");
      out.print(contextPath );
      out.write("/static/js/jquery.slimscroll.js\"></script>\r\n");
      out.write("	<script src=\"");
      out.print(contextPath );
      out.write("/static/js/jquery.dataTables.js\"></script>\r\n");
      out.write("	<!--Wave Effects -->\r\n");
      out.write("	<script src=\"");
      out.print(contextPath );
      out.write("/static/js/waves.js\"></script>\r\n");
      out.write("	<!-- Custom Theme JavaScript -->\r\n");
      out.write("	<script src=\"");
      out.print(contextPath );
      out.write("/static/js/custom.min.js\"></script>\r\n");
      out.write("	<script>\r\n");
      out.write("        $(document).ready(function () {\r\n");
      out.write("            $('#example').DataTable();\r\n");
      out.write("        });\r\n");
      out.write("    </script>\r\n");
      out.write("</body>\r\n");
      out.write("\r\n");
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

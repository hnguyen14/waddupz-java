package org.apache.jsp.main;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class jobInput_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static java.util.Vector _jspx_dependants;

  public java.util.List getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    JspFactory _jspxFactory = null;
    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      _jspxFactory = JspFactory.getDefaultFactory();
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;


	String elementId = request.getParameter("elementId");
	String jName = request.getParameter("jobName");
	String jClass = request.getParameter("jobClass");
	String jGroup = request.getParameter("jobGroup");
	String jParam = request.getParameter("jobParam");
	String jInterval = request.getParameter("jobInterval");
	String jStart = request.getParameter("jobStartTime");
	String isGroup = request.getParameter("isGroup");
	String cmd = request.getParameter("cmd");

      out.write("\r\n");
      out.write("\t<div style=\"float:right;\"><a href=\"javascript:showHide('");
      out.print(elementId);
      out.write("')\"> Hide this Tab </a> </div>\r\n");
      out.write("\t<div style=\"float:left; font-size:14px\"><b>Enter Job Information</b></div>\r\n");
      out.write("\t<form action=\"jobs.jsp\">\r\n");
      out.write("\t\t<input type=\"hidden\" name=\"action\" value=\"");
      out.print(cmd);
      out.write("\"/>\r\n");
      out.write("\t\t<br/> <b>Job Name:</b> <br/>\r\n");
      out.write("\t\t<div><input name=\"jobName\" type=\"text\" value=\"");
      out.print(jName == null ? "" : jName);
      out.write("\"/></div>\r\n");
      out.write("\t\t\t\t\t\t\t\r\n");
      out.write("\t\t<br/> <b>Job Class:</b> <br/>\r\n");
      out.write("\t\t<div><input name=\"jobClass\" type=\"text\" value=\"");
      out.print(jClass == null ? "" : jClass);
      out.write("\"/></div>\r\n");
      out.write("\t\t\t\t\t\t\t\r\n");
      out.write("\t\t");
 if (isGroup!= null) { 
      out.write("\r\n");
      out.write("\t\t\t<input type=\"hidden\" name=\"jobGroup\" type=\"text\" value=\"");
      out.print(jGroup == null ? "" : jGroup);
      out.write("\" />\r\n");
      out.write("\t\t");
 } else { 
      out.write("\r\n");
      out.write("\t\t\t<br/> <b>Job Group:</b> <br/>\r\n");
      out.write("\t\t\t<div><input name=\"jobGroup\" type=\"text\" value=\"");
      out.print(jGroup == null ? "" : jGroup);
      out.write("\" /></div>\r\n");
      out.write("\t\t");
 } 
      out.write("\r\n");
      out.write("\t\t\t\t\t\r\n");
      out.write("\t\t<br/> <b>Parameter:</b> <br/>\r\n");
      out.write("\t\t<div><textarea name=\"jobParam\" cols=40 rows=10>");
      out.print(jParam == null ? "" : jParam);
      out.write("</textarea></div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\r\n");
      out.write("\t\t<br/> <b>Start time  (YYYY/MM/DD HH:MM:SS AM):</b> <br/>\r\n");
      out.write("\t\t<div><input name=\"jobStartTime\" type=\"text\" value=\"");
      out.print(jStart == null ? "" : jStart);
      out.write("\"/></div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\r\n");
      out.write("\t\t<br/> <b>Interval (in seconds, -1 for non recurrent job):</b> <br/>\r\n");
      out.write("\t\t<div><input name=\"jobInterval\" type=\"text\" value=\"");
      out.print(jInterval == null ? "" : jInterval);
      out.write("\"/></div>\r\n");
      out.write("\t\t\t\t\t\t\t\r\n");
      out.write("\t\t<br/>\r\n");
      out.write("\t\t<div><input type =\"submit\" name=\"submit\" value=\"  Submit  \"/></div>\r\n");
      out.write("\t</form>\r\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      if (_jspxFactory != null) _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}

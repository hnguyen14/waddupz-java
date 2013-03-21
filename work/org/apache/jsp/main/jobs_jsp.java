package org.apache.jsp.main;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import app.Tasks.QuartzUtil.ServerScheduler;
import app.Tasks.QuartzUtil.SchedulerHelper;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import java.util.Map;
import java.util.Iterator;
import java.util.List;
import java.util.Date;

public final class jobs_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");


	String cmd = request.getParameter("action");
	
	if (cmd != null ) {
		String jobName = request.getParameter("jobName");
		String jobGroup = request.getParameter("jobGroup");
		if (cmd.equalsIgnoreCase("removeJob")) {
			ServerScheduler.removeJob(jobName,jobGroup);
		} else if (cmd.equalsIgnoreCase("resumeJob")) {
			ServerScheduler.resumeJob(jobName,jobGroup);
		} else if (cmd.equalsIgnoreCase("pauseJob")) {
			ServerScheduler.pauseJob(jobName,jobGroup);
		} else if (cmd.equalsIgnoreCase("addJob")) {
			String jName = request.getParameter("jobName");
			String jClass = request.getParameter("jobClass");
			String jGroup = request.getParameter("jobGroup");
			String jParam = request.getParameter("jobParam");
			String jInterval = request.getParameter("jobInterval");
			String jStart = request.getParameter("jobStartTime");
			
			ServerScheduler.addJob(jName, jClass, jGroup, jParam, jStart, jInterval);
		} else if (cmd.equalsIgnoreCase("editJob")) {
			String jName = request.getParameter("jobName");
			String jClass = request.getParameter("jobClass");
			String jGroup = request.getParameter("jobGroup");
			String jParam = request.getParameter("jobParam");
			String jInterval = request.getParameter("jobInterval");
			String jStart = request.getParameter("jobStartTime");

			ServerScheduler.removeJob(jName,jGroup);
			ServerScheduler.addJob(jName, jClass, jGroup, jParam, jStart, jInterval);
		}
	}

	Scheduler scheduler = ServerScheduler.getScheduler();
	Map map = SchedulerHelper.getAllJobs(scheduler);
	

      out.write("\r\n");
      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("\t<head>\r\n");
      out.write("\t\t<title> Job Monitor </title>\r\n");
      out.write("\t\t<script type=\"text/javascript\">\r\n");
      out.write("\t\t\tfunction showHide(elementid){\r\n");
      out.write("\t\t\t\tif (document.getElementById(elementid).style.display == 'none'){\r\n");
      out.write("\t\t\t\t\tdocument.getElementById(elementid).style.display = '';\r\n");
      out.write("\t\t\t\t} else {\r\n");
      out.write("\t\t\t\t\tdocument.getElementById(elementid).style.display = 'none';\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t</script>\r\n");
      out.write("\t\t<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\r\n");
      out.write("\t</head>\r\n");
      out.write("\t<body>\r\n");
      out.write("\t\t<table align=\"center\" width=\"1000px\">\r\n");
      out.write("\t\t<tr><td>\r\n");
      out.write("\t\t\t<div style=\"float:left; font-family : Verdana, Arial, Helvetica, Geneva, sans-serif;padding:10px 10px 10px 10px\">\r\n");
      out.write("\t\t\t\t<h1 style=\"font-size:18px;\">\r\n");
      out.write("\t\t\t\t\tServer Tasks Monitor\r\n");
      out.write("\t\t\t\t</h1>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div style=\"float:right; font-family : Verdana, Arial, Helvetica, Geneva, sans-serif;padding:10px 10px 10px 10px\">\r\n");
      out.write("\t\t\t\t<a href=\"jobs.jsp\" style=\"font-size:12px\"> Reload Monitor </a>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</td></tr>\r\n");
      out.write("\t\t<tr><td>\r\n");
      out.write("\t\t<table align=\"center\" border=2 width=\"100%\" style=\"border:solid 1px #000000;\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tborder-collapse:collapse; \r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tfont-family : Verdana, Arial, Helvetica, Geneva, sans-serif; \r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tfont-size: 11px;color: #000000\">\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t");

				for (Iterator iter = map.keySet().iterator(); iter.hasNext(); ) {
					String groupName = (String) iter.next();
					List l = (List) map.get(groupName);
					String groupId = "addToGroup" +groupName;
			
      out.write("\r\n");
      out.write("\t\t\t\t<tr style=\"background:#CCCCCC\">\r\n");
      out.write("\t\t\t\t\t<td colspan=\"9\" style=\"padding:5px 5px 5px 5px\">\r\n");
      out.write("\t\t\t\t\t\t<div style=\"font-size: 14px;float:left\"><b>");
      out.print(groupName);
      out.write("</b></div>\r\n");
      out.write("\t\t\t\t\t\t<div style=\"font-size: 12px;float:right\"> <a href=\"javascript:showHide('");
      out.print(groupId);
      out.write("')\"> Add More Job To This Group </a></div>\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td align=\"center\"> \r\n");
      out.write("\t\t\t\t\t\t<b>Job Name</b>\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t<td align=\"center\">\r\n");
      out.write("\t\t\t\t\t\t<b>Job Class</b>\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t<td align=\"center\"> \r\n");
      out.write("\t\t\t\t\t\t<b>Parameters</b>\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t<td align=\"center\"> \r\n");
      out.write("\t\t\t\t\t\t<b>State</b>\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t<td align=\"center\"> \r\n");
      out.write("\t\t\t\t\t\t<b>Previous Start Date</b>\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t<td align=\"center\"> \r\n");
      out.write("\t\t\t\t\t\t<b>Next Start Date</b>\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t<td align=\"center\" colspan=\"3\"> \r\n");
      out.write("\t\t\t\t\t\t<b>Action</b>\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t<tr id=\"");
      out.print(groupId);
      out.write("\" style=\"display:none\"><td colspan=\"9\"> \r\n");
      out.write("\t\t\t\t\t<div style=\"border: solid 1px #000000;padding: 10px 10px 10px 10px\"> \r\n");
      out.write("\t\t\t\t\t\t");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "jobInput.jsp" + (("jobInput.jsp").indexOf('?')>0? '&': '?') + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("cmd", request.getCharacterEncoding())+ "=" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("addJob", request.getCharacterEncoding()) + "&" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("isGroup", request.getCharacterEncoding())+ "=" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("yes", request.getCharacterEncoding()) + "&" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("elementId", request.getCharacterEncoding())+ "=" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode(String.valueOf(groupId), request.getCharacterEncoding()) + "&" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("jobGroup", request.getCharacterEncoding())+ "=" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode(String.valueOf(groupName), request.getCharacterEncoding()), out, false);
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t</td></tr>\r\n");
      out.write("\t\t\t");
	
				  	for (int i = 0 ; i < l.size(); i++) {
				  		SchedulerHelper.QuartzJobDetailsWrapper job = (SchedulerHelper.QuartzJobDetailsWrapper) l.get(i);
				  		String jobN = job.getJobName().replaceAll(" ","");
				  		long interval = 0;
				  		if (job.getPreviousStartTime() != null)
				  			interval = (job.getNextStartTime().getTime() -job.getPreviousStartTime().getTime()) / 1000;
				  		
				  		Date start = job.getNextStartTime();
				  		java.text.Format formatter = new java.text.SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				  		String startDateStr = formatter.format(start);
				  		
				  		Map params = job.getParams();
				  		String p = "";
				  		String display_p = "";
				  		for (Iterator it = params.keySet().iterator(); it.hasNext(); ) {
				  			String key = (String) it.next();
				  			p += key + "=" + params.get(key) + "|";
				  			display_p += "<b>" + key + "</b>" + " = " + params.get(key);
				  			if (it.hasNext())
				  				display_p += "<br/>";
				  		}
				  		int state = job.getState();
				  		String stateStr = "";
				  		String background = ""; 
				  		if (state == Trigger.STATE_NORMAL) {
				  			stateStr = "Scheduled";
				  			background = "#9BF794";
				  		} else if (state == Trigger.STATE_PAUSED) {
				  			stateStr = "Paused";
				  			background = "#88A6E2";
				  		} else if (state == Trigger.STATE_ERROR) {
				  			stateStr = "Error";
				  			background = "#E28A8B";
				  		} else if (state == Trigger.STATE_COMPLETE) {
				  			stateStr = "Completed";
				  			background = "#CCCCCC";
				  		}
			
      out.write("\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td align=\"center\"> \r\n");
      out.write("\t\t\t\t\t\t");
      out.print(job.getJobName());
      out.write("\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t<td align=\"center\">\r\n");
      out.write("\t\t\t\t\t\t");
      out.print(job.getJobClass());
      out.write(" \r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t<td align=\"center\"> \r\n");
      out.write("\t\t\t\t\t\t");
      out.print(display_p);
      out.write("\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t<td align=\"center\" style=\"background:");
      out.print(background);
      out.write("\"> \r\n");
      out.write("\t\t\t\t\t\t");
      out.print(stateStr);
      out.write("\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t<td align=\"center\"> \r\n");
      out.write("\t\t\t\t\t\t");
      out.print(job.getPreviousStartTime());
      out.write("\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t<td align=\"center\"> \r\n");
      out.write("\t\t\t\t\t\t");
      out.print(job.getNextStartTime());
      out.write("\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t<td align=\"center\"> \r\n");
      out.write("\t\t\t\t\t\t");
 if (job.getState() == Trigger.STATE_NORMAL) { 
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t<a href=\"jobs.jsp?action=pauseJob&jobName=");
      out.print(job.getJobName());
      out.write("&jobGroup=");
      out.print(groupName);
      out.write("\"> Pause </a>\r\n");
      out.write("\t\t\t\t\t\t");
 } else if (job.getState() == Trigger.STATE_PAUSED) {
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t<a href=\"jobs.jsp?action=resumeJob&jobName=");
      out.print(job.getJobName());
      out.write("&jobGroup=");
      out.print(groupName);
      out.write("\"> Resume </a>\r\n");
      out.write("\t\t\t\t\t\t");
 } 
      out.write("\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t<td align=\"center\"> \r\n");
      out.write("\t\t\t\t\t\t<a href=\"jobs.jsp?action=removeJob&jobName=");
      out.print(job.getJobName());
      out.write("&jobGroup=");
      out.print(groupName);
      out.write("\"> Remove </a>\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t<td align=\"center\"> \r\n");
      out.write("\t\t\t\t\t\t<a href=\"javascript:showHide('");
      out.print(jobN);
      out.write("')\"> Edit </a>\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t<tr id=\"");
      out.print(jobN);
      out.write("\" style=\"display:none\"><td colspan=\"9\"> \r\n");
      out.write("\t\t\t\t\t<div style=\"border: solid 1px #000000;padding: 10px 10px 10px 10px\"> \r\n");
      out.write("\t\t\t\t\t\t");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "jobInput.jsp" + (("jobInput.jsp").indexOf('?')>0? '&': '?') + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("jobName", request.getCharacterEncoding())+ "=" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode(String.valueOf(job.getJobName()), request.getCharacterEncoding()) + "&" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("jobClass", request.getCharacterEncoding())+ "=" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode(String.valueOf(job.getJobClass()), request.getCharacterEncoding()) + "&" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("jobParam", request.getCharacterEncoding())+ "=" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode(String.valueOf(p), request.getCharacterEncoding()) + "&" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("jobInterval", request.getCharacterEncoding())+ "=" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode(String.valueOf(interval), request.getCharacterEncoding()) + "&" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("jobStartTime", request.getCharacterEncoding())+ "=" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode(String.valueOf(startDateStr), request.getCharacterEncoding()) + "&" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("cmd", request.getCharacterEncoding())+ "=" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("editJob", request.getCharacterEncoding()) + "&" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("isGroup", request.getCharacterEncoding())+ "=" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("yes", request.getCharacterEncoding()) + "&" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("elementId", request.getCharacterEncoding())+ "=" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode(String.valueOf(jobN), request.getCharacterEncoding()) + "&" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("jobGroup", request.getCharacterEncoding())+ "=" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode(String.valueOf(groupName), request.getCharacterEncoding()), out, false);
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t</td></tr>\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t");

				  	}
				}
			
      out.write("\r\n");
      out.write("\t\t\t<tr><td colspan=\"9\">\r\n");
      out.write("\t\t\t\t<div style=\"padding:5px 5px 5px 5px\"><a href=\"javascript:showHide('addNewJobGroup')\"> Add a New Job Group </a></div>\r\n");
      out.write("\t\t\t\t<div id=\"addNewJobGroup\" style=\"display:none;padding:10px 10px 10px 10px; border: solid 1px #000000\">\r\n");
      out.write("\t\t\t\t\t");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "jobInput.jsp" + (("jobInput.jsp").indexOf('?')>0? '&': '?') + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("cmd", request.getCharacterEncoding())+ "=" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("addJob", request.getCharacterEncoding()) + "&" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("elementId", request.getCharacterEncoding())+ "=" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("addNewJobGroup", request.getCharacterEncoding()), out, false);
      out.write("\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</td></tr>\r\n");
      out.write("\t\t</table>\r\n");
      out.write("\t\t</td></tr>\r\n");
      out.write("\t\t</table>\r\n");
      out.write("\t</body>\r\n");
      out.write("</html>");
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

<%@page import="app.Tasks.QuartzUtil.ServerScheduler"%>
<%@page import="app.Tasks.QuartzUtil.SchedulerHelper"%>
<%@page import="org.quartz.Scheduler"%>
<%@page import="org.quartz.Trigger"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Date"%>
<%

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
	
%>

<%@page import="org.apache.commons.lang.StringEscapeUtils"%>
<html>
	<head>
		<title> Job Monitor </title>
		<script type="text/javascript">
			function showHide(elementid){
				if (document.getElementById(elementid).style.display == 'none'){
					document.getElementById(elementid).style.display = '';
				} else {
					document.getElementById(elementid).style.display = 'none';
				}
			}
		</script>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	</head>
	<body>
		<table align="center" width="1000px">
		<tr><td>
			<div style="float:left; font-family : Verdana, Arial, Helvetica, Geneva, sans-serif;padding:10px 10px 10px 10px">
				<h1 style="font-size:18px;">
					Server Tasks Monitor
				</h1>
			</div>
			<div style="float:right; font-family : Verdana, Arial, Helvetica, Geneva, sans-serif;padding:10px 10px 10px 10px">
				<a href="jobs.jsp" style="font-size:12px"> Reload Monitor </a>
			</div>
		</td></tr>
		<tr><td>
		<table align="center" border=2 width="100%" style="border:solid 1px #000000;
													border-collapse:collapse; 
													font-family : Verdana, Arial, Helvetica, Geneva, sans-serif; 
													font-size: 11px;color: #000000">
			
			<%
				for (Iterator iter = map.keySet().iterator(); iter.hasNext(); ) {
					String groupName = (String) iter.next();
					List l = (List) map.get(groupName);
					String groupId = "addToGroup" +groupName;
			%>
				<tr style="background:#CCCCCC">
					<td colspan="9" style="padding:5px 5px 5px 5px">
						<div style="font-size: 14px;float:left"><b><%=groupName%></b></div>
						<div style="font-size: 12px;float:right"> <a href="javascript:showHide('<%=groupId%>')"> Add More Job To This Group </a></div>
					</td>
				</tr>
				<tr>
					<td align="center"> 
						<b>Job Name</b>
					</td>
					<td align="center">
						<b>Job Class</b>
					</td>
					<td align="center"> 
						<b>Parameters</b>
					</td>
					<td align="center"> 
						<b>State</b>
					</td>
					<td align="center"> 
						<b>Previous Start Date</b>
					</td>
					<td align="center"> 
						<b>Next Start Date</b>
					</td>
					<td align="center" colspan="3"> 
						<b>Action</b>
					</td>
				</tr>
				<tr id="<%=groupId%>" style="display:none"><td colspan="9"> 
					<div style="border: solid 1px #000000;padding: 10px 10px 10px 10px"> 
						<jsp:include page="jobInput.jsp">
							<jsp:param name="cmd" value="addJob"/>
							<jsp:param name="isGroup" value="yes"/>
							<jsp:param name="elementId" value="<%=groupId%>"/>
							<jsp:param name="jobGroup" value="<%=groupName%>"/>
						</jsp:include>
						
					</div>
				</td></tr>
			<%	
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
				  			display_p += "&bull;&nbsp;<b>" + key + "</b>" + " = " + StringEscapeUtils.escapeHtml((String)params.get(key));
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
			%>
				<tr>
					<td align="center"> 
						<%=job.getJobName()%>
					</td>
					<td align="center">
						<%=job.getJobClass()%> 
					</td>
					<td align="left"> 
						<%=display_p%>
					</td>
					<td align="center" style="background:<%=background%>"> 
						<%=stateStr%>
					</td>
					<td align="center"> 
						<%=job.getPreviousStartTime()%>
					</td>
					<td align="center"> 
						<%=job.getNextStartTime()%>
					</td>
					<td align="center"> 
						<% if (job.getState() == Trigger.STATE_NORMAL) { %>
							<a href="jobs.jsp?action=pauseJob&jobName=<%=job.getJobName()%>&jobGroup=<%=groupName%>"> Pause </a>
						<% } else if (job.getState() == Trigger.STATE_PAUSED) {%>
							<a href="jobs.jsp?action=resumeJob&jobName=<%=job.getJobName()%>&jobGroup=<%=groupName%>"> Resume </a>
						<% } %>
					</td>
					<td align="center"> 
						<a href="jobs.jsp?action=removeJob&jobName=<%=job.getJobName()%>&jobGroup=<%=groupName%>"> Remove </a>
					</td>
					<td align="center"> 
						<a href="javascript:showHide('<%=jobN%>')"> Edit </a>
					</td>
				</tr>
				<tr id="<%=jobN%>" style="display:none"><td colspan="9"> 
					<div style="border: solid 1px #000000;padding: 10px 10px 10px 10px"> 
						<jsp:include page="jobInput.jsp">
							<jsp:param name="jobName" value="<%=job.getJobName()%>"/>
							<jsp:param name="jobClass" value="<%=job.getJobClass()%>"/>
							<jsp:param name="jobParam" value="<%=p%>"/>
							<jsp:param name="jobInterval" value="<%=interval%>"/>
							<jsp:param name="jobStartTime" value="<%=startDateStr%>"/>
							<jsp:param name="cmd" value="editJob"/>
							<jsp:param name="isGroup" value="yes"/>
							<jsp:param name="elementId" value="<%=jobN%>"/>
							<jsp:param name="jobGroup" value="<%=groupName%>"/>
						</jsp:include>
						
					</div>
				</td></tr>
				
			<%
				  	}
				}
			%>
			<tr><td colspan="9">
				<div style="padding:5px 5px 5px 5px"><a href="javascript:showHide('addNewJobGroup')"> Add a New Job Group </a></div>
				<div id="addNewJobGroup" style="display:none;padding:10px 10px 10px 10px; border: solid 1px #000000">
					<jsp:include page="jobInput.jsp">
						<jsp:param name="cmd" value="addJob"/>
						<jsp:param name="elementId" value="addNewJobGroup"/>
					</jsp:include>
				</div>
			</td></tr>
		</table>
		</td></tr>
		</table>
	</body>
</html>
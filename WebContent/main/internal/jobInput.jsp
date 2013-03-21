<%
	String elementId = request.getParameter("elementId");
	String jName = request.getParameter("jobName");
	String jClass = request.getParameter("jobClass");
	String jGroup = request.getParameter("jobGroup");
	String jParam = request.getParameter("jobParam");
	String jInterval = request.getParameter("jobInterval");
	String jStart = request.getParameter("jobStartTime");
	String isGroup = request.getParameter("isGroup");
	String cmd = request.getParameter("cmd");
%>
	<div style="float:right;"><a href="javascript:showHide('<%=elementId%>')"> Hide this Tab </a> </div>
	<div style="float:left; font-size:14px"><b>Enter Job Information</b></div>
	<form action="jobs.jsp" method="post">
		<input type="hidden" name="action" value="<%=cmd%>"/>
		<br/> <b>Job Name:</b> <br/>
		<div><input name="jobName" type="text" value="<%=jName == null ? "" : jName%>"/></div>
							
		<br/> <b>Job Class:</b> <br/>
		<div><input name="jobClass" type="text" value="<%=jClass == null ? "" : jClass%>"/></div>
							
		<% if (isGroup!= null) { %>
			<input type="hidden" name="jobGroup" type="text" value="<%=jGroup == null ? "" : jGroup%>" />
		<% } else { %>
			<br/> <b>Job Group:</b> <br/>
			<div><input name="jobGroup" type="text" value="<%=jGroup == null ? "" : jGroup%>" /></div>
		<% } %>
					
		<br/> <b>Parameter:</b> <br/>
		<div><textarea name="jobParam" cols=40 rows=10><%=jParam == null ? "" : jParam%></textarea></div>
								
		<br/> <b>Start time  (YYYY/MM/DD HH:MM:SS AM):</b> <br/>
		<div><input name="jobStartTime" type="text" value="<%=jStart == null ? "" : jStart%>"/></div>
								
		<br/> <b>Interval (in seconds, -1 for non recurrent job):</b> <br/>
		<div><input name="jobInterval" type="text" value="<%=jInterval == null ? "" : jInterval%>"/></div>
							
		<br/>
		<div><input type ="submit" name="submit" value="  Submit  "/></div>
	</form>

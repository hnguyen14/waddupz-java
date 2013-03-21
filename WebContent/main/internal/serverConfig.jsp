
<%@page import="java.util.Iterator"%>
<%@page import="app.Utils.Config.ServerConfig"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Collection"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.Collections"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

<%
	
	String cmd = request.getParameter("cmd");
	if (cmd != null) {
		if ("modify".equalsIgnoreCase(cmd)) {
			String name = request.getParameter("key");
			String value = request.getParameter("val");
			ServerConfig.modifyProperty(name,value);
		} else if ("reset".equalsIgnoreCase(cmd)) {
			String name = request.getParameter("key");
			ServerConfig.resetProperty(name);
		}
	}

%>

<html>
	<head>
		<title>Server Configuration</title>
	</head>
	<body>
		<table align="center" width="1000px">
			<tr><td>
				<div style="float:left; font-family : Verdana, Arial, Helvetica, Geneva, sans-serif;padding:10px 10px 10px 10px">
					<h1 style="font-size:18px;">
						Server Configuration Panel
					</h1>
				</div>
			</td></tr>
			<tr><td>
				<table align="center" border=2 cellpadding=5 width="100%" style="border:solid 1px #000000;
															border-collapse:collapse; 
															font-family : Verdana, Arial, Helvetica, Geneva, sans-serif; 
															font-size: 11px;color: #000000;">
				<%
					Map<String, ServerConfig.Property> props = ServerConfig.PROPERTIES;
					List<String> keys = new ArrayList<String>(props.keySet());
					Collections.sort(keys);
				
					int count = 0;
					for (Iterator<String> iter = keys.iterator(); iter.hasNext(); ) {
						String name = iter.next();
						String value = props.get(name).getValue();
						boolean isInDb = props.get(name).isInDB();
						String style = "";
						if (count++ % 2 == 0)
							style = "style=\"background-color:#FFEDA5\"";
				%>
					<tr <%=style%>>
						<jsp:include flush="true" page="configForm.jsp">
							<jsp:param name="name" value="<%=name%>"/>
							<jsp:param name="value" value="<%=value%>"/>
							<jsp:param name="inDB" value="<%=String.valueOf(isInDb)%>"/>
						</jsp:include>
					</tr>
				<% } %>
				</table>
			</td></tr>
		</table>		
	</body>
</html>


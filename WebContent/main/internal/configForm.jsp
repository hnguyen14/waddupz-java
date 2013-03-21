<%
	String name = request.getParameter("name");
	String value = request.getParameter("value");
	String inDB = request.getParameter("inDB");
	boolean isInDB = "true".equalsIgnoreCase(inDB);
%>
<td width="40%"><b><%=name%><b></td>
<form action="serverConfig.jsp" method="post">
<input type="hidden" name="cmd" value="modify"/>
<input type="hidden" name="key" value="<%=name%>"/>
<td><input type="text" name="val" size="50" value="<%=value%>"/></td>
<td><input type="submit" name="submit" value="Modify"/></td>
</form>

<form action="serverConfig.jsp" method="post">
<input type="hidden" name="cmd" value="reset"/>
<input type="hidden" name="key" value="<%=name%>"/>
<td><input type="submit" name="submit" value="Reset To Default" <%= isInDB? "" : "disabled" %>/></td>
</form>
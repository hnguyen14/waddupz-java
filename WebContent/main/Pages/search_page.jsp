<html>
<%@ taglib uri="http://jakarta.apache.org/taglibs/i18n-1.0" prefix="i18n"  %>
	<% 
		String lang = request.getParameter("lang");
		String outputMode = request.getParameter("outputMode");
		if (outputMode == null)
			outputMode = "news";
		if (lang == null)
			lang = "en";
	 %>
<jsp:useBean id="builderBean" scope="request" class="app.API.Layout.ContentBuilderBean"/>
<%
	builderBean.setPageType(app.API.URL.StaticUrlFactory.SEARCH_PAGE);
	builderBean.initialize(pageContext);
%>

<head>
	<%=builderBean.getTitle()%>
	<%=builderBean.getMetaDescription()%>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" pageEncoding="utf-8"/>
	<%=builderBean.getPageCss()%>
</head>
		
<body>
	<i18n:bundle baseName="<%=builderBean.getResourceBundle()%>" id="bundle" scope="request"/>
	<table width="1000px" align="center">
		<tr><td>
			<jsp:include flush="true" page="/main/Layout/Header.jsp">
			<jsp:param name="lang" value="<%=lang%>"/>
			<jsp:param name="outputMode" value="<%=outputMode%>"/>
			</jsp:include>
		</td></tr>
		<tr><td>
			<jsp:include flush="true" page="/main/Layout/Body.jsp">
			<jsp:param name="outputMode" value="<%=outputMode%>"/>
			<jsp:param name="lang" value="<%=lang%>"/>
			</jsp:include>
		</td></tr>
		<tr><td>
			<jsp:include flush="true" page="/main/Layout/Footer.jsp">
			<jsp:param name="lang" value="<%=lang%>"/>
			</jsp:include>
		</td></tr>
	</table>
</body>

</html>
<%@page import="org.hibernate.*"%>
<%@page import="app.Entry.*"%>
<%@page import="app.Utils.*"%>
<html>
<jsp:useBean id="builderBean" scope="request" class="app.API.Layout.ContentBuilderBean"/>
<%
	builderBean.setPageType(app.API.URL.StaticUrlFactory.SINGLE_POST);
	builderBean.initialize(pageContext);
%>
<%@ taglib uri="http://jakarta.apache.org/taglibs/i18n-1.0" prefix="i18n"  %>
	<% 
		String lang = request.getParameter("lang");
		String outputMode = request.getParameter("outputMode");
		if (outputMode == null)
			outputMode = "news";
		if (lang == null)
			lang = "en";
			
		String cmd = request.getParameter("cmd");
		if (cmd != null) {
			EntryComment comment = new EntryComment();
			comment.setComment(	request.getParameter("comment"));
			comment.setAuthor(request.getParameter("name"));
			comment.setEmail(request.getParameter("email"));
			comment.setCommentDate(new java.util.Date(System.currentTimeMillis()));
			
			Session hib_session = HibernateUtil.getSessionFactory().openSession();
			Transaction t = hib_session.beginTransaction();
			
			AbstractEntry entry = builderBean.getEntry();
			entry.addComment(comment);
			entry.updateCache(app.API.Object.CacheableObject.CACHE_UPDATE);
			
			hib_session.update(entry);
			t.commit();
			hib_session.close();				 
		}
			
	 %>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" pageEncoding="utf-8"/>
	<%=builderBean.getTitle()%>
	<%=builderBean.getMetaDescription()%>
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
		<script type="text/javascript"><!--
			 amzn_cl_tag="hoangublo-20";
			 amzn_cl_border_color="84B2D7";
			//-->
		</script>
		<script type="text/javascript" src="http://cls.assoc-amazon.com/s/cls.js"></script>
		<tr><td>
			<jsp:include flush="true" page="/main/Layout/Footer.jsp">
			<jsp:param name="lang" value="<%=lang%>"/>
			</jsp:include>
		</td></tr>
	</table>
</body>

</html>
<%
	String lang = request.getParameter("lang");
	String outputMode = request.getParameter("outputMode");
	
	String news_url = app.Utils.UrlUtils.getFrontPageLink(lang,app.API.URL.StaticUrl.FrontPageStaticUrl.PAGE_TYPE_NEWS);
	String shop_url = app.Utils.UrlUtils.getFrontPageLink(lang,app.API.URL.StaticUrl.FrontPageStaticUrl.PAGE_TYPE_SHOP_DEALS);
	
	String rootPath = StaticUrlFactory.root_dir;
	String tab_bg = rootPath + "/main/imgs/button-bg.bmp";
	String tab_bg_inactive = rootPath + "/main/imgs/button-bg-inactive.bmp";
%>
<%@ taglib uri="http://jakarta.apache.org/taglibs/i18n-1.0" prefix="i18n"  %>
<%@page import="app.API.URL.StaticUrlFactory"%>
<jsp:useBean id="builderBean" scope="request" class="app.API.Layout.ContentBuilderBean"/>
<div id="tabs" > 
	<ul> 				
		<% if (outputMode.equalsIgnoreCase(app.API.URL.StaticUrl.FrontPageStaticUrl.PAGE_TYPE_NEWS)) { %>
				<li>
					<a style="background-image:url(<%=tab_bg%>); background-repeat: repeat x; border:1px solid #2D5B77;color:#FFF" href="<%=news_url%>">
						<b><i18n:message key="news"/></b>
					</a>
				</li> 
		<% } else { %>
			<li><a style="background-image:url(<%=tab_bg_inactive%>); background-repeat: repeat x;border:1px solid #2D5B77" href="<%=news_url%>"><i18n:message key="news"/></a></li> 
		<% } %>
	</ul>
	<ul> 				
		<% if (outputMode.equalsIgnoreCase(app.API.URL.StaticUrl.FrontPageStaticUrl.PAGE_TYPE_SHOP_DEALS)) { %>
				<li>
					<a style="background-image:url(<%=tab_bg%>); background-repeat: repeat x; border:1px solid #2D5B77;color:#FFF" href="<%=shop_url%>">
						<b><i18n:message key="shop"/></b>
					</a>
				</li> 
		<% } else { %>
			<li><a style="background-image:url(<%=tab_bg_inactive%>); border:1px solid #2D5B77" href="<%=shop_url%>"><i18n:message key="shop"/></a></li> 
		<% } %>
	</ul> 
</div>
<div id="main" > 
	<div id="subtabs" >
		<%=builderBean.getSubTabContent()%>
	</div> 
	<div id="bodyarea" > 
		<table width="99%" align="center">
			<tr>
				<td id="newsContainer" valign="top">
					<jsp:include flush="true" page="/main/Layout/Content.jsp">
						<jsp:param name="outputMode" value="<%=outputMode%>"/>
						<jsp:param name="lang" value="<%=lang%>"/>
					</jsp:include>
				</td>
				<td id="adSpot" align="right" valign="top">
					<jsp:include flush="true" page="/main/Layout/Ad.jsp"/>
				</td>
			</tr>
		</table>
	</div>
</div>
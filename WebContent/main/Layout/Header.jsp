<%@ taglib uri="http://jakarta.apache.org/taglibs/i18n-1.0" prefix="i18n"  %>
<%@page import="app.API.URL.StaticUrl.*"%>
<%
	String lang = request.getParameter("lang");
	String other_lang = "vi";
	if (lang.equalsIgnoreCase("vi"))
		other_lang = "en";
	String outputMode = request.getParameter("outputMode");
	String en_front = UrlUtils.getPageUrl(app.Utils.UrlUtils.FRONT_PAGE,"en");
	String vi_front = UrlUtils.getPageUrl(app.Utils.UrlUtils.FRONT_PAGE,"vi");
	
	String requestURL = request.getRequestURL().toString();	
	String other_language_link = UrlUtils.getFrontPageLink(other_lang,outputMode);
	
	String serverPath = StaticUrlFactory.root_dir;
	String serverLogoUrl = serverPath + ConfigUtils.getAppProperty("server.img.logo");
	String searchJsp = serverPath + ConfigUtils.getAppProperty("server.jsp.searchJsp");

%>
<%@page import="app.Utils.UrlUtils"%>
<%@page import="app.API.URL.StaticUrlFactory"%>
<%@page import="app.Utils.ConfigUtils"%>
<div>
<table width="100%" align="center">
	<tr >
		<td	width="50%" align="left">
			<img src="<%=serverLogoUrl%>"/>
		</td>
		<td width="50%" align="right" height="10px">
			<form action="<%=searchJsp%>">
				<table bgcolor="#ffffff">
				<tr><td nowrap="nowrap" valign="top" align="left">
				<input type="text" name="q" size="31" maxlength="255" value="" id="sbi"></input>
				<input type="hidden" name="lang" value="<%=lang%>"/>
				<select name="outputMode">
					<option  value="<%=FrontPageStaticUrl.PAGE_TYPE_NEWS%>" 
						<%=outputMode.equalsIgnoreCase(FrontPageStaticUrl.PAGE_TYPE_NEWS) ? "selected" : "" %>>News</option>
					<option  value="<%=FrontPageStaticUrl.PAGE_TYPE_SHOP_DEALS%>"
						<%=outputMode.equalsIgnoreCase(FrontPageStaticUrl.PAGE_TYPE_SHOP_DEALS) ? "selected" : "" %>>Shopping Deals</option>
				</select>
				<input type="submit" name="sa" value="Search" id="sbb"></input>
				</td></tr></table>
			</form>
			
		</td>
	</tr>
	<tr>
		<td colspan="2" width="50%" align="right">
			<p class="menu_header" style="font-size:14px">
				<% if  (lang.equalsIgnoreCase("en")) { %>
					<i18n:message key="english"/> | <a href="<%=other_language_link%>"><i18n:message key="vietnamese"/></a>
				<% }  else if (lang.equalsIgnoreCase("vi")) {%>
					<a href="<%=other_language_link%>"><i18n:message key="english"/></a> | <i18n:message key="vietnamese"/>
				<% } %>
			</p>
		</td>
	</tr>
</table>
</div>
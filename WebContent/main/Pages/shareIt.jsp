<%@page import="app.Entry.*"%>
<%@page import="app.Utils.*"%>
<%@page import="org.hibernate.*"%>
<%@page import="app.API.Search.Keyword.*"%>
<%@page import="java.util.Date"%>
<%@page import="app.API.URL.StaticUrlFactory"%>
<html>
<%@ taglib uri="http://jakarta.apache.org/taglibs/i18n-1.0" prefix="i18n"  %>
	<% 
		String lang = request.getParameter("lang");
		String outputMode = request.getParameter("outputMode");
		
		if (outputMode == null)
			outputMode = "news";
		if (lang == null)
			lang = "en";
		String news_url = app.Utils.UrlUtils.getFrontPageLink(lang,app.API.URL.StaticUrl.FrontPageStaticUrl.PAGE_TYPE_NEWS);
		String shop_url = app.Utils.UrlUtils.getFrontPageLink(lang,app.API.URL.StaticUrl.FrontPageStaticUrl.PAGE_TYPE_SHOP_DEALS);
		
		
		String cmd = request.getParameter("cmd");
		System.out.println(cmd);
		
		boolean donePost = false;
		if (cmd != null) {
			String name  = request.getParameter("authorName");
			String email  = request.getParameter("email");
			String isNews = request.getParameter("isNews");
			String isShop  = request.getParameter("isShop");
			String title  = request.getParameter("title");
			String url  = request.getParameter("url");
			
			if (!url.startsWith("http://") && !url.startsWith("https://"))
				url = "http://" + url;
				
			String newsSource = url.replaceAll("http://", "").replaceAll("https://", "");
			int idx = newsSource.indexOf("/");
			if (idx > 0)
				newsSource = newsSource.substring(0,idx);				
				
			String desc  = request.getParameter("desc");
			String postLang  = request.getParameter("postLang");
			String listedPrice  = request.getParameter("listedPrice");
			String salePrice  = request.getParameter("price");
			String rebate  = request.getParameter("rebate");
			String moneyUnit  = request.getParameter("moneyUnit");
			String imageLink  = request.getParameter("imageLink");
			String couponCode = "N/A";
			Date startDate = null;
			Date endDate = null;
			String cate = "N/A";
			
			double lp = (listedPrice != null && listedPrice.length()>0) ? Double.valueOf(listedPrice):0;
			double sp = (salePrice != null && salePrice.length()>0) ? Double.valueOf(salePrice):0;
			double rb = (rebate != null && rebate.length()>0) ? Double.valueOf(rebate):0;
			
			System.out.println(name + " - " + email + " - " +  isNews + " - " + 
			isShop+ " - " + title+ " - " + url+ " - " + desc+ " - " + 
			postLang+ " - " + listedPrice+ " - " + salePrice+ " - " + rebate+ " - " + 
			moneyUnit+ " - " + imageLink);
			Session hib_session = HibernateUtil.getSessionFactory().openSession();
			Transaction t = hib_session.beginTransaction();
			Query query = hib_session.createQuery("from AbstractEntry as entry where entry.url=?");
			query.setString(0, url);

			java.util.List results = query.list();
		
			if (results != null || results.size() <= 0 ) {			
				AbstractEntry entry = null;
				
				
				String cat = "";
				
				/*if (isNews != null ) {
					entry = new NewsEntry(title,url,name,new java.util.Date(System.currentTimeMillis()),0,newsSource,desc);
					cat = "news";
				} else if (isShop != null) {
					entry = new ShoppingDealsEntry(title,url,name,new java.util.Date(System.currentTimeMillis()),0,desc,sp,lp,rb,moneyUnit,imageLink);
					cat = "shopping-deals";
				}*/
				
				java.util.List tag = TextUtils.extractKeyword(title + " " + desc);
				if (tag != null && tag.size() > 0) {
					for (int i = 0 ; i < tag.size(); i++) {
						Phrase p = new Phrase( ((String) tag.get(i)).trim(),cat,postLang,true) ;
					}
				}
				entry.setLanguage(postLang);
				hib_session.save(entry);
			}
			t.commit();
			hib_session.close();
			donePost = true;
			
		}
		
	 %>

<jsp:useBean id="builderBean" scope="request" class="app.API.Layout.ContentBuilderBean"/>
<%
	builderBean.initialize(pageContext);
	builderBean.setPageType(app.API.URL.StaticUrlFactory.FRONT_PAGE);
%>
<head>
	<title>TELL EVERYONE WADDUPZ ON THE NET</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" pageEncoding="utf-8"/>
	<%=builderBean.getPageCss()%>
</head>
<script language="javascript">
function enableShoppingField() {
	document.getElementById('rp').style.display = '';
	document.getElementById('sp').style.display = '';
	document.getElementById('rb').style.display = '';
	document.getElementById('img').style.display = '';
	document.getElementById('mu').style.display = '';
	myForm.news.checked=false;
	
}
function disableShoppingField() {
	document.getElementById('rp').style.display = 'none';
	document.getElementById('sp').style.display = 'none';
	document.getElementById('rb').style.display = 'none';
	document.getElementById('img').style.display = 'none';
	document.getElementById('mu').style.display = 'none';
	myForm.shop.checked=false;
}
</script>
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
		<div id="tabs" > 
			<ul> 
				<li>
					<a style="background: #A8D0F6;border: 1px solid #666;" href="http://www.waddupz.com">
						<b>WADDUP !?</b>
					</a>
				</li> 				
			</ul>
		</div>
		<div id="main" > 
			<% if (!donePost) { %>
			<form name="myForm" action="<%=StaticUrlFactory.root_dir + ConfigUtils.getAppProperty("server.jsp.vote") %>" method="post">
				<input type="hidden" name="cmd" value="add"/>
				<input name="lang" type="hidden" value="<%=lang%>"/>
				<input name="outputMode" type="hidden" value="<%=outputMode%>"/>
			<table width="80%">
				
				<tr>
					<td width="100px" align="right"><b>Name:   </b></td>
					<td><input type="text" name="authorName"/></td>
				</tr>
				<tr>
					<td width="100px" align="right"><b>Email:   </b></td>
					<td><input type="text" name="email"/></td>
				</tr>
				
				<tr>
					<td width="100px" align="right"><b>Category:   </b></td>
					<td>						
						News <input id="news" type="radio" name="isNews" onclick="disableShoppingField()" <%=outputMode.equalsIgnoreCase("news") ? "checked":""%>/>
						Shopping <input id="shop" type="radio" name="isShop" onclick="enableShoppingField()"<%=outputMode.equalsIgnoreCase("shopping-deals") ? "checked":""%>/>
					</td>
				</tr>
			
				<tr>
					<td width="100px" align="right"><b>Title:   </b></td>
					<td><input type="text" name="title"/></td>
				</tr>
				<tr>
					<td width="100px" align="right"><b>Url:   </b></td>
					<td><input type="text" name="url"/></td>
				</tr>
				<tr>
					<td width="100px" align="right" valign="top"><b>Description:   </b></td>
					<td> <textarea rows=10 cols=80 name="desc"> </textarea></td>
				</tr>
				<tr>
					<td width="100px" align="right"><b>Language:   </b></td>
					<td>
						<select name="postLang">
							<option value="en"> English	</option>
							<option value="vi"> Vietnamese</option>
						</select>
					</td>
				</tr>
								
				<tr id="rp" <%=!outputMode.equalsIgnoreCase("shopping-deals") ? "style=\"display:none\"":""%>>
					<td width="100px" align="right" ><b>Regular Price:   </b></td>
					<td><input id="lp" type="text" name="listedPrice" /></td>
				</tr>
				<tr id="sp" <%=!outputMode.equalsIgnoreCase("shopping-deals") ? "style=\"display:none\"":""%>>
					<td width="100px" align="right" ><b>Sale Price:   </b></td>
					<td><input id="p" type="text" name="price" /></td>
				</tr>
				<tr id="rb" <%=!outputMode.equalsIgnoreCase("shopping-deals") ? "style=\"display:none\"":""%>>
					<td width="100px" align="right" ><b>Rebate:   </b></td>
					<td><input id="r" type="text" name="rebate" /></td>
				</tr>
				<tr id="mu" <%=!outputMode.equalsIgnoreCase("shopping-deals") ? "style=\"display:none\"":""%>>
					<td width="100px" align="right" ><b>Monetary Unit:   </b></td>
					<td><input id="mu" type="text" name="moneyUnit" /></td>
				</tr>
				<tr id="img" <%=!outputMode.equalsIgnoreCase("shopping-deals") ? "style=\"display:none\"":""%>>
					<td width="100px" align="right"><b>Image Link:   </b></td>
					<td><input id="il" type="text" name="imageLink" /></td>
				</tr>
				
			<!--	<tr>
					<td width="100px" align="right"><b>Tag:   </b></td>
					<td><input type="text" name="tag"/></td>
				</tr>-->
					
				<tr><td></td><td ><input type="submit" name="Submit" value="   Submit  "/></tr>
			</table>
			</form>
			<% } else { %>
				<div align="center" style="padding:30px 30px 30px 30px">
					<i18n:message key="donePost"/>
				</div>
			<% } %>
		</div>
		</td></tr>
		
		<tr><td>
			<jsp:include flush="true" page="/main/Layout/Footer.jsp">
			<jsp:param name="lang" value="<%=lang%>"/>
			</jsp:include>
		</td></tr>
	</table>
</body>

</html>
<jsp:useBean id="builderBean" scope="request" class="app.API.Layout.ContentBuilderBean"/>
<%
	String query = request.getParameter("q");
	String output = request.getParameter("outputMode");
	int pageType = builderBean.getPageType();
	String title = builderBean.getEntryTitle();
	title = title.replaceAll(" ","%20");
%>
<br/>
	<% if (output.equalsIgnoreCase(app.API.URL.StaticUrl.FrontPageStaticUrl.PAGE_TYPE_SHOP_DEALS)) { %>
	<!--<SCRIPT charset="utf-8" type="text/javascript" src="http://ws.amazon.com/widgets/q?ServiceVersion=20070822&MarketPlace=US&ID=V20070822/US/hoangublo-20/8002/d3b22c9a-b8f5-43e7-a13c-45762cb22bc8"> </SCRIPT>-->
	<jsp:include page="/main/Layout/AdPages/CouponAds.jsp">
		<jsp:param name="numResult" value="20"/>
	</jsp:include>
	<%  }else if (output.equalsIgnoreCase(app.API.URL.StaticUrl.FrontPageStaticUrl.PAGE_TYPE_NEWS))  {%>
	<jsp:include page="/main/Layout/AdPages/AmazonTagCloud.jsp"/>
	<br/><br/>
	<jsp:include page="/main/Layout/AdPages/GoogleAdsense.jsp"/>
	<% } %>
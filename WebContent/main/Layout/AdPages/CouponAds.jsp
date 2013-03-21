<%@ page import = "app.API.Search.*"%>
<%@ page import = "app.Entry.*"%>
<%@ taglib uri="http://jakarta.apache.org/taglibs/i18n-1.0" prefix="i18n"  %>
<%@page import="app.API.URL.StaticUrlFactory"%>
<%@page import="app.Utils.ConfigUtils"%>
<script type="text/javascript">
			function showHide(elementid){
				if (document.getElementById(elementid).style.display == 'none'){
					document.getElementById(elementid).style.display = '';
				} else {
					document.getElementById(elementid).style.display = 'none';
				}
			}
		</script>
<table width="100%" style="border:1px solid #777777">
	<tr><td align="center" id="ad_header">
		<b><i18n:message key="coupon"/></b>
	</td></tr>
	
<% 
try {
	System.out.println();
	Searcher s = new Searcher(AbstractEntry.COUPONS_ENTRY);
	s.setParam(Searcher.PARAM_LANG, "en");
	s.setParam(Searcher.PARAM_COUPON_CAT, request.getParameter("outputMode"));
	s.setParam(Searcher.PARAM_NUMRESULT, request.getParameter("numResult"));

	java.util.List l = s.search();
	String expandImg = StaticUrlFactory.root_dir + ConfigUtils.getAppProperty("server.img.expandButton");
	for (int i = 0 ; i < l.size(); i++) {
		AbstractEntry entry = (AbstractEntry) l.get(i);
		String entry_id = "couponEntry"+i;
		String coupon_code = ((CouponEntry) entry).getCouponCode();
%>
	<tr><td>
		<div class="ad_link" style="font-size:13px; color:#000;">
			<img src="<%=StaticUrlFactory.root_dir + ConfigUtils.getAppProperty("server.img.expandButton")%>" onclick="javascript:showHide('<%=entry_id%>')"/>&nbsp;<a rel="nofollow" href="<%=entry.getUrl()%>" style="font:12px" ><%=entry.getTitle()%></a>
			<div id="<%=entry_id%>" style="display:none; border:1px solid #CCCCCC; padding:3px 3px 3px 3px"/>
				<b>Post Date: </b> <%=entry.getCreatedDate().toString()%> <br/>
				<b>Summary: </b> <%=entry.getSummary()%> <br/>
				<% if (coupon_code != null && coupon_code.length() >1) {%>
					<b>Coupon Code: </b> <%=coupon_code%> <br/>
				<% } %>
			</div>
		</div>
		
	</td></tr>
<%
	}
}catch (Exception e) {
	e.printStackTrace();
}
%>
	
</table>
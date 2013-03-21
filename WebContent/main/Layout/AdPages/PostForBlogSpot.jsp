<%@ page import = "app.API.Search.*"%>
<%@ page import = "app.Entry.*"%>
<%@ taglib uri="http://jakarta.apache.org/taglibs/i18n-1.0" prefix="i18n"  %>

<% 
try {
	System.out.println();
	Searcher s = new Searcher(Searcher.ENTRY_SEARCH);
	s.setParam(Searcher.PARAM_KEYWORD, request.getParameter("q"));
	s.setParam(Searcher.PARAM_LANG, "en");
	s.setParam(Searcher.PARAM_CATEGORY, "app.Entry.ShoppingDealsEntry");
	s.setParam(Searcher.PARAM_NUMRESULT, "30");

	java.util.List l = s.search();
	for (int i = 0 ; i < l.size(); i++) {
		AbstractEntry entry = (AbstractEntry) l.get(i); 
		ShoppingDealsEntry sde = (ShoppingDealsEntry) entry;
		String ret_val = "";
		ret_val += "<tr><td>";
		ret_val += "<div style=\"font-size:18px\">";
		ret_val += "<a href=\"" + sde.getUrl() + "\"><b>" + sde.getTitle() + "</b></a>";
		ret_val += "</div>";
		ret_val += "<div>";
		ret_val += "<div style=\"float:left;width:110px\">";
		ret_val += "<a href=\"" + sde.getUrl() + "\">" +
					"<img src=\"" + sde.getImageLink() + "\" border=0 width=\"100px\" height=\"100px\"/></a>";
		ret_val += "</div>";
		ret_val += "<div align=\"left\">";
		ret_val += sde.getSummary() + "<br/>";
		ret_val += "<span><b>Price :</b></span><b><font color=\"red\">$" +sde.getPrice() + " (" + sde.getMonetaryUnit() + ")" +
					"</font></b><br/>";
		ret_val += "&#32;&#32;<a href=\"" + sde.getUrl() + "\"> Go to Deal Page  >> </a>";
		ret_val += "</div>";
		ret_val +="</div>";
		ret_val += "</td></tr>\n";

%>
		<%=ret_val%>
		<textarea cols="80" rows="40"><%=org.apache.commons.lang.StringEscapeUtils.escapeHtml(ret_val)%>
		</textarea>
<%
	}

}catch (Exception e) {
	e.printStackTrace();
}
%>
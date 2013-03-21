<%@ page import = "app.API.Search.*"%>
<%@ page import = "app.Entry.*"%>
<%@ taglib uri="http://jakarta.apache.org/taglibs/i18n-1.0" prefix="i18n"  %>

<% 
try {
	System.out.println();
	Searcher s = new Searcher(Searcher.ENTRY_SEARCH);
	s.setParam(Searcher.PARAM_LANG, "en");
	s.setParam(Searcher.PARAM_CATEGORY, "app.Entry.CouponEntry");
	s.setParam(Searcher.PARAM_COUPON_CAT, "shopping-deals");
	s.setParam(Searcher.PARAM_NUMRESULT, "10");

	java.util.List l = s.search();
	String HTML = "<ul>\n";
	for (int i = 0 ; i < l.size(); i++) {
		AbstractEntry entry = (AbstractEntry) l.get(i);
		HTML  += "\t<li>\n";
		HTML += "\t\t<a rel=\"nofollow\" href=\"" + entry.getUrl()+ "\" style=\"font:12px\" >" + entry.getTitle() + "</a>\n";
		HTML  += "\t</li>\n";
	}
	HTML += "</ul>";
	HTML = org.apache.commons.lang.StringEscapeUtils.escapeHtml(HTML);
%>
	<%=HTML%>
<%
}catch (Exception e) {
	e.printStackTrace();
}
%>
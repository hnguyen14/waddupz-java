package org.apache.jsp.main.Layout.AdPages;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import app.API.Search.*;
import app.Entry.*;
import app.API.URL.StaticUrlFactory;
import app.Utils.ConfigUtils;

public final class CouponAds_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static java.util.Vector _jspx_dependants;

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_i18n_message_key_nobody;

  public java.util.List getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_i18n_message_key_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_i18n_message_key_nobody.release();
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    JspFactory _jspxFactory = null;
    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      _jspxFactory = JspFactory.getDefaultFactory();
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\t\t\tfunction showHide(elementid){\r\n");
      out.write("\t\t\t\tif (document.getElementById(elementid).style.display == 'none'){\r\n");
      out.write("\t\t\t\t\tdocument.getElementById(elementid).style.display = '';\r\n");
      out.write("\t\t\t\t} else {\r\n");
      out.write("\t\t\t\t\tdocument.getElementById(elementid).style.display = 'none';\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t</script>\r\n");
      out.write("<table width=\"100%\" style=\"border:1px solid #777777\">\r\n");
      out.write("\t<tr><td align=\"center\" id=\"ad_header\">\r\n");
      out.write("\t\t<b>");
      if (_jspx_meth_i18n_message_0(_jspx_page_context))
        return;
      out.write("</b>\r\n");
      out.write("\t</td></tr>\r\n");
      out.write("\t\r\n");
 
try {
	System.out.println();
	Searcher s = new Searcher(Searcher.ENTRY_SEARCH);
	s.setParam(Searcher.PARAM_LANG, "en");
	s.setParam(Searcher.PARAM_CATEGORY, "app.Entry.CouponEntry");
	s.setParam(Searcher.PARAM_COUPON_CAT, request.getParameter("outputMode"));
	s.setParam(Searcher.PARAM_NUMRESULT, request.getParameter("numResult"));

	java.util.List l = s.search();
	String expandImg = StaticUrlFactory.root_dir + ConfigUtils.getAppProperty("server.img.expandButton");
	for (int i = 0 ; i < l.size(); i++) {
		AbstractEntry entry = (AbstractEntry) l.get(i);
		String entry_id = "couponEntry"+i;
		String coupon_code = ((CouponEntry) entry).getCouponCode();

      out.write("\r\n");
      out.write("\t<tr><td>\r\n");
      out.write("\t\t<div class=\"ad_link\" style=\"font-size:13px; color:#000;\">\r\n");
      out.write("\t\t\t<img src=\"");
      out.print(StaticUrlFactory.root_dir + ConfigUtils.getAppProperty("server.img.expandButton"));
      out.write("\" onclick=\"javascript:showHide('");
      out.print(entry_id);
      out.write("')\"/>&nbsp;<a rel=\"nofollow\" href=\"");
      out.print(entry.getUrl());
      out.write("\" style=\"font:12px\" >");
      out.print(entry.getTitle());
      out.write("</a>\r\n");
      out.write("\t\t\t<div id=\"");
      out.print(entry_id);
      out.write("\" style=\"display:none; border:1px solid #CCCCCC; padding:3px 3px 3px 3px\"/>\r\n");
      out.write("\t\t\t\t<b>Post Date: </b> ");
      out.print(entry.getCreatedDate().toString());
      out.write(" <br/>\r\n");
      out.write("\t\t\t\t<b>Summary: </b> ");
      out.print(entry.getSummary());
      out.write(" <br/>\r\n");
      out.write("\t\t\t\t");
 if (coupon_code != null && coupon_code.length() >1) {
      out.write("\r\n");
      out.write("\t\t\t\t\t<b>Coupon Code: </b> ");
      out.print(coupon_code);
      out.write(" <br/>\r\n");
      out.write("\t\t\t\t");
 } 
      out.write("\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t\r\n");
      out.write("\t</td></tr>\r\n");

	}
}catch (Exception e) {
	e.printStackTrace();
}

      out.write("\r\n");
      out.write("\t\r\n");
      out.write("</table>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      if (_jspxFactory != null) _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_i18n_message_0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  i18n:message
    org.apache.taglibs.i18n.MessageTag _jspx_th_i18n_message_0 = (org.apache.taglibs.i18n.MessageTag) _jspx_tagPool_i18n_message_key_nobody.get(org.apache.taglibs.i18n.MessageTag.class);
    _jspx_th_i18n_message_0.setPageContext(_jspx_page_context);
    _jspx_th_i18n_message_0.setParent(null);
    _jspx_th_i18n_message_0.setKey("coupon");
    int _jspx_eval_i18n_message_0 = _jspx_th_i18n_message_0.doStartTag();
    if (_jspx_th_i18n_message_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_i18n_message_key_nobody.reuse(_jspx_th_i18n_message_0);
    return false;
  }
}

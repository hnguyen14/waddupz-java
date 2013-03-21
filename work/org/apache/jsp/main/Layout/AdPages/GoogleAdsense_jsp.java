package org.apache.jsp.main.Layout.AdPages;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class GoogleAdsense_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static java.util.Vector _jspx_dependants;

  public java.util.List getDependants() {
    return _jspx_dependants;
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

      out.write("<table width=\"100%\" style=\"border:1px solid #777777\">\r\n");
      out.write("\t<tr><td align=\"center\" id=\"ad_header\">\r\n");
      out.write("\t\t<b>Sponsor Links</b>\r\n");
      out.write("\t</td></tr>\r\n");
      out.write("\t<tr><td style=\"height:250px\">\r\n");
      out.write("\t\t<script type=\"text/javascript\"><!--\r\n");
      out.write("\t\t\tgoogle_ad_client = \"pub-4975641487974010\";\r\n");
      out.write("\t\t\t/* 300x250, created 4/8/08 */\r\n");
      out.write("\t\t\tgoogle_ad_slot = \"5527197388\";\r\n");
      out.write("\t\t\tgoogle_ad_width = 300;\r\n");
      out.write("\t\t\tgoogle_ad_height = 250;\r\n");
      out.write("\t\t\t//-->\r\n");
      out.write("\t\t\t</script>\r\n");
      out.write("\t\t\t<script type=\"text/javascript\"\r\n");
      out.write("\t\t\tsrc=\"http://pagead2.googlesyndication.com/pagead/show_ads.js\">\r\n");
      out.write("\t\t</script>\r\n");
      out.write("\t</td></tr>\r\n");
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
}

package org.apache.jsp.main.Layout;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class Ad_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      app.API.Layout.ContentBuilderBean builderBean = null;
      synchronized (request) {
        builderBean = (app.API.Layout.ContentBuilderBean) _jspx_page_context.getAttribute("builderBean", PageContext.REQUEST_SCOPE);
        if (builderBean == null){
          builderBean = new app.API.Layout.ContentBuilderBean();
          _jspx_page_context.setAttribute("builderBean", builderBean, PageContext.REQUEST_SCOPE);
        }
      }
      out.write('\r');
      out.write('\n');

	String query = request.getParameter("q");
	String output = request.getParameter("outputMode");
	int pageType = builderBean.getPageType();
	String title = builderBean.getEntryTitle();
	title = title.replaceAll(" ","%20");

      out.write("\r\n");
      out.write("<br/>\r\n");
      out.write("\t");
 if (output.equalsIgnoreCase(app.API.URL.StaticUrl.FrontPageStaticUrl.PAGE_TYPE_SHOP_DEALS)) { 
      out.write("\r\n");
      out.write("\t<!--<SCRIPT charset=\"utf-8\" type=\"text/javascript\" src=\"http://ws.amazon.com/widgets/q?ServiceVersion=20070822&MarketPlace=US&ID=V20070822/US/hoangublo-20/8002/d3b22c9a-b8f5-43e7-a13c-45762cb22bc8\"> </SCRIPT>-->\r\n");
      out.write("\t");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/main/Layout/AdPages/CouponAds.jsp" + (("/main/Layout/AdPages/CouponAds.jsp").indexOf('?')>0? '&': '?') + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("numResult", request.getCharacterEncoding())+ "=" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("20", request.getCharacterEncoding()), out, false);
      out.write('\r');
      out.write('\n');
      out.write('	');
  }else if (output.equalsIgnoreCase(app.API.URL.StaticUrl.FrontPageStaticUrl.PAGE_TYPE_NEWS))  {
      out.write('\r');
      out.write('\n');
      out.write('	');
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/main/Layout/AdPages/AmazonTagCloud.jsp", out, false);
      out.write("\r\n");
      out.write("\t<br/><br/>\r\n");
      out.write("\t");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/main/Layout/AdPages/GoogleAdsense.jsp", out, false);
      out.write('\r');
      out.write('\n');
      out.write('	');
 } 
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

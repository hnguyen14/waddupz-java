package org.apache.jsp.main.Layout;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import app.API.URL.StaticUrlFactory;

public final class Body_jsp extends org.apache.jasper.runtime.HttpJspBase
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


	String lang = request.getParameter("lang");
	String outputMode = request.getParameter("outputMode");
	
	String news_url = app.Utils.UrlUtils.getFrontPageLink(lang,app.API.URL.StaticUrl.FrontPageStaticUrl.PAGE_TYPE_NEWS);
	String shop_url = app.Utils.UrlUtils.getFrontPageLink(lang,app.API.URL.StaticUrl.FrontPageStaticUrl.PAGE_TYPE_SHOP_DEALS);
	
	String rootPath = StaticUrlFactory.root_dir;
	String tab_bg = rootPath + "/main/imgs/button-bg.bmp";
	String tab_bg_inactive = rootPath + "/main/imgs/button-bg-inactive.bmp";

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      app.API.Layout.ContentBuilderBean builderBean = null;
      synchronized (request) {
        builderBean = (app.API.Layout.ContentBuilderBean) _jspx_page_context.getAttribute("builderBean", PageContext.REQUEST_SCOPE);
        if (builderBean == null){
          builderBean = new app.API.Layout.ContentBuilderBean();
          _jspx_page_context.setAttribute("builderBean", builderBean, PageContext.REQUEST_SCOPE);
        }
      }
      out.write("\r\n");
      out.write("<div id=\"tabs\" > \r\n");
      out.write("\t<ul> \t\t\t\t\r\n");
      out.write("\t\t");
 if (outputMode.equalsIgnoreCase(app.API.URL.StaticUrl.FrontPageStaticUrl.PAGE_TYPE_NEWS)) { 
      out.write("\r\n");
      out.write("\t\t\t\t<li>\r\n");
      out.write("\t\t\t\t\t<a style=\"background-image:url(");
      out.print(tab_bg);
      out.write("); background-repeat: repeat x; border:1px solid #2D5B77;color:#FFF\" href=\"");
      out.print(news_url);
      out.write("\">\r\n");
      out.write("\t\t\t\t\t\t<b>");
      if (_jspx_meth_i18n_message_0(_jspx_page_context))
        return;
      out.write("</b>\r\n");
      out.write("\t\t\t\t\t</a>\r\n");
      out.write("\t\t\t\t</li> \r\n");
      out.write("\t\t");
 } else { 
      out.write("\r\n");
      out.write("\t\t\t<li><a style=\"background-image:url(");
      out.print(tab_bg_inactive);
      out.write("); background-repeat: repeat x;border:1px solid #2D5B77\" href=\"");
      out.print(news_url);
      out.write('"');
      out.write('>');
      if (_jspx_meth_i18n_message_1(_jspx_page_context))
        return;
      out.write("</a></li> \r\n");
      out.write("\t\t");
 } 
      out.write("\r\n");
      out.write("\t</ul>\r\n");
      out.write("\t<ul> \t\t\t\t\r\n");
      out.write("\t\t");
 if (outputMode.equalsIgnoreCase(app.API.URL.StaticUrl.FrontPageStaticUrl.PAGE_TYPE_SHOP_DEALS)) { 
      out.write("\r\n");
      out.write("\t\t\t\t<li>\r\n");
      out.write("\t\t\t\t\t<a style=\"background-image:url(");
      out.print(tab_bg);
      out.write("); background-repeat: repeat x; border:1px solid #2D5B77;color:#FFF\" href=\"");
      out.print(shop_url);
      out.write("\">\r\n");
      out.write("\t\t\t\t\t\t<b>");
      if (_jspx_meth_i18n_message_2(_jspx_page_context))
        return;
      out.write("</b>\r\n");
      out.write("\t\t\t\t\t</a>\r\n");
      out.write("\t\t\t\t</li> \r\n");
      out.write("\t\t");
 } else { 
      out.write("\r\n");
      out.write("\t\t\t<li><a style=\"background-image:url(");
      out.print(tab_bg_inactive);
      out.write("); border:1px solid #2D5B77\" href=\"");
      out.print(shop_url);
      out.write('"');
      out.write('>');
      if (_jspx_meth_i18n_message_3(_jspx_page_context))
        return;
      out.write("</a></li> \r\n");
      out.write("\t\t");
 } 
      out.write("\r\n");
      out.write("\t</ul> \r\n");
      out.write("</div>\r\n");
      out.write("<div id=\"main\" > \r\n");
      out.write("\t<div id=\"subtabs\" >\r\n");
      out.write("\t\t");
      out.print(builderBean.getSubTabContent());
      out.write("\r\n");
      out.write("\t</div> \r\n");
      out.write("\t<div id=\"bodyarea\" > \r\n");
      out.write("\t\t<table width=\"99%\" align=\"center\">\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<td id=\"newsContainer\" valign=\"top\">\r\n");
      out.write("\t\t\t\t\t");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/main/Layout/Content.jsp" + (("/main/Layout/Content.jsp").indexOf('?')>0? '&': '?') + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("outputMode", request.getCharacterEncoding())+ "=" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode(String.valueOf(outputMode), request.getCharacterEncoding()) + "&" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("lang", request.getCharacterEncoding())+ "=" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode(String.valueOf(lang), request.getCharacterEncoding()), out, true);
      out.write("\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t<td id=\"adSpot\" align=\"right\" valign=\"top\">\r\n");
      out.write("\t\t\t\t\t");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/main/Layout/Ad.jsp", out, true);
      out.write("\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t</table>\r\n");
      out.write("\t</div>\r\n");
      out.write("</div>");
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
    _jspx_th_i18n_message_0.setKey("news");
    int _jspx_eval_i18n_message_0 = _jspx_th_i18n_message_0.doStartTag();
    if (_jspx_th_i18n_message_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_i18n_message_key_nobody.reuse(_jspx_th_i18n_message_0);
    return false;
  }

  private boolean _jspx_meth_i18n_message_1(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  i18n:message
    org.apache.taglibs.i18n.MessageTag _jspx_th_i18n_message_1 = (org.apache.taglibs.i18n.MessageTag) _jspx_tagPool_i18n_message_key_nobody.get(org.apache.taglibs.i18n.MessageTag.class);
    _jspx_th_i18n_message_1.setPageContext(_jspx_page_context);
    _jspx_th_i18n_message_1.setParent(null);
    _jspx_th_i18n_message_1.setKey("news");
    int _jspx_eval_i18n_message_1 = _jspx_th_i18n_message_1.doStartTag();
    if (_jspx_th_i18n_message_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_i18n_message_key_nobody.reuse(_jspx_th_i18n_message_1);
    return false;
  }

  private boolean _jspx_meth_i18n_message_2(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  i18n:message
    org.apache.taglibs.i18n.MessageTag _jspx_th_i18n_message_2 = (org.apache.taglibs.i18n.MessageTag) _jspx_tagPool_i18n_message_key_nobody.get(org.apache.taglibs.i18n.MessageTag.class);
    _jspx_th_i18n_message_2.setPageContext(_jspx_page_context);
    _jspx_th_i18n_message_2.setParent(null);
    _jspx_th_i18n_message_2.setKey("shop");
    int _jspx_eval_i18n_message_2 = _jspx_th_i18n_message_2.doStartTag();
    if (_jspx_th_i18n_message_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_i18n_message_key_nobody.reuse(_jspx_th_i18n_message_2);
    return false;
  }

  private boolean _jspx_meth_i18n_message_3(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  i18n:message
    org.apache.taglibs.i18n.MessageTag _jspx_th_i18n_message_3 = (org.apache.taglibs.i18n.MessageTag) _jspx_tagPool_i18n_message_key_nobody.get(org.apache.taglibs.i18n.MessageTag.class);
    _jspx_th_i18n_message_3.setPageContext(_jspx_page_context);
    _jspx_th_i18n_message_3.setParent(null);
    _jspx_th_i18n_message_3.setKey("shop");
    int _jspx_eval_i18n_message_3 = _jspx_th_i18n_message_3.doStartTag();
    if (_jspx_th_i18n_message_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_i18n_message_key_nobody.reuse(_jspx_th_i18n_message_3);
    return false;
  }
}

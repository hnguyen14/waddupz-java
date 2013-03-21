package org.apache.jsp.main.Layout;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import app.API.URL.StaticUrl.*;
import app.Utils.UrlUtils;
import app.API.URL.StaticUrlFactory;
import app.Utils.ConfigUtils;

public final class Header_jsp extends org.apache.jasper.runtime.HttpJspBase
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


      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<div>\r\n");
      out.write("<table width=\"100%\" align=\"center\">\r\n");
      out.write("\t<tr >\r\n");
      out.write("\t\t<td\twidth=\"50%\" align=\"left\">\r\n");
      out.write("\t\t\t<img src=\"");
      out.print(serverLogoUrl);
      out.write("\"/>\r\n");
      out.write("\t\t</td>\r\n");
      out.write("\t\t<td width=\"50%\" align=\"right\" height=\"10px\">\r\n");
      out.write("\t\t\t<form action=\"");
      out.print(searchJsp);
      out.write("\">\r\n");
      out.write("\t\t\t\t<table bgcolor=\"#ffffff\">\r\n");
      out.write("\t\t\t\t<tr><td nowrap=\"nowrap\" valign=\"top\" align=\"left\">\r\n");
      out.write("\t\t\t\t<input type=\"text\" name=\"q\" size=\"31\" maxlength=\"255\" value=\"\" id=\"sbi\"></input>\r\n");
      out.write("\t\t\t\t<input type=\"hidden\" name=\"lang\" value=\"");
      out.print(lang);
      out.write("\"/>\r\n");
      out.write("\t\t\t\t<select name=\"outputMode\">\r\n");
      out.write("\t\t\t\t\t<option  value=\"");
      out.print(FrontPageStaticUrl.PAGE_TYPE_NEWS);
      out.write("\" \r\n");
      out.write("\t\t\t\t\t\t");
      out.print(outputMode.equalsIgnoreCase(FrontPageStaticUrl.PAGE_TYPE_NEWS) ? "selected" : "" );
      out.write(">News</option>\r\n");
      out.write("\t\t\t\t\t<option  value=\"");
      out.print(FrontPageStaticUrl.PAGE_TYPE_SHOP_DEALS);
      out.write("\"\r\n");
      out.write("\t\t\t\t\t\t");
      out.print(outputMode.equalsIgnoreCase(FrontPageStaticUrl.PAGE_TYPE_SHOP_DEALS) ? "selected" : "" );
      out.write(">Shopping Deals</option>\r\n");
      out.write("\t\t\t\t</select>\r\n");
      out.write("\t\t\t\t<input type=\"submit\" name=\"sa\" value=\"Search\" id=\"sbb\"></input>\r\n");
      out.write("\t\t\t\t</td></tr></table>\r\n");
      out.write("\t\t\t</form>\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t</td>\r\n");
      out.write("\t</tr>\r\n");
      out.write("\t<tr>\r\n");
      out.write("\t\t<td colspan=\"2\" width=\"50%\" align=\"right\">\r\n");
      out.write("\t\t\t<p class=\"menu_header\" style=\"font-size:14px\">\r\n");
      out.write("\t\t\t\t");
 if  (lang.equalsIgnoreCase("en")) { 
      out.write("\r\n");
      out.write("\t\t\t\t\t");
      if (_jspx_meth_i18n_message_0(_jspx_page_context))
        return;
      out.write(" | <a href=\"");
      out.print(other_language_link);
      out.write('"');
      out.write('>');
      if (_jspx_meth_i18n_message_1(_jspx_page_context))
        return;
      out.write("</a>\r\n");
      out.write("\t\t\t\t");
 }  else if (lang.equalsIgnoreCase("vi")) {
      out.write("\r\n");
      out.write("\t\t\t\t\t<a href=\"");
      out.print(other_language_link);
      out.write('"');
      out.write('>');
      if (_jspx_meth_i18n_message_2(_jspx_page_context))
        return;
      out.write("</a> | ");
      if (_jspx_meth_i18n_message_3(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t\t\t");
 } 
      out.write("\r\n");
      out.write("\t\t\t</p>\r\n");
      out.write("\t\t</td>\r\n");
      out.write("\t</tr>\r\n");
      out.write("</table>\r\n");
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
    _jspx_th_i18n_message_0.setKey("english");
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
    _jspx_th_i18n_message_1.setKey("vietnamese");
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
    _jspx_th_i18n_message_2.setKey("english");
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
    _jspx_th_i18n_message_3.setKey("vietnamese");
    int _jspx_eval_i18n_message_3 = _jspx_th_i18n_message_3.doStartTag();
    if (_jspx_th_i18n_message_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_i18n_message_key_nobody.reuse(_jspx_th_i18n_message_3);
    return false;
  }
}

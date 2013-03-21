package org.apache.jsp.main.Layout;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class Footer_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write("\r\n");
      out.write("<div id=\"big_gap\"></div><br/>\t\r\n");
      out.write("<p class=\"menu_footer\" align=\"center\">\r\n");
      out.write("\t<a href=\"#\">");
      if (_jspx_meth_i18n_message_0(_jspx_page_context))
        return;
      out.write("</a>&nbsp;|&nbsp;\r\n");
      out.write("\t<a href=\"#\">");
      if (_jspx_meth_i18n_message_1(_jspx_page_context))
        return;
      out.write("</a>&nbsp;|&nbsp;\r\n");
      out.write("\t<a href=\"#\">");
      if (_jspx_meth_i18n_message_2(_jspx_page_context))
        return;
      out.write("</a>\r\n");
      out.write("</p>\r\n");
      out.write(" ");
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
    _jspx_th_i18n_message_0.setKey("aboutUs");
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
    _jspx_th_i18n_message_1.setKey("contact");
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
    _jspx_th_i18n_message_2.setKey("feedBack");
    int _jspx_eval_i18n_message_2 = _jspx_th_i18n_message_2.doStartTag();
    if (_jspx_th_i18n_message_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_i18n_message_key_nobody.reuse(_jspx_th_i18n_message_2);
    return false;
  }
}

package org.apache.jsp.main.Pages;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.hibernate.*;
import app.Entry.*;
import app.Utils.*;

public final class singleEntry_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static java.util.Vector _jspx_dependants;

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_i18n_bundle_scope_id_baseName_nobody;

  public java.util.List getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_i18n_bundle_scope_id_baseName_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_i18n_bundle_scope_id_baseName_nobody.release();
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
      out.write("<html>\r\n");
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

	builderBean.setPageType(app.API.URL.StaticUrlFactory.SINGLE_POST);
	builderBean.initialize(pageContext);

      out.write("\r\n");
      out.write("\r\n");
      out.write("\t");
 
		String lang = request.getParameter("lang");
		String outputMode = request.getParameter("outputMode");
		if (outputMode == null)
			outputMode = "news";
		if (lang == null)
			lang = "en";
			
		String cmd = request.getParameter("cmd");
		if (cmd != null) {
			EntryComment comment = new EntryComment();
			comment.setComment(	request.getParameter("comment"));
			comment.setAuthor(request.getParameter("name"));
			comment.setEmail(request.getParameter("email"));
			comment.setCommentDate(new java.util.Date(System.currentTimeMillis()));
			
			Session hib_session = HibernateUtil.getSessionFactory().openSession();
			Transaction t = hib_session.beginTransaction();
			
			AbstractEntry entry = builderBean.getEntry();
			entry.addComment(comment);
			entry.updateCache(app.API.Object.CacheableObject.CACHE_UPDATE);
			
			hib_session.update(entry);
			t.commit();
			hib_session.close();				 
		}
			
	 
      out.write("\r\n");
      out.write("\r\n");
      out.write("<head>\r\n");
      out.write("\t<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" pageEncoding=\"utf-8\"/>\r\n");
      out.write("\t");
      out.print(builderBean.getTitle());
      out.write('\r');
      out.write('\n');
      out.write('	');
      out.print(builderBean.getMetaDescription());
      out.write('\r');
      out.write('\n');
      out.write('	');
      out.print(builderBean.getPageCss());
      out.write("\r\n");
      out.write("\t\r\n");
      out.write("</head>\r\n");
      out.write("\t\t\r\n");
      out.write("<body>\r\n");
      out.write("\t");
      //  i18n:bundle
      java.util.ResourceBundle bundle = null;
      org.apache.taglibs.i18n.BundleTag _jspx_th_i18n_bundle_0 = (org.apache.taglibs.i18n.BundleTag) _jspx_tagPool_i18n_bundle_scope_id_baseName_nobody.get(org.apache.taglibs.i18n.BundleTag.class);
      _jspx_th_i18n_bundle_0.setPageContext(_jspx_page_context);
      _jspx_th_i18n_bundle_0.setParent(null);
      _jspx_th_i18n_bundle_0.setBaseName(builderBean.getResourceBundle());
      _jspx_th_i18n_bundle_0.setId("bundle");
      _jspx_th_i18n_bundle_0.setScope("request");
      int _jspx_eval_i18n_bundle_0 = _jspx_th_i18n_bundle_0.doStartTag();
      bundle = (java.util.ResourceBundle) _jspx_page_context.findAttribute("bundle");
      if (_jspx_th_i18n_bundle_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
        return;
      bundle = (java.util.ResourceBundle) _jspx_page_context.findAttribute("bundle");
      _jspx_tagPool_i18n_bundle_scope_id_baseName_nobody.reuse(_jspx_th_i18n_bundle_0);
      out.write("\r\n");
      out.write("\t<table width=\"1000px\" align=\"center\">\r\n");
      out.write("\t\t<tr><td>\r\n");
      out.write("\t\t\t");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/main/Layout/Header.jsp" + (("/main/Layout/Header.jsp").indexOf('?')>0? '&': '?') + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("lang", request.getCharacterEncoding())+ "=" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode(String.valueOf(lang), request.getCharacterEncoding()) + "&" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("outputMode", request.getCharacterEncoding())+ "=" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode(String.valueOf(outputMode), request.getCharacterEncoding()), out, true);
      out.write("\r\n");
      out.write("\t\t</td></tr>\r\n");
      out.write("\t\t<tr><td>\r\n");
      out.write("\t\t\t");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/main/Layout/Body.jsp" + (("/main/Layout/Body.jsp").indexOf('?')>0? '&': '?') + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("outputMode", request.getCharacterEncoding())+ "=" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode(String.valueOf(outputMode), request.getCharacterEncoding()) + "&" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("lang", request.getCharacterEncoding())+ "=" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode(String.valueOf(lang), request.getCharacterEncoding()), out, true);
      out.write("\r\n");
      out.write("\t\t</td></tr>\r\n");
      out.write("\t\t<script type=\"text/javascript\"><!--\r\n");
      out.write("\t\t\t amzn_cl_tag=\"hoangublo-20\";\r\n");
      out.write("\t\t\t amzn_cl_border_color=\"84B2D7\";\r\n");
      out.write("\t\t\t//-->\r\n");
      out.write("\t\t</script>\r\n");
      out.write("\t\t<script type=\"text/javascript\" src=\"http://cls.assoc-amazon.com/s/cls.js\"></script>\r\n");
      out.write("\t\t<tr><td>\r\n");
      out.write("\t\t\t");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/main/Layout/Footer.jsp" + (("/main/Layout/Footer.jsp").indexOf('?')>0? '&': '?') + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("lang", request.getCharacterEncoding())+ "=" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode(String.valueOf(lang), request.getCharacterEncoding()), out, true);
      out.write("\r\n");
      out.write("\t\t</td></tr>\r\n");
      out.write("\t</table>\r\n");
      out.write("</body>\r\n");
      out.write("\r\n");
      out.write("</html>");
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

package org.apache.jsp.main.Pages;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.hibernate.*;
import app.Entry.*;
import app.Utils.*;

public final class vote_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");

	String id_str = request.getParameter("id");
	long id = Long.parseLong(id_str);
	String vote = request.getParameter("action");
	
	String referer = request.getHeader("Referer");
	
	Session hib_session = HibernateUtil.getSessionFactory().openSession();
	Transaction t = hib_session.beginTransaction();
	
	AbstractEntry entry = (AbstractEntry) hib_session.load(AbstractEntry.class,new Long(id));
	java.util.Random rand = new java.util.Random();
	if (vote.equalsIgnoreCase("up"))
		entry.addPoint((Math.abs(rand.nextInt()) % 4) + 1);
	else 
		entry.addPoint(-1);
	
	hib_session.update(entry);
	entry.updateCache(app.API.Object.CacheableObject.CACHE_UPDATE);
	t.commit();
	hib_session.close();
	
	// Redirect to the page it came from
	response.sendRedirect(referer);
	

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

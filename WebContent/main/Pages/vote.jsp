<%@page import="org.hibernate.*"%>
<%@page import="app.Entry.*"%>
<%@page import="app.Utils.*"%>
<%
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
	
%>
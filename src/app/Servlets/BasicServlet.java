package app.Servlets;


import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import app.API.URL.StaticUrlFactory;
import app.API.URL.StaticUrl.AbstractStaticUrl;
import app.Utils.ConfigUtils;

public class BasicServlet extends HttpServlet{
	
	private Logger logger = Logger.getLogger("ServerLog");
	private static String servletRequestRootPath = ConfigUtils.getAppProperty("server.request.path", "/waddupz");
	
	private void logRequest(HttpServletRequest request){
		String referer = request.getHeader("Referer");
		String user = request.getHeader("User-Agent");
		String url = request.getRequestURL().toString();
		String remoteAddress = request.getRemoteAddr();
		long date = System.currentTimeMillis();
		logger.info("Incoming|" + date + "|" + remoteAddress + "|" +user + "|" + url + "|" + referer);
	}
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		logRequest(request);
		
		String requestURI = request.getRequestURI();
		if (requestURI.startsWith(servletRequestRootPath))
			requestURI = requestURI.replaceFirst(servletRequestRootPath, "");
		
		if (requestURI.endsWith("html")) {
			AbstractStaticUrl handler = StaticUrlFactory.getStaticUrlHandler(requestURI);
			String url = handler.decode();
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher(requestURI);
			dispatcher.forward(request, response);
		}
		return;
	}
	public static void main(String args[]) {
		
	}

}

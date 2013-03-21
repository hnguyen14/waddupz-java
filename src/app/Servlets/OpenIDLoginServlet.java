package app.Servlets;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.openid4java.OpenIDException;
import org.openid4java.consumer.ConsumerException;
import org.openid4java.consumer.ConsumerManager;
import org.openid4java.consumer.VerificationResult;
import org.openid4java.discovery.DiscoveryInformation;
import org.openid4java.discovery.Identifier;
import org.openid4java.message.AuthRequest;
import org.openid4java.message.AuthSuccess;
import org.openid4java.message.ParameterList;
import org.openid4java.message.ax.AxMessage;
import org.openid4java.message.ax.FetchRequest;
import org.openid4java.message.ax.FetchResponse;

public class OpenIDLoginServlet extends HttpServlet{
	
	private ServletContext context;
	private ConsumerManager manager;

	private Logger log = Logger.getLogger("ServerLog");
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		context = config.getServletContext();

		try {
			this.manager = new ConsumerManager();
		} catch (ConsumerException e) {
			throw new ServletException(e);
		}
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		log.debug("------------------------");
		log.debug("context: " + context);
		Identifier identifier = this.verifyResponse(req);
		log.debug("identifier: " + identifier);
		if (identifier == null) {
			this.getServletContext().getRequestDispatcher("/index.jsp")
					.forward(req, resp);
		} else {
			req.setAttribute("identifier", identifier.getIdentifier());
			this.getServletContext().getRequestDispatcher("/return.jsp")
					.forward(req, resp);
		}
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {
		String identifier = req.getParameter("identifier");
		this.authRequest(identifier, req, resp);
	}

	public String authRequest(String userSuppliedString,
			HttpServletRequest httpReq, HttpServletResponse httpResp)
	throws IOException {
		try {
			String returnToUrl = httpReq.getRequestURL().toString();

			List discoveries = manager.discover(userSuppliedString);
			DiscoveryInformation discovered = manager.associate(discoveries);
			httpReq.getSession().setAttribute("openid-disc", discovered);
			AuthRequest authReq = manager.authenticate(discovered, returnToUrl);

			// Attribute Exchange example: fetching the 'email' attribute
			FetchRequest fetch = FetchRequest.createFetchRequest();

			if ("1".equals(httpReq.getParameter("nickname"))) {
				fetch.addAttribute("nickname",
						// attribute alias
						"http://schema.openid.net/contact/nickname", // type
						// URI
						true); // required
			}
			if ("1".equals(httpReq.getParameter("email"))) {
				fetch.addAttribute("email",
						// attribute alias
						"http://schema.openid.net/contact/email", // type URI
						true); // required
			}
			if ("1".equals(httpReq.getParameter("fullname"))) {
				fetch.addAttribute("fullname",
						// attribute alias
						"http://schema.openid.net/contact/fullname", // type
						// URI
						true); // required
			}
			if ("1".equals(httpReq.getParameter("dob"))) {
				fetch.addAttribute("dob",
						// attribute alias
						"http://schema.openid.net/contact/dob", // type URI
						true); // required
			}
			if ("1".equals(httpReq.getParameter("gender"))) {
				fetch.addAttribute("gender",
						// attribute alias
						"http://schema.openid.net/contact/gender", // type URI
						true); // required
			}
			if ("1".equals(httpReq.getParameter("postcode"))) {
				fetch.addAttribute("postcode",
						// attribute alias
						"http://schema.openid.net/contact/postcode", // type
						// URI
						true); // required
			}
			if ("1".equals(httpReq.getParameter("country"))) {
				fetch.addAttribute("country",
						// attribute alias
						"http://schema.openid.net/contact/country", // type URI
						true); // required
			}
			if ("1".equals(httpReq.getParameter("language"))) {
				fetch.addAttribute("language",
						// attribute alias
						"http://schema.openid.net/contact/language", // type
						// URI
						true); // required
			}
			if ("1".equals(httpReq.getParameter("timezone"))) {
				fetch.addAttribute("timezone",
						// attribute alias
						"http://schema.openid.net/contact/timezone", // type
						// URI
						true); // required
			}

			// attach the extension to the authentication request
			authReq.addExtension(fetch);

			if (!discovered.isVersion2()) {
				httpResp.sendRedirect(authReq.getDestinationUrl(true));
				return null;
			} else {
				// Option 2: HTML FORM Redirection (Allows payloads >2048 bytes)

				// RequestDispatcher dispatcher =
				// getServletContext().getRequestDispatcher("formredirection.jsp");
				// httpReq.setAttribute("prameterMap",
				// response.getParameterMap());
				// httpReq.setAttribute("destinationUrl",
				// response.getDestinationUrl(false));
				// dispatcher.forward(request, response);
			}
		} catch (OpenIDException e) {
			// present error to the user
		}

		return null;
	}
//	--- processing the authentication response ---
	public Identifier verifyResponse(HttpServletRequest httpReq) {
		try {
			ParameterList response = new ParameterList(httpReq.getParameterMap());
			
			DiscoveryInformation discovered = (DiscoveryInformation) httpReq.getSession().getAttribute("openid-disc");

			// extract the receiving URL from the HTTP request
			StringBuffer receivingURL = httpReq.getRequestURL();
			String queryString = httpReq.getQueryString();
			if (queryString != null && queryString.length() > 0)
				receivingURL.append("?").append(httpReq.getQueryString());

			// verify the response; ConsumerManager needs to be the same
			// (static) instance used to place the authentication request
			VerificationResult verification = manager.verify(receivingURL.toString(), response, discovered);

			// examine the verification result and extract the verified
			// identifier
			Identifier verified = verification.getVerifiedId();
			if (verified != null) {
				AuthSuccess authSuccess = (AuthSuccess) verification.getAuthResponse();

				if (authSuccess.hasExtension(AxMessage.OPENID_NS_AX)) {
					FetchResponse fetchResp = (FetchResponse) authSuccess
					.getExtension(AxMessage.OPENID_NS_AX);

					// List emails = fetchResp.getAttributeValues("email");
					// String email = (String) emails.get(0);

					List aliases = fetchResp.getAttributeAliases();
					for (Iterator iter = aliases.iterator(); iter.hasNext();) {
						String alias = (String) iter.next();
						List values = fetchResp.getAttributeValues(alias);
						if (values.size() > 0) {
							log.debug(alias + " : " + values.get(0));
							httpReq.setAttribute(alias, values.get(0));
						}
					}
				}

				return verified; // success
			}
		} catch (OpenIDException e) {
			// present error to the user
		}

		return null;
	}

	
}

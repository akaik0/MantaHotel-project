package servlet;

import resource.*;
import rest.BookingRestResource;
import rest.UserRestResource;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.ErrorCode;

import javax.naming.NamingException;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Date;
import java.sql.SQLException;
import java.util.UUID;

public final class RestManagerServlet extends AbstractServlet {

	/**
	 * The JSON MIME media type
	 */
	private static final String JSON_MEDIA_TYPE = "application/json";

	/**
	 * The JSON UTF-8 MIME media type
	 */
	private static final String JSON_UTF_8_MEDIA_TYPE = "application/json; charset=utf-8";

	/**
	 * The any MIME media type
	 */
	private static final String ALL_MEDIA_TYPE = "*/*";

	@Override
	protected final void service(final HttpServletRequest req, final HttpServletResponse res)
			throws ServletException, IOException {

		res.setContentType(JSON_UTF_8_MEDIA_TYPE);
		final OutputStream out = res.getOutputStream();

		try {
			// if the request method and/or the MIME media type are not allowed, return.
			// Appropriate error message sent by {@code checkMethodMediaType}
			if (!checkMethodMediaType(req, res)) {
				return;
			}

			// if the requested resource was an User, delegate its processing and return
			if (processUser(req, res)) {
				return;
			}

			if (processBooking(req, res)) {
				return;
			}

			// if none of the above process methods succeeds, it means an unknown resource has been requested
			final Message m = new Message("Unknown resource requested.", "E4A6",
					String.format("Requested resource is %s.", req.getRequestURI()));
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			m.toJSON(out);
		} finally {
			// ensure to always flush and close the output stream
			out.flush();
			out.close();
		}
	}

	/**
	 * Checks that the request method and MIME media type are allowed.
	 *
	 * @param req the HTTP request.
	 * @param res the HTTP response.
	 * @return {@code true} if the request method and the MIME type are allowed; {@code false} otherwise.
	 *
	 * @throws IOException if any error occurs in the client/server communication.
	 */
	private boolean checkMethodMediaType(final HttpServletRequest req, final HttpServletResponse res)
			throws IOException {

		final String method = req.getMethod();
		final String contentType = req.getHeader("Content-Type");
		final String accept = req.getHeader("Accept");
		final OutputStream out = res.getOutputStream();

		Message m = null;

		if(accept == null) {
			m = new Message("Output media type not specified.",
					"E4A1", "Accept request header missing.");
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			m.toJSON(out);
			return false;
		}

		if(!accept.contains(JSON_MEDIA_TYPE) && !accept.equals(ALL_MEDIA_TYPE)) {
			m = new Message("Unsupported output media type. Resources are represented only in application/json.",
					"E4A2", String.format("Requested representation is %s.", accept));
			res.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
			m.toJSON(out);
			return false;
		}

		switch(method) {
			case "GET":
			case "DELETE":
				// nothing to do
				break;

			case "POST":
			case "PUT":
				if(contentType == null) {
					m = new Message("Unsupported operation.",
							"E4A5", String.format("Requested operation %s.", method));
					res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
					m.toJSON(out);
					return false;
				}

				if(!contentType.contains(JSON_MEDIA_TYPE)) {
					m = new Message("Unsupported input media type. Resources are represented only in application/json.",
							"E4A4", String.format("Submitted representation is %s.", contentType));
					res.setStatus(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
					m.toJSON(out);
					return false;
				}

				break;
			default:
				m = new Message("Unsupported operation.",
						"E4A5", String.format("Requested operation %s.", method));
				res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
				m.toJSON(out);
				return false;
		}

		return true;
	}


	private boolean processBooking(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String op = req.getRequestURI();
		String[] tokens = op.split("/");

		if (tokens.length<4 || !(tokens[3].equals("booking"))){
			return false;
		}

		try{
			if (tokens.length == 6 && tokens[4].equals("customer")){
				// the request URI is: /booking/costumer/{personid}

				//check if a UUID is inserted
				UUID id = UUID.fromString(tokens[tokens.length -1]);

				BookingRestResource brr = new BookingRestResource(req, res, getDataSource().getConnection());
				switch (req.getMethod()) {
					case "GET" -> brr.getBookingListByCustomer();
					default -> writeError(res, ErrorCode.METHOD_NOT_ALLOWED);
				}
			} else if (tokens.length==6 && tokens[4].equals("date")) {
				// the request URI is: /booking/date/{date}

				//check if a Date is inserted
				Date date = Date.valueOf(tokens[tokens.length -1]);
				BookingRestResource brr = new BookingRestResource(req, res, getDataSource().getConnection());
				switch (req.getMethod()) {
					case "GET" -> brr.getBookingListByDate();
					default -> writeError(res, ErrorCode.METHOD_NOT_ALLOWED);
				}
			} else {
				// the request URI is: /booking/{bookingid}

				UUID id = UUID.fromString(tokens[4]);
				BookingRestResource brr = new BookingRestResource(req, res, getDataSource().getConnection());
				switch (req.getMethod()) {
					case "GET" -> brr.getBooking();
					default -> writeError(res, ErrorCode.METHOD_NOT_ALLOWED);
				}
			}
		} catch (NumberFormatException e){
			writeError(res, ErrorCode.WRONG_REST_FORMAT);
		} catch (NamingException | SQLException e){
			writeError(res, ErrorCode.INTERNAL_ERROR);
			logger.error("stacktrace:", e);
		}

		return true;
	}



	private boolean processUser(HttpServletRequest req, HttpServletResponse res) throws IOException {

		final String method = req.getMethod();
		final OutputStream out = res.getOutputStream();

		String path = req.getRequestURI();
		String[] tokens = path.split("/");

		Message m = null;
		int flag = 1;

		if(path.lastIndexOf("rest/user") <= 0) {
			return false;
		}

		try {

			if (tokens[tokens.length - 1].equals("user")) {
				flag = 2;

				switch (method) {
					case "GET":
						new UserRestResource(req, res, getDataSource().getConnection()).listUsers();
						break;
					case "POST":
						new UserRestResource(req, res, getDataSource().getConnection()).createUser();
						break;
					default:
						m = new Message("Unsupported operation for URI /user.",
								"E4A5", String.format("Requested operation %s.", method));
						res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
						m.toJSON(res.getOutputStream());
						break;
				}
			} else {
				m = new Message("Else Cond.",
						"E4A5", "Else Cond.");
				res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
				m.toJSON(res.getOutputStream());
			}
		} catch(Throwable t) {
			m = new Message("Unexpected error.", "E5A1", t.getMessage() + "flag=" + flag + ", Path=" + path + ", Path len:" + path.length());
			res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			m.toJSON(res.getOutputStream());
		}

		return true;

	}
}

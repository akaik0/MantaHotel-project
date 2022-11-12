package servlet;

import dao.*;
import jakarta.servlet.http.HttpSession;
import org.json.JSONObject;
import resource.Log;
import resource.Message;
import resource.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

import utils.ErrorCode;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.naming.NamingException;

public final class UserServlet extends AbstractServlet {

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//take the request uri
		//and remove everything prior to /user/ (included) and use the remainder as
		//indicator for the required operation
		String op = req.getRequestURI().split("/", 4)[3];
		Message m = null;

		try{

			//invalidate the user session
			//redirect the browser to the homepage
			switch (op) {
				case "logout/" -> {
					// inserting user Log
					HttpSession session = req.getSession(false);
					Log log = new Log((String) session.getAttribute("email"), (Timestamp) session.getAttribute("login"),
							new Timestamp(System.currentTimeMillis()));
					new InsertLogDatabase(getDataSource().getConnection(), log).insertLog();

					req.getSession().invalidate();
					res.sendRedirect(req.getContextPath() + "/jsp/homepage.jsp");
				}
				/*case "protected/email/" -> {
					String email = req.getParameter("email");
					if (email == null || email.equals("")) {
						ErrorCode ec = ErrorCode.EMAIL_MISSING;
						res.setStatus(ec.getHTTPCode());
						writeError(res, ec);
					} else {
						res.setContentType("application/json");
						JSONObject resJSON = new JSONObject();
						res.setStatus(HttpServletResponse.SC_OK);
						resJSON.put("data", new GetUserByMailDatabase(getDataSource().getConnection(), new User(email)).getUserByMail().toJSON());
						res.getWriter().write(resJSON.toString());
					}
				}*/

				//Get a list of all the available user (both Front Office and Managers)
				case "userList/" -> {
					List <User> userList = null;
					try {
						userList = new ListUsersDatabase(getDataSource().getConnection()).listUsers();
					} catch (NamingException | SQLException e) {
						m = new Message(true, "No user found");
					}
					req.setAttribute("userList", userList);
					req.getRequestDispatcher("/jsp/manager-area/user-list.jsp").forward(req, res);
					}
			}
		}
		catch (NamingException | SQLException e){
				writeError(res, ErrorCode.INTERNAL_ERROR);
				logger.error("stacktrace:", e);
			}
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//parse URI
		String op = req.getRequestURI().split("/", 4)[3];

		System.out.println("INSIDE METHOD POST" + op);

		System.out.println(op);
		switch (op) {
			// the requested operation is login
			case "login/" -> loginOperations(req, res);
			// the requested operation is register
			case "register/" -> registrationOperations(req, res);
			// the requested operation is update user
			case "update/" -> updateOperations(req, res);
			// the requested operation is unknown
			default -> writeError(res, ErrorCode.OPERATION_UNKNOWN);
		}
	}

	public void registrationOperations(HttpServletRequest req, HttpServletResponse res) throws IOException {
		try {
			System.out.println("STARTING REGISTRATION");
			System.out.println("PRINTING REQUEST:" + req.toString());

			boolean registrable = true;
			//get the registration parameters

			String password = req.getParameter("password");
			String rpassword = req.getParameter("rpassword");
			String first_name = req.getParameter("first_name");
			String last_name = req.getParameter("last_name");
			String role = req.getParameter("role");
			String email = first_name + "." + last_name + "@hotelmanta.com";

			System.out.println("RETRIEVED INFORMATION:" + email + password + first_name + last_name + role);

			ErrorCode ec = null;
			Message m = null;
			User u = null;

			//check that all registrations parameters have been set and are not null
			if (first_name == null || first_name.equals("") ||
					password == null || password.equals("") || rpassword == null || rpassword.equals("") ||
					last_name == null || last_name.equals("") || role==null) {

				registrable = false;
				//if not, notify it to the user
				ec = ErrorCode.EMPTY_INPUT_FIELDS;
				m = new Message(true, "Some fields are empty");
				System.out.println("EMPTY FIELDS FOUND");
			}

			// check that the password and the repeated password are equal
			else if (!password.equals(rpassword)) {

				registrable = false;
				//if not, notify it to the user
				ec = ErrorCode.DIFFERENT_PASSWORDS;
				m = new Message(true, "Passwords do not coincide");
				System.out.println("PASSWORD DO NOT COINCIDE");

			} else {

				System.out.println("CHECK PASSED, CREATING USER");

				u = new User(email, password, first_name, last_name, role);  //need to change role!!!!!

				System.out.println("USER CREATED");
				try {
					Class.forName("org.postgresql.Driver");
				} catch (Exception e) {
					System.out.println("############ CLASSE NON CARICATA ##########");
				}


				//if the email has been already used (the DAO did not return null)
				if (new GetUserByMailDatabase(getDataSource().getConnection(), u).getUserByMail() != null) {
					registrable = false;
					//notify it to the user
					ec = ErrorCode.MAIL_ALREADY_USED;
					m = new Message(true, "This mail has been already used");
					System.out.println("EMAIL ALREADY USED");
				}
				System.out.println("EMAIL IS OK");
			}
			System.out.println("USER IS REGISTRABLE? " + registrable);
			if (registrable) {
				//else, create a new user resource

				System.out.println("USER REGISTRABLE");

				//pass it to the dao to register it
				User newUser =  new InsertUserDatabase(getDataSource().getConnection(), u).insertUser();
				if (newUser!=null) {
					System.out.println("USER REGISTERED");
					req.getRequestDispatcher("/jsp/user-area/users_homepage.jsp").forward(req, res);
				}else{
					System.out.println("USER NOT PASSED TO THE DAO");
				}

			}
		} catch (SQLException|ServletException e) {
			writeError(res, ErrorCode.INTERNAL_ERROR);
			System.out.println("ERROR FOUND:   " + e.getMessage() + " \n  " + e.toString() + "  \n  " + e.getCause());
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public void loginOperations(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		try {

			boolean loggable=true;
			ErrorCode ec = null;
			Message m = null;


			//take from the request, the parameters (email and password)
			String email = req.getParameter("email");
			String password = req.getParameter("password");

			User u = new User(email, password);
			User loggedUser=null;
			if (email == null || email.equals("")) {
				//the email is empty
				loggable = false;
				ec = ErrorCode.EMAIL_MISSING;
				m = new Message(true, "missing email");

			} else if (password == null || password.equals("")) {
				//the password was empty
				loggable = false;
				ec = ErrorCode.EMAIL_MISSING;
				m = new Message(true, "missing password");

			} else{
				//try to authenticate the user
				//check if email exists and the password matches and the
				loggedUser = new GetUserByMailAndPasswordDatabase(getDataSource().getConnection(), u).getUserByMailAndPassword();
				if (loggedUser == null) {
					loggable = false;
					//if not, tell it to the user
					ec = ErrorCode.WRONG_CREDENTIALS;
					m = new Message(true, "credentials are wrong");
				}
			}


			if (loggable){
				// activate a session to keep the user data
				HttpSession session = req.getSession();

				// insert in the session the email an the role
				session.setAttribute("email", loggedUser.getEmail());
				session.setAttribute("role", loggedUser.getRole());

				// adding login timestamp for the Log resource
				session.setAttribute("login", new Timestamp(System.currentTimeMillis()));

				// login credentials were correct: we redirect the user to the homepage
				// now the session is active and its data can used to change the homepage
				res.sendRedirect(req.getContextPath()+"/jsp/user-area/users_homepage.jsp");
			} else {
				res.setStatus(ec.getHTTPCode());
				req.setAttribute("message", m);
				req.getRequestDispatcher("/jsp/login.jsp").forward(req, res);
			}

		} catch ( SQLException e){   // NamingException |
			//something unexpected happened: we write it into the logger
			writeError(res, ErrorCode.INTERNAL_ERROR);

		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	public void updateOperations(HttpServletRequest req, HttpServletResponse res) throws IOException {
		try{
			String email = req.getParameter("email");
			String first_name = req.getParameter("first_name");
			String last_name = req.getParameter("last_name");
			String role = req.getParameter("role");
			User u = new User(email, first_name, last_name, role);

			if(email==null){
				writeError(res, ErrorCode.EMAIL_MISSING);
				System.out.println("email_missing");
			}
			else if(first_name==null|| first_name.equals("")||
					last_name==null|| last_name.equals("")||
					role==null|| role.equals("")) {
				writeError(res, ErrorCode.EMPTY_INPUT_FIELDS);
				System.out.println("empty_input_fields");
			}
			else if(new GetUserByMailDatabase(getDataSource().getConnection(), u).getUserByMail() == null) {
				writeError(res, ErrorCode.EMAIL_MISSING);
				System.out.println("email missing");
			} else {
				User updatedUser = new UpdateUserNotPasswordDatabase(getDataSource().getConnection(), u)
						.updateUserNotPassword();
				if (updatedUser!=null){
					res.setStatus(HttpServletResponse.SC_OK);
					req.setAttribute("updatedUser", updatedUser);
					req.getRequestDispatcher("/jsp/manager-area/user-list.jsp").forward(req, res);
				} else{
					writeError(res, ErrorCode.INTERNAL_ERROR);
					System.out.println("dao error");
				}
			}
		} catch (SQLException | NamingException | ServletException e) {
			writeError(res, ErrorCode.INTERNAL_ERROR);
			System.out.println("internal_error_exception  "+ e);
		}
	}

}



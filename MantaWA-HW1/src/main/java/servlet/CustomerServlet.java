package servlet;

import dao.*;
import jakarta.servlet.http.HttpSession;
import org.json.JSONObject;
import resource.Customer;
import resource.Message;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;
import javax.mail.*;
import javax.mail.Message.RecipientType;
import javax.mail.internet.*;
import utils.ErrorCode;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.naming.NamingException;

public final class CustomerServlet extends AbstractServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String op = req.getRequestURI().split("/", 4)[3];

        try{

            switch (op) {
                case "logout/" -> {
                    req.getSession().invalidate();
                    res.sendRedirect(req.getContextPath() + "/jsp/homepage.jsp");
                }
                default -> writeError(res, ErrorCode.OPERATION_UNKNOWN);
            }
        } catch (Exception e){    //NamingException | SQLException
            writeError(res, ErrorCode.INTERNAL_ERROR);
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
            // the requested operation is insert new customer from user
            case "guest/" -> insertCustomer(req, res);
            default -> writeError(res, ErrorCode.OPERATION_UNKNOWN);
        }
    }
    public void insertCustomer(HttpServletRequest req, HttpServletResponse res) throws IOException {
        try{
            System.out.println("STARTING CUSTOMER INSERTION");

            boolean registrable = true;
            //get the registration parameters
            String email = req.getParameter("email");
            String first_name = req.getParameter("name");
            String last_name = req.getParameter("surname");
            String birthDate = req.getParameter("birthDate");
            String documentNumber = req.getParameter("documentNumber");

            Customer c = null;

            if (email == null || email.equals("") || first_name == null || first_name.equals("") ||
                    birthDate == null || birthDate.equals("") || documentNumber == null || documentNumber.equals("") ||
                    last_name == null || last_name.equals("")) {

                registrable = false;
                System.out.println("EMPTY FIELDS FOUND");
            } else{
                System.out.println("CHECK PASSED, CREATING CUSTOMER");
                //Create customer resource to insert in the database
                UUID customerId = UUID.randomUUID();
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date date = null;
                java.sql.Date sqlDate = null;
                try {
                    date = format.parse(birthDate);
                    sqlDate = new java.sql.Date(date.getTime());
                } catch (ParseException e) {
                    e.printStackTrace();
                    System.out.println("DATE NOT CONVERTED, birthDate: " + birthDate);
                }
                System.out.println("DATE CONVERTED: "+ date);

                c = new Customer(customerId, first_name, last_name, sqlDate, email, documentNumber);

                System.out.println("USER CREATED");
            }

            if (registrable) {
                System.out.println("CUSTOMER REGISTRABLE");
                //pass it to the dao to register it
                Customer newCustomer =  new InsertCustomerDatabase(getDataSource().getConnection(), c).insertCustomer();
                if (newCustomer!=null) {
                    System.out.println("CUSTOMER PASSED TO THE DAO");
                    req.getRequestDispatcher("/jsp/user-homepage.jsp").forward(req, res);
                }else{
                    System.out.println("CUSTOMER NOT PASSED TO THE DAO");
                }
            }

        } catch (SQLException|ServletException e) {
            writeError(res, ErrorCode.INTERNAL_ERROR);
            System.out.println("ERROR FOUND:   " + e.getMessage() + " \n  " + "  \n  " + e.getCause());
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }


    public void registrationOperations(HttpServletRequest req, HttpServletResponse res) throws IOException {
        try {
            System.out.println("STARTING REGISTRATION");

            boolean registrable = true;
            //get the registration parameters
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            String rpassword = req.getParameter("rpassword");
            String first_name = req.getParameter("name");
            String last_name = req.getParameter("surname");
            String birthDate = req.getParameter("birthDate");
            String phoneNumber = req.getParameter("phoneNumber");

            System.out.println("RETRIEVED INFORMATION:" + email + password + first_name + last_name + birthDate + phoneNumber);

            ErrorCode ec = null;
            Message m = null;
            Customer c = null;

            //check that all registrations parameters have been set and are not null
            if (email == null || email.equals("") || first_name == null || first_name.equals("") ||
                    password == null || password.equals("") || rpassword == null || rpassword.equals("") ||
                    last_name == null || last_name.equals("") || birthDate == null || birthDate.equals("")||
                    phoneNumber == null || phoneNumber.equals("")) {

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

                System.out.println("PASSWORD CHECK PASSED");

                //Create customer resource to insert in the database
                UUID customerId = UUID.randomUUID();

                DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date Bdate = null;
                java.sql.Date sqlBDate = null;
                try {
                    Bdate = format.parse(birthDate);
                    sqlBDate = new java.sql.Date(Bdate.getTime());
                } catch (ParseException e) {
                    e.printStackTrace();
                    System.out.println("DATE NOT CONVERTED, birthDate: " + birthDate);
                }
                System.out.println("DATE CONVERTED: "+ Bdate);

                c = new Customer(customerId, first_name, last_name, sqlBDate, phoneNumber, email, password);

                System.out.println("CUSTOMER CREATED");


				//if the email has been already used (the DAO did not return null)
				if (new GetCustomerByMailDatabase(getDataSource().getConnection(), c).getCustomerByMail() != null) {
					registrable = false;
					//notify it to the user
					ec = ErrorCode.MAIL_ALREADY_USED;
					m = new Message(true, "This mail has been already used");
					System.out.println("EMAIL ALREADY USED");
				}
				System.out.println("EMAIL IS OK");
            }

            if (registrable) {
                //else, create a new user resource

                System.out.println("CUSTOMER REGISTRABLE");

                //pass it to the dao to register it
                Customer newCustomer =  new InsertCustomerDatabase(getDataSource().getConnection(), c).insertCustomer();
                if (newCustomer!=null) {
                    System.out.println("CUSTOMER PASSED TO THE DAO");
                    sendEmail(email, first_name, last_name);
                    req.getRequestDispatcher("/customer/login/").forward(req, res);
                }else{
                    System.out.println("CUSTOMER NOT PASSED TO THE DAO");
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

            String email = req.getParameter("email");
            String password = req.getParameter("password");

            Customer c = null;
            UUID personId = null;

            // CHECKING THAT THE EMAIL IS OF HOTEL USER AND REDIRECT TO USER LOGIN IN THAT CASE + CHECK THAT @ IS USED ONCE
            if (email.contains("@hotelmanta.com") && !(email.indexOf("@",(email.indexOf("@")+1))!=-1)) {
                req.getRequestDispatcher("/user/login/").forward(req, res);
                System.out.println("Email of an hotel user used for login ---> redirecting to userServlet");
            } else{

                if (email.equals("")) {
                    loggable = false;
                    ec = ErrorCode.EMAIL_MISSING;
                    m = new Message(true, "missing email");

                } else if (password == null || password.equals("")) {
                    loggable = false;
                    ec = ErrorCode.PASSWORD_MISSING;
                    m = new Message(true, "missing password");

                } else{
                    personId = new GetCustomerIdByEmailAndPasswordDatabase(getDataSource().getConnection(), email,
                            password).getCustomerIdByEmailAndPassword();
                    if (personId == null) {
                        loggable = false;
                        //if not, tell it to the user
                        ec = ErrorCode.WRONG_CREDENTIALS;
                        m = new Message(true, "credentials are wrong");
                    }
                }


                if (loggable){

                    HttpSession session = req.getSession();

                    session.setAttribute("personId", personId);
                    session.setAttribute("email", email);

                    // login credentials were correct: we redirect the user to the homepage
                    // now the session is active and its data can used to change the homepage
                    res.sendRedirect(req.getContextPath()+"/jsp/homepage.jsp");
                    // REDIRECT to a new homepage dedicated to Customers
                } else {
                    res.setStatus(ec.getHTTPCode());
                    req.setAttribute("message", m);
                    req.getRequestDispatcher("/jsp/homepage.jsp").forward(req, res);
                }
            }
        } catch (SQLException | NamingException e){
            writeError(res, ErrorCode.INTERNAL_ERROR);
            e.printStackTrace();
        }
    }
    private void sendEmail(String to, String first_name, String last_name) {


        //insert google mail and app password here
        final String username = "";
        final String password = "";


        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS
        prop.put("mail.smtp.ssl.protocols", "TLSv1.2");

        Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
        });

        try {

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("noreply@hotelmanta.com"));
            message.addRecipient(RecipientType.TO, new InternetAddress(to));
            message.setSubject("Welcome to MANTA HOTEL!");
            message.setText("Dear "+first_name+" "+last_name+",\n"+
                    "We warmly welcome you in our brand new site.\n\n"+
                    "Kind regards,\n"+
                    "Manta Hotel *****");

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}



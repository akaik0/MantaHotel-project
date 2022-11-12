package servlet;

import dao.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import resource.Log;
import resource.Message;
import utils.ErrorCode;

import javax.naming.NamingException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

public class LogServlet extends AbstractServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String op = req.getRequestURI();
        op = op.substring(op.lastIndexOf("logs/") + 5);

        Message m = null;
        List<Log> logs = null;

        switch (op) {
            case "user":
                String user = req.getParameter("user");
                String day = req.getParameter("log_day");

                try {
                    logs = new ListLogsByUserAndDateDatabase(getDataSource().getConnection(), user, day).listLogsByUserAndDate();
                } catch (NamingException | SQLException e) {
                    ErrorCode ec = ErrorCode.LOGS_NOT_FOUND;
                    m = new Message(true, ec.getErrorMessage());
                    req.setAttribute("message", m);
                    req.getRequestDispatcher("/jsp/manager-area/view-logs.jsp").forward(req, res);
                }
                req.setAttribute("logList", logs);
                res.setStatus(200);
                req.getRequestDispatcher("/jsp/manager-area/view-logs.jsp").forward(req, res);
                break;

            case "day":
                try {
                    logs = new ListLogsDatabase(getDataSource().getConnection()).listLogs();
                } catch (SQLException | NamingException e) {
                    ErrorCode ec = ErrorCode.LOGS_NOT_FOUND;
                    m = new Message(true, ec.getErrorMessage());
                    req.setAttribute("message", m);
                    req.getRequestDispatcher("/jsp/manager-area/view-logs.jsp").forward(req, res);
                }
                req.setAttribute("logList", logs);
                res.setStatus(200);
                req.getRequestDispatcher("/jsp/manager-area/view-logs.jsp").forward(req, res);
                break;


            default:
                m = new Message(true, "Url not found".concat(op));
                res.setStatus(404);
                req.setAttribute("message", m);
                req.getRequestDispatcher("/jsp/show-message.jsp").forward(req, res);
        }
    }
}
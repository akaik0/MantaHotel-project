package servlet;

import dao.*;
import dao.GetAllRoomsDatabase;
import resource.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import resource.Room;
import utils.ErrorCode;

import javax.naming.NamingException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

public final class RoomServlet extends AbstractServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        List<Room> rl = null;
        //Message m = null;
        int isError = 0;

        try {
            // creates a new object for accessing the database and searching the employees
            rl = new GetAllRoomsDatabase(getDataSource().getConnection()).getAllRooms();

            PrintWriter out = res.getWriter();
            out.printf("Rooms got successfully");
            if (rl == null) {
                Message m = new Message(true, "There is no Room");
                req.setAttribute("message", m);
                isError = 1;
                out.printf("Rooms failed");
            } else {
                req.setAttribute("rooms", rl);
            }
            res.setStatus(200);
            req.getRequestDispatcher("/jsp/show_all_rooms.jsp").forward(req, res);

        } catch (SQLException ex) {
            isError = 1;
            PrintWriter out = res.getWriter();
            out.printf(ex.getMessage().toString());
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String op = req.getRequestURI().split("/", 4)[3];

        System.out.println(op);
        switch (op) {

            case "insert" -> {
                String roomNumber = req.getParameter("roomNumber");
                String beds = req.getParameter("beds");
                String price = req.getParameter("price");
                String type = req.getParameter("type");
                String description = req.getParameter("description");

                ErrorCode ec = null;
                Message m = null;

                if (roomNumber == null || roomNumber.equals("") || beds == null || beds.equals("")
                        || price == null || price.equals("") || type == null || type.equals("")
                        || description == null || description.equals("")) {
                    ec = ErrorCode.EMPTY_INPUT_FIELDS;
                    m = new Message(true, ec.getErrorMessage());
                    req.setAttribute("message", m);
                } else {
                    Room newRoom = null;
                    RoomType rt = RoomType.valueOf(type.toUpperCase(Locale.ROOT).replace(' ', '_'));
                    Room r = new Room(roomNumber, Integer.parseInt(beds), Double.parseDouble(price), rt, description);

                    try {
                        newRoom = new InsertRoomDatabase(getDataSource().getConnection(), r).insertRoom();
                    } catch (SQLException | NamingException e) {
                        e.printStackTrace();
                    }

                    if(newRoom == null) {
                        m = new Message(true, "Errors in room insertion");
                        req.setAttribute("message", m);
                    }

                    req.setAttribute("newRoom", newRoom);
                    req.getRequestDispatcher("/jsp/manager-area/insert-room.jsp").forward(req, res);
                }
            }
			// the requested operation is unknown
			default -> writeError(res, ErrorCode.OPERATION_UNKNOWN);
        }
    }

}

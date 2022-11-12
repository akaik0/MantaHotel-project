package rest;

import dao.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import resource.*;

import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.UUID;

public class RoomRestResource extends RestResource {

    protected final String op;
    protected String response = null;
    protected final String[] tokens;

    public RoomRestResource(HttpServletRequest req, HttpServletResponse res, Connection con) {
        super(req, res, con);
        op = req.getRequestURI();
        tokens = op.split("/");
    }

    public void getAvailableRooms()  throws IOException {

        List<Room> roomList  = null;
        Message m = null;

        try{
            String path = req.getRequestURI();
            String[] t = path.split("/");

            final int beds = Integer.parseInt(t[t.length-3]);
            String checkin = t[t.length-2];
            String checkout = t[t.length-1];

            String[] tk1 = checkin.split("T");
            checkin = tk1[0]+" "+tk1[1] +":00";

            String[] tk2 = checkout.split("T");
            checkout = tk2[0]+" "+tk2[1] +":00";
            Timestamp cin = Timestamp.valueOf(checkin);
            Timestamp cout = Timestamp.valueOf(checkout);

            roomList = new ListRoomByDateDatabase(con, beds, cin, cout).listRoomByDate();

            if(roomList != null) {
                res.setStatus(HttpServletResponse.SC_OK);
                new ResourceList(roomList).toJSON(res.getOutputStream());
            } else {
                m = new Message("Rooms not found.", "E5A3", null);
                res.setStatus(HttpServletResponse.SC_NOT_FOUND);
                m.toJSON(res.getOutputStream());
            }
        } catch (Throwable t) {
            m = new Message("Cannot search rooms: unexpected error.", "E5A1", t.getMessage());
            res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            m.toJSON(res.getOutputStream());
        }
    }
}

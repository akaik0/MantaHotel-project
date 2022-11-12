/*
 * Copyright 2018 University of Padua, Italy
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package servlet;

import dao.*;
import dao.GetAllRoomsDatabase;
import resource.Room;
import resource.Message;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import resource.Room;

import javax.naming.NamingException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

/**
 * Searches employees by their salary.
 * 
 * @author Nicola Ferro (ferro@dei.unipd.it)
 * @version 1.00
 * @since 1.00
 */
public final class GetRooms extends AbstractServlet {

	/**
	 * Searches employees by their salary.
	 * 
	 * @param req
	 *            the HTTP request from the client.
	 * @param res
	 *            the HTTP response from the server.
	 * 
	 * @throws ServletException
	 *             if any error occurs while executing the servlet.
	 * @throws IOException
	 *             if any error occurs in the client/server communication.
	 */
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		// model
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
/*
		// set the MIME media type of the response
		res.setContentType("text/html; charset=utf-8");

		// get a stream to write the response
		PrintWriter out = res.getWriter();

		// write the HTML page
		out.printf("<!DOCTYPE html>%n");
		
		out.printf("<html lang=\"en\">%n");
		out.printf("<head>%n");
		out.printf("<meta charset=\"utf-8\">%n");
		out.printf("<title>Search Employee</title>%n");
		out.printf("</head>%n");

		out.printf("<body>%n");
		out.printf("<h1>Search Employee</h1>%n");
		out.printf("<hr/>%n");

		if(isError == 1) {
			out.printf("<ul>%n");
			out.printf("<li>error code: %s</li>%n", "500");
			out.printf("<li>message: %s</li>%n", "Error");
			out.printf("<li>details: %s</li>%n", "Error");
			out.printf("</ul>%n");
		} else {
			out.printf("<p>%s</p>%n", "Rooms list");
			out.printf("<p>%s</p>%n", "room list:" + rl.size());
			
			out.printf("<table>%n");
			out.printf("<tr>%n");
			out.printf("<td>Room num</td><td>beds</td><td>price</td><td>room type</td><td>Desc</td>%n");
			out.printf("</tr>%n");
			
			for(Room r: rl) {
				out.printf("<tr>%n");
				out.printf("<td>%s</td><td>%s</td><td>%s</td><td>%s</td><td>%s</td>%n",
					r.getRoomNumber(), r.getBeds(), r.getPrice(), r.getRoomType().getName(), r.getDescription());
				out.printf("</tr>%n");
			}
			out.printf("</table>%n");
		}

		out.printf("</body>%n");
		
		out.printf("</html>%n");

		// flush the output stream buffer
		out.flush();

		// close the output stream
		out.close();
*/
	}

}

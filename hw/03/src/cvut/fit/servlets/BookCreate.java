package cvut.fit.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cvut.fit.config.Configuration;
import cvut.fit.service.Booking;
import cvut.fit.service.ServletUtils;
import cvut.fit.service.StringUtils;

/**
 * Servlet implementation class BookCreate
 */
public class BookCreate extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fullName = request.getParameter("fullName");
        String price = request.getParameter("price");
        System.out.println(fullName);
        System.out.println(price);
        if (StringUtils.isAnyBlank(fullName, price)) {
            response.sendError(400, "Missing parameter\n");
            return;
        }

        try {
            int parseInt = Integer.parseInt(price);
            Booking booking = new Booking(fullName, parseInt);
            HttpSession session = request.getSession();
            session.setAttribute(Configuration.BOOKING_SESSION, booking);


            ServletUtils.sendOK(response);

        } catch (NumberFormatException ex) {
            response.sendError(400, ex.getMessage());
        }

    }


}

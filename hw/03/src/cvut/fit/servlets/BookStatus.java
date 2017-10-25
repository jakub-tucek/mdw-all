package cvut.fit.servlets;

import cvut.fit.config.Configuration;
import cvut.fit.service.Booking;
import cvut.fit.service.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BookStatus extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object attribute = req.getSession().getAttribute(Configuration.BOOKING_SESSION);

        if (attribute == null) {
            resp.sendError(404, "No status\n");
            return;
        }
        resp.getWriter().println(attribute.toString());
    }
}

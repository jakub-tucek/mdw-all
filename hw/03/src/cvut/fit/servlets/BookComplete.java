package cvut.fit.servlets;

import cvut.fit.config.Configuration;
import cvut.fit.service.Booking;
import cvut.fit.service.BookingState;
import cvut.fit.service.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class BookComplete extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Optional<Booking> validatedBooking = ServletUtils.getValidatedBooking(request, response, BookingState.PAYMENT);

        if (validatedBooking.isPresent()) {
            request.getSession().removeAttribute(Configuration.BOOKING_SESSION);
            ServletUtils.sendOK(response);
        }
    }
}

package cvut.fit.servlets;

import cvut.fit.service.Booking;
import cvut.fit.service.BookingState;
import cvut.fit.service.ServletUtils;
import cvut.fit.service.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class BookPay extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Optional<Booking> validatedBooking = ServletUtils.getValidatedBooking(request, response, BookingState.NEW);


        if (validatedBooking.isPresent()) {
            Booking booking = validatedBooking.get();
            String id = request.getParameter("paymentId");

            if (StringUtils.isBlank(id)) {
                response.sendError(403, "MISSING PARAM\n");
                return;
            }

            booking.pay(id);
            ServletUtils.sendOK(response);
        }

    }

}

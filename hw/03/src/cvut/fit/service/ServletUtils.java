package cvut.fit.service;

import cvut.fit.config.Configuration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class ServletUtils {


    public static Optional<Booking> getValidatedBooking(HttpServletRequest request, HttpServletResponse response, BookingState allowedState) throws IOException {
        Object bookingObj = request.getSession().getAttribute(Configuration.BOOKING_SESSION);

        if (bookingObj == null) {
            response.sendError(403, "MISSING BOOKING\n");
            return Optional.empty();
        }

        Booking booking = (Booking) bookingObj;

        if (!booking.getState().equals(allowedState)) {
            response.sendError(403, "INVALID STATE\n");
            return Optional.empty();
        }

        return Optional.of(booking);
    }

    public static void sendOK(HttpServletResponse response) throws IOException {
        response.setStatus(200);
        response.getWriter().println("OK\n");
    }
}

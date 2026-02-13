
package AirlineSystem;

import java.io.IOException;

public interface Bookable {
    void bookTicket(Passenger p, Flight f) throws SeatNotAvailableException;
}
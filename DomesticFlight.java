
package AirlineSystem;

import java.util.Date;

public class DomesticFlight extends Flight {
    public DomesticFlight(String flightNumber, String source, String destination, int totalSeats, Date date){
        super(flightNumber, source, destination, totalSeats, date);
    }
    public void displayFlightInfo() {
        System.out.println("Domestic: "+flightNumber+" | "+source+"->"+destination+" | Seats Left: "+(totalSeats-bookedSeats));
    }
}

package AirlineSystem;
    import java.util.Date;

public class InternationalFlight extends Flight {
    public InternationalFlight(String flightNumber, String source, String destination, int totalSeats, Date date){
        super(flightNumber, source, destination, totalSeats, date);
    }
    public void displayFlightInfo() {
        System.out.println("International: "+flightNumber+" | "+source+"->"+destination+" | Seats Left: "+(totalSeats-bookedSeats));
    }
}


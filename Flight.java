
package AirlineSystem;
import java.util.Date;

public abstract class Flight {
    protected String flightNumber;
    protected String source;
    protected String destination;
    protected int totalSeats;
    protected int bookedSeats;
    protected Date date;

    static {
        System.out.println("=== Airline System Loaded ===");
    } 
    
  {
        System.out.println("Flight object is being created...");   //instance block
    }

    public Flight(String flightNumber, String source, String destination, int totalSeats, Date date) {
        this.flightNumber = flightNumber;
        this.source = source;
        this.destination = destination;
        this.totalSeats = totalSeats;
        this.bookedSeats = 0;
        this.date = date;
       //System.out.println("Flight constructor called");   //constructor block
    }
    public abstract void displayFlightInfo();

    public void bookSeat() throws SeatNotAvailableException {
        if(bookedSeats >= totalSeats) throw new SeatNotAvailableException("No seats available for " + flightNumber);
        bookedSeats++;
    }

    public void cancelSeat() {
        if(bookedSeats > 0) bookedSeats--;
    }

    public Date getDate() { return date; }
    public String getSource() { return source; }
    public String getDestination() { return destination; }
}
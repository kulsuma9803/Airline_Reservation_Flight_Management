
package AirlineSystem;

public class Ticket<T extends Passenger> {
    T passenger;
    Flight flight;

    public Ticket(T passenger, Flight flight){
        this.passenger = passenger;
        this.flight = flight;
    }

    public void showTicketDetails(){
        System.out.println("Passenger: "+passenger.getName()+" | Flight: "+flight.flightNumber);
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("Ticket for "+passenger.getName()+" removed from memory.");
    }
}
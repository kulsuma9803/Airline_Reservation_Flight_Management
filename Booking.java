
package AirlineSystem;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

public class Booking<T extends Ticket<?>> implements Reportable {
    private ArrayList<T> bookings = new ArrayList<>();

    public void addBooking(T t){ bookings.add(t); }
    public void cancelBooking(T t){ bookings.remove(t); }
    public void showAllBookings(){ 
        if(bookings.isEmpty()) System.out.println("No bookings found.");
        else for(T t:bookings) t.showTicketDetails();
    }
    public ArrayList<T> getBookings(){ return bookings; }

    @Override
    public void generateReport() throws IOException {
        try (FileWriter fw = new FileWriter("DailyReport.txt")) {
            if(bookings.isEmpty()) fw.write("No bookings today.\n");
            else {
                for(T t : bookings) {
                    fw.write("Passenger: "+t.passenger.getName()+" | Flight: "+t.flight.flightNumber+"\n");
                }
            }
        }
        System.out.println("Daily report generated: DailyReport.txt");
    }
}
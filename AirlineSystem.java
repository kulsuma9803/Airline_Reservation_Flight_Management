package AirlineSystem;
import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;

public class AirlineSystem {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Flight> flights = new ArrayList<>();
    static Booking<Ticket<?>> bookingSystem = new Booking<>();
    static SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    public static void main(String[] args) throws Exception {
        // Sample Flights
        flights.add(new DomesticFlight("D101","Dhaka","Chittagong",5,sdf.parse("09-09-2025")));
        flights.add(new DomesticFlight("D102","Dhaka","Mymensingh",7,sdf.parse("10-09-2025")));
        flights.add(new InternationalFlight("I201","Dhaka","Kolkata",8,sdf.parse("10-09-2025")));
        flights.add(new InternationalFlight("I202","Dhaka","London",5,sdf.parse("11-09-2025")));

        BackupDaemon daemon = new BackupDaemon();
        daemon.setDaemon(true);
        daemon.start();

        // Start FlightChecker Threads
        for(Flight f : flights){
            FlightChecker fc = new FlightChecker(f);
            fc.start();
        }

        int choice;
        do{
            System.out.println("\n=== Airline System Menu ===");
            System.out.println("1. View Flights\n2. Search Flights\n3. Book Ticket\n4. Cancel Ticket\n5. Admin Menu\n6. Show All Bookings\n7. Generate Report\n0. Exit");
            System.out.print("Choice: "); choice=sc.nextInt(); sc.nextLine();
            switch(choice){
                case 1: for(Flight f:flights) f.displayFlightInfo(); break;
                case 2: searchFlights(); break;
                case 3: bookTicketFlow(); break;
                case 4: cancelTicketFlow(); break;
                case 5: adminMenu(); break;
                case 6: bookingSystem.showAllBookings(); break;
                case 7: 
                    try { bookingSystem.generateReport(); } 
                    catch(IOException e) { System.out.println("Error generating report: "+e.getMessage()); }
                    break;
                case 0: System.out.println("Exiting..."); break;
                default: System.out.println("Invalid choice!");
            }
        }while(choice!=0);
    }

    public static void bookTicketFlow(){
        try{
            System.out.print("Enter Name: "); String name=sc.nextLine();
            System.out.print("Enter Age: "); int age=sc.nextInt(); sc.nextLine();
            Passenger p = (age<12)? new Child(name,age):(age>=60)? new Senior(name,age):new Adult(name,age);

            System.out.println("Select Flight:");
            for(int i=0;i<flights.size();i++){ System.out.print((i+1)+". "); flights.get(i).displayFlightInfo(); }
            int fchoice=sc.nextInt(); sc.nextLine();
            Flight selectedFlight=flights.get(fchoice-1);

            selectedFlight.bookSeat();
            Ticket<Passenger> ticket=new Ticket<>(p,selectedFlight);
            bookingSystem.addBooking(ticket);
            System.out.println("Ticket booked successfully!");
        }catch(SeatNotAvailableException e){ System.out.println("Error: "+e.getMessage()); }
          catch(Exception e){ System.out.println("Invalid input!"); }
    }

    public static void cancelTicketFlow(){
        try{
            ArrayList<Ticket<?>> all=bookingSystem.getBookings();
            if(all.isEmpty()){ System.out.println("No bookings."); return; }
            bookingSystem.showAllBookings();
            System.out.print("Enter Booking Index to Cancel: "); int idx=sc.nextInt(); sc.nextLine();
            if(idx<1 || idx>all.size()){ System.out.println("Invalid index!"); return; }
            Ticket<?> t=all.get(idx-1);
            t.flight.cancelSeat();
            bookingSystem.cancelBooking(t);
            System.out.println("Booking cancelled!");
        }catch(Exception e){ System.out.println("Error cancelling booking!"); }
    }

    public static void searchFlights(){
        try{
            System.out.print("Enter Source: "); String src=sc.nextLine();
            System.out.print("Enter Destination: "); String dest=sc.nextLine();
            System.out.print("Enter Date (dd-MM-yyyy): "); String dateStr=sc.nextLine();
            Date date = sdf.parse(dateStr);
            boolean found=false;
            for(Flight f:flights){
                if(f.getSource().equalsIgnoreCase(src) && f.getDestination().equalsIgnoreCase(dest) && f.getDate().equals(date)){
                    f.displayFlightInfo(); found=true;
                }
            }
            if(!found) System.out.println("No flights found!");
        }catch(Exception e){ System.out.println("Invalid input!"); }
    }

    public static void adminMenu(){
        System.out.println("\n--- Admin Menu ---");
        System.out.println("1. Add Flight\n2. Remove Flight\n3. Update Flight\n0. Back");
        System.out.print("Choice: "); int choice=sc.nextInt(); sc.nextLine();
        switch(choice){
            case 1: addFlight(); break;
            case 2: removeFlight(); break;
            case 3: updateFlight(); break;
            case 0: return;
            default: System.out.println("Invalid choice!");
        }
    }

    public static void addFlight(){
        try{
            System.out.print("Enter Flight Number: "); String fn=sc.nextLine();
            System.out.print("Enter Source: "); String src=sc.nextLine();
            System.out.print("Enter Destination: "); String dest=sc.nextLine();
            System.out.print("Enter Total Seats: "); int seats=sc.nextInt(); sc.nextLine();
            System.out.print("Enter Date (dd-MM-yyyy): "); String dateStr=sc.nextLine(); Date date=sdf.parse(dateStr);
            System.out.print("Type (D/I): "); String type=sc.nextLine();
            Flight f=(type.equalsIgnoreCase("D"))? new DomesticFlight(fn,src,dest,seats,date):new InternationalFlight(fn,src,dest,seats,date);
            flights.add(f);
            System.out.println("Flight added successfully!");
        }catch(Exception e){ System.out.println("Error adding flight!"); }
    }

    public static void removeFlight(){
        try{
            for(int i=0;i<flights.size();i++){ System.out.print((i+1)+". "); flights.get(i).displayFlightInfo(); }
            System.out.print("Select Flight Index to Remove: "); int idx=sc.nextInt(); sc.nextLine();
            flights.remove(idx-1);
            System.out.println("Flight removed successfully!");
        }catch(Exception e){ System.out.println("Error removing flight!"); }
    }

    public static void updateFlight(){
        try{
            for(int i=0;i<flights.size();i++){ System.out.print((i+1)+". "); flights.get(i).displayFlightInfo(); }
            System.out.print("Select Flight Index to Update: "); int idx=sc.nextInt(); sc.nextLine();
            Flight f = flights.get(idx-1);
            System.out.print("Enter new Total Seats: "); int seats=sc.nextInt(); sc.nextLine();
            f.totalSeats=seats;
            System.out.println("Flight updated successfully!");
        }catch(Exception e){ System.out.println("Error updating flight!"); }
    }

}

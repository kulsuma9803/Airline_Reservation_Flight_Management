
package AirlineSystem;
public class FlightChecker extends Thread {
    Flight flight;
    public FlightChecker(Flight f){ 
        this.flight=f;
    }
    public void run(){
        try{
               {
                //System.out.println("[Checker] Flight "+flight.flightNumber+" Seats Left: "+(flight.totalSeats-flight.bookedSeats));
                Thread.sleep(15000);
            }
        }catch(Exception e){}
    }
}

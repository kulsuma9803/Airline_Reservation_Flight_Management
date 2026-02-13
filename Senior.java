
package AirlineSystem;
public class Senior extends Passenger {
    public Senior(String n,int a){ super(n,a); }
    @Override
    public double calculateFare(double baseFare){ return baseFare*0.7; }
}
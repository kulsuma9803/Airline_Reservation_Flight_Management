
package AirlineSystem;
public class Adult extends Passenger {
    public Adult(String n,int a){ super(n,a); }
    @Override
    public double calculateFare(double baseFare){ return baseFare; }
}
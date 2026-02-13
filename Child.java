
package AirlineSystem;
public class Child extends Passenger {
    public Child(String n,int a){ super(n,a); }
    @Override
    public double calculateFare(double baseFare){ return baseFare*0.5; }
}
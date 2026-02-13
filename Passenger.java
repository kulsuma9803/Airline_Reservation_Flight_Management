
package AirlineSystem;
public class Passenger {
    private String name;
    private int age;

    public Passenger(String name, int age){
        this.name = name;
        this.age = age;
    }
    public String getName()
    { return name; 
    }
    public int getAge(){
        return age;
    }
    public double calculateFare(double baseFare){ return baseFare; }
}
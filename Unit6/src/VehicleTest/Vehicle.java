package VehicleTest;

public class Vehicle {
	//fields
	public double length;
	public double maxSpeed;
	public boolean isMoving;
	public String name;
	//constructor
	
	//methods
	public void move() {
		isMoving = true;
		System.out.println(name + "is moving.");
	}
	public void stop() {
		isMoving = false;
		System.out.println(name + "is stopping");
	}
}

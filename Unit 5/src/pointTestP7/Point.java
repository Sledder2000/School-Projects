package pointTestP7;

public class Point {
	//make fields
	public double x;
	public double y;
	//make constructor (sets values of fields with new comands)
	public Point(double xPos, double yPos) {
		this.x = xPos;
		this.y = yPos;
	}
	//make methods
	
	//prints point
	public String print() {
		return "[" + x + ", " + y + "]";
	}
	
	//draws point
	public void draw() {
		
	}
	//calculate distance to origin
	public double distanceToOrigin() {
		//sxrR((x2 - x1)^2 + (y2 - y1)^2)
		//sxrR((0 - x)^2 + (0 - y)^2)
		//x^2 + y^2
		return Math.sqrt(x * x + y * y);
	}
	
	//1) Methods that just get information without changing states
	public double getx() {
		return x;
	}
	//2) Methods that chage states
	public void setX(double x) {
		this.x = x;
	}
}

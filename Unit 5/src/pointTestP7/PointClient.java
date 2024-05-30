package pointTestP7;

public class PointClient {

	public static void main(String[] args) {
		Point a = new Point(5.0, 17.0);
		//a.x = 5.0;
		//a.y = 17.0;
		
		Point b = new Point(9.0, 40.0);
		//b.x = 9.0;
		//b.y = 40.0;
		
		System.out.println(a.print());
		System.out.println(b.print());
		System.out.println("Distance to Origin" + " " + a.distanceToOrigin() + "");
		System.out.println("Distance to Origin" + " " + b.distanceToOrigin() + "");
	}

}

//draws a complex figure
public class ComplexFigure {

	public static void main(String[] args) {
		drawHexagon();
		drawBowl();
		drawStopSign();
		drawHelmet();
	}
	public static void drawHexagon() {
		drawTop();
		drawBottom();
		System.out.println();
	}
	public static void drawBowl() {
		drawBottom();
		drawLine();
		System.out.println();
	}
	public static void drawStopSign() {
		drawTop();
		System.out.println("|  STOP  |");
		drawBottom();
		System.out.println();
	}
	public static void drawHelmet() {
		drawTop();
		drawLine();
	}
	public static void drawTop() {
		System.out.println("  ______  ");
		System.out.println(" /      \\");
		System.out.println("/        \\");
	}
	public static void drawBottom() {
		System.out.println("\\        /");
		System.out.println(" \\______/");
	}
	public static void drawLine() {
		System.out.println("+--------+");
	}
}

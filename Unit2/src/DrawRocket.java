// Draws a rocket
public class DrawRocket {
	// Rocket's Constant
	public static final int CONE_HEIGHT = 1000; 
	//Main that prints cone, fuel, cone
	public static void main(String[] args) {
		drawCone();
		drawFuel();
		drawCone();
	}
		// Draws both fuel tanks
		public static void drawFuel() {
			lining();
			drawFuelFirst();
			lining();
			drawFuelR();
			lining();
		}
		//draws second fuel tank
		public static void drawFuelR() {
			drawFuel2();
			drawFuel1();
		}
		//Draws first fuel tank
		public static void drawFuelFirst() {
			drawFuel1();
			drawFuel2();
		}
		//Draws the lining of the fuel tank
		public static void lining() {
			System.out.print("+");
			for (int eqatime = 1; eqatime <= CONE_HEIGHT * 2; eqatime++) {
				System.out.print("=*");
			}
			System.out.println("+");
		}
		//Draws the fuel with triangle pointing up
		public static void drawFuel1() {
			for (int line = 1; line <= CONE_HEIGHT; line++) {
				System.out.print("|");
				for (int firstDot = 1; firstDot <= CONE_HEIGHT - line; firstDot++) {
					System.out.print(".");
				}
				for(int littleCone1 = 1; littleCone1 <= line; littleCone1++) {
					System.out.print("/\\");
				}
				for (int midDot = 1; midDot <= (CONE_HEIGHT - line) * 2; midDot++) {
					System.out.print(".");
				}
				for(int littleCone1 = 1; littleCone1 <= line; littleCone1++) {
					System.out.print("/\\");
				}
				for (int firstDot = 1; firstDot <= CONE_HEIGHT - line; firstDot++) {
					System.out.print(".");
				}
				
				System.out.println("|");
			}
		}
		//draws the fuel with triangle pointing down
		public static void drawFuel2() {
			for (int line = 1; line <= CONE_HEIGHT; line++) {
				System.out.print("|");
				for (int firstDot = 1; firstDot <= line - 1; firstDot++) {
					System.out.print(".");
				}
				for(int littleCone1 = 1; littleCone1 <= CONE_HEIGHT - line + 1; littleCone1++) {
					System.out.print("\\/");
				}
				for (int midDot = 1; midDot <= (line - 1) * 2; midDot++) {
					System.out.print(".");
				}
				for(int littleCone1 = 1; littleCone1 <= CONE_HEIGHT - line + 1; littleCone1++) {
					System.out.print("\\/");
				}
				for (int firstDot = 1; firstDot <= line - 1; firstDot++) {
					System.out.print(".");
				}
				
				System.out.println("|");
			}
		}
		
		
		//draws the cone
		public static void drawCone() {
			for (int line = 1; line <= CONE_HEIGHT * 2 - 1; line++) {
				
				for (int space = CONE_HEIGHT * 2 - 1; space >= line; space--) {
					System.out.print(" ");
				}
				for (int forwardSlash = 1; forwardSlash <= line; forwardSlash++) {
					System.out.print("/");
				}
					System.out.print("**");
				for (int backSlash = 1; backSlash <= line; backSlash++) {
					System.out.print("\\");
				}
				System.out.println();
			}
		}
}

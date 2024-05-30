import java.util.Scanner;

/** Prompts a user for 5 fractions and prints them as real numbers
 * 
 * @author s-BLELIVELT
 *
 */
public class FractionsLab {

	public static void main(String[] args) {
		//Greet the user
		System.out.println("Hello there!");
		//instansiate a Scanner to console
		Scanner input = new Scanner(System.in);
		//loop 5 times
			for(int i = 0; i < 5; i++) {
			//Prompt user for command_i>
				System.out.print("Command_" + i + "> ");
			//reads a line from the console and store it in a variable
				String line = input.nextLine();
			
			//call the calculate function and print the result
				
			System.out.println(calculateFraction(line));
			}
		//close Scanner and tell user goodbye
			input.close();
			System.out.println("Goodbye!");
	}
	public static double calculateFraction(String fraction) {
		//create Scanner that
		Scanner parser = new Scanner(fraction);
		//use _ as delimeter
		parser.useDelimiter("_");
		//creates whole int
		int w = parser.nextInt();
		parser.useDelimiter("/");
		//use / as delimiter
		parser.skip("_");
		//create numerator 
		int n = parser.nextInt();
		//creates denominator
		int d = parser.nextInt();
		//close scanner
		parser.close();
		//returns the equation
		double realV = w + (double)n/d;
		return realV;
}
}


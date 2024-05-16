package activity2;

import java.util.Scanner;

/**
 * A simple class to run the Magpie class.
 * @author Laurie White
 * @version April 2012
 */
public class MagpieRunner2 {
    /**
     * Create a Magpie, give it user input, and print its replies.
     */
    public static void main(String[] args) {
        Magpie2 maggie = new Magpie2();
        
        // Print initial greeting, then read first statement
        System.out.println (maggie.getGreeting());
        Scanner in = new Scanner (System.in);
        String statement = in.nextLine();
        
        // As long as the statement is not "Bye", get a response,
        // print it out and then read another statement.
        while (!statement.equalsIgnoreCase("Bye")) {
            System.out.println (maggie.getResponse(statement));
            statement = in.nextLine();
        }
        
        System.out.println("Goodbye!");
        in.close();
    }
}

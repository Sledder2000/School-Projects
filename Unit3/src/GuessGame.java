//create imports
import java.util.Scanner;
import java.util.Random;

public class GuessGame {

    public static int MAX_VALUE = 100;
    static Scanner input = new Scanner(System.in);
    //Main that plays a game and repeats until stoped by user
    public static void main(String[] args) {
        //Haiku
        System.out.println("You will play my game, \nYou will fail it many times, \nBut you will succeed.");
        
        Random ranNum = new Random();
        
        boolean playAgain = true;
        int playerGuess = 0;
        String playerAnswer = "";
        int totalGuesses = 0;
        int totalGames = 0;
        int bestGame = 1000000000;
        //While loops for if the user wants to play again
        while (playAgain == true) {
        	boolean wrongGuess = true;
        	int gameGuesses = 0;
        	int guessNum = ranNum.nextInt(MAX_VALUE + 1);
        	System.out.println("Im thinking of a number between 1 and " + MAX_VALUE + ("..."));
        	//While loop for if the guess is right, if not repeat
        	while (wrongGuess == true) {
        		wrongGuess = playGame(wrongGuess, playerGuess, guessNum);
        		gameGuesses += 1;
        	}
        	//Counts up stats
        	totalGuesses += gameGuesses;
        	totalGames += 1;
        	if (gameGuesses < bestGame) {
        		bestGame = gameGuesses;
        	}
        		System.out.println("You got it right in " + gameGuesses + " guesses!");
        		System.out.print("Do you want to play again? (Yes or No)");
            	System.out.println();
        		playerAnswer = input.next();
        		if (playerAnswer.equals("No")) {
        			playAgain = false;
        		}
        	}
       calculateStats(totalGames, totalGuesses, bestGame);
        
        }
    /** Gives the Stats of all the games combined
     * 
     * @param totalGames the total number of games played
     * @param totalGuesses the total number of guesses 
     * @param bestGame the best game played
     */
    public static void calculateStats(int totalGames, int totalGuesses, int bestGame) {
    	System.out.println("Overall Stats:");
        System.out.println("Total Games = " + totalGames);
        System.out.println("Total Guesses = " + totalGuesses);
        System.out.println("Guesses/Game = " + (double) ((double) (totalGuesses) / totalGames));
        System.out.println("Best game = " + bestGame);
    }
    /** Plays  one game with the user
     * 
     * @param wrongGuess if the guess is right
     * @param playerGuess the players guess
     * @param guessNum the random guess the program picked
     * @return if the guess is right or wrong
     */
    public static boolean playGame(boolean wrongGuess, int playerGuess, int guessNum) {
    		System.out.print("Your guess? ");
    		playerGuess = input.nextInt();
    		if (playerGuess == guessNum) {
    			wrongGuess = false;
    		}
    		if (playerGuess > guessNum) {
    			System.out.println("It's lower.");
    		} else if (playerGuess < guessNum) {
    			System.out.println("it's higher.");
    		}
    		return wrongGuess;
    }
}


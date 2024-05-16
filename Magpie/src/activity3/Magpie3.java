package activity3;

/**
 * A program to carry on conversations with a human user.<br>
 * This version:<br>
 * - Uses advanced search for keywords<br>
 * @author Laurie White
 * @version April 2012
 */
public class Magpie3
{
    /**
     * Get a default greeting.
     * @return a greeting
     */
    public String getGreeting() {
        return "Hello, let's talk.";
    }

    /**
     * Gives a response to a user statement
     * @param statement - the user statement
     * @return a response based on the rules given
     */
    public String getResponse(String statement) {
        String response = "";
        
        if (statement.length() == 0) {
            response = "Say something, please.";
        } else if (findKeyword(statement, "no") >= 0) {
            response = "Why so negative?";
        } else if (findKeyword(statement, "mother") >= 0
                || findKeyword(statement, "father") >= 0
                || findKeyword(statement, "sister") >= 0
                || findKeyword(statement, "brother") >= 0) {
            response = "Tell me more about your family.";
        } else {
            response = getRandomResponse();
        }
        
        return response;
    }

    /**
     * Search for one word in phrase. The search is case insensitive. 
     * This method will check that the given goal is not a substring 
     * of a longer string (for example, "I know" does NOT contain the
     * word "no").
     * @param statement - the string to search
     * @param goal - the string to search for
     * @param iStart - the index in the string where to start searching from.
     * @return the index of the first occurrence of goal in statement 
     *         or -1 if it's not found
     */
    private int findKeyword(String statement, String goal, int iStart)
    {
        String phrase = statement.trim().toLowerCase();
        goal = goal.toLowerCase();

        // The only change to incorporate the startPos is in
        // the line below
        int i = phrase.indexOf(goal, iStart);

        // Refinement--make sure the goal isn't part of a
        // word
        while (i >= 0) {
            // Find the string of length 1 preceding word, if one exists
            String before = " ";
            if (i > 0) {
                before = phrase.substring(i - 1, i);
            }
            
            // Find the string of length 1 following word, if one exists
            String after = " ";
            if (i + goal.length() < phrase.length()) {
                after = phrase.substring(
                        i + goal.length(),
                        i + goal.length() + 1);
            }

            // If before and after aren't letters, we've found the word
            if (!isLetter(before) && !isLetter(after)) {
                return i;
            }

            // The last position didn't work, so let's find
            // the next, if there is one.
            i = phrase.indexOf(goal, i + 1);
        }

        return -1;
    }
    
    /**
     * Determines if a string contains one alphabetic character only (a-z).
     * @param str - string to test
     * @return true if the string is one character, in alphabetic range (a-z).
     */
    public boolean isLetter(String str) {
        return (str.length() == 1 
            && str.compareTo("a") >= 0
            && str.compareTo("z") <= 0);
    }

    /**
     * Search for one word in phrase. The search is not case sensitive.
     * This method will check that the given goal is not a substring of
     * a longer string (for example, "I know" does not contain "no").
     * The search begins at the beginning of the string.
     * @param statement - the string to search
     * @param goal - the string to search for
     * @return the index of the first occurrence of goal in
     *         statement or -1 if it's not found
     */
    private int findKeyword(String statement, String goal) {
        return findKeyword(statement, goal, 0);
    }

    /**
     * Pick a default response to use if nothing else fits.
     * @return a noncommittal string
     */
    private String getRandomResponse() {
        final int NUMBER_OF_RESPONSES = 4;
        int whichResponse = (int) (Math.random() * NUMBER_OF_RESPONSES);
        String response = "";

        if (whichResponse == 0) {
            response = "Interesting, tell me more.";
        } else if (whichResponse == 1) {
            response = "Hmmm.";
        } else if (whichResponse == 2) {
            response = "Do you really think so?";
        } else if (whichResponse == 3) {
            response = "You don't say.";
        }

        return response;
    }
}

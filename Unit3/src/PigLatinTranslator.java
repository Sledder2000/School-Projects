import java.util.Scanner;
/** Prompts user for sentences and converts each to pig latin 
 * 
 * @author s-BLELIVELT
 *
 */
public class PigLatinTranslator {

	public static void main(String[] args) {
		// Promps user for sentence
		Scanner input = new Scanner(System.in);
		System.out.print("Type something: ");
		String line = input.nextLine();
		
		//Keep going while user types something
		while(line.length() > 0) {
			// get converted versions
			String converted = convertSentence(line);
			System.out.println(converted);
			
			System.out.print("Type something: ");
			line = input.nextLine();
		}
		System.out.println("Bye");
		input.close();
	}
		/**
		 * reads a line and converts to pig latin
		 * @param line
		 * @return pig latin version
		 */
		public static String convertSentence(String line) {
			// Make scanner of the line
			Scanner lineScan = new Scanner(line);
					String result = "";
					while(lineScan.hasNext()) { // Keep going brother
						String currantWord = lineScan.next();
						String converted = convertWord(currantWord);
						result = result + converted + " ";
					}
					return result;
		}
		/** Reads single word and converts it
		 * 
		 * @param line
		 * @return pig latin version
		 */
		public static String convertWord(String word) {
			//find location of first vowel
			int firstVowel = -1; //-1 means not found
			for(int i = 0; i < word.length() && firstVowel == -1; i++) {
				char c = word.charAt(i);
				if (isVowel(c)) {
					firstVowel = i;
				}
			}
			String pigLatinWord = word;
			//build string
			if(firstVowel > 0) {
				pigLatinWord = word.substring(firstVowel) + word.substring(0, firstVowel);
			}
			return pigLatinWord + "ay";
		}
		
		/**Returns true if the character is a vowel
		 * 
		 * @param c character
		 * @return true if vowel
		 */
		public static boolean isVowel(char c) {
			c = Character.toLowerCase(c);
			if (c == 'a' || c== 'o' || c== 'u' || c == 'i' || c == 'e' || c == 'y') {
				return true;
			} else {
			return false;
		}
	}
	

}

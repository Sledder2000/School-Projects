//Benji Lelivelt
//9/24/2021
//AP Computer Science A
//Period 7
//Assignment #1

//This project will print a song in structured static methods
public class SongProject {
	public static void main(String[] args) {
		printVerse1();
		printVerse2();
		printVerse3();
		printVerse4();
		printVerse5();
		printVerse6();
		printVerse7();
	}
	//Prints verse 1
	public static void printVerse1() {
		System.out.println("There was an old woman who swallowed a fly,");
		printRepeated1();
	}
	//prints repeated part of verse 1
	public static void printRepeated1() {
		System.out.println("I don't know why she swallowed that fly,");
		System.out.println("Perhaps she'll die.");
		System.out.println();
	}
	//Prints verse 2
	public static void printVerse2() {
		System.out.println("There was an old woman who swallowed a spider,");
		System.out.println("That wiggled and iggled and jiggled inside her.");
		printRepeated2();
	}
	//prints repeated part of verse 2
	public static void printRepeated2() {
		System.out.println("She swallowed the spider to catch the fly,");
		printRepeated1();
	}
	//Prints verse 3
	public static void printVerse3() {
		System.out.println("There was an old woman who swallowed a bird,");
		System.out.println("How absurd to swallow a bird.");
		printRepeated3();
	}
	//prints repeated part of verse 3
	public static void printRepeated3() {
		System.out.println("She swallowed the bird to catch the spider,");
		printRepeated2();
	}
	//Prints verse 4
	public static void printVerse4() {
		System.out.println("There was an old woman who swallowed a cat,");
		System.out.println("Imagine that to swallow a cat.");
		printRepeated4();
	}
	//prints repeated part of verse 4
	public static void printRepeated4() {
		System.out.println("She swallowed the cat to catch the bird");
		printRepeated3();
	}
	//Prints verse 5
	public static void printVerse5() {
		System.out.println("There was an old woman who swallowed a dog,");
		System.out.println("What a hog to swallow a dog");
		printRepeated5();
	}
	//prints repeated part of verse 5
	public static void printRepeated5() {
		System.out.println("She swallowed the dog to catch the cat");
		printRepeated4();
	}
	//Prints verse 6
	public static void printVerse6() {
		System.out.println("There was an old woman who swallowed a coyote,");
		System.out.println("Her head must have bean floaty to swallow a coyote.");
		printRepeated6();
	}
	//prints repeated part of verse 6
	public static void printRepeated6() {
		System.out.println("She swallowed the coyote to catch the dog,");
		printRepeated5();
	}
	//Prints verse 7
	public static void printVerse7() {
		System.out.println("There was an old woman who swallowed a horse, She");
		System.out.println("died of course.");
	}
}

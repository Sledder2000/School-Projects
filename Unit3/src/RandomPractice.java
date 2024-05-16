
public class RandomPractice {

	public static void main(String[] args) {
		int randomNumber1 = (int) (Math.random() * 3);
		System.out.println(randomNumber1);
		int randomNumber2 = (int) (Math.random() * 10999) + 1002;
		System.out.println(randomNumber2);
		int randomNumber3 = (int) (Math.random() * 51) * 2 + 100;
		System.out.println(randomNumber3);
		int range = (int) 'z' + 1 -(int) 'a';
		char Randomletter = (char) ((Math.random() * 26) + (int) 'a');
		System.out.println(Randomletter);
	}

}

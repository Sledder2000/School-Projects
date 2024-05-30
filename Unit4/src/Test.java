
public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String text = "foobar";
		for (int i = 1; i < text.length(); i+=2) {
			System.out.print(text.substring(i-1, i + 1));
		}
	}

}

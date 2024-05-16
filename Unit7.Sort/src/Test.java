
public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.print(mystery6(5, 4));
	}
	public static int mystery6(int n, int k) {
	    if (k == 0 || k == n) {
	        return 1;
	    } else if (k > n) {
	        return 0;
	    } else {
	        return mystery6(n - 1, k - 1) + mystery6(n - 1, k);
	    }
	}
	
}

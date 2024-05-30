//Three demonstrations of nested loops
public class NestedForLoops {
	public static void main(String[] args) {
		loop1();
		loop2();
		loop3();
		}
		public static void loop2() {
			for (int i = 1; i <= 5; i++) {
				for (int e = 1; e <= i; e++) {
					System.out.print("*");
			}
				System.out.println();
		}
	}
		public static void loop1() {
			for(int i = 1; i <= 5; i++) {
				for (int e = 1; e <= i; e++) {
					System.out.print("*");
				}
				System.out.println();
		}
	}
		public static void loop3() {
			for(int i = 1; i <= 5; i++) {
				for (int e = 4; e >= i; e--) {
					System.out.print("*");
		}
				System.out.println(i);
			}
		}
	}

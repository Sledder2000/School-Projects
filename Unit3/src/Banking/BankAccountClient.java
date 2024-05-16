package Banking;

public class BankAccountClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		BankAccount b = new BankAccount(1234.0);
		
		System.out.println(b);
		b.addMoney(66);
		System.out.println(b);
		
		BankAccount x = new BankAccount(1000.0);
		System.out.println(x);
	}

}

package Banking;

public class BankAccount {
	private static int accounts = 0;
	
	private double balance;
	private int accountID;
	
	public BankAccount(double balance) {
		this.balance = balance;
		BankAccount.accounts++;
		this.accountID = accounts;
	}
	
	public double getBalance() {
		return this.balance;
	}
	
	public void addMoney(double amount) {
		if (amount < 0) {
			throw new IllegalArgumentException("Stop Bro");
		}
		this.balance += amount;
	}
	
	public String toString() {
		return "Account #" + this.accountID + " balance: " + balance;
	}
}

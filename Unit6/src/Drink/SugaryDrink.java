package Drink;

public class SugaryDrink extends Drink{
	private boolean containsJuice;
	
	public SugaryDrink(String name, boolean hasCarbonation, boolean cjuice, double sugar) {
	     // must be first line after constructor header
	     super(name, hasCarbonation, sugar);

	     containsJuice = cjuice;
	   }
	
	public void printDrinkLabel () {
		if (containsJuice) {
			System.out.println("Contains real fruit juice");
		}
		System.out.println("Contains no acual juice");
	}
}

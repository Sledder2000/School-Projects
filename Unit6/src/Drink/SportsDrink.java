package Drink;

public class SportsDrink extends Drink {
	private boolean electrolytes;
	private double ounces;
	
	public SportsDrink(String name, boolean hasCarbonation, boolean e, double sugar, double o) {
	     super(name, hasCarbonation, sugar);
	     electrolytes = e;
	     ounces = o;
	   }
	
	public boolean equals(Object obj) {
		 if (obj instanceof Drink) {
			 Drink otherDrink = (Drink)obj;
			 return otherDrink.ounces == this.ounces;
		 } else {
			 return false;
		 }
	}
}

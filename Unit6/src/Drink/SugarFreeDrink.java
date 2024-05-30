package Drink;

public class SugarFreeDrink extends Drink {
	   private boolean hasSweetener;
	   private double  caffeineContent;

	   public SugarFreeDrink(String name, boolean hasCarbonation, boolean h, double c, int o) {
	     super(name, hasCarbonation, 0.0);
	     hasSweetener = h;
	     caffeineContent = c;
	   }

	   public void printDrinkLabel() {
	     if (hasSweetener) {
	         System.out.println("This drink is not all natural");
	     } else {
	         System.out.println("This drink contains no artificial sweeteners.");
	     }
	   }
}
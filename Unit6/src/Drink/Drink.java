package Drink;

public class Drink {
	   private String  name;
	   private boolean hasCarbonation;
	   private double  gramsOfSugar;
	   protected double ounces;

	   public Drink (String n, Boolean h, double g) {
	       name = n;
	       hasCarbonation = h;
	       gramsOfSugar = g;
	       ounces = 8;           //FDA defines a serving as 8 oz.
	   }

	   public void chug (double gulp) {
	       if (ounces < gulp) {
	           throw new IllegalArgumentException ("Not enough " + name + " left.");
	       } else {
	           System.out.println ("Glug, glug, glug!");
	           ounces -=gulp;
	           System.out.println("You have " + ounces + "oz. of " + name + " left.");
	       }
	   }

	   public String getState() {
	       return "liquid";
	   }

	   public void printLabel() {
	       System.out.println ("Enjoy refreshing " + name + " !");
	   }
	}

	
	   
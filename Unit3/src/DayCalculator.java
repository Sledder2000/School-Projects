import java.util.Scanner;

/** Makes user input day, month and year.
 * 
 * @author s-BLELIVELT
 *
 */
public class DayCalculator {

	public static void main(String[] args) {
		//prompt the user for day, month and a year
		Scanner input = new Scanner(System.in);
		//get the day month and year from the user
		System.out.print("Day: ");
		int day = input.nextInt();
		System.out.print("Month: ");
		int month = input.nextInt();
		System.out.print("Year: ");
		int year = input.nextInt();
		System.out.println("The day of the week is: " + calculateDay(day, month, year));
		input.close();
	}
	
	
	
	/** tells if year is leap year.
	 * 
	 * parameter is year input
	 * returns true if it is a leap year, false if not a leap year.
	 */
	public static boolean isLeapYear(int year) {
		if(year % 400 == 0) {
			return true;
		} else if(year % 100 == 0) {
			return false;
		} else if(year % 4 == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	/** Tells how many days in year
	 * 
	 * parameter is the year
	 * returns the number of days in the year
	 */
	public static int daysInYear(int year) {
		if(isLeapYear(year)) {
			return 366;
		} else {
			return 365;
		}
	}
	
	/** Calculates the number of days in a month
	 * 
	 * @param month
	 * @param year 
	 * @return Number of days in month
	 */
	public static int daysInMonth(int month, int year) {
		if(month == 4 || month == 6 || month == 9 || month == 11) {
			return 30;
		} else if(month == 2 && isLeapYear(year) == true) {
			return 29;
		} else if(month == 2) {
			return 28;
		} else {
			return 31;
		}
	}
	
	/** calculates the number of days since 1_1_1601
	 * 
	 * @param day
	 * @param month
	 * @param year
	 * @return days since 1/1/1601
	 */
	public static int daysSince1_1_1601(int day, int month, int year) {
		//set up accumulator
		int totalDays = 0;
		//count years
		for(int y = 1601; y < year; y++) {
			totalDays += daysInYear(y);
		}
		//count months
		for(int m = 1; m < month; m++) {
			totalDays += daysInMonth(m, year);
		}
		//count days
		totalDays += day - 1;
		return totalDays;
	}
	
	/** Calculates the day of the week given that 1/1/1601 is a Monday
	 * 
	 * @param day current day
	 * @param month current month
	 * @param year current year
	 * @return The day as a String
	 */
	public static String calculateDay(int day, int month, int year) {
		//days since 1/1/1601
		int daysElapsed = daysSince1_1_1601(day, month, year);
		//0 is monday, 6 is sunday
		int dayOfWeek = daysElapsed % 7;
		String week = "";
		if(dayOfWeek == 0) {
			week = "Monday";
		} else if(dayOfWeek == 1) {
			week = "Tuesday";
		} else if(dayOfWeek == 2) {
			week = "Wednesday";
		} else if(dayOfWeek == 3) {
			week = "Thursday";
		} else if(dayOfWeek == 4) {
			week = "Friday";
		} else if(dayOfWeek == 5) {
			week = "Saturday";
		} else {
			week = "Sunday";
		}
		return week;
	}
}

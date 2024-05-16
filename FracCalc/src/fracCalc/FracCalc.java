package fracCalc;

import java.util.Scanner;

public class FracCalc {

    public static void main(String[] args) {
    	 Scanner input = new Scanner(System.in);
    	 System.out.print("enter one line> ");
     	 while (input.hasNext()) {
    	 String line = input.nextLine();
    	 String result = produceAnswer(line);
    	 System.out.println(result);
    	 System.out.print("enter one line> ");
     	}
    }
    
    
    public static String parseMixed(String mix) {
    	String nums = "";
    	if (mix.indexOf("_") == -1 && mix.indexOf("/") == -1) {
    		nums += mix + "_0/1";
    	} else if (mix.indexOf("_") == -1) {
    		nums += "0_" + (mix.substring(0, mix.indexOf("/"))) + "/";
    		nums += (mix.substring(mix.indexOf("/") + 1, mix.length()));
    	} else if (mix.indexOf("/") == -1) {
    		nums += (mix.substring(0, mix.indexOf("_"))) + "_";
    		nums += (mix.substring(mix.indexOf("_") + 1, mix.length())) + "/1";
    	} else {
    		nums += (mix.substring(0, mix.indexOf("_"))) + "_";
    		nums += (mix.substring(mix.indexOf("_") + 1, mix.indexOf("/")) + "/");
    		nums += (mix.substring(mix.indexOf("/") + 1, mix.length()));
    	}
    	return nums;
    	
    }
    // ** IMPORTANT ** DO NOT DELETE THIS FUNCTION.  This function will be used to test your code
    // This function takes a String 'input' and produces the result
    //
    // input is a fraction string that needs to be evaluated.  For your program, this will be the user input.
    //      e.g. input ==> "1/2 + 3/4"
    //        
    // The function should return the result of the fraction after it has been calculated
    //      e.g. return ==> "1_1/4"
    public static String produceAnswer(String input)
    { 
    	String result = "";
        Scanner parser = new Scanner(input);
    	String operand1 = parser.next();
    	String operator = parser.next();
    	String operand2 = parser.next();
    	String nums1 = impFrac(parseMixed(operand1)); 
    	String nums2 = impFrac(parseMixed(operand2)); 
    	if (operator.equals("+")) {
    		result = (Integer.parseInt(nums1.substring(0, nums1.indexOf("/"))) * (Integer.parseInt(nums2.substring(nums2.indexOf("/") + 1))) + Integer.parseInt(nums2.substring(0, nums2.indexOf("/"))) * Integer.parseInt(nums1.substring(nums1.indexOf("/") + 1))) + "/" + ((Integer.parseInt(nums1.substring(nums1.indexOf("/") + 1))) * Integer.parseInt(nums2.substring(nums2.indexOf("/") + 1)));
    	} else if (operator.equals("-")) {
    		result = (Integer.parseInt(nums1.substring(0, nums1.indexOf("/"))) * (Integer.parseInt(nums2.substring(nums2.indexOf("/") + 1))) - Integer.parseInt(nums2.substring(0, nums2.indexOf("/"))) * Integer.parseInt(nums1.substring(nums1.indexOf("/") + 1))) + "/" + ((Integer.parseInt(nums1.substring(nums1.indexOf("/") + 1))) * Integer.parseInt(nums2.substring(nums2.indexOf("/") + 1)));
    	} else if (operator.equals("*")) {
    		result = (Integer.parseInt(nums1.substring(0, nums1.indexOf("/"))) * Integer.parseInt(nums2.substring(0, nums2.indexOf("/")))) + "/" + (Integer.parseInt(nums1.substring(nums1.indexOf("/") + 1)) * Integer.parseInt(nums2.substring(nums2.indexOf("/") + 1)));
    	} else if (operator.equals("/")) {
    		result = (Integer.parseInt(nums1.substring(0, nums1.indexOf("/"))) * (Integer.parseInt(nums2.substring(nums2.indexOf("/") + 1)))) + "/" + (Integer.parseInt(nums2.substring(0, nums2.indexOf("/"))) * (Integer.parseInt(nums1.substring(nums1.indexOf("/") + 1))));
    	}
    	int gcf = GCF(Integer.parseInt(result.substring(0, result.indexOf("/"))), Integer.parseInt(result.substring(result.indexOf("/") + 1)));
    	int num = (Integer.parseInt(result.substring(0, result.indexOf("/"))) / gcf);
    	int nom = (Integer.parseInt(result.substring(result.indexOf("/") + 1)) / gcf);
    	String trueResult = (num / nom) + "_";
    		   trueResult += (num % nom) + "/" + nom;
    		   if (trueResult.substring(trueResult.indexOf("_") + 1, trueResult.indexOf("_") + 2).equals("-")) {
    	    	trueResult = (num / nom) + "_" + (((num % nom) - (num % nom)) - (num % nom)) + "/" + nom;
       	}
       	if (trueResult.substring(trueResult.indexOf("/") + 1, trueResult.indexOf("/") + 2).equals("-")) {
   		trueResult = (num / nom) + "_" + (num % nom) + "/" + (nom - nom - nom);
    	}
    	if (Integer.parseInt(trueResult.substring(0, trueResult.indexOf("_"))) == 0) {
        	trueResult = num % nom + "/" + nom;
        } 
    	if (Integer.parseInt(trueResult.substring(trueResult.indexOf("_") + 1, trueResult.indexOf("/"))) == 0) {
        	trueResult = Integer.toString(num / nom);
        }
    	return trueResult;
    } 

    public static String impFrac(String operator) {
    	String result = "";
    	int whole = Integer.parseInt(operator.substring(0, operator.indexOf("_")));
    	int numerator = Integer.parseInt(operator.substring(operator.indexOf("_") + 1, operator.indexOf("/")));
    	int denominator = Integer.parseInt(operator.substring(operator.indexOf("/") + 1, operator.length()));
    	if (operator.substring(0, 1).equals("-")) {
    		numerator = (whole * denominator) - numerator;
    	} else {
    	numerator = (whole * denominator) + numerator;
    	}
    	result = numerator + "/" + denominator;
    	return result;
    }
    public static int GCF (int denom1, int denom2) {
    	while (denom1 % denom2 != 0) {
    		int temp = denom2;
    		denom2 = denom1 % denom2;
    		denom1 = temp;
    	}
    	return denom2;
    }
    
}

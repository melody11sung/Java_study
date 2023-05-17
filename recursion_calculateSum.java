package M5_Recursion;

import java.util.Scanner;

public class recursion_calculateSum {
	
	// test
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("<<<<< Welcome to Recursion Sum >>>>>/n");
		boolean loopEnd = false;
		
		do {
			System.out.print("How many numbers to you want to calculate sum? ");
			if (sc.hasNextInt()) {
				int num = sc.nextInt(); // take user input int
				Double sum = recursionSum(num);  // use recursionSum method
				System.out.println("/n<Total Sum> " + sum);
				loopEnd = true;
			} else { // error control
				System.out.print("<Invalid Input> Try again. ");
				sc.next();
			}
		} while (loopEnd==false); // loop unless the user puts an integer
	}

	
	// a method that takes 'num' number of doubles from the user
	// and calculate its sum
	public static Double recursionSum(int num) {
		
		Scanner sc = new Scanner(System.in);
		Double sum = 0.0;
		boolean loopEnd=false;
		
		if (num == 0) { // base case
			return 0.0;
			
		} else { // recursion part
			
			do { // to prevent input error and exceptions
				System.out.print("What number do you want to add? ");
				if (sc.hasNextDouble()) { // error control - only with double input
					Double input = sc.nextDouble(); // take a user input double
					sum = input + recursionSum(num-1); // add all numbers by recursion
					loopEnd=true;
				} else { // if the user did not put numbers
					System.out.print("<Invalid Input> Try Again. ");
					sc.next();
				}
			} while (loopEnd==false); // loop unless sum is calculated
			
			return sum; // the output
		}
		
	} // end recursionSum
	
} // end class

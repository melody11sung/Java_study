package M5_Recursion;

public class recursion_iteration_arrayBackwardSort {
	
	// methods that prints 'num' elements of array in backwards,
	// one using recursion,
	// and another using iteration
	
	
	// a method using recursion
	public static void recursionPrint(String[] array, int num) {
		
		if (num > 0) {
			System.out.print(array[num-1] + " ");
			recursionPrint(array, num-1);  // repeat recursion
		} else { // when index reaches 0, baseline
			System.out.print("<the end>");
		}
		
	}
	
	
	// another method using iteration
	public static void iterationPrint(String[] array, int num) {
		
		for (int i=num; i>0; i--) {
			System.out.print(array[i-1] + " ");
		}
	}
	
	
	// test
	public static void main(String[] args) {
		
		String[] array = {"a","b","c","d","e","f","g"};
		
		System.out.println("1. recursionPrint(array, 5)");
		recursionPrint(array, 5);

		System.out.println("\n\n2. iterationPrint(array, 3)");
		iterationPrint(array, 3);
		
	}

}

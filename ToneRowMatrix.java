import java.util.Scanner;

/**
 * Calculates and prints all 48 rows for use in a twelve-tone composition
 * based on a given prime row.
 * <p>
 * Date: 1/8/2016
 * @author Bob Richey
 */

public class ToneRowMatrix {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
				
		final int SIZE = 12;
		int[][] matrix = new int[SIZE][SIZE];
		
		System.out.print("ENTER PRIME ROW: "); 
		
		for (int i = 0; i < SIZE; i++) {
			matrix[0][i] = input.nextInt();
		}
		
		int[] intervals = new int[SIZE - 1];
		
		// Calculate number of half steps (descending) between each pitch
		for (int i = 0; i < intervals.length; i++) {
			intervals[i] = ((matrix[0][i] - matrix[0][i + 1]) + SIZE) % SIZE;
		}
		
		// Fill matrix based on prime row and intervals
		for (int i = 1; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				matrix[i][j] = (matrix[i - 1][j] + intervals[i - 1]) % SIZE;
			}
		}
		
		printMatrix(matrix, intervals);
	}
	
	/**
	 * Prints the tone rows with labels for primes, inverstions, retrogrades,
	 * and retrograde-inverstions. Includes printout of prime intervals.	
	 *
	 * @param matrix    a 2d array containing all tone rows
	 * @param intervals an array containing the intervals between each tone
	 *                  in the prime row
	 */
	public static void printMatrix(int[][] matrix, int[] intervals) {
		String[] pitches = {"C", "C#", "D", "D#", "E", "F", 
			"F#", "G", "G#", "A", "A#", "B"};
		
		System.out.print("PRIME INTERVALS (DESCENDING): "); 
		
		for (int i = 0; i < intervals.length; i++) {
			System.out.print(intervals[i] + " "); 
		}		
		System.out.println("\n\n"); 
		
		// Print row matrix with labels
		System.out.println("                            INVERSIONS \u21d3");
		System.out.println(); 
		
		for (int i = 0; i < matrix[0].length; i++) {
			if (i == 5) {
				System.out.print("PRIMES \u21d2");
			}
			else {
				System.out.print("        ");
			}
			for (int j = 0; j < matrix[0].length; j++) {
				int pitch = matrix[i][j];
				System.out.printf("%4s", pitches[pitch]); 
			}
			if (i == 5) {
				System.out.print("  \u21d0 RETROGRADES");
			}
			System.out.println(); 
		}
		System.out.println();
		System.out.println("                       RETROGRADE-INVERSIONS \u21d1");
		System.out.println(); 
	}
}
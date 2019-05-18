/*Chris Liang
 * CS1450 Sec 001
 * Assignment 1
 * Due: August 29th 10:50 A.M.
 * The Program takes 25 randomly generated numbers, stores them in an array, 
 * outputs the numbers to a .txt file in ascending order while calculating the sum, mean, median and mode, 
 * then displaying the results to the console.
 */
import java.util.*;
import java.io.*;

public class cliangAssignment1 {
	public static void main(String[] args) {
		int[] Arraynumbers = new int[25];
		double sum = 0;
		double avg;
		File file = new File("Assignment1.txt");
		
	//Filling array with random numbers 0-99
		System.out.println("The numbers in the array are:");
		for (int i=0; i<Arraynumbers.length; i++) {
			Arraynumbers[i] = (int)(Math.random()*100);
				System.out.println(Arraynumbers[i]);
					sum += Arraynumbers[i];
		}//end for loop
		
	//Sum
		System.out.println("\nThe Sum is: "+(sum));
		
	//Mean
			avg = sum/25;
		System.out.println("The Mean is: "+(avg));
		
	//Median
		Arrays.sort(Arraynumbers);
			double median = Arraynumbers[Arraynumbers.length/2];
			System.out.println("The Median is: "+ (median));
			
	//Mode
			int maxNumber = -1;
			int maxAppearances = -1;
			
			for(int i=0; i<Arraynumbers.length; i++) {
				int count = 0;
				for(int j=0; j<Arraynumbers.length; j++) {
					
					if(Arraynumbers[i]==Arraynumbers[j])
						count++;
				}//end if loop
				if(count>maxAppearances) {
					
					maxNumber = Arraynumbers[i];
					maxAppearances = count;
				}//end if loop
			}//end for loop
			System.out.println("The Mode is: "+ maxNumber+" and occured "+maxAppearances+ " times.");
			
	//Sorting numbers in ascending order	
		for (int i=0; i<Arraynumbers.length; i++) {
		}//end for loop
		
	//write to Assignment1.txt
		try {
			PrintWriter output = new PrintWriter(file);
			for(int i=0; i<Arraynumbers.length;i++) {
				output.println(Arraynumbers[i]);
			}//end for loop
			output.close();
		}//end try	
		catch (IOException ex) {
			System.out.println("Error");
		}//end catch
		
	//read from assingment1.txt
		System.out.println("\nThe numbers from the file in ascending order:");
		try {
			FileReader reader = new FileReader("Assignment1.txt");
			BufferedReader bufferedReader = new BufferedReader(reader);
			
			String sortedNumbers;
			while((sortedNumbers = bufferedReader.readLine()) != null){
				System.out.println(sortedNumbers);
			}//end while loop
			reader.close();
		}//end try
		catch (IOException e) {
			System.out.println("Error");
		}//end catch
	}//end main
}//end class	
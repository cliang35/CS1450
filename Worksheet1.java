// Worksheet1 - Stacks

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Worksheet1 {

	public static void main(String[] args) throws IOException {
		
		// Name of file to read from
		final String TRAINS_FILE_NAME = "Worksheet1Trains.txt";	

		// Setup a file reference variable to refer to text file
		File trainsFileName = new File(TRAINS_FILE_NAME);

		// First value in the file tells how many trains are in the file
		Scanner trainsFile = new Scanner (trainsFileName);
		int numTrainsInFile = trainsFile.nextInt();

		//************************
		// Worksheet1 Question #2: 
		// Write the code to create a stack of trains
		// ***********************
		// ADD YOUR CODE HERE

		GenericStack<Train> trainStack = new GenericStack<Train>();
		
		// Create the train objects from the details provided in the file
		for(int i = 0; i < numTrainsInFile; i++) {
			
			// Read information from file and create a train
			int engineNumber = trainsFile.nextInt();
			String destCity = (trainsFile.nextLine()).trim();

			Train1w train = new Train1w(engineNumber, destCity);		

			// Print the train's details
			System.out.println(train.print());
	
			// ***********************
			// Worksheet1 Question #3: 
			// What code is needed to add the train to the stack
			//*************************
			// ADD YOUR CODE HERE

			numTrainsInFile = scan.nextLine();
			trainStack.push(numTrainsInFile);
			
			
		} // for each train
		
		
		//***********************
		// Worksheet1 Question #5
		// What code prints the destination city of each train on the stack.  
		// Use a while loop to perform this task.
		//***********************
		// ADD YOUR CODE HERE
		System.out.println();
		System.out.println();

		
		
		
		
		trainsFile.close();
		
	} // main

} // Worksheet1


//Class representing the generic stack - named it GenericStack1w to avoid name clashing issues
class GenericStack1w<E>  {

	private ArrayList<E> list;		// Storage for stack is provided by an ArrayList
	
	public GenericStack1w() {
		list = new ArrayList<>();
	}
	
	public boolean isEmpty (){
		return list.isEmpty();
	}
	
	public int getSize(){
		return list.size();
	}

	public E peek(){
		return list.get(getSize() - 1);
	}
	
	public E pop(){		
		E value = list.get(getSize()-1);
		list.remove(getSize() - 1);
		return value;
	}

	public void push(E value){
		list.add(value); 
	}
	
} // GenericStack1w


//Represents a train - named it Train1w due to name clashing issues - too many trains! 
class Train1w { 
	
	private int engineNumber;			// Train's engine number, assuming there is only one engine
	private String destinationCity;		// Where train is departing to

	
	public Train1w (int engineNumber, String destinationCity) {
		this.engineNumber= engineNumber;
		this.destinationCity = destinationCity;
	}
		
	public int getEngineNumber() {
		return engineNumber;
	}
	
	public String getDestinationCity() {
		return destinationCity;
	}
	
	// Create and returns a string containing train details 	
	public String print() {
		return String.format("%d\t%-5s", 
							 engineNumber, 
							 destinationCity);
	} // print
		
} // Train1w



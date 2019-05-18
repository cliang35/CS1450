// Worksheet2: Iterators and Arrays

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Worksheet2 {

	public static void main(String[] args) throws IOException {
		
		// Name of file to read from
		final String TRAINS_FILE_NAME = "Worksheet2Trains.txt";	
		File trainsFileName = new File(TRAINS_FILE_NAME);

		// First value in the file tells how many trains are int he file
		Scanner trainsFile = new Scanner (trainsFileName);
		int numTrainsInFile = trainsFile.nextInt();
		
		// Create array to hold train objects then fill array with trains in the file
		Train2w[] denverTrains = new Train2w[numTrainsInFile];
		for(int i = 0; i < numTrainsInFile; i++) {
			
			int engineNumber = trainsFile.nextInt();
			denverTrains[i] = new Train2w(engineNumber);	
			
		} // for each train
		
		
		//*******************************************
		// Worksheet2 Question 2
		// What error occurs with this code if the array contains any null values?  
		//*******************************************
		// ADD CODE TO FIX ISSUE
		// Iterate through array using the train iterator 	
		System.out.println();
		System.out.println("Iterating through array with iterator");
		TrainIterator iterator = new TrainIterator(denverTrains);
		while (iterator.hasNext()) {
			Train2w aTrain = iterator.next();
			System.out.println(aTrain.getEngineNumber());
		}

		trainsFile.close();
		
	} // main

} // Worksheet1

 
class Train2w { 
	
	private int engineNumber;			

	
	public Train2w (int engineNumber) {
		this.engineNumber= engineNumber;
	}
		
	public int getEngineNumber() {
		return engineNumber;
	}
			
} // Train2w



class TrainIterator {
	int currentIndex = 0;
	Train2w[] train;
	
	
	// Sets up the train instance variable to incoming train
	public TrainIterator(Train2w[] trains) {
		// ADD CODE HERE

		
	}
	
	// Returns the next train in the array
	public Train2w next() {
		// ADD CODE HERE

		
	}

	// Returns true if there are trains in the array that the iterator hasn't return yet 
	public boolean hasNext() {
		// ADD CODE HERE

		
	}
	
} // TrainIterator





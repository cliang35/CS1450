// Worksheet3: Nested Objects


import java.util.LinkedList;
import java.util.Queue;

public class Worksheet3 {

	public static void main(String[] args) {
		
		//*******************************************
		// Worksheet3 Question #1a and Question #1b
		// Queue in main - no nesting
		// Practice creating a queue here and adding the values 48, 20, 68
		// Name your queue - queueNotNested
		//*******************************************
		// ADD CODE HERE

		Queue<Integer> queueNotNested = new LinkedList<>();
			queueNotNested.offer(48);
			queueNotNested.offer(20);
			queueNotNested.offer(68);
		
		
		
		
		// Test your non-nested queue by displaying the values in the queue
		System.out.println("Values in a queue that is not nested in a class");
		int queueLength = queueNotNested.size();
		for (int i = 0; i < queueLength; i++) {
			System.out.println("aQueue[" + i + "] = " + queueNotNested.remove());
		}


		//*************************
		// Worksheet3 Question #2
		// Use this code to test your IntegerClass
		// Queue is now in a class
		// Create the IntegerQueue class and add the values 48,20,68 
		//*************************
		// ADD CODE HERE
		IntegerQueue aQueue = new IntegerQueue();

		
		
		
		
		// Test your nested queue by displaying the values in the queue
		System.out.println();
		System.out.println("Values in a queue that is nested in a class");
		queueLength = aQueue.size();
		for (int i = 0; i < queueLength; i++) {
			System.out.println("aQueue[" + i + "] = " + aQueue.remove());
		}
		

		
		//*************************
		// Worksheet3 Question #4
		// Create a railroad yard object that contains a sorting yard with 5 tracks
		// Use this code to test your answers 
		//*************************
		int numberTracks = 4;
		RailroadYard3w railroadYard = new RailroadYard3w(numberTracks+1);

		
		System.out.println();
		System.out.println("Hard coding 2 trains");
		System.out.println("8172 contains 4 rail cars");
		System.out.println("6242 contains 2 rail cars");
		
		// Hard code some values to create three trains - hard coding is the way to go when learning!
		// Of course we would not do this in an assignment!  I'm doing it for testing purposes.
		Train3w train1 = new Train3w(8172);      // Train with engine #8172
		Train3w train2 = new Train3w(6242);      // Train with engine #6242
		
		
		// Add trains to railroad yard - I'm hard coding the track number for trains for quick setup.
		// Of course we would not do this in a regular assignment - this is just for testing purposes
		railroadYard.addTrainToSortingYard(1, train1);
		railroadYard.addTrainToSortingYard(2, train2);
		
		
		// Add some rail cars to each train
		// Hard code rail car number to create 4 rail car objects and add them to train1's queue
		train1.addRailCar(new RailCar3w(11));
		train1.addRailCar(new RailCar3w(12));
		train1.addRailCar(new RailCar3w(62));
		train1.addRailCar(new RailCar3w(81));
		
		// Hard code rail car number to create 2 rail car objects and add them to train2's queue
		train2.addRailCar(new RailCar3w(66));
		train2.addRailCar(new RailCar3w(90));
	
		// Display some information to test the code 
		System.out.println();
		System.out.println("Testing code - displaying train information");
		for (int i = 0; i < numberTracks; i++) {
			Train3w aTrain = railroadYard.getNextTrainInSortingYard();
			if (aTrain != null) {
				System.out.print("Train " + aTrain.getEngineNumber());
				System.out.println(" contains " + aTrain.getRailCarsSize() + " rail cars");				
			}
		}
		
	} // main

} // Worksheet3


class IntegerQueue {
	
	private Queue<Integer> queue = new LinkedList<>();

	public int size() {
		// ADD CODE HERE
		
		
	}
	
	public void offer(Integer value) {
		// ADD CODE HERE

		queue.offer (12);
		queue.offer(21);
		
	}
	
	public Integer remove() {
		// ADD CODE HERE


	}
	
} // IntegerQueue




class RailCar3w  {
	
	private int number;			
	
	public RailCar3w (int number) {
		this.number = number;
	}

	public int getNumber() {
		return number;
	}
	
} // RailCar


//Represents a train 
class Train3w { 
	
	private int engineNumber;			// Train's engine number, assuming there is only one engine

	private Queue<RailCar3w> railCars = new LinkedList<>();  // Queue of rail cars that make up this train

	
	public Train3w (int engineNumber) {
		this.engineNumber= engineNumber;
	}
		
	public int getEngineNumber() {
		return engineNumber;
	}
		
	public int getRailCarsSize() {
		// ADD CODE HERE
		return railCars.size();

	}
	
	public void addRailCar (RailCar3w railCar) {
		// ADD CODE HERE
		railCars.offer(railCar);

	}
	
} // Train


class RailroadYard3w {

	private int numberTracks;		// Number of tracks the railroad's sorting yard contains - track 0 is not used
	private int currentTrack;
	private Train3w[] sortingYard; 	// Array of trains that represent the sorting yard
	
	
	public RailroadYard3w (int numberTracks) {
		this.numberTracks = numberTracks;
		currentTrack = 0;
		sortingYard = new Train3w[numberTracks]; 
	}

	
	// Add train to a specific track in the sorting yard
	// This means, place the train into the array at the location specified by the track number
	public void addTrainToSortingYard (int trackNumber, Train3w train) {
		sortingYard[trackNumber] = train;
	}
	
	
	// Add a rail car to a specific train in the sorting yard
	public void addRailCarTrainInToSortingYard(RailCar3w railCar, int trackNumber) {
		// ADD CODE HERE
		sortingYard[trackNumber].addRailCar(railCar);

		
	}
	 
	
	// Returns the NEXT train in the tracks array.  
	public Train3w getNextTrainInSortingYard() {
		
		Train3w currentTrain = sortingYard[currentTrack];			
		currentTrack++;
		
		return currentTrain;
	}

} // RailroadYard


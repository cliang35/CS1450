// Worksheet4: Priority Queues And Comparable Interface

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Worksheet4 {

	public static void main(String[] args) {

		// *************************
		// Worksheet4 Question 4a
		// Write the declaration for a priority queue of trains (Train4w objects)
		//**************************
		// ADD PRIORITY QUEUE OF TRAINS DECLARATION HERE

		PriorityQueue<Train4w> departureTrack = new PriorityQueue<>();
		
		
		// Use this code to test your answers 
		// Hard code some values to create a three trains - hard coding is the way to go when learning!
		// Of course we would not do this in an assignment!  I'm doing it for testing purposes.
		Train4w train1 = new Train4w(8172);      // Train with engine #8172
		Train4w train2 = new Train4w(6242);      // Train with engine #6242
		
	
		// Add some rail cars to each train
		// Hard code rail car number to create 4 rail car objects and add them to train1's queue
		train1.addRailCar(new RailCar4w(11));
		train1.addRailCar(new RailCar4w(12));
		train1.addRailCar(new RailCar4w(62));
		train1.addRailCar(new RailCar4w(81));
		
		// Hard code rail car number to create 2 rail car objects and add them to train2's queue
		train2.addRailCar(new RailCar4w(66));
		train2.addRailCar(new RailCar4w(90));
		
		
		// *******************************
		// Worksheet4 Question #4c 
		// Write code to add train1 to the priority queue
		//********************************
		// ADD CODE HERE TO ADD TRAIN1 TO PRIORITY QUEUE
		PriorityQueue<Train4w> train1 = new PriorityQueue<>();
		PriorityQueue<Train4w> train2 = new PriorityQueue<>();

		System.out.println("Train #1 in priority queue");
		
		
		// *******************************
		// Worksheet4 Question #4e 
		// Write code to add train2 to the priority queue
		//********************************
		// ADD CODE HERE TO ADD TRAIN2 TO PRIORITY QUEUE AND UNCOMMENT THE PRINT STMT
		departureTrack.offer(train2);

		System.out.println("Train #2 in priority queue");

	} // main
	
} // Worksheet4



class RailCar4w  {
	
	private int number;			
	
	public RailCar4w (int number) {
		this.number = number;
	}

	public int getNumber() {
		return number;
	}
	
} // RailCar



class Train4w { 
	
	private int engineNumber;			// Train's engine number, assuming there is only one engine

	private Queue<RailCar4w> railCars = new LinkedList<>();

	
	public Train4w (int engineNumber) {
		this.engineNumber= engineNumber;
	}
		
	// *************************
	// Worksheet4 Question #4b
	// Add your answer from Worksheet3 here
	// *************************
	public int getRailCarsSize() {
		// ADD CODE HERE
		return engineNumber;
		
	}
	
	// *************************
	// Worksheet4 Question #4b
	// Add your answer from Worksheet3 here
	//**************************
	public void addRailCar(RailCar4w railCar) {
		// ADD CODE HERE

	}
	
	
	// *************************
	// Worksheet4 Question #4g
	// Add the code from question 4g here
	//**************************
	// ADD CODE HERE

	
	
	
	
	
	
} // Train



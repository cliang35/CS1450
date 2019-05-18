/*Chris Liang
 *CS1450 Sec 001
 *Due October 22, @ 10:50 A.M.
 *In this assignment, we are continuing from our Assignment 4 Trains.  In this assignment, we are completing the rail yard by adding a sorting track, and departure track
 *along with the trains having new cargo and destinations.  The goal of this assignment is to learn how to use queues and priority queues.  
 */

import java.util.*;
import java.io.*;

public class CliangAssignment7 {
	public static void main(String[] args) throws IOException {  
		
		//Import text files
		final String TRAINS_FILE = "SortingYardTrains7.txt";
		final String RAILCAR_FILE = "ReceivingYardRailCars7.txt";
		
		File trainFileName = new File(TRAINS_FILE);
		File railcarFileName = new File(RAILCAR_FILE);
		//Scan the two text files
		Scanner trainFile = new Scanner (trainFileName);
		Scanner railcarFile = new Scanner (railcarFileName);
		
		//set numTracks to read the number of tracks in the SortingYardTrains7.txt
		int numTracks = trainFile.nextInt();
		
		//Create RailRoadYard object to store tracks
		RailroadYard railroadYard = new RailroadYard(numTracks+1);
		
		//SortingYardTrains7
		System.out.println("Loading trains onto tracks in sorting yard...");
		System.out.println();
		System.out.println ("-------------------------------------------------------------------------------------------");
		System.out.printf("%34s \t%s\t\t%s\t%s\n", "Engine", "Company", "Rail Cars", "Destination");
		System.out.println ("-------------------------------------------------------------------------------------------");
		
		//Iterate through SortingYardTrains7.txt to find trackNumber, engineNumber, company, numbercars, and destination city.
		while (trainFile.hasNext()) {
			int trackNumber = trainFile.nextInt();
			int engineNumber = trainFile.nextInt();
			String company = trainFile.next();
			int numberCars = trainFile.nextInt();
			String destinationCity = (trainFile.nextLine()).trim();
		
		//Create train object that stores engineNumber, company, numberCars, and destinationCity.
			Train7 currentTrain = new Train7(engineNumber, company, numberCars, destinationCity);
		
		//RailroadYard yard sorting track holds trackNumber and currentTrain.
			railroadYard.addTrainToSortingYard(trackNumber, currentTrain);
			
			System.out.print("Loading sorting track " + trackNumber + ":");
			System.out.println(currentTrain.print());
		}//while
		trainFile.close();
		
		//ReceivingYardRailCars7
		System.out.println();
		System.out.println("Loading rail cars on receiving track...");
		System.out.println ("-------------------------------------------------------------------------------------------");
		System.out.printf("%20s\t%s\n", "Number", "Destination");
		System.out.println ("-------------------------------------------------------------------------------------------");
		
		//Iterate through railcarfile to extract number, freight, weight, and destination.
		while (railcarFile.hasNext()) {
			int number = railcarFile.nextInt();
			String freight = railcarFile.next();
			int weight = railcarFile.nextInt();
			String destination = (railcarFile.nextLine()).trim();
		
			//RailCar object that holds number, freight, weight and destination
			RailCar7 railcar = new RailCar7 (number, freight, weight, destination);
			
			//RailroadYard recieving track holds railCars.
			railroadYard.addRailCarToReceivingTrack(railcar);
			
			System.out.print("Loading Railcar " + number);
			System.out.println(railcar.print());
		}//while
		railcarFile.close();
		
		//RailroadYardController
		RailroadYardController.moveRailCarsToTrains(railroadYard);
		RailroadYardController.moveTrainsToDepartureTrack(railroadYard);
		RailroadYardController.clearedForDeparture(railroadYard);
		System.out.println();
		System.out.println();
		System.out.println("...End railroad yard simulation...");
	}//main
}//Assignment7

interface PrintableFall {
	public String print();
}//interface print

//In the RailroadYard class, we are creating an array of train objects, a queue that holds our railcars and a priority queue that holds our trains that will be moved
//to the departure track.
class RailroadYard{
	private int numberTracks;
	private int currentTrack;
	private Train7[] sortingYard;
	private Queue<RailCar7> receivingTrack;
	private PriorityQueue<Train7> departureTrack;
	
	//RailroadYard constructor
	public RailroadYard (int numberTracks) {
		this.numberTracks = numberTracks;
		this.currentTrack = 0;
		
		sortingYard = new Train7[numberTracks];
		receivingTrack = new LinkedList<>();
		departureTrack = new PriorityQueue<>();
	}

	public int getNumberTracks() {
		return numberTracks;
	}
	
	//Adds train to a sorting yard track
	public void addTrainToSortingYard (int trackNumber, Train7 train) {
		sortingYard[trackNumber] = train;
	}

	//Iterates through and places trains on tracks
	public Train7 getNextTrainInSortingYard(){
		Train7 currentTrain = sortingYard[currentTrack];
		currentTrack++;
		return currentTrain;
	}
	
//Receiving Track
	//Checks to see if receiving track is empty
	public boolean isReceivingTrackEmpty() {
		return receivingTrack.isEmpty();
	}

	//Adds railcar to receiving track 
	public void addRailCarToReceivingTrack(RailCar7 railCar){            		
		receivingTrack.offer(railCar);
	}
	
	//Removes railcar from queue.
	public RailCar7 removeRailCarFromReceivingTrack() {
		return receivingTrack.remove();
	}
	
//Departure Track
	//Adds train to departure track
	public void addTrainToDepartureTrack(Train7 train) {	
		departureTrack.offer(train);
	}
	
	//Removes train from departure track
	public Train7 removeTrainFromDepartureTrack() {
		return departureTrack.remove();
	}
	
	//Checks to see if departure track is empty
	public boolean isDepartureTrackEmpty(){
		return departureTrack.isEmpty();
	}
	
//findTrain matches the railcar destinations to the train destinations
	public int findTrain(RailCar7 railCar){
		String railCarDestination = railCar.getDestination();
		int trackNumber = 0;
		boolean matched = false;
		
		while (!matched && trackNumber < sortingYard.length) {
			String trainDestination = " ";
			if(sortingYard[trackNumber] != null) {
				trainDestination = sortingYard[trackNumber].getDestinationCity();
			}
			if(railCarDestination.equals(trainDestination)) {
				matched = true;
			}
			else {
				trackNumber++;
			}
		}
		return trackNumber;
	}

//Sorting Yard
	//Adds railcar to sorting yard with trains
	public void addRailCarToTrainInSortingYard(RailCar7 railCar, int trackNumber) {
		sortingYard[trackNumber].addRailCar(railCar);
	}
	//Removes from new trian from the track number.
	public void removeTrainFromSortingYard(int trackNumber){
		sortingYard[trackNumber] = null;
	}
	
}//RailroadYard

//Train class defines the train object with engine number, company, destination and rail cars.
class Train7 implements PrintableFall, Comparable<Train7> {
	private int engineNumber;
	private String company;
	private String destinationCity;
	private Queue<RailCar7> railCars = new LinkedList<>();


	public Train7 (int engineNumber, String company, int railCars, String destinationCity) {
		this.engineNumber = engineNumber;
		this.company = company;
		this.destinationCity = destinationCity;
	}

	public int getEngineNumber() {
		return engineNumber;
	}

	public String getCompany() {
		return company;
	}

	public int getRailCarSize() {
		return railCars.size();
	}
	
	public void addRailCar (RailCar7 railCar) {
		railCars.offer(railCar);
	}
		
	public String getDestinationCity() {
		return destinationCity;
	}
	
	//compareTo compares the railcar sizes from the rail yard and compares it to the new trains with rail cars attached.
	public int compareTo(Train7 train) {
		if(this.getRailCarSize() < train.getRailCarSize()) {
			return -1;
		}
		else if(this.getRailCarSize() > train.getRailCarSize()) {
			return 1;
		}
		else 
			return 0;
		}//compareTo

@Override
	public String print() {
		return String.format("%9d\t%-5s\t\t%-3d\t\t%-15s", engineNumber, company, getRailCarSize(), destinationCity);
	}

}//train

//Railcar class defines the rail car objects with the railcar number, freight, weight and destinations
class RailCar7 implements PrintableFall{
	private int number;
	private String freight;
	private int weight;
	private String destination;
	
	public RailCar7 (int number, String freight, int weight, String destination) {
		this.number = number;
		this.freight = freight;
		this.weight = weight;
		this.destination = destination;
	}
	
	public int getNumber() {
		return number;
	}
	
	public String getFreight() {
		return freight;
	}
	
	public int getWeight() {
		return weight;
	}
	
	public String getDestination() {
		return destination;
	}

	@Override
	public String print() {
		return String.format("\t" + destination);

	}
}//RailCar

//The railroadyard controller is the logic that moves the trains and railcars from the sorting yard, to the receiving yard, and from the receiving yard to the departure tracks	
class RailroadYardController {
	
	private RailroadYardController() {
	}
	
	//Moves the rail cars to the trains and moves everything to receiving track
	public static void moveRailCarsToTrains(RailroadYard yard) {
		System.out.println();
		System.out.println("...Start railroad yard simulation...");
		System.out.println();
		System.out.println();
		System.out.println("Controller: Moving rail cars from receiving track to sorting yard:");
		System.out.println("-------------------------------------------------------------------");
		
		while(!yard.isReceivingTrackEmpty()) {
			RailCar7 railCar = yard.removeRailCarFromReceivingTrack();
			int sortingTrackNumber = yard.findTrain(railCar);
			yard.addRailCarToTrainInSortingYard(railCar, sortingTrackNumber);
			
			System.out.println("Railcar " + railCar.getNumber() + " proceed to sorting track "+ sortingTrackNumber);
		}//while
	}//moveRailCarsToTrains
	
	//Moves the new full trains to the departure track
	public static void moveTrainsToDepartureTrack(RailroadYard yard) {
		System.out.println();
		System.out.println();
		System.out.println("Controller: Moving trains from sorting yard to departure track");
		System.out.println("--------------------------------------------------------------");
		for(int trackNumber = 0; trackNumber < yard.getNumberTracks(); trackNumber++) {
			Train7 train = yard.getNextTrainInSortingYard();
				if(train != null) {
					yard.removeTrainFromSortingYard(trackNumber);  	
					yard.addTrainToDepartureTrack(train);			
				
					System.out.println("Train " + train.getEngineNumber() + " proceed to depature track");
			}//if	
		}//for
	}//moveTrainsToDepartureTrack
	
	//Checks to make sure that the departure track is empty and sends trains off to destinations.
	public static void clearedForDeparture(RailroadYard yard) {
		System.out.println();
		System.out.println();
		System.out.println("Controller: Moving trains from departure track to main line - smallest trains go first");
		System.out.println("------------------------------------------------------------------------------------");
		
		while(!yard.isDepartureTrackEmpty()) {
			Train7 train = yard.removeTrainFromDepartureTrack();
			System.out.println("Train " + train.getEngineNumber() + " with " + train.getRailCarSize() + " rail cars is departing to " + train.getDestinationCity());
		}//while
	}//cleared
}//RailroadYardController
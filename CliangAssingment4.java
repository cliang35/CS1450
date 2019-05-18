/*Chris Liang
 * CS1450 SEC 001
 * Assignment 4
 * Due: September 19th @10:50 A.M. 
 * In this program, we are practicing creating and manipulating classes and interfaces along with implementing and using comparable and iterator interfaces.  
 * Reading the sortYardTrains.txt file, there will be two outputs, 1 will list all the trains on their individual tracks sorted in ascending order, the other output will be the trains sorted by number of cars.
 */

import java.util.*;
import java.io.*;

public class CliangAssingment4 {

	public static void main(String[] args) throws IOException {
		
		final String FILE_NAME = "sortingYardTrains.txt";
		File fileName = new File(FILE_NAME);
		Scanner trainFile = new Scanner (fileName);
		int numTracks = trainFile.nextInt();
		
		RailroadYard railroadYard = new RailroadYard(numTracks+1);
		
		System.out.println("-----------------------------------------------------------------------------------------------------");
		System.out.println("\t\t\t\t\t\t  Engine       Company\tRail Cars\tDestination");
		System.out.println("-----------------------------------------------------------------------------------------------------");
		
		while (trainFile.hasNext()) {
			int trackNumber = trainFile.nextInt();
			int engineNumber = trainFile.nextInt();
			String company = trainFile.next();
			int maxRailCars = trainFile.nextInt();
			String destinationCity = trainFile.next();
			
			
			Train currentTrain = new Train (engineNumber, company, maxRailCars, destinationCity);
		
			railroadYard.moveTrainToSortingYard(trackNumber, currentTrain);
			
			
			System.out.println("Loading trains onto tracks in sorting yard... " + trackNumber + ":"+ currentTrain.print());
			//System.out.println(currentTrain.print());
		}//while
		trainFile.close();
	printOutboundReport(railroadYard);
	}//main
	

	public static void printOutboundReport (RailroadYard yard) {
		
		ArrayList<OutboundReport> outboundReport = new ArrayList<OutboundReport>();
		
		for(int track = 0; track < yard.getnumberTracks(); track++) {
			
			Train currentTrain = yard.getNextTrainInSortingYard();
			
			if(currentTrain != null) {
				int engineNumber = currentTrain.getEngineNumber();
				int railCars = currentTrain.numberRailCars();
				String destinationCity = currentTrain.destinationCity();
				
				OutboundReport record = new OutboundReport(track, engineNumber, railCars, destinationCity);
				outboundReport.add(record);
			}
		}
		Collections.sort(outboundReport);
		
		System.out.println();
		System.out.println("*****************************************************************************");
		System.out.println("\t\t\t\tOUTBOUND REPORT");
		System.out.println("\t\t(Ordered by Max Number of Rail Cars)");
		System.out.println("*****************************************************************************");
		System.out.println("");
		System.out.println("Sorting track\tEngine\t\tRail Cars\tDeparting To");
		System.out.println("-----------------------------------------------------------------------------");
		
		Iterator<OutboundReport> iterator = outboundReport.iterator();
		while(iterator.hasNext()) {
			OutboundReport currentReport = iterator.next();
			System.out.println(currentReport.print());
		}
	}
	
}//CliangAssignment4

interface Printable {
	public String print();
}//interface print


class RailroadYard{
	private int numberTracks;
	private int currentTrack;
	private Train[] sortingYard;
	
		
	public RailroadYard (int numberTracks) {
		this.numberTracks = numberTracks;
		currentTrack = 0;
		sortingYard = new Train[numberTracks];
		
	}

	public int getnumberTracks() {
		return numberTracks;
	}

	public void moveTrainToSortingYard (int trackNumber, Train train) {
		sortingYard[trackNumber] = train;
	}

	public Train getNextTrainInSortingYard(){
		Train aTrain = sortingYard[currentTrack];
		currentTrack++;
		return aTrain;
	}

}//RailroadYard



class Train{
	private int engineNumber;
	private String company;
	private int numberRailCars;
	private String destinationCity;

	public Train(int engineNumber, String company, int numberRailCars, String destinationCity) {
		this.engineNumber = engineNumber;
		this.company = company;
		this.numberRailCars = numberRailCars;
		this.destinationCity = destinationCity;
	}

	public int getEngineNumber() {
		return engineNumber;
	}

	public String company() {
		return company;
	}

	public int numberRailCars() {
		return numberRailCars;
	}

	public String destinationCity() {
		return destinationCity;
	}

	public String print() {
		return String.format("%9d\t%-5s\t%-3d\t\t%-15s", engineNumber, company, numberRailCars,destinationCity);
	}

}//train


class OutboundReport implements Printable, Comparable<OutboundReport> {
	private int trackNumber;
	private int engineNumber;
	private int numberRailCars;
	private String destinationCity;


	public OutboundReport (int trackNumber, int engineNumber, int numberRailCars, String destinationCity) {
		this.trackNumber = trackNumber;
		this.engineNumber = engineNumber;
		this.numberRailCars = numberRailCars;
		this.destinationCity = destinationCity;
	}

	
	public int compareTo(OutboundReport railCars) {
		if(this.numberRailCars < railCars.numberRailCars) {
			return -1;
		}
		else if(this.numberRailCars > railCars.numberRailCars) {
			return 1;
		}
		else 
			return 0;
		}

@Override
	public String print() {
		return String.format("%2d\t\t%-5d\t\t%-3d\t\t%-15s",  trackNumber, engineNumber, numberRailCars, destinationCity);
	}
}//Outbound report

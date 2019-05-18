/*Chris Liang
 * CS1450 Sec 001
 * Due: September 12 @ 10:50
 * This file reads the birds.txt file and creates an array to store each of the birds.  From there, array lists are created to store the birds traits and what they cannot
 * do.  For example, a penguin can run and swim but it cannot fly, therefore it gets stored in the cannot fly array list.  We then create abstract classes for our birds
 * class to give it traits.  Finally we create the different bird classes that override the traits in the birds class using an interface.  The birds print to the console
 * with the correct array list category and an interesting fact about each bird.
 */

import java.util.*;
import java.io.*;

public class CliangAssignment3 {
	
	public static void main(String[] args) throws IOException {

		final String FILE_NAME = "birds.txt";
		File inputFileName = new File (FILE_NAME);
		
		Scanner inputScanner = new Scanner(inputFileName);
		int size = inputScanner.nextInt();
		
		Birds[] theBirds = new Birds[size];
		
	
		for(int i=0;i<size;i++) {
			String type = inputScanner.next();
			String name = inputScanner.next();
			int runSpeed = inputScanner.nextInt();
			int swimSpeed = inputScanner.nextInt();
			int flyAlt = inputScanner.nextInt();
		

		switch(type) {
		case "p": theBirds[i] = new Penguin (name, runSpeed, swimSpeed); break;
		case "o": theBirds[i] = new Ostrich (name, runSpeed, swimSpeed); break;
		case "d": theBirds[i] = new Duck (name, runSpeed, swimSpeed, flyAlt); break;
		case "s": theBirds[i] = new SootyTern (name, runSpeed, flyAlt); break;
		case "l": theBirds[i] = new Loon (name, swimSpeed, flyAlt); break;
		case "h": theBirds[i] = new Hummingbird (name, flyAlt); break; 
			}//switch		
		}//for loop 

		
		ArrayList<Birds> cannotFly = findCannotFly(theBirds);
		for(int i=0;i<cannotFly.size();i++) {
			System.out.println(cannotFly.get(i).getName()+ " is a " + cannotFly.get(i).getType()+ " and cannot fly.");
			System.out.println(cannotFly.get(i).interestingFact()+("\n"));
		}
		
		ArrayList<Birds> cannotRun = findCannotRun(theBirds);
		for(int i=0;i<cannotRun.size();i++) {
			System.out.println(cannotRun.get(i).getName()+" is a " + cannotRun.get(i).getType()+ " and cannot run.");
			System.out.println(cannotRun.get(i).interestingFact()+("\n"));
		}

		ArrayList<Birds> cannotSwim = findCannotSwim(theBirds);
		for(int i=0; i<cannotSwim.size();i++) {
			System.out.println(cannotSwim.get(i).getName()+ " is a "+ cannotSwim.get(i).getType()+" and cannot swim.");
			System.out.println(cannotSwim.get(i).interestingFact()+("\n"));
		}

		
		ArrayList<Birds> canFlySwimRun = new ArrayList<>();
		for(int i = 0;i<canFlySwimRun.size();i++) {
			System.out.println(canFlySwimRun.get(i).getName()+ " is a "+ canFlySwimRun.get(i).getType()+" and can do it all.\n");
			System.out.println();//display run speed, flight alt and swim speed
				
			}
		}//main
	
	public static ArrayList<Birds> findCannotFly(Birds[] theBirds){
		System.out.println();
		System.out.println("BIRDS THAT CANNOT FLY!");
		System.out.println("--------------------------");
			ArrayList<Birds> nonFlyers = new ArrayList<>();
			for(int i=0;i<theBirds.length;i++) {
				if((theBirds[i] instanceof Flyer)) {
					nonFlyers.add(theBirds[i]);  
				}
			}
			return nonFlyers;
	}
	
	public static ArrayList<Birds> findCannotRun(Birds[] theBirds){
		System.out.println();
		System.out.println("BRIDS THAT CANNOT RUN!");
		System.out.println("--------------------------");
			ArrayList<Birds> nonRunners = new ArrayList<>();
			for(int i=0; i<theBirds.length;i++) {
			if(!(theBirds[i] instanceof Runner)) {
					nonRunners.add(theBirds[i]);
				}
			}
			return nonRunners;
	}
			
	public static ArrayList<Birds> findCannotSwim(Birds[] theBirds){
		System.out.println();
		System.out.println("BIRDS THAT CANNOT SWIM!");
		System.out.println("--------------------------");
		ArrayList<Birds> nonSwimmers = new ArrayList<>();
		for(int i=0; i<theBirds.length;i++) {
				if(!(theBirds[i] instanceof Swimmer)) {
					nonSwimmers.add(theBirds[i]);
				}
			}
		return nonSwimmers;
	}
	
	public static ArrayList<Birds> findFlySwimRun(Birds[] theBirds){
		System.out.println();
		System.out.println("BIRDS THAT CAN DO IT ALL - RUN, SWIM, AND FLY!");
		System.out.println("--------------------------------");
		ArrayList<Birds> canFlySwimRun = new ArrayList<>();
		for(int i = 0; i<canFlySwimRun.size(); i++) {
			if(theBirds[i] instanceof Flyer) {  
				canFlySwimRun.add(theBirds[i]);
			}
			if (theBirds[i] instanceof Runner) {
				canFlySwimRun.add(theBirds[i]);
			}
			if (theBirds[i] instanceof Swimmer) {
				canFlySwimRun.add(theBirds[i]);
			}
		}
			return canFlySwimRun;
	}

}//CliangAssingment3

abstract class Birds{
	private String name;
	private String type;
	
	public String getName() {
		return name;
	}

	public String interestingFact() {

		return null;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public abstract String getInterestingFact();
}//end birds

	interface Swimmer {
		public int swim();
	}
	
	interface Runner{
		public int run();
	}
	
	interface Flyer{
		public int fly();
	}
	
	class Penguin extends Birds implements Runner,Swimmer {
		private int runSpeed;
		private int swimSpeed;
				
		public Penguin(String name, int runSpeed, int swimSpeed) {
			setName(name);
			setType("Penguin");
			this.runSpeed = runSpeed;
			this.swimSpeed = swimSpeed;
		}
		
		@Override
		public int run() {
			return runSpeed;
		}
		
		@Override
		public int swim() {
			return swimSpeed;
		}
		@Override
			public String interestingFact() {
				return "I can’t fly but I’m the fastest swimmer and deepest diver\n of any bird and can stay underwater up to 20 minutes.";
			}

		@Override
		public String getInterestingFact() {     
			return null;
		}


	}

	
	class Ostrich extends Birds implements Runner,Swimmer{
		private int runSpeed;
		private int swimSpeed;
				
		public Ostrich(String name, int runSpeed, int swimSpeed) {
			setName(name);
			setType("Ostrich");
			this.runSpeed = runSpeed;
			this.swimSpeed = swimSpeed;
		}		
		@Override
		public int run() {
			return runSpeed;
		}
		
		@Override
		public int swim() {
			return swimSpeed;
		}
		@Override
			public String interestingFact() {
				return "Who needs flying when you’re the biggest bird on earth!\nI can grow up to 9 feet tall and weigh as much\nas 300 pounds! Don't mess with me!";
			}
		@Override
		public String getInterestingFact() {   
			return null;
		}

		
	}
	
	class Duck extends Birds implements Swimmer, Runner,Flyer{
		private int runSpeed;
		private int swimSpeed;
		private int flyAlt;
		
		public Duck(String name, int runSpeed, int swimSpeed, int flyAlt) {
			setName(name);
			setType("Duck");
			this.runSpeed = runSpeed;
			this.swimSpeed = swimSpeed;
			this.flyAlt = flyAlt;
		}		
		
		@Override
		public int run() {
			return runSpeed;
		}
		@Override
		public int swim() {
			return swimSpeed;
		}
		@Override
		public int fly() {
			return flyAlt;
		}
		@Override
			public String interestingFact() {
				return "A duck's highest documented flight was over Nevada where a \nplane struck a mallard at 21,000 feet!";
			}

		@Override
		public String getInterestingFact() {
			return null;
		}
	}
	
	class SootyTern extends Birds implements Runner,Flyer{
		private int runSpeed;
		private int flyAlt;
		
		public SootyTern(String name, int runSpeed, int flyAlt) {
			setName(name);
			setType("Sooty Tern");
			this.runSpeed = runSpeed;
			this.flyAlt = flyAlt;
		}
		@Override
		public int run() {
			return runSpeed; 
		}
		@Override 
		public int fly() {
			return flyAlt;
		}
		@Override
			public String interestingFact() {
				return "Strange as it may sound, I spend most of my life at sea and yet I can't swim!\nI also take 1 to 2 second naps while flying! ";
			}
		@Override
		public String getInterestingFact() {
			return null;
		}

	}
	
	class Loon extends Birds implements Swimmer,Flyer{
		private int swimSpeed;
		private int flyAlt;
		
		public Loon(String name, int swimSpeed, int flyAlt) {
			setName(name);
			setType("Loon");
			this.swimSpeed = swimSpeed;
			this.flyAlt = flyAlt;
		}
		@Override
		public int swim() {
			return swimSpeed;
		}
		@Override
		public int fly() {
			return flyAlt;
		}
		@Override
		public String interestingFact() {
			return "My legs are so far back on my body that I cannot walk on land, instead\nI push myself along the ground on my chest.";
		}
		@Override
		public String getInterestingFact() {
			return null;
		}		
	}
	
	class Hummingbird extends Birds implements Flyer{
		private int flyAlt;
		
		public Hummingbird(String name, int flyAlt) {
			setName(name);
			setType("Hummingbird");
			this.flyAlt = flyAlt;
		}
		@Override
		public int fly() {
			return flyAlt;
		}
		@Override
		public String interestingFact() {
			return "My feet are so small and my flying so adept!\n I've got the fastest beating heart in the animal kingdom with heart rates exceeding 1,200 beats per minute.";
		}
		@Override
		public String getInterestingFact() {

			return null;
		}
	}
	
	

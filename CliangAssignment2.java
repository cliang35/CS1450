/*Chris Liang
 * CS1450 Sec 001
 * Assignment 2
 * Due: September 6th @ 10:50 A.M.
 * This program reads characters.txt, then takes the information and creates an array that stores each character into its appropriate sublclass.  
 * The output will be a table that correctly defines each character and outputs their name, type (Hero, Villain, Monster, Droid) and a catchphrase 
 * for each character type.
 */
import java.util.*;
import java.io.*;

public class CliangAssignment2 {
	
	public static void main(String[] args) throws IOException {

		final String FILE_NAME = "characters.txt";
		File inputFileName = new File (FILE_NAME);
		
		Scanner inputScanner = new Scanner (inputFileName);
		int size=inputScanner.nextInt();
		Character[] character = new Character[size];
		
		for(int i=0;i<size;i++) {
		String type = inputScanner.next();
		String name = inputScanner.nextLine();
			
		switch (type) {
		case "h": character[i] = new Hero (name); break;
		case "v": character[i] = new Villain (name);break;
		case "m": character[i] = new Monster (name);break;
		case "d": character[i] = new Droid (name); break;
		}//switch
	}//for loop
	
	System.out.println();
	System.out.printf("Character Name\t\t Character Type\t\t Statement\n");
	System.out.println("------------------------------------------------------------------");
	
	for(int i=0;i<size;i++) {
		System.out.printf("%-15S\t\t",character[i].getName());
		System.out.printf(character[i].getType()+"\t\t");
		System.out.printf(character[i].speak()+"\t");
		System.out.println();
		}//for loop
	}//end main
}//end public class


class Character{
		
	private String name;
	private String type;
	
	
	public Character(String name, String type)
	{
		this.name = name;
		this.type = type;
	}

	public String getName() {
		return name;
	}
	
	public String getType() {
		return type;
	}
		
	public String speak() {
	return "Characters";
	}

}//end character


class Hero extends Character{
	

	public Hero(String name) {
		super(name,"Hero");
	}
	@Override//speak
	public String speak() {
		return "To the rescue!  KAPOW!! BAM!! POW!!";
	}
}
	

class Villain extends Character{
	public Villain(String name) {
		super(name,"Villain");
	}

	@Override//speak
	public String speak() {
		return "You’ll never stop me!  Haaaaaaa!";
	}
}

class Monster extends Character{
	public Monster(String name) {
		super(name,"Monster");
	}

	@Override//speak
	public String speak() {
		return "RRAAAUUGGHH GRROWR!!!";
	}
}

class Droid extends Character{
	public Droid(String name) {
		super(name,"Droid");
	}

	@Override//speak
	public String speak() {
	return "Beep Beep Bloop Boop Beep!";
	}
}	
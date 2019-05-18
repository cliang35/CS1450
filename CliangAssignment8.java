/*Chris Liang
 * SC1450 Sec 001
 * Due October 29, @ 10:50 A.M.
 * In this assignment, we are given 6 files, and we are going to use stacks and queues to decipher two secret codes that are hidden in the files.
 * We will be iterating through each of the files, and using our decoding algorithm to go through the string and int files in order to decode the message.
 * We will be using a stack class that is the final step to decode the message, and a translator class to translate the message.
 */
import java.util.*;
import java.io.*;
import java.lang.Character;

public class CliangAssignment8 {

	public static void main(String[] args) throws IOException {
		
		//Import the array messages and array key files.
		final String FILE_ARRAY_MESSAGE1 = "arrayMessage1.txt";
		final String FILE_ARRAY_MESSAGE2 = "arrayMessage2.txt";
		final String FILE_ARRAY_KEY 	 = "arrayKey.txt";
		//Import the queue messages and queue key files.
		final String FILE_QUEUE_MESSAGE1 = "queueMessage1.txt";
		final String FILE_QUEUE_MESSAGE2 = "queueMessage2.txt";
		final String FILE_QUEUE_KEY 	 = "queueKey.txt"; 
		

		File arrayMessage1Name = new File (FILE_ARRAY_MESSAGE1);
		File arrayMessage2Name = new File (FILE_ARRAY_MESSAGE2);
		File arrayKeyName 	   = new File (FILE_ARRAY_KEY);
		
		File queueMessage1Name = new File (FILE_QUEUE_MESSAGE1);
		File queueMessage2Name = new File (FILE_QUEUE_MESSAGE2);
		File queueKeyName	   = new File (FILE_QUEUE_KEY);

		//Scan in all of the files.
		Scanner arrayMessage1File = new Scanner (arrayMessage1Name);
		Scanner arrayMessage2File = new Scanner (arrayMessage2Name);
		Scanner arrayKeyFile	  = new Scanner (arrayKeyName);
		
		Scanner queueMessage1File = new Scanner (queueMessage1Name);
		Scanner queueMessage2File = new Scanner (queueMessage2Name);
		Scanner queueKeyFile	  = new Scanner (queueKeyName);
	
		//Create an array list for each of the messages and array key.
		ArrayList <Character> arrayMessage1 = new ArrayList<>();
		ArrayList <Integer> arrayMessage2 = new ArrayList<>();
		ArrayList <Integer> arrayKey = new ArrayList<>();
		
		//This string is used to store the values of the first file.
		String messageString = arrayMessage1File.nextLine();
		//These for loops move through the files and extracts each of the elements
		//This loop goes through the char files
		for (int i = 0; i < messageString.length(); i++) {
			char aChar = messageString.charAt(i);
			arrayMessage1.add(aChar);
		}
		
		//This loop goes through the int files
		int messageInt1 = arrayMessage2File.nextInt();
		for (int i = 0; i < messageInt1; i++) {
			int value = arrayMessage2File.nextInt();
			arrayMessage2.add(value);
		}
		
		//This loop also goes through the int files.
		int messageInt2 = arrayKeyFile.nextInt();
		for (int i = 0; i < messageInt2; i++) {
			int keyValue = arrayKeyFile.nextInt();
			arrayKey.add(keyValue);
		}
		
		//Call the translator class and create a new translator object.
		Translator translator = new Translator();
		//Iterate through the array messages
		Iterator <Character> array1Iterator = arrayMessage1.iterator();
		Iterator <Integer> array2Iterator = arrayMessage2.iterator();
		Iterator <Integer> arrayKeyIterator = arrayKey.iterator();
		
		//Use decode method to translate the characters inputs in the stack.
		String secretMessage1 = translator.decode(array1Iterator, array2Iterator, arrayKeyIterator);
		
		//Print the message.
		System.out.println("The secret string is: " + secretMessage1);
		
		//Create queues for the queue messages and queue key.
		Queue <Character> queueMessage1 = new LinkedList<>();
		Queue <Integer> queueMessage2 = new LinkedList<>();
		Queue <Integer> queueKey = new LinkedList<>();
		
		//Also a string that takes from the queueMessages1.txt file
		messageString = queueMessage1File.nextLine();
		for (int i = 0; i < messageString.length(); i++) {
			char aChar = messageString.charAt(i);
			queueMessage1.offer(aChar);
		}
		
		//Loop that reads through the int file.
		messageInt1 = queueMessage2File.nextInt();
		for (int i = 0; i < messageInt1; i++) {
			int value = queueMessage2File.nextInt();
			queueMessage2.offer(value);
		}
		
		//Loop that reads through the queueKey.txt
		messageInt2 = queueKeyFile.nextInt();
		for (int i = 0; i < messageInt2; i++) {
			int keyValue = queueKeyFile.nextInt();
			queueKey.offer(keyValue);
		}
		
		//Iterator that iterates through the queueMessages and queueKey
		Iterator <Character> queue1Iterator = queueMessage1.iterator();
		Iterator <Integer> queue2Iterator = queueMessage2.iterator();
		Iterator <Integer> queueKeyIterator = queueKey.iterator();
		
		//Use the decode method to translate the queue messages
		String secretMessage2 = translator.decode(queue1Iterator, queue2Iterator, queueKeyIterator);
		//Print the message.
		System.out.println("The secret string is: " + secretMessage2);
		
		//Close the files to prevent memory leaks.
		arrayMessage1File.close();
		arrayMessage2File.close();
		arrayKeyFile.close();
		queueMessage1File.close();
		queueMessage2File.close();
		queueKeyFile.close();
		
	}//main

}//Assignment8

//Translator class contains the decode method that is used to translate the messages.  It also reads through the iterated files.
class Translator {
	private Stack myStack;
	
	//Create stack myStack for the translator object.
	public Translator() {
		myStack = new Stack();
	}
	
	public String decode (Iterator<Character> msg1Iterator, 
						 Iterator<Integer> msg2Iterator, 
						 Iterator<Integer>keyIterator) {
		while (keyIterator.hasNext()) {
			int keyValue = keyIterator.next();
			
			if(keyValue == 0) {
				char myChar = msg1Iterator.next();
				myStack.push(myChar);
			} else {
				int myInt = msg2Iterator.next();
				char myChar = (char)myInt;
				myStack.push(myChar);
			}
		}
		String clearMessage = " ";
		while (!myStack.isEmpty()) {
			char aChar = myStack.pop();
			clearMessage = clearMessage + aChar;
		}
		return clearMessage;
	}//decode
}//Translator

//The key to the decodeing process.  Stack holds the final decoded files in a stack so they can be output decoded.  It creates 
//an array list for the characters, then pushes the characters onto the stack then the decoded messages are called in the main and 
//outputed.
class Stack {
	
	private ArrayList<Character> list;
	
	public Stack() {
		list = new ArrayList<>();
	}
	
	public int getSize() {
		return list.size();
	}
	
	public boolean isEmpty() {
		return list.isEmpty();
	}
	
	public void push(Character value) {
		list.add(value);
	}
	
	public Character pop () {
		Character value = list.get(getSize()-1);
		list.remove(getSize() - 1);
		return value;
	}
	
}//Stack

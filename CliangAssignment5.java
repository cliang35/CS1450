/*Chris Liang
 * Assignment 5
 * CS1450 SEC 001
 * Due September 25 @ 10:50 A.M.
 * In this assignment, we are taking integers1.txt and integers2.txt, displaying them separately, putting them into stacks, then displaying the stack in a sorted order.
 * For the integers, the numbers are in ascending order while the states are in Alphabetical order.
 */
import java.io.*;
import java.util.*;

public class CliangAssignment5 {

	public static void main(String[] args) throws IOException {
		
		//Generic Stack for integer files
		GenericStack<Integer> intStack1 = new GenericStack<Integer>();
		GenericStack<Integer> intStack2 = new GenericStack<Integer>();

		int num;

		
		//Scanner 1 for integers1
		Scanner scan1 = new Scanner (new File("integers1.txt"));
		System.out.println("Values read from integers1.txt and pushed onto stack1");
		System.out.println("-------------------------------------------------------");
		
		while(scan1.hasNext())
		{
			num = scan1.nextInt();
			System.out.println(num);
			intStack1.push(num);
		}//while
		
		//Scanner 2 for integer2
		Scanner scan2 = new Scanner(new File("integers2.txt"));
		System.out.println("\n\nValues read from integers2.txt and pushed onto stack2");
		System.out.println("-------------------------------------------------------");
		
		while(scan2.hasNext())
		{
			num = scan2.nextInt();
			System.out.println(num);
			intStack2.push(num);
		}
		
		//Merge Stacks
		GenericStack<Integer> intMergeStack = new GenericStack<Integer>();
		
		mergeStacks(intStack1, intStack2, intMergeStack);
		System.out.println("\n\nMerged Stack with lowest value on top: ");
		System.out.println("---------------------------------------");
		printStack(intMergeStack);
		
		scan1.close();
		scan2.close();
		
		
		//Generic stack for state files
		GenericStack<String> strStack1 = new GenericStack<String>();
		GenericStack<String> strStack2 = new GenericStack<String>();
				
		
		String states;
		//read states files
		
		//Print state values
		
		//States1
		scan1 = new Scanner(new File("states1.txt"));
		System.out.println("\n\nValues read from states1.txt and pushed onto stack1");
		System.out.println("----------------------------------------------------");
		
		while(scan1.hasNextLine())
		{
			states = scan1.nextLine();
			System.out.println(states);
			strStack1.push(states);
		}
		
		//States2
		scan2 = new Scanner(new File("states2.txt"));
		System.out.println("\n\nValues read from states2.txt and pushed onto stack2");
		System.out.println("------------------------------------------------------");
		
		while(scan2.hasNextLine())
		{
			states = scan2.nextLine();
			System.out.println(states);
			strStack2.push(states);
		}
		
		//Merge the stacks
		GenericStack<String> strMergeStack = new GenericStack<String>();
		
		//Print the stack in alphabetical order
		mergeStacks(strStack1, strStack2, strMergeStack);
		System.out.println("\nMerged Stack in alphabetical order");
		System.out.println("---------------------------------------");
		printStack(strMergeStack);
		
		scan1.close();
		scan2.close();
	}//main

	//Method to merge each stack
	public static <E extends Comparable<E>> void mergeStacks(GenericStack<E> stack1, GenericStack<E> stack2, GenericStack<E> mergedStack)
	{
		
		GenericStack<E> tempStack = new GenericStack<E>();
		
		while(!stack1.isEmpty() && !stack2.isEmpty())
		{
			if(stack1.peek().compareTo(stack2.peek()) < 0)
			{
				tempStack.push(stack1.pop());
			} else {
				tempStack.push(stack2.pop());
			}//else
		}//while
		
		
		while(!stack1.isEmpty())
		{
			tempStack.push(stack1.pop());
		}
		while(!stack2.isEmpty())
		{
			tempStack.push(stack2.pop());
		}
		reverseStack(tempStack, mergedStack);
	}//mergeStacks
	
	//Method to reverse each stack
	public static <E> void reverseStack(GenericStack<E> stack, GenericStack<E> reverseStack)
	{
		while(!stack.isEmpty())
			reverseStack.push(stack.pop());
	}//Reverse Stack
	
	//Method to print stack
	public static <E> void printStack(GenericStack<E> stack)
	{
		while(!stack.isEmpty())
			System.out.println(stack.pop());
	}//Print Stack
	
}//CliangAssignment5

//This class is the generic stack class that creates each stack, puts each element into an array list (integers in one stack and states in another)
// and provides the functions of the generic stack
class GenericStack<E>
{
	private ArrayList<E> list;
	
	public GenericStack()
	{
		list = new ArrayList<E>();
	}
	
	public int getSize()
	{
		return list.size();
	}
	
	public boolean isEmpty()
	{
		return getSize() ==0;
	}
	
	public E peek()
	{
		return list.get(list.size()-1);
	}
	
	public void push(E data)
	{
		list.add(data);
	}
	
	public E pop()
	{
		return list.remove(list.size()-1);
	}

}//Generic Stack
/*Chris Liang
 * CS1450 Sec 001
 * Due: November 12th @ 10:50 A.M.
 * In this assignment, we are returning to the trains but this time, we are
 * implementing a singly-linked list.  The linked list holds the different 
 * destinations and the railcars that are to be dropped off.  Each railcar has
 * a specific destination, and is removed when the destination is reached.
 */

import java.util.*;
import java.io.*;

public class CliangAssignment9 {
	public static void main(String args[]) throws IOException {
		//Create a linked list object
		RailCarLinkedList list = new RailCarLinkedList();
		
		//import Railcars information to be added to the linked list
		final String FILE_RAILCARS = "railcars.txt";
		
		File railCarsName = new File(FILE_RAILCARS);
		Scanner railCarsFile = new Scanner (railCarsName);
		
		int number;
		String destination;
		String freight;
		
		while (railCarsFile.hasNext())
		{
			number = railCarsFile.nextInt();
			destination = railCarsFile.next();
			freight = railCarsFile.next();
			list.addInOrder(new RailCar9(number, destination,freight));
		}
		railCarsFile.close();
		
		//Final outputs
		System.out.println("Train departs Chicago");
		list.printList();
		
		System.out.println("\nStop 1: Train Arrives in Denver");
		System.out.println("Remove: Denver rail cars\n");
		list.removeCarWithDestination("DEN");
		list.printList();
		
		System.out.println("Stop 2: Train Arrives in Colorado Springs");
		System.out.println("Remove: Colorado Springs rail cars");
		System.out.println("Remove: Rail cars with parrots\n");
		list.removeCarWithDestination("COS");
		list.removeCarsWithFreight("parrots");
		list.printList();
		
		System.out.println("Stop 3: Train Arrives in Pueblo");
		System.out.println("Remove: Pueblo rail cars\n");
		list.removeCarWithDestination("PBO");
		list.printList();
		
		System.out.println("Stop 4: Train Arrives in Santa Fe");
		System.out.println("Remove: Santa Fe rail cars - train should be empty!");
		list.removeCarWithDestination("SFE");
		list.printList();
	}//main
}//CliangAssingment9

//Railcar class that creates railcars
class RailCar9 implements Comparable<RailCar9> {
	private int number;
	private String destination;
	private String freight;
	
	public RailCar9(int number, String destination, String freight) {
		this.number = number;
		this.destination = destination;
		this.freight = freight;
	}
	
	public String getFreight() {
		return freight;
	}
	
	public String getDestination() {
		return destination;
	}
	
	public String print() {
		return (String.format("%20d %20s %20s", number, destination, freight));
	}
	
	//ComapareTo that "compares" each city so that the correct cargo can be 
	//off loaded at the correct city each city is compared by DEN>COS>PBO>SFE.
	public int compareTo(RailCar9 railcar) {
		if(destination.equals ("SFE"))
		{
			if (this.destination.equals("SFE"))
				return 0;
			else
				return 1;
		}//if SFE
		
		else if(destination.equals("PBO"))
		{
			if(this.destination.equals("SFE"))
					return -1;
			else if(this.destination.equals("PBO"))
				return 0;
			else
				return 1;
		}//if PBO
		
		else if(destination.equals("COS"))
		{
			if(this.destination.equals("PBO") || this.destination.equals("SFE"))
				return -1;
			else if (this.destination.equals("COS"))
				return 0;
			else
				return 1;
		}//if COS
		
		else 
		{
			if (this.destination.equals("DEN"))
				return 0;
			else
				return -1;
		}//else
	}//compareTo
}//RailCar9

//The RailCarLinkedList class is where the link list is created and each node contains a car,
//a destination and cargo.
 
class RailCarLinkedList {
	private Node head;
	
	private static class Node {
		private RailCar9 railCar;
		private Node next;
		public Node (RailCar9 railCar)
		{
			this.railCar = railCar;
			next = null;
		}//Node
	}//Static Class Node
	
	//Call railcarlinkedlist object
	public RailCarLinkedList() {
		head = null;
	}//RealLinkedList
	
	//addInOrder adds each railcar in order
	public void addInOrder(RailCar9 railCarToAdd) {
		if(head == null) 
		{
			head = new Node(railCarToAdd);
		} else {
			Node node = new Node (railCarToAdd);
			if(head.railCar.compareTo(railCarToAdd) == -1) {
				node.next = head;
				head = node;
			} else {
				Node current = head;
				while (current.next != null)
				{
					if (current.next.railCar.compareTo(railCarToAdd) == -1)
					{
						node.next = current.next;
						current.next = node;
						return;
					}//if
					current = current.next;
				}
				current.next = node;
			} 
		}//else
	}//addInOrder
	
	//removeCarWithDestination removes each car at the appropriate destination.  It removes
	// each of the railcars at the correct destination.
	public void removeCarWithDestination(String destination) {
		if (head == null)
			return;
		
		while (head != null && head.railCar.getDestination().equalsIgnoreCase(destination))
		{
			head = head.next;
		}
		
		Node current = head;
		if (current != null)
		{
			while (current.next != null)
			{
				if (current.next.railCar.getDestination().equalsIgnoreCase(destination))
				{
					current.next = current.next.next;
				} else {
					current = current.next;
				}//else
			}//while
		}//if
	}//removeCarWithDestination
	
	//removeCarsWithFreight removes specific cars with specific freight at the specific destination
	public void removeCarsWithFreight (String freight) {
		if (head == null)
			return;
		while(head != null && head.railCar.getFreight().equalsIgnoreCase(freight)) {
			head = head.next;
		}
		Node node = head;
		if (node != null) 
		{
			while (node.next != null)
			{
				if (node.next.railCar.getFreight().equalsIgnoreCase(freight))
				{
					node.next = node.next.next;
				} else {
					node = node.next;
				}//else
			}//while
		}//if
	}//removeCarsWithFreight
	
		public void printList() {
			Node current = head;
			System.out.printf("\n %20s %20s %20s", "RailCar", "Destination City", "Freight");
			System.out.println("\n----------------------------------------------------------------");
			while (current != null)
			{
				System.out.println(current.railCar.print());
				current = current.next;
			}
		}//printList
}//RailLinkedLIst

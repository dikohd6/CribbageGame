/*
 * @author Dikran Kahiaian
 * This set class helps to create the linked list and do linked list operations
 */
public class Set<T> {
	// instance variables needed
	private LinearNode<T> setStart;
	// constructor
	public Set() {
		setStart=null;
	}
	// add class that adds an element into the end of linked list
	public void add(T element) {
		LinearNode<T> newNode= new LinearNode<T>(element);
		// check if the linked list is empty and if it's not, loop through the nodes until its null and add it to the end
		if (setStart==null) {
			setStart=newNode;
		}else {
			LinearNode<T> current=setStart;
			while(current.getNext()!=null) {
				current=current.getNext();
				
			}
			current.setNext(newNode);
		}
	}
	// get length method
	public int getLength() {
		int counter=0;
		LinearNode<T> current=setStart;
		// loop through each node and count up
		while(current!=null) {
			current=current.getNext();
			counter++;
		}
		return counter;
	}
	// get element method that gets the i th element
	public T getElement(int i) {
		int counter=0;
		LinearNode<T> current=setStart;
		// loop through each node i times and return the element
		while (counter!=i && current!=null) {
		
			current=current.getNext();
			counter++;
		}
		if (current ==null)
		{		
			return null;
		}
		return current.getElement();
	}
	// contains method that checks if that element is in the node
	public boolean contains (T element) {
		LinearNode<T> current=setStart;
		// check each nodes element
		while(current.getElement()!=element) {
			current=current.getNext();
			if(current==null) {
				return false;
			}
		}
		return true;
	}
	// to string that returns the string representation of linked list
	public String toString() {
		LinearNode<T> current=setStart;
		String elements="";
		while (current!=null) {
			elements+=current.getElement()+" ";
			current=current.getNext();
		}
		return elements;
	}
	
}

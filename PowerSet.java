/*
 * @author Dikran Kahiaian
 * power set class that takes an array of cards and turns it to the binary representation depending on the length of the array
 */
public class PowerSet<T> {
	//instance variable
	private Set<T>[] set;
	
	// constructor
	public PowerSet(T[] elements) {
		String binaryNum;

		double pow=Math.pow(2, elements.length);
		// initialize the set
		set=new Set[(int)pow];
		// loop through the answer after taking the power and find the binary representation
		for (int i=0; i<pow; i++) {
			
			binaryNum=(String.format("%0"+elements.length+"d",Integer.valueOf(Integer.toBinaryString(i))));
			
			
			// look at each char of binaryNum and see if it equals 1
				Set<T> temp=new Set<T>();
				for (int k=0; k<binaryNum.length(); k++){
						
					if (binaryNum.charAt(k)=='1') {
						// add it to a temp set
						temp.add(elements[k]);
					}
				}
				// add it to the actual set
				set[i]=temp;
				
		}
	}
	// method that returns length
	public int getLength() {
		return set.length;
	}
	// method that returns the i th set
	public Set<T> getSet(int i){
		return set[i];
	}
	
}

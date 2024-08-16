/*
 * @author Dikran Kahiaian
 * Counter class that calculates the score of each hand of cards
 */
public class Counter {
	// instance variables
	private PowerSet<Card> cardps;
	private Card starter;
	
	// constructor that takes the hand and the starter card
	public Counter(Card[] hand, Card starter) {
		// initialize variables
		this.starter=starter;
		cardps= new PowerSet<Card>(hand);
		// countpoints method that adds up all the points and returns it
	}public int countPoints() {
		int totalPoints=0;
		totalPoints=Pairs()+Fifteen()+HisKnobs()+Runs()+Flush();
	
		return totalPoints;
	}
	// pairs method that checks if 2 cards are pairs
	private int Pairs() {
		int points=0;
		// loop through each set that has length 2 and check the cards
			for (int i=0; i< cardps.getLength(); i++) {
				Set<Card> hand = cardps.getSet(i);
				if (hand.getLength()==2 && hand.getElement(0).getRunRank() == hand.getElement(1).getRunRank()) {
					points += 2;
				}
			}
	return points;
	}
	
	// fifteen method that checks if any combination of cards add up to 15
	private int Fifteen() {
		int points=0;
		int cardPoints;
		// go through each set and get the element
		for (int i=0; i< cardps.getLength(); i++) {
			Set<Card> hand = cardps.getSet(i);
				cardPoints=0;
			for (int j=0; j<hand.getLength(); j++) {
				// add the cards 
				cardPoints+=hand.getElement(j).getFifteenRank();
				
			}
			// check if they equal 15 and return the result
			if (cardPoints==15) {
				points+=2;
			}
		}
		return points;
		
	}
	// flush method that checks if all cards have the same suit
	private int Flush() {
        int points=0;
        int counter=0;
        String suit;
        	// only check the set that contains all the cards
            Set<Card> hand = cardps.getSet(cardps.getLength()-1);
            // set the suit variable to any suit in the deck but not the starter suit
            if (!hand.getElement(0).toString().equals(starter.toString())) {
            	suit = hand.getElement(0).getSuit();
            }
 
            else {
            	suit = hand.getElement(1).getSuit();
            }
          
            for (int j=0; j<hand.getLength(); j++)    {
            	// check if the suits match
                if (!hand.getElement(j).toString().equals(starter.toString()) && !hand.getElement(j).getSuit().equals(suit)) {
                    break;
                    // check the starter seperately
                } else if (hand.getElement(j).equals(starter)) {
                		if (suit.equals(starter.getSuit())) {
                			points++;
                		}
                }else {
                	points++; 
                }
            }
            
            
            // only return if the points equal 4 or 5
          if (points==4 || points==5) {
        	  return points;
          }else {
        return 0;
          }
    }
	// his knobs method that checks if the j card's suit matches the starter's suit
	private int HisKnobs() {
		int points=0; 
		// only check the set that contains all cards
			Set<Card> hand = cardps.getSet(cardps.getLength()-1);
			for(int j=0; j<hand.getLength(); j++) {
				// check if the suit matches
				if (hand.getElement(j).getLabel().equals("J") && (!hand.getElement(j).toString().equals(starter.toString())) ) {
					if (hand.getElement(j).getSuit().equals(starter.getSuit())) {
						points++;
				}
			}
		}
		return points;
	}
	
// runs method that checks if there is a run	
	private int Runs() {
	    int max = 0;
	    int points=0;
	    // go through each set
	    for (int i = 0; i < cardps.getLength(); i++) {
	        Set<Card> hand = cardps.getSet(i);
	        //store the max run and reset the point variable everytime there is a new run
	        if (isRun(hand)> max) {
	        		
	            max = isRun(hand);
	            points=0;
	           // if the run and the max run match, add them
	        }  else if(isRun(hand)==max) {
	        	points+=isRun(hand);
	        	}
	    }
	    return max+points;
	
	}
	

	private int isRun (Set<Card> set) {
		// In this method, we are going through the given set to check if it constitutes a run of 3 or more
		// consecutive cards. To do this, we are going to create an array of 13 cells to represent the
		// range of card ranks from 1 to 13. We go through each card and increment the cell corresponding to
		// each card's rank. For example, an Ace (rank 1) would cause the first (index 0) cell to increment.
		// An 8 would cause the 8th (index 7) cell to increment. When this loop is done, the array will
		// contain 5 or less cells with values of 1 or more to represent the number of cards with each rank.
		// Then we can use this array to search for 3 or more consecutive non-zero values to represent a run.
		
		int n = set.getLength();
		if (n <= 2) return 0; // Run must be at least 3 in length.
		int[] rankArr = new int[13];
		for (int i = 0; i < 13; i++) rankArr[i] = 0; // Ensure the default values are all 0.
		
		for (int i = 0; i < n; i++) {
			rankArr[set.getElement(i).getRunRank()-1] += 1;
		}

		// Now search in the array for a sequence of n consecutive 1's.
		int streak = 0;
		int maxStreak = 0;
		for (int i = 0; i < 13; i++) {
			if (rankArr[i] == 1) {
				streak++;
				if (streak > maxStreak) maxStreak = streak;
			} else {
				streak = 0;
			}
		}
		if (maxStreak == n) { // Check if this is the maximum streak.
			return maxStreak;
		} else {
			return 0;
		}

	}

}

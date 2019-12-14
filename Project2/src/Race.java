import java.security.SecureRandom; 
public class Race {
	// positionOne and positionTwo are the starting positions of both the runners
	public static int positionOne = 1;
	public static int positionTwo = 1;
	// generateNumber() is a function that returns a random number between 1 and 10 using SecureRandom
	public static int generateNumber(){
	    SecureRandom rand = new SecureRandom(); 
		int randomNumber = rand.nextInt(9); 
		return randomNumber + 1;

	}
	// This method overload is to return the string "Race simulation class"
	public String toString() { 
	    return  "Race simulation class";
	}
	//getNewPositionOne() is a method that returns the positions that the first runner is moving forward or backwards. Forward is positive and backwards is negative. This is based on figure one.
	public static int getNewPositionOne(int percentageOne) {
		int position = 0;
		switch (percentageOne) {
		  case 1:
			  position = 3;
			  break;
		  case 2:
			  position =  3;
			  break;
		  case 3:
			  position = 3;
			  break;
		  case 4:
			  position =  3;
			  break;
		  case 5:
			  position = 3;
			  break;
		  case 6:
			  position =  -6;
			  break;
		  case 7:
			  position = -6;
			  break;
		  case 8:
			  position =  1;
			  break;
		  case 9:
			  position = 1;
			  break;
		  case 10:
			  position =  1;
			  break;
		}
		return position;
	}
	//getNewPositionTwo() is a method that returns the positions that the second runner is moving forward or backwards. Forward is positive and backwards is negative. This is based on figure one.
	public static int getNewPositionTwo(int percentageTwo) {
		int position = 0;
		switch (percentageTwo) {
		  case 2:
			  position =  5;
			  break;
		  case 3:
			  position = 5;
			  break;
		  case 4:
			  position =  -2;
			  break;
		  case 5:
			  position = -2;
			  break;
		  case 6:
			  position =  -10;
			  break;
		  case 7:
			  position = 1;
			  break;
		  case 8:
			  position =  1;
			  break;
		  case 9:
			  position = 1;
			  break;
		  case 10:
			  position =  1;
			  break;
		}
		return position;
	}
	// getSeperator() returns the seperator after every race iteration
	public static String getSeperator(){
		String seperator = "";
		for(int i = 0; i < 100; i++) {
			seperator += "-";
		}
		return seperator;
	}
	// startRace() is responsible for the race.
	public static void startRace(){
		//hasWon is false until someone has won
		boolean hasWon = false;
		// time stores the ticks
		int time = 0;
		String winner = "";
		// This printf statement is for the first iteration
		System.out.printf("%s%nTime: %d%n%s%n%s%n","On Your Mark, Get, Set, Go", time, "B", getSeperator());
		time += 1;
		// This loop runs until someone has won
		while(hasWon == false) {
			// We take two percentages for the positions. getNewPositionOne() and getNewPositionTwo() returns the positions to go forward and backward and is then added to positionOne and positionTwo
			int percentageOne = generateNumber();
			int percentageTwo = generateNumber();
			positionOne += getNewPositionOne(percentageOne);
			positionTwo += getNewPositionTwo(percentageTwo);
			// The next two if statements are to make sure that the positions do not go less than one. If they go less one it is assigned back to one.
			if(positionOne < 1) {
				positionOne = 1;	
			}
			if(positionTwo < 1) {
				positionTwo = 1;
			}
			// The next two if statements are to determine if we have a winner
			if(positionOne >= 100 && positionTwo < 100 ){
				winner = "Runner1";
				hasWon = true;
			}
			if(positionTwo >= 100  && positionOne < 100) {
				winner = "Runner2";
				hasWon = true;
			}
			String result = "";
			// This if statement runs when the two positions aren't equal. The for loop iterates and adds to the string result. If the runner's position is equal to i, it is added to the string as R1 or R2, else we just add a space. The else statement takes care of a tie.
			if(positionOne != positionTwo) {
				for(int i = 0; i < 100; i++) {
					if(i == positionOne){
						result += "R1";
					}
					else {
						if(i == positionTwo){
							result += "R2";
						}
						else {
							result += " ";
						}
					}
				}
			}
			else {
				result = "IT's A TIE";
			}
			//This statement is to print after each iteration. The time, result, and seperator are printed.
			System.out.printf("Time: %d%n%s%n%s%n", time, result, getSeperator());
			time += 1;
		}
		//This statement is to print the results of the race. If there is a tie, that would also be taken care of.
		if(winner.length() > 0) {
			System.out.printf("%s wins.%nTime elapsed = %d seconds%n", winner, time);
		}
		else {
			System.out.printf("There is a tie.%nTime elapsed = %d seconds%n", time);
		}
		
	}
	//This function calls startRace() to start and complete the race.
	public static void main(String[] args) {
		startRace();
	}
	
}


package hutchison.grant;

import java.util.Random;

/**
 * Purpose: Creates The 6 Sided Dice Used For The Shut The Box Game
 * @author Turner Aucoin
 * @date December 20th, 2024
 */

public class Die {
	private int value;
	private int numSides;
	
	Die (){
		numSides = 6;
		value = roll();
	}
	
	Die(int n){
		numSides = n;
		value = roll();
	}
	
	public int roll() {
		Random rand = new Random();
		value = rand.nextInt(1, numSides+1);
		return value;
	}
	
	public void setSides(int n) {
		numSides = n;
	}
}

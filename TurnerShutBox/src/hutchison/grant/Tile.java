package hutchison.grant;

/**
 * Purpose: Code For Checking And Showing The Tiles Are Down Or Up
 * @author Turner Aucoin
 * @date December 20th, 2024
 */

public class Tile {
	private int value;
	private boolean isUp;
	
	Tile(int n){
		value = n;
		isUp = true;
	}
	
	public int getValue() {
		return value;
	}
	
	public boolean isUp() {
		return isUp;
	}
	
	public void putDown() {
		isUp = false;
	}
	
	@Override
	public String toString() {
		String response = "";
		if (isUp) {
			response = "" + value + "(U) ";
		}
		else {
			response = "" + value + "(D) ";			
		}
		return response;
	}
}

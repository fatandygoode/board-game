//class not used

package models;

public class NumberSquare extends Square
{
	private int numberSquareType; 
 
    public NumberSquare(int numberSquareType) {
    	super();
    	setSquareType("Number");
    }

	public int getNumberSquareType() {
		return numberSquareType;
	}

	public void setNumberSquareType(int numberSquareType) {
		this.numberSquareType = numberSquareType;
	}   
}  
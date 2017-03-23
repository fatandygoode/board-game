package models;

public class Square 
{

	private int squareNumber;
    private String squareType;
    private boolean isOccupied; 
 

    /**
     * Constructor for objects of class Square
     * 
     * @param squareNumber - The square number
     */
    public Square(int squareNumber){//, String squareType) {
    	this.squareNumber = squareNumber;
    	//this.squareType = squareType;
    }
     
    //Getters
    public String getSquareType() {
        return squareType;
    }
     
    public boolean getIsOccupied() {
        return isOccupied;
    }
    public int getSquareNumber() {
		return squareNumber;
	}
     
    //Setters
    

    public void setSquareType(String squareType) {
    	this.squareType = squareType;
    	}
    
    public void setIsOccupied(boolean isOccupied) {
        this.isOccupied = isOccupied;
    }

	//public void setSquareNumber(int squareNumber) {//method not required
	//	this.squareNumber = squareNumber;
	//}
    
    public String toString() {
    	return squareNumber + "\t" + squareType + "\n";
    }
}


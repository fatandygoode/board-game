package models;

public class Square 
{
    //Instance Fields
	private int squareNumber;
    private String squareType;
    private boolean isOccupied; 
 
    //Constructor
    public Square(int squareNumber, String squareType) {
    	this.squareNumber = squareNumber;
    	this.squareType = squareType;
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
    
    //public void setSquareType(String squareType) {
    //        this.squareType = squareType;
    //}
    
    public void setIsOccupied(boolean isOccupied) {
        this.isOccupied = isOccupied;
    }

	//public void setSquareNumber(int squareNumber) {
	//	this.squareNumber = squareNumber;
	//}
    
    public String toString() {
    	return squareNumber + squareType + isOccupied;
    }
}


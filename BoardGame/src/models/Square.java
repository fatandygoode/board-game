package models;

public class Square 
{
    //Instance Fields
	private int squareNumber;
    private String squareType;
    private boolean isOccupied; 
 
    //Constructor
   // public Square(int squareNumber, String squareType) {
    //	this.squareNumber = squareNumber;
    	//this.squareType = squareType;
   // }
    

   // public Square()
	//{
		//for(int squareNumber = 0; squareNumber < 66; squareNumber++)
		//{
		//	this.squareNumber = squareNumber;
		//	if(squareNumber == 0)
		//	{
		//		squareType = "Start";
		//	}
		//	else if(squareNumber == 1 || squareNumber == 3 || squareNumber == 6 || squareNumber == 14 ||
		//			squareNumber == 25 || squareNumber == 31 || squareNumber == 34 || squareNumber == 39
		//			 || squareNumber == 46 || squareNumber == 51 || squareNumber == 58 || squareNumber == 62)
		//	{
		//		squareType = "Hare";
		//	}
		//}
//	}
		
     
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


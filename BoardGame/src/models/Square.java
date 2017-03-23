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
    

    public void makeSquare()
	{
		for(int squareNumber = 0; squareNumber < 66; squareNumber++)
		{
			this.squareNumber = squareNumber;
			if(squareNumber == 0)
			{
				squareType = "Start";
			}
			else if(squareNumber == 65)
			{
				squareType = "Finish";
			}
			else if(squareNumber == 1 || squareNumber == 3 || squareNumber == 6 || squareNumber == 14 ||
					squareNumber == 25 || squareNumber == 31 || squareNumber == 34 || squareNumber == 39
					 || squareNumber == 46 || squareNumber == 51 || squareNumber == 58 || squareNumber == 62)
			{
				squareType = "Hare";
			}
			else if(squareNumber == 2 || squareNumber == 5 || squareNumber == 13 || squareNumber == 21|| 
					squareNumber == 26 || squareNumber == 33 || squareNumber == 38 || squareNumber == 40 || 
					 squareNumber == 49 || squareNumber == 55 || squareNumber == 59 || squareNumber == 61 ||
					 squareNumber == 63)
			{
				squareType = "Carrot";
			}
			else if (squareNumber == 10 || squareNumber == 22 || squareNumber == 42 || squareNumber == 57)
			{
				squareType = "Lettuce";
			}
			else if(squareNumber == 11 || squareNumber == 15 || squareNumber == 19 || squareNumber == 24 || 
					squareNumber == 30 || squareNumber == 37 || squareNumber == 43 || squareNumber == 50 ||
					squareNumber == 56)
			{
				squareType = "Tortoise";
			}
			else
			{
				squareType = "Number";
			}
		}
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
    	return "Number: " + squareNumber  
    			+ " Type: " + squareType 
    			//+ "Occupied" + isOccupied  - Code to only show this section if a square is occupied
    			+ "\n";
    }
}


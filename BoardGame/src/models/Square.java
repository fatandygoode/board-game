package models;

public class Square 
{
 
    //Instance Fields
    private String squareType;
    private boolean isOccupied; 
 
    //Constructor
    public Square(String squareType, boolean isOccupied)
    {
        setSquareType(squareType);
        isOccupied = false;
    }
     
    //Getters
    public String getSquareType()
    {
        return squareType;
    }
     
    public boolean getIsOccupied()
    {
        return isOccupied;
    }
     
    //Setters
    public void setSquareType(String squareType)
    {
        if((squareType.toUpperCase().equals("START")) || 
                (squareType.toUpperCase().equals("HARE")) ||
                (squareType.toUpperCase().equals("CARROT")) ||
                (squareType.toUpperCase().equals("NUMBER")) ||
                (squareType.toUpperCase().equals("LETTUCE")) ||
                (squareType.toUpperCase().equals("TORTOISE")) ||
                (squareType.toUpperCase().equals("FINISH")))
        {
            this.squareType = squareType;
        }
    }
    public void setIsOccupied(boolean isOccupied)
    {
        this.isOccupied = isOccupied;
    }
}


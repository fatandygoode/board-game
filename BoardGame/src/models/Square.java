package models;

public class Square 
{
	//Instance Fields
	private String type;	//change to squareType ?? --- JM
	
	private int position; 	//consider changing variable to either 
							//currentPosition or squarePosition 
							//depending on what it's intended to represent --- JM
	/**
     * Constructor for objects of class Square
     */
	public Square(String type, int position)
	{
		if(		(type.toUpperCase().equals("START")) 	|| 
				(type.toUpperCase().equals("HARE")) 	||
				(type.toUpperCase().equals("CARROT")) 	||
				(type.toUpperCase().equals("NUMBER")) 	||
				(type.toUpperCase().equals("LETTUCE")) 	||
				(type.toUpperCase().equals("TORTOISE")) ||
				(type.toUpperCase().equals("FINISH"))	)
		{
			this.type = type;
		}
		if((position >= 0) && (position <= 65))
		{
			this.position = position;
		}
	}

	//Getters
	public int getPosition()
	{
		return position;
	}
	
	public String getType()
	{
		return type;
	}
	
	//Setters
	public void setType(String type)
	{
		if(		(type.toUpperCase().equals("START")) 	|| 
				(type.toUpperCase().equals("HARE")) 	||
				(type.toUpperCase().equals("CARROT")) 	||
				(type.toUpperCase().equals("NUMBER")) 	||
				(type.toUpperCase().equals("LETTUCE")) 	||
				(type.toUpperCase().equals("TORTOISE")) ||
				(type.toUpperCase().equals("FINISH"))	)
		{
			this.type = type;
		}
	}
	
	public void setPosition(int position)
	{
		if((position >= 0) && (position <= 65))
		{
			this.position = position;
		}
	}
}






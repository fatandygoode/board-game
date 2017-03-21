package models;

import java.util.ArrayList;
import models.Player;

public class Board
{
	
    public ArrayList<Player> players;

    /**
     * Constructor for objects of class Board
     */
    public Board()
    {
        players = new ArrayList<Player>();
    }

    //methods
    	
    public void add(Player player)
    {
        players.add(player);  
    }
    
    public int numberOfPlayers()
    {
    	return players.size();
    }
    
    public String listPlayers()
    {
        String list = "";
        for (int index = 0; index < players.size(); index++)
        {
        	list = "\n" + list + index + " - " + players.get(index).getPlayerName() + "\n";
        }
        if (list.equals(""))
        {
            return "No players";
        }
        else
        {
            return list;
        }        
    }

	public void remove(int index) 
	{
		if ( (index >= 0) && (index < players.size() ) )
        {
            players.remove(index);
        }
		
	}
	public Player get(int index)
    {
		return players.get(index);
    }
}


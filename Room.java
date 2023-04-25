/*
Shihab Abdelrahim
CPSC1061 Section 005
April 17, 2023
*/

import java.util.ArrayList;

public class Room
{
    String name;
    String description;
    ArrayList<String> exits;
    boolean gameOver;
    
    /**
     * Initialize a room
     * @param name the name of the room
     * @param description the description of the room
     */
    
    public Room(String name, String description)
    {
        this.name = name;
        this.description = description;
        this.exits = new ArrayList<String>();
        this.gameOver = gameOver; 
    }

    /* Generate getters and setters for the naems and descriptions */
    
    /**
     * Adds an exit to the room
     * @param exit room name of the exit to be added to the room
     */
    public void addExit(String exit)
    {
        this.exits.add(exit);
    }

    public void setOver(boolean gameOver)
    {
        this.gameOver = gameOver;
    }

    public boolean getOver()
    {
        return this.gameOver;
    }

    //method to get the name of the room
    public String getName() {
        return this.name;
    }

    /**
     * List all of the rooms as a string
     * @return returns all of the names of the rooms on new lines
     */
    public ArrayList<String> listExits()
    {
        return this.exits;
    }

    /**
     * Generates a string representation of the room using the name and description and lists all of the exits.
     */
    public String toString()
    {
        return this.name + ": "+ this.description;
    }
}
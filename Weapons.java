/*
Shihab Abdelrahim
CPSC1061 Section 005
April 17, 2023
*/

import java.util.ArrayList;

public class Weapons
{
    boolean weaponUse;
    ArrayList<String> weaponOptions;

    //main class constructor
    public Weapons()
    {
        this.weaponUse = false;
        this.weaponOptions = new ArrayList<String>();
    }

    //adds the weapon chosen to the arraylist
    public void addWeapon(String weapon)
    {
        this.weaponOptions.add(weapon);
    }

    //gets the chosen weapon from the arrayList
    public ArrayList<String> getWeapons()
    {
        return this.weaponOptions;
    }

    //returns if the user loses
    public void useWeapon(boolean gameOver)
    {
        this.weaponUse = weaponUse;
    }

    //checks if the user wants to use the weapon
    public boolean useWeapon()
    {
        return this.weaponUse;
    }

}
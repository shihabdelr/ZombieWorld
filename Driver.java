/*
Shihab Abdelrahim
CPSC1061 Section 005
April 17, 2023
*/

import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.IOException;

public class Driver
{

    //method that validates the exit choice which the user enters
    public static String validateExit(String exitChoice, ArrayList<String> exitOptions, Scanner scnr) {
        boolean validExit = false;
        while (!validExit) {
            //if the user's input is in the exit choices, then the while loop stops
            if (exitOptions.contains(exitChoice)) {
                validExit = true;
            } else {
                //else, the user is prompted to enter a proper exit choice
                System.out.println("Invalid exit.\nPlease choose an exit");
                exitChoice = scnr.nextLine();
            }
        }
        //returning the valid user exit choice
        return exitChoice;
    }

    public static void main(String[] args)
    {   
        //creating the file output
        FileOutputStream fileStream = null;
        PrintWriter outFS = null;

        try {
            fileStream = new FileOutputStream("outcome.txt");
        }
        catch (Exception excpt){
            System.out.println("excpt.getMessage()");
        }
        
        
        outFS = new PrintWriter(fileStream);


        //creating all the different pathways for the game
        AdventureMap map = new AdventureMap();
        Room begin = new Room("Beginning", "You are currently in your room.");
        begin.setOver(false);
        begin.addExit("Stay");
        begin.addExit("Leave");
        
        Room stay = new Room("Stay", "You decide to stay in your room and hope that the zombies magically dissapear. Unfortuantely for you, they don't.");
        stay.setOver(true);

        Room leave = new Room("Leave", "You decide to leave, good decision. You make it outside your house and can go either left or right. Which way will you choose?");
        map.addRoom(leave);
        leave.setOver(false);
        leave.addExit("Left");
        leave.addExit("Right");

        Room left = new Room("Left", "You decide to go left. You begin to walk half a mile left when, unfortunately for you, you're caught off gaurd by a mob zombies and die.");
        left.setOver(true);
        map.addRoom(left);

        Room right = new Room("Right", "You decide to go right. You walk for half a mile before you hear someone screaming for help. Should you be a hero and save them? or ignore them?");
        map.addRoom(right);
        right.setOver(false);
        right.addExit("Save");
        right.addExit("Ignore");

        Room save = new Room("Save", "You decide to save the man who needs help. He thanks you so much, and informs you that his name is Gary.");
        map.addRoom(save);
        save.setOver(false);
        save.addExit("Attack");
        save.addExit("Dodge");
        save.addExit("Sacrifice");

        Room ignore = new Room("Ignore", "You decide to mind your own business.");
        map.addRoom(ignore);
        ignore.setOver(false);
        ignore.addExit("Attack");
        ignore.addExit("Dodge");

        Room dodge = new Room("Dodge", "You decide to dodge the zombie. Unfortunatley for you, the zombie's attack was too fast.");
        map.addRoom(dodge);
        dodge.setOver(true);

        Room attack = new Room("Attack", "You successfully attacked the zombie with your sword, good thinking. You found the hospital and enter it.\nNow, should you take the elevator or the stairs?");
        map.addRoom(attack);
        attack.setOver(false);
        attack.addExit("Elevator");
        attack.addExit("Stairs");

        Room sacrifice = new Room("Sacrifice", "You decided to sacrifice Gary. You run towards the hospital's location and thankfully make it in time. Now, do you use the elevator, or the stairs?");
        map.addRoom(sacrifice);
        sacrifice.addExit("Elevator");
        sacrifice.addExit("Stairs");

        Room elevator = new Room("Elevator", "You chose to go into the elevator in the hospital because you were so tired, and decided you needed a break.\nWell, tough luck. The elevator breaks on its way up, and you fall to your death.");
        map.addRoom(elevator);
        elevator.setOver(true);

        Room stairs = new Room("Stairs", "You chose to go up the stairs in the hospital. You finally meet up with your friend, and you can finally rest (for now)");
        map.addRoom(stairs);
        stairs.setOver(false);




        
        map.addRoom(stay);

        System.out.println();

        //main description of the game
        Scanner scnr = new Scanner(System.in);
        System.out.println("You wake up in a post-apocalyptic world that has been overrun by zombies.");
        System.out.println("You check your phone and see this message from your friend:\n'There are survivors in the hospital. If you're still alive, come immediately!!!!'");
        System.out.println("What will you do next?");

        
        boolean gameOver = false;
        boolean print = false;
        ArrayList<String> exitOptions = new ArrayList<String>();
        ArrayList<String> weaponOptions = new ArrayList<String>();
        ArrayList<String> weaponAquired = new ArrayList<String>();
        String weaponChoice = "";
        String exitChoice = "";
        
        //weapons available for the user later
        Weapons sharp = new Weapons();
        sharp.addWeapon("Sword");
        sharp.addWeapon("Nunchuks");
        sharp.addWeapon("Knife");


        String roomName = "Begin";

        System.out.println();
        System.out.println(begin.toString());
        System.out.println("Here are your options:");
        exitOptions = begin.listExits();
        for (String ex : exitOptions) {
            System.out.println(ex);
        }

        //runs while the game is not over
        while (!gameOver){
            //if to see wether the user wants to save Gary or not


            if (roomName.equals("Save") || roomName.equals("Ignore")) {
                System.out.println();
                if (roomName.equals("Ignore")) {
                    System.out.println("While walking after ignoring the man who needed help (rude), you encounter these weapons:");
                } else {
                    System.out.println("After saving Gary's life, you guys decide to walk to the hospital to find the others. While walkings you encounter these weapons:");
                }

                //asks the user if they would like to use a weapon
                weaponOptions = sharp.getWeapons();
                for (String ex : weaponOptions) {
                    System.out.println(ex);
                }

                //validates users output
                System.out.println("Would you like to pick up any of these weapons? if so, type in the weapon name. else, type no.");
                weaponChoice = scnr.nextLine();
                while(!(weaponChoice.equalsIgnoreCase("No")) && !(weaponChoice.equalsIgnoreCase("Sword")) && !(weaponChoice.equalsIgnoreCase("Nunchuks")) && !(weaponChoice.equalsIgnoreCase("Knife"))) {
                    System.out.println("Please enter a valid weapon choice, or enter No if you would not like a weapon");
                    weaponChoice = scnr.nextLine();
                }
                outFS.println(weaponChoice);

                //the user's fate based on the weapon they chose
                if (!(weaponChoice.equalsIgnoreCase("No"))) {
                    weaponAquired.add(weaponChoice);
                }
                if (roomName.equals("Ignore")) {
                    System.out.println();
                    System.out.println("While continuing on after ignoring the man screaming for help (again, rude), you decide to continue walking to the hospital to find the other survivors.\nWhile walkings, you turn your head and see that a zombie about to attck you. What will you do?");
                } else {
                    System.out.println();
                    System.out.println("While you guys walk to the hospital to find the others, you two caught off gaurd by a zombie about to attack you both. What should you do?");
                }
            }

            //printing out the exit options
            if (print) {
                System.out.println("Here are your options:");
                for (String ex : exitOptions) {
                    System.out.println(ex);
                }
            }
            
            print = true;

            //getting the user's requested exit method
            roomName = scnr.nextLine();

            if (roomName.equals("Attack")) {
                if (weaponAquired.isEmpty()) {
                    System.out.println("Unfortunately, you don't have any weapons to attack the zombie with. Should've picked up one of them while you were on your way smh.");
                    gameOver = true;
                    break;
                } else if (weaponChoice.equalsIgnoreCase("Nunchuks") || weaponChoice.equalsIgnoreCase("Knife")) {
                    System.out.println("Sorry, your " + weaponChoice + " didn't even phase the zombie. Tough luck");
                    gameOver = true;
                    break;
                } else {
                    System.out.println("You attacked a zombie with a sword. Right decision!");
                }
            }
            
            //else, the validateExit method will validate the exit and return once a valid exit is entered
            roomName = validateExit(roomName, exitOptions, scnr);
            outFS.println(roomName);
            //Creating and setting the room object to the exit desired from the AdventureMap class
            Room room = map.getRoom(roomName);
            
            //setting the arraylist of exit options
            exitOptions = room.listExits();

            gameOver = room.getOver();
            System.out.println();
            
            //printing out the desired room's description
            System.out.println(room.toString());
            if (roomName.equalsIgnoreCase("Stairs")) {
                break;
            }
        }

        //printing out the user's final outcome of the game and sending it to outcome.txt
        if (gameOver) {
            System.out.println("Uh oh, something bad happened. Go to outcome.txt to find out your outcome.");
            outFS.println("GAME OVER");
        } else {
            System.out.println("Woah, something happend. Go to output.txt to see the outcome of your game.");
            outFS.println("Congrats, you didn't die");
        }
        outFS.close();
    }

    

}
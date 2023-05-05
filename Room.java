/*
    Kenny Nguyen
    CPSC 1060: The RPG Project
    Section 3
    05-04-23
*/

import java.util.ArrayList;

public class Room {

    String name;
    String description;
    ArrayList<String> exits;
    ArrayList<Item> items;
    boolean locked;
    
    /**
     * Initialize a room
     * @param name the name of the room
     * @param description the description of the room
     */
    public Room(String name, String description) {
        this.name = name;
        this.description = description;
        this.exits = new ArrayList<String>();
        this.items = new ArrayList<Item>();
    }

    /* Generate getters and setters for the names and descriptions */
    public String getName() {
        return this.name;
    }
    public String getDescription() {
        return this.description;
    }
    public boolean getLocked() {
        return this.locked;
    }
    public void setLocked() {
        this.locked = true;
    }
    public void setUnlocked() {
        this.locked = false;
    }

    /**
     * Adds an exit to the room
     * @param exit room name of the exit to be added to the room
     */
    public void addExit(String exit) {
        this.exits.add(exit);
    }

    /**
     * Adds an exit to the room
     * @param exit room name of the exit to be added to the room
     */
    public void addItem(Item item) {
        this.items.add(item);
    }

    /**
     * List all of the rooms as a string
     * @return returns all of the names of the rooms on new lines
     */
    public String listExits() {
        String exitList = "";
        exitList += "Exits:\n";
        for (int i = 0; i < exits.size(); i++) {
            exitList = exitList + exits.get(i) + "\n";
        }
        return exitList;
    }

    /**
     * Generates a string representation of the room using the name and description and lists all of the exits.
     */
    public String toString() {
        String exitList = "";
        exitList += "Exits:\n";
        for (int i = 0; i < exits.size(); i++) {
            exitList = exitList + exits.get(i) + "\n";
        }
        return name + ": " + description + "\n" + "\n" + exitList;
    }

    public String ratKingDialogue() {
        return "Rat King: 'You cannot enter this place! We have claimed this land as ours! You cannot take it away from us!'\n" +
               "\nYou (Whiskers): 'No, this is my home! You can't just claim anything as yours.'\n" +
               "\nRat King: 'You dare to challenge me? That is a declaration of war! We will conquer this entire land, which you call home...'\n" +
               "\nYou (Whiskers): *ponders* 'I need to find a way to get rid of these scallywags...'\n";
    }

    public String killRatKing() {
        return "Rat King: 'Huh? You've come to challenge me again? Wait, what is this?'\n" +
               "\nYou (Whiskers): 'I have come to surrender! Please take this as a symbol of submission...'\n" +
               "\nRat King: 'Oh, I see... As the new conquerer of this house, I hath banish you from my kingdom! Begone from my sight!'\n" + 
               "\nYou leave the Pantry.\n" +
               "\nYou (Whiskers): 'Ha! Time to plug in this bomb...'\n" +
               "\nYou plug in the bomb." +
               "\nThe bomb starts quickly filling up with air..." +
               "\nYou walk away from the pantry in slow motion because cool guys don't look at explosions..." + 
               "\nA couple seconds later... You have defeated the Rat King and his entourage.\n";
    }
}

/*
    Kenny Nguyen
    CPSC 1060: The RPG Project
    Section 3
    05-04-23
    https://github.com/kknguye/ClawsAndConquest.git
*/

import java.util.ArrayList;

public class Item {

    String name;
    String description;
    boolean used;
    boolean taken;

    /**
     * Initialize an item
     * @param name the name of the item
     * @param description the description of the item
     */
    public Item(String name, String description) {
        this.name = name;
        this.description = description;
        this.used = false;
        this.taken = false;
    }

    /* Generate getters and setters for the names and descriptions */
    public String getName() {
        return this.name;
    }
    public String getDescription() {
        return this.description;
    }
    public boolean getUsed() {
        return this.used;
    }
    public void setUsed() {
        this.used = true;
    }
    public boolean getTaken() {
        return this.used;
    }
    public void setTaken() {
        this.used = true;
    }

    /**
     * Generates a string representation of the item using the name and description.
     */
    public String toString() {
        return name + ": " + description + "\n";
    }

    /**
     * @return returns item found
     */
    public String itemFound() {
        return "You found a '" + name + "'!\n" + "Description: '" + description + "'\nIt has been added to your inventory.";
    }
    
    /**
     * @return returns item equipped
     */
    public String itemEquipped() {
        return "You found a '" + name + "'!\n" + "Description: '" + description + "'\nIt has been equipped.";
    }

    /**
     * @return returns item created
     */
    public String itemCreated() {
        return "'" + name + "' created!\n" + "Description: '" + description + "'\nIt has been added to your inventory.";
    }

}

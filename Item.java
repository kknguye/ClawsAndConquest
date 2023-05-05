/*
    Kenny Nguyen
    CPSC 1060: The RPG Project
    Section 3
    05-04-23
*/

import java.util.ArrayList;

public class Item {

    String name;
    String description;
    boolean used;
    boolean taken;

    public Item(String name, String description) {
        this.name = name;
        this.description = description;
        this.used = false;
        this.taken = false;
    }

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

    public String toString() {
        return name + ": " + description + "\n";
    }

    public String itemFound() {
        return "You found a '" + name + "'!\n" + "Description: '" + description + "'\nIt has been added to your inventory.";
    }
    
    public String itemEquipped() {
        return "You found a '" + name + "'!\n" + "Description: '" + description + "'\nIt has been equipped.";
    }

    public String itemCreated() {
        return "'" + name + "' created!\n" + "Description: '" + description + "'\nIt has been added to your inventory.";
    }

}
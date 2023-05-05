/*
    Kenny Nguyen
    CPSC 1060: The RPG Project
    Section 3
    05-04-23
*/

import java.util.Scanner;
import java.util.HashMap;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Driver {

    public static void main(String[] args) throws IOException {

        // Create Scanner
        Scanner scnr = new Scanner(System.in);

        // Get local time
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss");
        LocalDateTime time = LocalDateTime.now();

        // Create file 
        FileOutputStream fileStream = new FileOutputStream("outputLog.txt");
        PrintWriter outFS = new PrintWriter(fileStream);
        outFS.println(dtf.format(time) + " - Program started");

        // Welcome user
        System.out.println("\nWelcome to Claws and Conquest! You are a cat, named Whiskers, tasked with the mission to rid of the rodents that are invading your home. Is there a way to expel these scoundrels? Entering the litter box.\n");

        // Initialize Adventure Map
        AdventureMap adventureMap = new AdventureMap();

        /** Initialize Litter Box with exits
         * Exits: Bathroom
         * Items: Closet Key
         */
        Room litterBox = new Room("Litter Box", "The room is cramped, surrounded by low walls. This room offers no privacy nor seclusion, yet there is something strangely comforting about this room. The ground is covered in a sand-like substance that crunches as you take a step. The air is musty and thick with a hint of Febreze.");
        Item closetKey = new Item("Closet Key", "An old, rusted key.");
        litterBox.addExit("Bathroom");
        litterBox.addItem(closetKey);
        adventureMap.addRoom(litterBox);

        /** Initialize Bathroom with exits
         * Exits: Living Room, Litter Box
         * Items: Poop Scooper
         */
        Room bathroom = new Room("Bathroom", "The room is tiled from floor to ceiling in gleaming white ceramic, and the air is filled with the clean scent of soap and fresh linens. It's so clean, you could probably eat off of the toilet seat.");
        Item poopScooper = new Item("Poop Scooper", "A tool designed to scoop poop.");
        bathroom.addExit("Living Room");
        bathroom.addExit("Litter Box");
        bathroom.addItem(poopScooper);
        adventureMap.addRoom(bathroom);
        
        /** Initialize Living Room with exits
         * Exits: Kitchen, Hallway, Bathroom
         * Items: None
         */
        Room livingRoom = new Room("Living Room", "A cozy, well-lit room. You see your human caretakers roam this room frequently. They are often staring aimlessly at a black box with random colored lights flashing. You could waltz pass without them batting an eye.");
        livingRoom.addExit("Kitchen");
        livingRoom.addExit("Hallway");
        livingRoom.addExit("Bathroom");
        adventureMap.addRoom(livingRoom);

        /** Initialize Kitchen with exits
         * Exits: Living Room, Pantry
         * Items: Rodent Repellent
         */
        Room kitchen = new Room("Kitchen", "The kitchen is a bright, spacious room filled with all the modern amenities needed for cooking and entertainment. You are greeted with the aroma of freshly, brewed coffee and slightly, burnt toast.");
        Item rodentRepellent = new Item("Rodent Repellent", "This emits a pungent aroma that is particularly unpleasant to mice and rats, discouraging them from entering the area where it is applied. The smell is not harmful to humans or pets and can be used safely around the house to keep unwanted rodents away.");
        kitchen.addExit("Living Room");
        kitchen.addExit("Pantry");
        kitchen.addItem(rodentRepellent);
        adventureMap.addRoom(kitchen);

        /** Initialize Pantry with exits
         * Exits: Kitchen
         * Items: None
         */
        Room pantry = new Room("Pantry", "A rodent's dream. This room is filled with nothing, but shelves loaded with snacks and other edible delights. This is where the rodents' hideout is.");
        pantry.addExit("Kitchen");
        adventureMap.addRoom(pantry);

        /** Initialize Hallway with exits
         * Exits: Living Room, Office, Bedroom
         * Items: Office Key
         */
        Room hallway = new Room("Hallway", "The hallway is a long, narrow space that connects the rooms of the house. Its walls are adorned with family photos and artwork, while a soft, plush runner lines the hardwood floors, muffling the sound of footsteps.");
        Item officeKey = new Item("Office Key", "A basic, silver key.");
        hallway.addExit("Living Room");
        hallway.addExit("Office");
        hallway.addExit("Bedroom");
        hallway.addItem(officeKey);
        adventureMap.addRoom(hallway);

        /** Initialize Bedroom with exits
         * Exits: Closet, Hallway
         * Items: None
         */
        Room bedroom = new Room("Bedroom", "The room is designed to be a peaceful oasis, a place to rest and recharge. The soft glow of a bedside lamp illuminates the room, casting gentle shadows across the walls and ceiling.");
        bedroom.addExit("Closet");
        bedroom.addExit("Hallway");
        adventureMap.addRoom(bedroom);

        /** Initialize Closet with exits
         * Exits: Bedroom
         * Items: Air Compressor
         */
        Room closet = new Room("Closet", "The closet is a chaotic mess, with clothing and accessories scattered haphazardly across the shelves and floor. The air is heavy with the scent of fabric and dust, and it's clear that some serious organization is desperately needed.");
        Item airCompressor = new Item("Air Compressor", "A powerful tool that pressurizes and stores compressed air.");
        closet.addExit("Bedroom");
        closet.addItem(airCompressor);
        closet.setLocked();
        adventureMap.addRoom(closet);

        /** Initialize Office with exits
         * Exits: Hallway
         * Exit added after unlocked: Wardrobe
         * Items: None
         */
        Room office = new Room("Office", "The office is a serene and productive space, with a large desk and comfortable chair ready for work. Natural light pours through the window, illuminating the room and providing a sense of calm and focus.");
        office.addExit("Hallway");
        office.setLocked();
        adventureMap.addRoom(office);

        /** Initialize Wardrobe with exits
         * Exits: Office
         * Items: Suit, Sunglasses
         */
        Room wardrobe = new Room("Wardrobe", "A tall storage solution, with multiple drawers and shelves for organizing a variety of clothing and accessories. Its polished wooden exterior looks elegant in the gleams of light. It is mostly filled with suits and other formal attire.");
        Item suit = new Item("Suit", "Dressed to impress.");
        Item sunglasses = new Item("Sunglasses", "An eye-catching accessory.");
        wardrobe.addExit("Office");
        wardrobe.addItem(suit);
        wardrobe.addItem(sunglasses);
        adventureMap.addRoom(wardrobe);

        // Initialize Rodent Killer Bomb
        Item rodentKillerBomb = new Item("Rodent Killer Bomb", "Clear the infestation with a powerful rodent-killing explosion!");

        /*
         * Create a scanner, put the inital room (entrance) to "Litter Box"
         * While the user's input is not exit, continue to traverse the map. 
         * If there is an invalid selection, then print out "Invalid exit."
         * After you are done selecting, print out "Where would you like to go?" and get next exit
         */

        // Print "Litter Box" as the initial room
        String selection = "Litter Box";
        String yesno = "";
        System.out.println(adventureMap.getRoom("Litter Box"));
        if (selection.equals("Litter Box")) {
            System.out.println("Looks like there's something under the sand-like substance. You might need something before you can search under it.");
            System.out.println();
        }
        
        // Loop until the user defeats the Rat King
        boolean gameOver = false;
        while (!gameOver) {
            // Prompt user to choose an exit
            System.out.println("Where would you like to go?");
            selection = scnr.nextLine();
            System.out.println();
            // If statement to break if "exit"
            if (selection.equals("exit")) {
                gameOver = true;
                time = LocalDateTime.now();
                outFS.println(dtf.format(time) + " - Program terminated");
                outFS.close();
            }
            // If input returns null, print "Invalid exit."
            else if (adventureMap.getRoom(selection) == null) {
                System.out.println("Invalid area.");
            }
            // Otherwise, print the room based on user input
            else {
                time = LocalDateTime.now();
                outFS.println(dtf.format(time) + " - Player enters " + selection);
                System.out.println(adventureMap.getRoom(selection));

                // Litter Box selection
                if (selection.equalsIgnoreCase("Litter Box")) {
                    // Player does not have 'Poop Scooper'
                    if (poopScooper.getTaken() == false) {
                        System.out.println("Looks like there's something under the sand-like substance. You might need something before you can search under it.");
                        System.out.println();
                    }
                    // Player has 'Poop Scooper'
                    else if (poopScooper.getTaken() == true) {
                        System.out.println("Looks like there is something under the sand-like substance. Would you like to use 'Poop Scooper' to dig it up?");
                        yesno = scnr.nextLine();
                        while (!(yesno.equalsIgnoreCase("yes") || yesno.equalsIgnoreCase("no"))) {
                            System.out.println("Please enter yes or no.");
                            yesno = scnr.nextLine();
                        }
                        // Player picks up 'Closet Key'
                        if (yesno.equalsIgnoreCase("yes")) {
                            System.out.println();
                            System.out.println(closetKey.itemFound());
                            closetKey.setTaken();
                            time = LocalDateTime.now();
                            outFS.println(dtf.format(time) + " - Player picked up 'Closet Key'");
                        }
                        System.out.println();
                    }
                }

                // Bathroom selection
                if (selection.equalsIgnoreCase("Bathroom")) {
                    // Player did not pick up 'Poop Scooper'
                    if (poopScooper.getTaken() == false) {
                        System.out.println("There's something sticking out of that cabinet under the sink. Would you like to open it?");
                        yesno = scnr.nextLine();
                        while (!(yesno.equalsIgnoreCase("yes") || yesno.equalsIgnoreCase("no"))) {
                            System.out.println("Please enter yes or no.");
                            yesno = scnr.nextLine();
                        }
                        // Player picks up 'Poop Scooper'
                        if (yesno.equalsIgnoreCase("yes")) {
                            System.out.println();
                            System.out.println(poopScooper.itemFound());
                            poopScooper.setTaken();
                            time = LocalDateTime.now();
                            outFS.println(dtf.format(time) + " - Player picked up 'Poop Scooper'");
                        }
                        System.out.println();
                    }
                }

                // Hallway selection
                if (selection.equalsIgnoreCase("Hallway")) {
                    // Player did not pick up 'Office Key'
                    if (officeKey.getTaken() == false) {
                        System.out.println("There's a bump under that rug... Would you like to lift it up?");
                        yesno = scnr.nextLine();
                        while (!(yesno.equalsIgnoreCase("yes") || yesno.equalsIgnoreCase("no"))) {
                            System.out.println("Please enter yes or no.");
                            yesno = scnr.nextLine();
                        }
                        // Player picks up 'Office Key'
                        if (yesno.equalsIgnoreCase("yes")) {
                            System.out.println();
                            System.out.println(officeKey.itemFound());
                            officeKey.setTaken();
                            time = LocalDateTime.now();
                            outFS.println(dtf.format(time) + " - Player picked up 'Office Key'");
                        }
                        System.out.println();
                    }
                }

                // Office selection
                if (selection.equalsIgnoreCase("Office")) {
                    // Office is locked & Player does not have 'Office Key'
                    if ((office.getLocked() == true) && (officeKey.getTaken() == false)) {
                        System.out.println("This door is locked. Look around the house for a key.");
                        System.out.println();
                    }
                    // Office is locked & Player has 'Office Key'
                    else if ((office.getLocked() == true) && (officeKey.getTaken() == true)) {
                        System.out.println("This door is locked. Would you like to use 'Office Key'?");
                        yesno = scnr.nextLine();
                        while (!(yesno.equalsIgnoreCase("yes") || yesno.equalsIgnoreCase("no"))) {
                            System.out.println("Please enter yes or no.");
                            yesno = scnr.nextLine();
                        }
                        // Player uses 'Office Key' & Office becomes unlocked
                        if (yesno.equalsIgnoreCase("yes")) {
                            System.out.println();
                            System.out.println("'Office Key' used.");
                            System.out.println("Door unlocked.");
                            officeKey.setUsed();
                            office.setUnlocked();
                            office.addExit("Wardrobe");
                            adventureMap.addRoom(office);
                            time = LocalDateTime.now();
                            outFS.println(dtf.format(time) + " - Player used 'Office Key'");
                        }
                        System.out.println();
                        System.out.println(office.listExits());
                    }
                }

                // Wardrobe selection
                // Player did not pick up 'Suit' and 'Sunglasses'
                if ((selection.equalsIgnoreCase("Wardrobe")) && (suit.getTaken() == false) && (sunglasses.getTaken() == false)) {
                    System.out.println("There's some cool-looking suits and accessories. Would you like to take them?");
                    yesno = scnr.nextLine();
                    while (!(yesno.equalsIgnoreCase("yes") || yesno.equalsIgnoreCase("no"))) {
                        System.out.println("Please enter yes or no.");
                        yesno = scnr.nextLine();
                    }
                    // Player equips 'Suit' and 'Sunglasses'
                    if (yesno.equalsIgnoreCase("yes")) {
                        System.out.println();
                        System.out.println(suit.itemEquipped());
                        suit.setTaken();
                        System.out.println();
                        System.out.println(sunglasses.itemEquipped());
                        sunglasses.setTaken();
                        time = LocalDateTime.now();
                        outFS.println(dtf.format(time) + " - Player equipped 'Suit'");
                        outFS.println(dtf.format(time) + " - Player equipped 'Sunglasses'");
                    }
                    System.out.println();
                }

                // Kitchen selection
                // Player did not pick up 'Rodent Repellent'
                if ((selection.equalsIgnoreCase("Kitchen")) && (rodentRepellent.getTaken() == false)) {
                    System.out.println("There's something sticking out of that cabinet under the sink. Would you like to open it?");
                    yesno = scnr.nextLine();
                    while (!(yesno.equalsIgnoreCase("yes") || yesno.equalsIgnoreCase("no"))) {
                        System.out.println("Please enter yes or no.");
                        yesno = scnr.nextLine();
                    }
                    // Player picks up 'Rodent Repellent'
                    if (yesno.equalsIgnoreCase("yes")) {
                        System.out.println();
                        System.out.println(rodentRepellent.itemFound());
                        rodentRepellent.setTaken();
                        time = LocalDateTime.now();
                        outFS.println(dtf.format(time) + " - Player picked up 'Rodent Repellent'");
                        // Player has both 'Air Compressor' and 'Rodent Repellent'
                        if ((airCompressor.getTaken() == true) && (rodentRepellent.getTaken() == true)) {
                            System.out.println();
                            System.out.println("Looks like you can attach the 'Air Compressor' to the 'Rodent Repellent'. Would you like to combine them?");
                            yesno = scnr.nextLine();
                            while (!(yesno.equalsIgnoreCase("yes") || yesno.equalsIgnoreCase("no"))) {
                                System.out.println("Please enter yes or no.");
                                yesno = scnr.nextLine();
                            }
                            // Player creates 'Rodent Killer Bomb'
                            if (yesno.equalsIgnoreCase("yes")) {
                                System.out.println();
                                System.out.println(rodentKillerBomb.itemCreated());
                                rodentKillerBomb.setTaken();
                                time = LocalDateTime.now();
                                outFS.println(dtf.format(time) + " - Player created 'Rodent Killer Bomb'");
                            }
                        }
                    }
                    System.out.println();
                }

                // Closet selection
                if (selection.equalsIgnoreCase("Closet")) {
                    // Closet is locked & Player does not have 'Closet Key'
                    if ((closet.getLocked() == true) && (closetKey.getTaken() == false)) {
                        System.out.println("This door is locked. Look around the house for a key.");
                        System.out.println();
                    }
                    // Closet is locked & Player has 'Closet Key'
                    else if ((closet.getLocked() == true) && (closetKey.getTaken() == true)) {
                        System.out.println("This door is locked. Would you like to use 'Closet Key'?");
                        yesno = scnr.nextLine();
                        while (!(yesno.equalsIgnoreCase("yes") || yesno.equalsIgnoreCase("no"))) {
                            System.out.println("Please enter yes or no.");
                            yesno = scnr.nextLine();
                        }
                        // Player uses 'Closet Key' & Closet becomes unlocked
                        if (yesno.equalsIgnoreCase("yes")) {
                            System.out.println();
                            System.out.println("'Closet Key' used.");
                            System.out.println("Door unlocked.");
                            closetKey.setUsed();
                            closet.setUnlocked();
                            time = LocalDateTime.now();
                            outFS.println(dtf.format(time) + " - Player used 'Closet Key'");
                            System.out.println();
                            System.out.println("There's a pile of junk on the floor. Would you like to look through it?");
                            yesno = scnr.nextLine();
                            while (!(yesno.equalsIgnoreCase("yes") || yesno.equalsIgnoreCase("no"))) {
                                System.out.println("Please enter yes or no.");
                                yesno = scnr.nextLine();
                            }
                            // Player picks up 'Air Compressor'
                            if (yesno.equalsIgnoreCase("yes")) {
                                System.out.println();
                                System.out.println(airCompressor.itemFound());
                                airCompressor.setTaken();
                                time = LocalDateTime.now();
                                outFS.println(dtf.format(time) + " - Player picked up 'Air Compressor'");
                                // Player has both 'Air Compressor' and 'Rodent Repellent'
                                if ((airCompressor.getTaken() == true) && (rodentRepellent.getTaken() == true)) {
                                    System.out.println();
                                    System.out.println("Looks like you can attach the 'Air Compressor' to the 'Rodent Repellent'. Would you like to combine them?");
                                    yesno = scnr.nextLine();
                                    while (!(yesno.equalsIgnoreCase("yes") || yesno.equalsIgnoreCase("no"))) {
                                        System.out.println("Please enter yes or no.");
                                    yesno = scnr.nextLine();
                                    }
                                    // Player creates 'Rodent Killer Bomb'
                                    if (yesno.equalsIgnoreCase("yes")) {
                                        System.out.println();
                                        System.out.println(rodentKillerBomb.itemCreated());
                                        rodentKillerBomb.setTaken();
                                        time = LocalDateTime.now();
                                        outFS.println(dtf.format(time) + " - Player created 'Rodent Killer Bomb'");
                                    }
                                }
                            }
                        }
                        System.out.println();
                    }
                    // Closet is unlocked & Player did not pick up 'Air Compressor'
                    else if ((closet.getLocked() == false) && (airCompressor.getTaken() == false)) {
                        System.out.println("There's a pile of junk on the floor. Would you like to look through it?");
                        yesno = scnr.nextLine();
                        while (!(yesno.equalsIgnoreCase("yes") || yesno.equalsIgnoreCase("no"))) {
                            System.out.println("Please enter yes or no.");
                            yesno = scnr.nextLine();
                        }
                        // Player picks up 'Air Compressor'
                        if (yesno.equalsIgnoreCase("yes")) {
                            System.out.println("You found an 'Air Compressor'!\n" + "Description: '" + airCompressor.getDescription() + "'\nIt has been added to your inventory.");
                            airCompressor.setTaken();
                            time = LocalDateTime.now();
                            outFS.println(dtf.format(time) + " - Player picked up 'Air Compressor'");
                            // Player has both 'Air Compressor' and 'Rodent Repellent'
                            if ((airCompressor.getTaken() == true) && (rodentRepellent.getTaken() == true)) {
                                System.out.println();
                                System.out.println("Looks like you can attach the 'Air Compressor' to the 'Rodent Repellent'. Would you like to combine them?");
                                yesno = scnr.nextLine();
                                while (!(yesno.equalsIgnoreCase("yes") || yesno.equalsIgnoreCase("no"))) {
                                    System.out.println("Please enter yes or no.");
                                    yesno = scnr.nextLine();
                                }
                                // Player creates 'Rodent Killer Bomb'
                                if (yesno.equalsIgnoreCase("yes")) {
                                    System.out.println();
                                    System.out.println(rodentKillerBomb.itemCreated());
                                    rodentKillerBomb.setTaken();
                                    time = LocalDateTime.now();
                                    outFS.println(dtf.format(time) + " - Player created 'Rodent Killer Bomb'");
                                }
                            }
                        }
                        System.out.println();
                    }
                }

                // Pantry selection
                if (selection.equalsIgnoreCase("Pantry")) {
                    // Player did not create 'Rodent Killer Bomb' & does not have materials for it
                    if ((rodentKillerBomb.getTaken() == false) && ((rodentRepellent.getTaken() == false) || (airCompressor.getTaken() == false))) {
                        System.out.println(pantry.ratKingDialogue());
                    }
                    // Player has both 'Air Compressor' and 'Rodent Repellent' & did not create 'Rodent Killer Bomb'
                    else if ((rodentKillerBomb.getTaken() == false) && (rodentRepellent.getTaken() == true) && (airCompressor.getTaken() == true)) {
                        System.out.println("Looks like you can attach the 'Air Compressor' to the 'Rodent Repellent'. Would you like to combine them?");
                        yesno = scnr.nextLine();
                        while (!(yesno.equalsIgnoreCase("yes") || yesno.equalsIgnoreCase("no"))) {
                            System.out.println("Please enter yes or no.");
                            yesno = scnr.nextLine();
                        }
                        // Player creates 'Rodent Killer Bomb'
                        if (yesno.equalsIgnoreCase("yes")) {
                            System.out.println();
                            System.out.println(rodentKillerBomb.itemCreated());
                            rodentKillerBomb.setTaken();
                        }
                        // Make sure player creates 'Rodent Killer Bomb' to defeat Rat King
                        else if (yesno.equalsIgnoreCase("no")) {
                            while (!(yesno.equalsIgnoreCase("yes"))) {
                                System.out.println("You should really combine them.");
                                yesno = scnr.nextLine();
                            }
                            System.out.println(rodentKillerBomb.itemCreated());
                            time = LocalDateTime.now();
                            outFS.println(dtf.format(time) + " - Player created 'Rodent Killer Bomb'");
                            System.out.println();
                            System.out.println("Use 'Rodent Killer Bomb'?");
                            yesno = scnr.nextLine();
                            while (!(yesno.equalsIgnoreCase("yes") || yesno.equalsIgnoreCase("no"))) {
                                System.out.println("Please enter yes or no.");
                                yesno = scnr.nextLine();
                            }
                            // Player defeats Rat King
                            if (yesno.equalsIgnoreCase("yes")) {
                                System.out.println(pantry.killRatKing());
                                time = LocalDateTime.now();
                                outFS.println(dtf.format(time) + " - Player killed Rat King");
                                System.out.println();
                                gameOver = true;
                                time = LocalDateTime.now();
                                outFS.println(dtf.format(time) + " - Program terminated");
                                outFS.close();
                            }
                        }
                    }
                    // Player does not look cool enough and needs 'Suit' and 'Sunglasses' equipped
                    else if ((rodentKillerBomb.getTaken() == true) && (suit.getTaken() == false) && (sunglasses.getTaken() == false)) {
                        System.out.println();
                        System.out.println("Before you take on the Rat King, you need to look cool. Search the house for some cool-looking clothes.");
                    }
                    // Player has 'Rodent Killer Bomb', 'Suit', and 'Sunglasses'
                    else if ((rodentKillerBomb.getTaken() == true) && (suit.getTaken() == true) && (sunglasses.getTaken() == true)) {
                        System.out.println();
                        System.out.println("Use 'Rodent Killer Bomb'?");
                        yesno = scnr.nextLine();
                        while (!(yesno.equalsIgnoreCase("yes") || yesno.equalsIgnoreCase("no"))) {
                            System.out.println("Please enter yes or no.");
                            yesno = scnr.nextLine();
                        }
                        // Player defeats Rat King
                        if (yesno.equalsIgnoreCase("yes")) {
                            System.out.println();
                            System.out.println(pantry.killRatKing());
                            time = LocalDateTime.now();
                            outFS.println(dtf.format(time) + " - Player killed Rat King");
                            System.out.println();
                            gameOver = true;
                            time = LocalDateTime.now();
                            outFS.println(dtf.format(time) + " - Program terminated");
                            outFS.close();
                        }
                    }
                }
            }
        }
    }
}


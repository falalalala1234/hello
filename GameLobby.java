import java.util.Scanner;

/**
 * The {@code GameLobby} class provides functionalities for the game lobby, including fast travel and leveling up options.
 * It facilitates navigation between different game areas and managing character progression.
 */
public class GameLobby {
    private static Character.CharacterStats characterStats;
    
    private static Character user;
    GameArea gameArea = new GameArea();
    private static Weapon[] weapons;



    /**
     * Presents the Fast Travel menu to the player, allowing them to choose different destinations.
     * Depending on the player's choice, they can travel to various locations in the game or return to the game lobby.
     * This method uses a scanner to capture user input and switch to the desired game location.
     */
    public static void FastTravel(Character.CharacterStats characterStats) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("[1] Stormveil Castle");
        System.out.println("[2] Raya Lucaria Academey");
        System.out.println("[3] The Elden Throne");
        System.out.println("[4] Back");

        System.out.print("\nEnter your choice: ");
        int option = scanner.nextInt();

        switch (option) {
            case 1:
                System.out.print("\033\143");
                System.out.println("Going to Stormveil Castle");
                Menus.Pause();
                System.out.print("\033\143");
                GameArea gameArea = new GameArea();
                gameArea.FirstFloor(scanner);
                break;
            case 2:
                System.out.print("\033\143");
                System.out.println("Going to Raya Lucaria Academy");
                Menus.Pause();
                System.out.print("\033\143");
                GameArea2 gameArea2 = new GameArea2();
                gameArea2.firstFloor(scanner);
                break;
            case 3:
                System.out.print("\033\143");
                System.out.println("Going to The Elden Throne");
                Menus.Pause();
                System.out.print("\033\143");
                GameArea3 gameArea3 = new GameArea3();
                gameArea3.firstFloor(scanner);
                break;
            case 4:
                System.out.print("\033\143");
                Menus.menusGameLobby(characterStats);
                break;
            default:
                System.out.print("\033\143");
                System.out.println("Invalid choice. Please try again.");
                Menus.Pause();
                System.out.print("\033\143");
                FastTravel(characterStats);
                break;
        }

        scanner.close(); 
    }


    /**
     * Allows the player to level up their character by spending runes. The player can choose which attribute to level up,
     * affecting the character's overall stats. Each level up increases the cost in runes, requiring the player to manage
     * their resources wisely.
     *
     * @param characterStats The character's current stats, used to calculate the cost and effect of leveling up.
     * @param user The character instance being leveled up, which holds the rune balance and stats.
     */
    public void levelUpCharacter(String attribute) {
        Character.CharacterStats stats = user.getCharacterStats();
        int runeCost = (stats.getPlayerLevel() * 100) / 2;
    
        if (user.getRunes() >= runeCost) {
            user.subtractRunes(runeCost);
            switch (attribute) {
                case "HP":
                    stats.setHP(stats.getHP() + 1);
                    break;
                case "END":
                    stats.setEND(stats.getEND() + 1);
                    break;
                case "DEX":
                    stats.setHP(stats.getHP() + 1);
                    break;
                case "STR":
                    stats.setHP(stats.getHP() + 1);
                    break;
                case "INT":
                    stats.setHP(stats.getHP() + 1);
                    break;
                case "FTH":
                    stats.setHP(stats.getHP() + 1);
                    break;
            }
            stats.setPlayerLevel(stats.getPlayerLevel() + 1);
        } else {
            // Handle not having enough runes, e.g., show a message dialog in the game lobby
        }
    }
    
    /**
     * Manages the player's inventory, allowing them to equip weapons or return to the game lobby.
     * Players can view their equipped weapon and inventory, and choose to equip a different weapon.
     *
     * @param user The character whose inventory is being managed.
     */
    public static void Inventory(Character user) {
        Scanner scanner = new Scanner(System.in);
    
        // Display equipped weapon
        System.out.println("Equipped Weapon: " + (user.getEquippedWeapon() != null ? user.getEquippedWeapon().getName() : " "));
    
        // Display inventory
        System.out.println("\nInventory:");
        user.displayInventory();
    
        // Prompt user for action
        System.out.println("\n[1] Select a weapon to equip");
        System.out.println("[2] Back");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
    
        switch (choice) {
            case 1:
                System.out.print("\033\143"); // Clear screen
                user.displayInventory();
                System.out.print("Enter the number of the weapon to equip: ");
                int index = scanner.nextInt();
                if (index >= 1 && index <= user.getInventory().size()) {
                    Weapon selectedWeapon = user.getInventory().get(index - 1); // Adjust index to match list
                    if (user.getCharacterStats().getDEX() >= selectedWeapon.getWeaponDEX()) {
                    // Set the chosen weapon as the equipped weapon
                    user.setEquippedWeapon(selectedWeapon);
                    System.out.print("\033\143");
                    System.out.println(selectedWeapon.getName() + " equipped successfully!");
                } else {
                    System.out.print("\033\143");
                    System.out.println("You do not meet the dexterity requirement to equip this weapon.");
                }
                } else {
                    System.out.println("Invalid input.");
                }
                Menus.Pause();
                System.out.print("\033\143");
                Inventory(user); // Go back to manage inventory
                break;
            case 2:
                System.out.print("\033\143"); // Clear screen
                return; // Back to main game lobby
            default:
                System.out.println("Invalid choice. Please try again.");
                Menus.Pause();
                System.out.print("\033\143");
                Inventory(user); // Go back to manage inventory
                break;
        }
    
        scanner.close();
    }
    
    /**
     * Displays the shop menu where the player can purchase weapons with runes.
     * Players can view available weapons and their costs, then select a weapon to purchase.
     *
     * @param weapons Array of available weapons in the shop.
     * @param user The character making the purchase.
     */
    public static void Shop(Weapon[] weapons, Character user) {
        Scanner scanner = new Scanner(System.in);
    
        weapons = Weapon.initializeWeapons();
    
        while (true) {
            Weapon.displayAvailableWeapons(weapons);
            
            System.out.println("\nCurrent Runes: " + user.getRunes());

            System.out.println("\nType '0' to exit.");
            System.out.print("Which weapon would you like to buy?: ");
            
    
            int shopOption = scanner.nextInt();
    
            if (shopOption == 0) {
                System.out.print("\033\143");
                System.out.println("Exiting the shop...");
                Menus.Pause();
                System.out.print("\033\143");
                return;
            }
    
            try {
                if (shopOption >= 1 && shopOption <= weapons.length) {
                    Weapon selectedWeapon = weapons[shopOption]; // Adjust index to match array
                    int cost = selectedWeapon.getWeaponCost();
                    if (user.getRunes() >= cost) {
                        user.subtractRunes(cost);
                        System.out.print("\033\143");
                        System.out.println("You have purchased: " + selectedWeapon.getName());
                        user.addtoInventory(selectedWeapon);
                        Menus.Pause();
                        System.out.print("\033\143");
                    } else {
                        System.out.print("\033\143");
                        System.out.println("You don't have enough runes to buy this weapon!");
                        Menus.Pause();
                        System.out.print("\033\143");
                }
                    
                } else {
                    System.out.print("\033\143");
                    System.out.println("Invalid Option.");
                    Menus.Pause();
                    System.out.print("\033\143");
                }
            } catch (Exception e) {
                System.out.print("\033\143");
                System.out.println("An error occurred: " + e.getMessage());
                Menus.Pause();
                System.out.print("\033\143");
            }
            
        }
        
    }
        
}


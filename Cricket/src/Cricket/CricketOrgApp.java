package Cricket;
import java.util.InputMismatchException;
import java.util.Scanner;


public class CricketOrgApp {
    private static final String PASSWORD = "password";
    private static final int MAX_ATTEMPTS = 3;
    private static final int MAX_CONSECUTIVE_FAILED_ATTEMPTS = 4;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int maxCricketers;

        // Display a welcome message
        System.out.println("Welcome to the Cricket Organization Management System!");

        // Prompt the user for the maximum number of Cricketers an organization can manage
        System.out.print("Enter the maximum number of Cricketers the organization can manage: ");
        maxCricketers = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        // Create an array to store Cricketer objects
        Cricketer[] cricketerDatabase = new Cricketer[maxCricketers];

        int consecutiveFailedAttempts = 0; // Track consecutive failed password attempts

        while (true) {
            // Display the main menu
            System.out.println("\nMain Menu:");
            System.out.println("1. Enter new Cricketers (password required)");
            System.out.println("2. Change information of a Cricketer (password required)");
            System.out.println("3. Display available Cricketers with a bowlingAvg greater than user value");
            System.out.println("4. Display all Cricketers that can play as an AllRounder");
            System.out.println("5. Quit");

            System.out.print("Please enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            if (choice == 1) {
                // Option 1: Enter new Cricketers
                boolean authenticated = authenticateUser(scanner);
                if (authenticated) {
                    // Add Cricketers to the database
                    addCricketers(cricketerDatabase, scanner);
                } else {
                    consecutiveFailedAttempts++;
                    checkSuspiciousActivities(consecutiveFailedAttempts);
                }
            } else if (choice == 2) {
                // Option 2: Change information of a Cricketer
                boolean authenticated = authenticateUser(scanner);
                if (authenticated) {
                    updateCricketerInformation(cricketerDatabase, scanner);
                }
            } else if (choice == 3) {
                // Option 3: Display available Cricketers with a bowlingAvg greater than user value
                System.out.print("Enter the maximum bowlingAvg: ");
                float maxBowlingAvg = scanner.nextFloat();
                scanner.nextLine(); // Consume the newline character
                Cricketer.displayCricketersByBowlingAvg(cricketerDatabase, maxBowlingAvg);
            } else if (choice == 4) {
                // Option 4: Display all Cricketers that can play as an AllRounder
                System.out.print("Enter minimum strike rate: ");
                float minStrikeRate = scanner.nextFloat();
                scanner.nextLine(); // Consume the newline character
                System.out.print("Enter maximum economy rate: ");
                float maxEconomyRate = scanner.nextFloat();
                scanner.nextLine(); // Consume the newline character
                Cricketer.displayAllRounders(cricketerDatabase, minStrikeRate, maxEconomyRate);
            } else if (choice == 5) {
                // Option 5: Quit
                System.out.println("Thank you for using the Cricket Organization Management System!");
                break;
            } else {
                System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
        }
        scanner.close();
    }

    private static boolean authenticateUser(Scanner scanner) {
        int attempts = 0;
        while (attempts < MAX_ATTEMPTS) {
            System.out.print("Enter password: ");
            String enteredPassword = scanner.nextLine();
            if (enteredPassword.equals(PASSWORD)) {
                return true;
            } else {
                System.out.println("Incorrect password. Please try again.");
                attempts++;
            }
        }
        return false;
    }

    private static void checkSuspiciousActivities(int consecutiveFailedAttempts) {
        if (consecutiveFailedAttempts >= MAX_CONSECUTIVE_FAILED_ATTEMPTS) {
            System.out.println("Program detected suspicious activities and will terminate immediately!");
            System.exit(0);
        }
    }

    private static void addCricketers(Cricketer[] cricketerDatabase, Scanner scanner) {
        System.out.print("Enter the number of Cricketers you want to add: ");
        int numCricketersToAdd = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
//        Cricketer[] cricketerDatabase = new Cricketer[numCricketersToAdd];
        // Check if there is enough space in the database
        int remainingSpace = cricketerDatabase.length - Cricketer.getNumberOfCricketers();

        if (numCricketersToAdd <= remainingSpace) {
            for (int i = 0; i < numCricketersToAdd; i++) {
                System.out.println("Enter details for Cricketer #" + (i + 1));
                System.out.print("CricketerID: ");
                long cricketerID = scanner.nextLong();
                scanner.nextLine(); // Consume the newline character
                System.out.print("CricketerName: ");
                String cricketerName = scanner.nextLine();
                System.out.print("Batting Average: ");
                float battingAvg = scanner.nextFloat();
                scanner.nextLine(); // Consume the newline character
                System.out.print("Bowling Average: ");
                float bowlingAvg = scanner.nextFloat();
                scanner.nextLine(); // Consume the newline character
                System.out.print("Strike Rate: ");
                float strikeRate = scanner.nextFloat();
                scanner.nextLine(); // Consume the newline character
                System.out.print("Economy Rate: ");
                float economyRate = scanner.nextFloat();
                scanner.nextLine(); // Consume the newline character
                System.out.print("Availability (true/false): ");
                boolean isAvailable = scanner.nextBoolean();
                scanner.nextLine(); // Consume the newline character

                // Create a new Cricketer object and add it to the database
                Cricketer cricketer = new Cricketer(cricketerID, cricketerName, battingAvg, bowlingAvg,
                        strikeRate, economyRate, isAvailable);

                // Add the Cricketer to the database (You'll need to implement this method in the Cricketer class)
                addCricketerToDatabase(cricketerDatabase, cricketer);
            }
            System.out.println("Cricketers added successfully.");
        } else {
            System.out.println("There is not enough space to add that many Cricketers.");
        }
    }

    public static void addCricketerToDatabase(Cricketer[] database, Cricketer cricketer) {
        for (int i = 0; i < database.length; i++) {
            if (database[i] == null) {
                database[i] = cricketer;
                return; // Successfully added the Cricketer
            }
        }
    }
    private static void updateCricketerInformation(Cricketer[] cricketerDatabase, Scanner scanner) {
        System.out.print("Enter the CricketerID of the Cricketer you want to update: ");
        long cricketerIDToUpdate = scanner.nextLong();
        scanner.nextLine(); // Consume the newline character

        // Find the Cricketer in the database based on CricketerID
        Cricketer cricketerToUpdate = null;
        for (Cricketer cricketer : cricketerDatabase) {
            if (cricketer != null && cricketer.getCricketerID() == cricketerIDToUpdate) {
                cricketerToUpdate = cricketer;
                break;
            }
        }

        if (cricketerToUpdate == null) {
            System.out.println("Cricketer not found. Do you want to continue? (yes/no)");
            String continueChoice = scanner.nextLine();
            if (!continueChoice.equalsIgnoreCase("yes")) {
                return;
            }
        } else {
            while (true) {
                // Display the update menu (Figure 2)
                System.out.println("\nUpdate Menu:");
                System.out.println("1. Update Cricketer Name");
                System.out.println("2. Update Batting Average");
                System.out.println("3. Update Bowling Average");
                System.out.println("4. Update Strike Rate");
                System.out.println("5. Update Economy Rate");
                System.out.println("6. Update Availability");
                System.out.println("7. Return to the main menu");
                // ...

                System.out.print("Please enter your choice: ");
                int updateChoice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                switch (updateChoice) {
                    case 1:
                        System.out.print("Enter new Cricketer Name: ");
                        String newName = scanner.nextLine();
                        cricketerToUpdate.setCricketerName(newName);
                        break;
                    case 2:
                        System.out.print("Enter new Batting Average: ");
                        float newBattingAvg;
                        try {
                            newBattingAvg = scanner.nextFloat();
                            scanner.nextLine(); // Consume the newline character
                            cricketerToUpdate.setBattingAvg(newBattingAvg);
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input. Please enter a valid number for Batting Average.");
                        }
                        break;
                    case 3:
                        System.out.print("Enter new Bowling Average: ");
                        float newBowlingAvg;
                        try {
                            newBowlingAvg = scanner.nextFloat();
                            scanner.nextLine(); // Consume the newline character
                            cricketerToUpdate.setBowlingAvg(newBowlingAvg);
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input. Please enter a valid number for Bowling Average.");
                        }
                        break;
                    case 4:
                        System.out.print("Enter new Strike Rate: ");
                        float newStrikeRate;
                        try {
                            newStrikeRate = scanner.nextFloat();
                            scanner.nextLine(); // Consume the newline character
                            cricketerToUpdate.setStrikeRate(newStrikeRate);
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input. Please enter a valid number for Strike Rate.");
                        }
                        break;
                    case 5:
                        System.out.print("Enter new Economy Rate: ");
                        float newEconomyRate;
                        try {
                            newEconomyRate = scanner.nextFloat();
                            scanner.nextLine(); // Consume the newline character
                            cricketerToUpdate.setEconomyRate(newEconomyRate);
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input. Please enter a valid number for Economy Rate.");
                        }
                        break;
                    case 6:
                        System.out.print("Enter new Availability (true/false): ");
                        boolean newAvailability;
                        try {
                            newAvailability = scanner.nextBoolean();
                            scanner.nextLine(); // Consume the newline character
                            cricketerToUpdate.setAvailable(newAvailability);
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input. Please enter 'true' or 'false' for Availability.");
                        }
                        break;
                    case 7:
                        return; // Return to the main menu
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 7.");
                }

            }
        }
    }


}
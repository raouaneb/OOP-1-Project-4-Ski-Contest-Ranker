import java.util.Scanner;

/**
 * Ski Contest Ranker Program:
 *
 * This program allows the user to rank skiers based on their skiing times in a skiing contest.
 * It prompts the user to enter the number of skiers they want to rank and then proceeds to input
 * the skiers' names and corresponding skiing times. The program updates the top three skiers' podium
 * standings based on their times and displays the current podium standings after each entry. Once all
 * skiers are ranked or the user decides to stop, the program exits with a goodbye message.
 *
 * Program Flow:
 * 1. Welcome message and prompt for the number of skiers.
 * 2. Input loop:
 *    a. Prompt user for skier's name and time.
 *    b. Update podium standings based on input.
 *    c. Display current podium standings.
 *    d. Repeat until all skiers are ranked or user decides to stop.
 * 3. Goodbye message.
 *
 * Note:
 * - The program allows the user to input any positive integer as the number of skiers.
 * - Skiers' names and times are stored in arrays.
 * - Podium standings are updated dynamically as skiers are entered.
 *
 */

public class SkiContestRanker {

    public static void main(String[] args) {

        // Welcome message
        System.out.println("Welcome to the Extreme Ski Challenge!");

        // Create a scanner for user input
        Scanner input = new Scanner(System.in);

        // Prompt user for the number of skiers
        System.out.print("Enter the number of skiers: ");
        int numSkiers = input.nextInt();
        input.nextLine(); // Consume newline left by nextInt()

        // Check if the number of skiers is valid
        if (numSkiers <= 0) {
            System.out.println("Invalid number of skiers. Exiting program.");
            input.close(); // Close scanner
            return; // Exit program
        }

        // Arrays to store skiers' names and times
        String[] skierNames = new String[numSkiers];
        double[] skiingTimes = new double[numSkiers];

        // Indices for podium positions
        int[] podiumIndices = {-1, -1, -1}; // Initialized to -1 because no skier data has been recorded yet

        // Counter for the number of skiers
        int skierCount = 0;

        // Loop to input skiers' data
        do {
            // Prompt for skier's name
            System.out.print("Enter skier's name: ");
            String name = input.nextLine();

            // Prompt for skier's time
            System.out.print("Enter skier's time (in seconds): ");
            double time = input.nextDouble();
            input.nextLine(); // Consume newline left by nextDouble()

            // Add skier's data to arrays
            skierNames[skierCount] = name;
            skiingTimes[skierCount] = time;

            // Update podium indices
            updatePodiumIndices(podiumIndices, skierCount, skiingTimes);

            // Display current podium standings
            displayPodium(skierNames, skiingTimes, podiumIndices);

            // Increment skier count
            skierCount++;

            // Check if maximum skier limit reached
            if (skierCount == numSkiers) {
                System.out.println("All skiers entered. Exiting input loop.");
                break;
            }

            // Prompt user to add another skier
            System.out.print("Do you want to add another skier? (yes/no): ");
        } while (input.nextLine().equalsIgnoreCase("yes"));

        // Display goodbye message
        System.out.println("Thank you for participating! Goodbye!");

        // Close scanner
        input.close();
    }

    // Method to update podium indices
    private static void updatePodiumIndices(int[] podiumIndices, int skierCount, double[] skiingTimes) {
        for (int i = 0; i < skierCount; i++) {
            if (podiumIndices[0] == -1 || skiingTimes[i] < skiingTimes[podiumIndices[0]]) {
                podiumIndices[2] = podiumIndices[1];
                podiumIndices[1] = podiumIndices[0];
                podiumIndices[0] = i;
            } else if (podiumIndices[1] == -1 || skiingTimes[i] < skiingTimes[podiumIndices[1]]) {
                podiumIndices[2] = podiumIndices[1];
                podiumIndices[1] = i;
            } else if (podiumIndices[2] == -1 || skiingTimes[i] < skiingTimes[podiumIndices[2]]) {
                podiumIndices[2] = i;
            }
        }
    }

    // Method to display podium standings
    private static void displayPodium(String[] skierNames, double[] skiingTimes, int[] podiumIndices) {
        System.out.println("Current Podium Standings:");
        for (int i = 0; i < podiumIndices.length; i++) {
            if (podiumIndices[i] != -1) {
                String podiumPosition;
                switch (i) {
                    case 0:
                        podiumPosition = "First";
                        break;
                    case 1:
                        podiumPosition = "Second";
                        break;
                    case 2:
                        podiumPosition = "Third";
                        break;
                    default:
                        podiumPosition = "Error";
                        break;
                }
                System.out.println(podiumPosition + " place: " + skierNames[podiumIndices[i]] + " - " + skiingTimes[podiumIndices[i]] + " seconds");
            }
        }
        System.out.println();
    }
}

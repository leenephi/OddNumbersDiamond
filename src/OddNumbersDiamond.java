import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author Lee Smithson
 *
 * The goal of this class is to draw a diamond pattern of
 * incrementing numbers. For example:
 *
 *-1--2--3--4--5--6--7  
 *       .     .     .
 *    2  .     .     .
 * 1     4     .     .   Entered 3
 *    3        .     .
 *             .     .
 *       4     .     .
 *    2     7  .     .
 * 1     5     9     .   Entered 5
 *    3     8        .
 *       6           .
 *                   .
 *          7        .
 *       4    11     .
 *    2     8    14  .
 * 1     5    12    16   Entered 7
 *    3     9    15  .
 *       6    13     .
 *          10       .
 *                   .
 *--------------------          
 *
 * The program requires an odd number greater
 * than 1. Thus, numbers such as 3, 5, and 7+
 * will display the pattern.
 *
 * TODO: Implement a way to keep the pattern from skewing due to large numbers
 *
 */

public class OddNumbersDiamond {

    public static void main(String[] args) {

        boolean again = false;

        do {
            // Get the valid odd number
            int number = promptOddNumber();

            // Use the number to get the pattern
            String pattern[][] = calculatePattern(number);

            // Display the pattern!
            displayPattern(pattern);

            // Another?
            again = promptAgain();

        } while (again);

        System.out.println("Thanks for doing patterns!");
    }

    /**
     * Calculates the pattern based on the odd
     * number given
     * @return contained pattern
     */
    private static String[][] calculatePattern(int number) {

        // for 3, middle is 1
        // for 5, middle is 2
        // for 7, middle is 3
        int middle = number / 2;

        // the first row in each column to start positioning
        int firstPos = middle + 1;

        // used to keep track of and increment the number pattern
        int counter = 1;

        // multidimensional array storing the pattern
        String[][] patternArray = new String[number][number];

        // how many numbers are in the pattern for current column
        int valuesInColumn = 1;

        for (int col = 0; col < number; col++) {

            // handle the increasing and decreasing
            // of the diamond shape
            if(col > middle) {
                valuesInColumn--;
                firstPos++;
            } else {
                valuesInColumn = col + 1;
                firstPos--;
            }

            boolean foundFirstPos = false;
            boolean foundNextPos = false;
            // how many values we have placed so far in this column
            int valuesPlaced = 0;

            for (int row = 0; row < number; row++) {

                // We want to parse until we find the first position
                // in this column of the pattern
                if (!foundFirstPos) {
                    if (row != firstPos) {
                        patternArray[row][col] = "--";
                        continue;

                    } else {
                        // We've arrived at the first position
                        patternArray[row][col] = counter + "-";
                        valuesPlaced++;
                        counter++;
                        foundFirstPos = true;
                        continue;
                    }
                    // Once we have found the first position
                    // we count by 2 for each following position
                } else {

                    // keep filling other positions until we've
                    // reached the total for the column
                    if (valuesPlaced < valuesInColumn) {

                        if (foundNextPos) {
                            patternArray[row][col] = counter + "-";
                            valuesPlaced++;
                            counter++;
                            foundNextPos = false;
                        } else {
                            patternArray[row][col] = "--";
                            foundNextPos = true;
                        }

                    } else {

                        patternArray[row][col] = "--";

                    }
                }
            }
        }

        return patternArray;
    }

    /**
     * Displays the pattern by parsing through the
     * multidimensional array
     * @param pattern multidimensional array of strings
     */
    private static void displayPattern(String[][] pattern) {
        for (int row = 0; row < pattern.length; row++) {

            String rowResults = new String();
            for (int col = 0; col < pattern.length; col++) {

                // add the row strings together
                rowResults += pattern[row][col];
            }

            System.out.println(rowResults + "\n");
        }
    }

    /**
     * Prompts the user for an odd number
     * greater than 1
     *
     * @return odd integer
     */
    private static int promptOddNumber() {
        int number = 0;
        // Prompt for an odd number greater than 1
        // Loop the prompt until we get a valid number
        do {
            System.out.println("Please enter any odd number greater than 1:");
            try {
                // for some reason Scanner needs to be called again
                // after an InputMismatchException
                number = new Scanner(System.in).nextInt();
            } catch (InputMismatchException e) {
                System.out.println("That isn't a number!");
            }
            System.out.println("\n");

        } while (number < 3 || number % 2 == 0);

        // getting here means the number is odd and greater than 1!
        System.out.println("Great! " + number + " is an excellent choice. \n");
        return number;
    }

    /**
     * Prompts, asking the user if they want to
     * try another pattern
     * @return true if yes, false if no
     */
    private static boolean promptAgain() {

        boolean answered = false;
        boolean again = false;

        do {
            String response = "";
            System.out.println("Would you like to bust out another pattern? " +
                    "(yes / no)");
            try {
                response = new Scanner(System.in).nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Hmm?");
            }

            if(response.toLowerCase().equals("yes")) {
                answered = true;
                again = true;
            } else if (response.toLowerCase().equals("no")) {
                answered = true;
                again = false;
            } else {
                System.out.println("What does that even mean?");
            }
            System.out.println("\n");
        } while (!answered);

        return again;
    }

}
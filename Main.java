import java.util.Scanner;

public class CampusQuickAssist {

    // ----------------------------------------------------
    // MANDATORY METHODS REQUIRED BY SPECIFICATION
    // ----------------------------------------------------

    // 1. Displays the main menu
    public static void displayMenu() {
        System.out.println("\n========================================");
        System.out.println("   Campus QuickAssist - Support System  ");
        System.out.println("========================================");
        System.out.println("1. Register a student problem");
        System.out.println("2. View priority guidelines");
        System.out.println("3. Exit the application");
        System.out.print("Please enter your choice (1-3): ");
    }

    // 2. Maps category number (1-4) to category name
    public static String getCategoryName(int choice) {
        switch (choice) {
            case 1:
                return "Academic";
            case 2:
                return "Technical";
            case 3:
                return "Administrative";
            case 4:
                return "Other";
            default:
                return "Unknown";
        }
    }

    // 3. Validates whether urgency is within [1, 5]
    public static boolean isValidUrgency(int urgency) {
        return (urgency >= 1 && urgency <= 5);
    }

    // 4. Calculates priority code: 3 = HIGH, 2 = MEDIUM, 1 = LOW
    public static int calculatePriority(int urgency, boolean deadlineToday, boolean affectsMultipleStudents) {
        boolean hasSpecialCondition = (deadlineToday || affectsMultipleStudents);

        // HIGH priority
        if (urgency >= 4 && hasSpecialCondition) {
            return 3;
        }
        // MEDIUM priority (Condition 1 OR Condition 2)
        else if ((urgency >= 4 && !hasSpecialCondition) || 
                 (urgency >= 2 && urgency <= 3 && hasSpecialCondition)) {
            return 2;
        }
        // LOW priority (all other valid combinations)
        else {
            return 1;
        }
    }

    // 5. Converts numeric priority code to descriptive label
    public static String getPriorityLabel(int priority) {
        switch (priority) {
            case 3:
                return "HIGH";
            case 2:
                return "MEDIUM";
            case 1:
                return "LOW";
            default:
                return "UNKNOWN";
        }
    }

    // 6. Prints formatted ticket summary
    public static void printTicketSummary(String studentName, String category, int urgency, 
                                          boolean deadlineToday, boolean affectsMultipleStudents, 
                                          String priorityLabel) {
        System.out.println("\n========================================");
        System.out.println("             TICKET SUMMARY             ");
        System.out.println("========================================");
        System.out.println("Student Name       : " + studentName);
        System.out.println("Category           : " + category);
        System.out.println("Urgency Level      : " + urgency + " / 5");
        System.out.println("Deadline Today     : " + (deadlineToday ? "Yes" : "No"));
        System.out.println("Multiple Affected  : " + (affectsMultipleStudents ? "Yes" : "No"));
        System.out.println("Calculated Priority: " + priorityLabel);
        System.out.println("========================================");
    }

    // ----------------------------------------------------
    // INPUT VALIDATION HELPER METHODS
    // ----------------------------------------------------

    // Helper: Validates that a student name contains only letters, spaces, hyphens, or apostrophes (no digits)
    public static boolean isValidName(String name) {
        if (name.isEmpty()) {
            return false;
        }

        boolean hasLetter = false;

        for (int i = 0; i < name.length(); i++) {
            char ch = name.charAt(i);

            // Reject any digit
            if (Character.isDigit(ch)) {
                return false;
            }

            // Allow letters, spaces, hyphens, and apostrophes
            if (Character.isLetter(ch)) {
                hasLetter = true;
            } else if (ch != ' ' && ch != '-' && ch != '\'') {
                return false;
            }
        }

        return hasLetter;
    }

    // Helper: Reads and validates the student name
    public static String readValidStudentName(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("Error: Name cannot be empty. Please try again.");
            } else if (!isValidName(input)) {
                System.out.println("Error: Name cannot contain numbers or special symbols. Please enter letters only.");
            } else {
                return input;
            }
        }
    }

    // Reads an integer and ensures it is within [min, max] without crashing on text
    public static int readValidInteger(Scanner scanner, String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                int value = scanner.nextInt();
                scanner.nextLine(); // Clear leftover newline
                if (value >= min && value <= max) {
                    return value;
                } else {
                    System.out.println("Error: Please enter a number between " + min + " and " + max + ".");
                }
            } else {
                String badInput = scanner.next();
                scanner.nextLine(); // Clear leftover newline
                System.out.println("Error: '" + badInput + "' is not a valid number. Please try again.");
            }
        }
    }

    // Specifically validates urgency using the mandatory isValidUrgency() method
    public static int readUrgency(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                int urgency = scanner.nextInt();
                scanner.nextLine(); // Clear leftover newline
                if (isValidUrgency(urgency)) {
                    return urgency;
                } else {
                    System.out.println("Error: Urgency must be between 1 and 5.");
                }
            } else {
                String badInput = scanner.next();
                scanner.nextLine(); // Clear leftover newline
                System.out.println("Error: '" + badInput + "' is not a number. Please enter a number from 1 to 5.");
            }
        }
    }

    // Reads Yes/No and converts to boolean
    public static boolean readYesNo(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("y") || input.equals("yes")) {
                return true;
            } else if (input.equals("n") || input.equals("no")) {
                return false;
            } else {
                System.out.println("Error: Invalid response. Please enter 'Y' for Yes or 'N' for No.");
            }
        }
    }

    // Displays priority guidelines
    public static void displayGuidelines() {
        System.out.println("\n---------------- PRIORITY GUIDELINES ----------------");
        System.out.println("HIGH   : Urgency 4 or 5 AND (Deadline is Today OR Multiple Students Affected)");
        System.out.println("MEDIUM : Urgency 4 or 5 WITHOUT special conditions");
        System.out.println("         OR Urgency 2 or 3 AND (Deadline is Today OR Multiple Students Affected)");
        System.out.println("LOW    : All other valid combinations (e.g., Urgency 1, or Urgency 2-3 without special conditions)");
        System.out.println("-----------------------------------------------------");
    }

    // ----------------------------------------------------
    // MAIN EXECUTION LOOP
    // ----------------------------------------------------
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        System.out.println("Welcome to Campus QuickAssist!");

        while (isRunning) {
            displayMenu();

            if (scanner.hasNextInt()) {
                int menuChoice = scanner.nextInt();
                scanner.nextLine(); // Clear newline

                switch (menuChoice) {
                    case 1:
                        // 1. Student Name
                        String studentName = readValidStudentName(scanner, "Enter student full name: ");

                        // 2. Problem Category (1-4)
                        System.out.println("\nSelect Problem Category:");
                        System.out.println("1. Academic\n2. Technical\n3. Administrative\n4. Other");
                        int categoryChoice = readValidInteger(scanner, "Enter category (1-4): ", 1, 4);
                        String categoryName = getCategoryName(categoryChoice);

                        // 3. Urgency Level (1-5)
                        int urgency = readUrgency(scanner, "Enter urgency level (1-5): ");

                        // 4. Special conditions
                        boolean deadlineToday = readYesNo(scanner, "Is the deadline today? (Y/N): ");
                        boolean affectsMultiple = readYesNo(scanner, "Are multiple students affected? (Y/N): ");

                        // 5. Calculate Priority
                        int priorityCode = calculatePriority(urgency, deadlineToday, affectsMultiple);
                        String priorityLabel = getPriorityLabel(priorityCode);

                        // 6. Display Ticket
                        printTicketSummary(studentName, categoryName, urgency, deadlineToday, affectsMultiple, priorityLabel);
                        break;

                    case 2:
                        displayGuidelines();
                        break;

                    case 3:
                        System.out.println("\nThank you for using Campus QuickAssist. Goodbye!");
                        isRunning = false;
                        break;

                    default:
                        System.out.println("\nInvalid option! Please enter 1, 2, or 3.");
                        break;
                }
            } else {
                String invalidInput = scanner.next();
                scanner.nextLine();
                System.out.println("\nInvalid input: '" + invalidInput + "' is not a number. Please enter 1, 2, or 3.");
            }
        }

        scanner.close();
    }
}
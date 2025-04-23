import java.util.Scanner;

public class SafeInput {
    private static final Scanner in = new Scanner(System.in);  // Shared Scanner

    public static String getNonZeroLenString(String prompt) {
        String retString = "";
        do {
            System.out.print("\n" + prompt + ": ");
            retString = in.nextLine().trim();
        } while (retString.length() == 0);
        return retString;
    }

    public static int getInt(String prompt) {
        int value;
        while (true) {
            System.out.print("\n" + prompt + ": ");
            if (in.hasNextInt()) {
                value = in.nextInt();
                in.nextLine();
                return value;
            } else {
                System.out.println("Invalid input. Please enter an integer.");
                in.next();
            }
        }
    }

    public static double getDouble(String prompt) {
        double value;
        while (true) {
            System.out.print("\n" + prompt + ": ");
            if (in.hasNextDouble()) {
                value = in.nextDouble();
                in.nextLine();
                return value;
            } else {
                System.out.println("Invalid input. Please enter a double.");
                in.next();
            }
        }
    }

    public static int getRangedInt(String prompt, int low, int high) {
        int value;
        do {
            value = getInt(prompt + " [" + low + " - " + high + "]");
            if (value < low || value > high) {
                System.out.println("Input out of range. Try again.");
            }
        } while (value < low || value > high);
        return value;
    }

    public static double getRangedDouble(String prompt, double low, double high) {
        double value;
        do {
            value = getDouble(prompt + " [" + low + " - " + high + "]");
            if (value < low || value > high) {
                System.out.println("Input out of range. Try again.");
            }
        } while (value < low || value > high);
        return value;
    }

    public static boolean getYNConfirm(String prompt) {
        String response;
        do {
            System.out.print("\n" + prompt + " [Y/N]: ");
            response = in.nextLine().trim().toLowerCase();

            if (response.equals("y")) return true;
            if (response.equals("n")) return false;

            System.out.println("Invalid input. Please enter Y or N.");
        } while (true);
    }

    public static String getRegExString(String prompt, String regEx) {
        String response;
        do {
            System.out.print("\n" + prompt + ": ");
            response = in.nextLine().trim();

            if (response.matches(regEx)) return response;
            System.out.println("Invalid input. Does not match pattern. Try again.");
        } while (true);
    }

    public static void prettyHeader(String msg) {
        final int WIDTH = 60;
        int msgLength = msg.length();
        int padding = (WIDTH - msgLength - 6) / 2;

        System.out.println("*".repeat(WIDTH));
        System.out.println("***" + " ".repeat(padding) + msg + " ".repeat(WIDTH - msgLength - 6 - padding) + "***");
        System.out.println("*".repeat(WIDTH));
    }
}




//pretty header



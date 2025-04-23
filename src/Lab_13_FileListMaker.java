import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Lab_13_FileListMaker {
    private static boolean needsToBeSaved=false;

    private static String currentFileName=null;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<>();
        boolean done =false;




        while (!done) {
            displayList(list);
            displayMenu();
            String choice =SafeInput.getRegExString("Choose option: ","[AaDdIiVvQqMmOoSsCc]");

            switch (choice.toUpperCase()) {
                case "A":
                    addItem(list);

                    break;
                case "D":
                    deleteItem(list);

                    break;
                case "I":
                    insertItem(list);

                    break;
                case "V":
                    viewList(list);

                    break;
                case "M":
                    moveItem(list);
                    break;
                case "O":
                    if (needsToBeSaved &&SafeInput.getYNConfirm("Unsaved changes. Save before opening a new list?")) {
                        saveFile(list);
                    }
                    list = openFile();
                    break;
                case "S":
                    saveFile(list);
                    break;
                case "C":
                    if (SafeInput.getYNConfirm("Clear the list?")) {
                        list.clear();
                        needsToBeSaved = true;
                        System.out.println("List cleared.");
                    }
                    break;
                case "Q":
                    if (needsToBeSaved && SafeInput.getYNConfirm("You have unsaved changes. Save before quitting?")) {
                        saveFile(list);
                    }
                    done =SafeInput.getYNConfirm("Are you sure you want to quit?");
                    break;
            }
        }
        System.out.println("Goodbye.");
    }

    private static void displayMenu() {
        System.out.println("""
            \nMenu Options:
            [A] -   Add item
            [D] -   Delete item
            [I] -   Insert item
            [M] -   Move item
            [V] -   View list
            [O] -   Open list file
            [S] -   Save current list
            [C] -   Clear list
            [Q] -   Quit
        """);
    }

    private static void displayList(ArrayList<String> list) {
        System.out.println("\nCurrent List:");
        if (list.isEmpty()) {
            System.out.println("[list is empty]");
        } else {
            for (int i = 0; i < list.size(); i++) {
                System.out.printf("%3d: %s\n", i + 1, list.get(i));
            }
        }
    }

    private static void viewList(ArrayList<String> list) {
        printLine("Viewing List");
        if (list.isEmpty()) {
            System.out.println("[List is empty]");
        } else {
            for (int i = 0; i<list.size(); i++) {
                System.out.printf("%3d: %s\n", i +1, list.get(i));
            }
        }
        printLine("----------------");
    }

    private static void addItem(ArrayList<String> list) {
        String item = SafeInput.getNonZeroLenString("Item to add:");
        list.add(item);
        needsToBeSaved =true;
        System.out.println("Added.");
    }

    private static void deleteItem(ArrayList<String> list) {
        if (list.isEmpty()) {
            System.out.println("List is empty.");
            return;
        }
        int index = SafeInput.getRangedInt("Item number to delete:", 1, list.size()) - 1;
        System.out.println("Deleted: " + list.remove(index));
        needsToBeSaved = true;
    }

    private static void insertItem(ArrayList<String> list) {
        String item =SafeInput.getNonZeroLenString("Insert Item:");
        int index = SafeInput.getRangedInt("Insert at position (1 to " + (list.size() + 1) + "):", 1, list.size() + 1) - 1;
        list.add(index, item);

        needsToBeSaved = true;


        System.out.println("Item inserted.");
    }

    private static void moveItem(ArrayList<String> list) {
        if (list.isEmpty()) {
            System.out.println("List is empty.");
            return;
        }
        int from = SafeInput.getRangedInt("Item number to move:", 1, list.size()) - 1;
        int to = SafeInput.getRangedInt("New position (1 to " + list.size() + "):", 1, list.size()) - 1;

        String item = list.remove(from);
        list.add(to, item);
        needsToBeSaved = true;
        System.out.println("Moved.");
    }

    private static void saveFile(ArrayList<String> list) {
        try {
            if (currentFileName ==  null) {
                currentFileName = SafeInput.getNonZeroLenString("Enter filename to save (NO extension):") + ".txt";
            }
            Path path = Paths.get(currentFileName);
            Files.write(path,  list);
            needsToBeSaved = false;
            System.out.println("Saved to " +   currentFileName);
        } catch (IOException e) {


            System.out.println("ERROR:  Can't save file.");
        }
    }

    private static ArrayList<String> openFile() {
        try {
            String fileName = SafeInput.getNonZeroLenString("Enter filename to open (without extension):") + ".txt";
            Path path = Paths.get(fileName);
            ArrayList<String> list = new ArrayList<>(Files.readAllLines(path));
            currentFileName = fileName;
            needsToBeSaved = false;
            System.out.println("Opened: " + fileName);
            return list;
        } catch (IOException e) {
            System.out.println("ERROR: File not found/unable to read.");
            return new ArrayList<>();
        }
    }

    private static void printLine(String title) {



        System.out.println("\n=== " + title + " ===");
    }














}

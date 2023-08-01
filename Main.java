import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<String> itemList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean quit = false;

        while (!quit) {
            displayMenu();
            String choice = SafeInput.getRegExString(scanner, "Enter your choice", "[AaDdPpQq]");

            switch (choice.toLowerCase()) {
                case "a":
                    addItem();
                    break;
                case "d":
                    deleteItem();
                    break;
                case "p":
                    printItems();
                    break;
                case "q":
                    quit = confirmQuit();
                    break;
            }
        }

        System.out.println("Thank you for using the program!");
        scanner.close();
    }

    private static void displayMenu() {
        SafeInput.prettyHeader("Menu");
        System.out.println("A - Add an item to the list");
        System.out.println("D - Delete an item from the list");
        System.out.println("P - Print the list");
        System.out.println("Q - Quit the program");
    }

    private static void addItem() {
        String item = SafeInput.getRegExString(scanner, "Enter the item to add", ".+");
        itemList.add(item);
        System.out.println(item + " has been added to the list.");
    }

    private static void deleteItem() {
        if (itemList.isEmpty()) {
            System.out.println("The list is empty. Nothing to delete.");
            return;
        }

        printNumberedItems();
        int itemToDelete = SafeInput.getRangedInt(scanner, "Enter the number of the item to delete", 1, itemList.size()) - 1;
        String deletedItem = itemList.remove(itemToDelete);
        System.out.println(deletedItem + " has been deleted from the list.");
    }

    private static void printItems() {
        if (itemList.isEmpty()) {
            System.out.println("The list is empty.");
            return;
        }

        System.out.println("Current list:");
        for (int i = 0; i < itemList.size(); i++) {
            System.out.println((i + 1) + ". " + itemList.get(i));
        }
    }

    private static void printNumberedItems() {
        System.out.println("Select the item number to delete:");
        for (int i = 0; i < itemList.size(); i++) {
            System.out.println((i + 1) + ". " + itemList.get(i));
        }
    }

    private static boolean confirmQuit() {
        return SafeInput.getYNConfirm(scanner, "Are you sure you want to quit?");
    }
}

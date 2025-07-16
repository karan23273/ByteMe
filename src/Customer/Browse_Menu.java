package Customer;

import Admin_Interface.Food_Item;
import Admin_Interface.Menu_Management;

import java.util.*;

public class Browse_Menu {
    Menu_Management menu;
    public Browse_Menu() {
        menu = new Menu_Management();
    }
    public void displayMenu() {
        TreeMap<String, Food_Item> foodies = menu.getFooditems();
        List<Food_Item> itemsList = new ArrayList<>(foodies.values());


        System.out.println("_____________________________________________________________________________________________");
        System.out.printf("%-20s %-15s %-15s %-20s%n", "Item Name", "Item Price", "Availability", "Category");
        System.out.println("_____________________________________________________________________________________________");

        for (Food_Item item : itemsList) {
            System.out.printf("%-20s %-15d %-15s %-20s\n",
                    item.getName(),
                    item.getPrice(),
                    item.isAvailable() ? "Available" : "Not Available",
                    item.getCategory());
        }

        System.out.println("_____________________________________________________________________________________________");
    }



    public void displayBYprice() {
        TreeMap<String, Food_Item> fooditems = menu.getFooditems();
        List<Food_Item> itemsList = new ArrayList<>(fooditems.values());

        Scanner scan = new Scanner(System.in);
        System.out.println("1. Sort items in increasing order of price");
        System.out.println("2. Sort items in decreasing order of price");
        System.out.print("Enter your choice: ");
        String choice = scan.nextLine();

        // Sort based on the user's choice
        if (choice.equals("1")) {
            itemsList.sort(Comparator.comparing(Food_Item::getPrice)); // Ascending order
            System.out.println("Menu sorted by price in ascending order:");
        } else if (choice.equals("2")) {
            itemsList.sort(Comparator.comparing(Food_Item::getPrice).reversed()); // Descending order
            System.out.println("Menu sorted by price in descending order:");
        } else {
            System.out.println("Invalid choice. Displaying unsorted menu.");
            return;
        }

        System.out.println("_____________________________________________________________________________________________");
        System.out.printf("%-20s %-15s %-15s %-20s%n", "Item Name", "Item Price", "Availability", "Category");
        System.out.println("_____________________________________________________________________________________________");

        for (Food_Item item : itemsList) {
            System.out.printf("%-20s %-15d %-15s %-20s\n",
                    item.getName(),
                    item.getPrice(),
                    item.isAvailable() ? "Available" : "Not Available",
                    item.getCategory());
        }

        System.out.println("_____________________________________________________________________________________________");
    }

    public void displayBYcategory(String cat) {
        TreeMap<String, Food_Item> fooditems = menu.getFooditems();
        List<Food_Item> itemsList = new ArrayList<>(fooditems.values());

        itemsList.sort(Comparator.comparing(Food_Item::getCategory));

        boolean found = false;
        System.out.println("_____________________________________________________________________________________________");
        System.out.printf("%-20s %-15s %-15s %-20s%n", "Item Name", "Item Price", "Availability", "Category");
        System.out.println("_____________________________________________________________________________________________");

        for (Food_Item i : itemsList) {
            if (Objects.equals(i.getCategory(), cat)){
                System.out.printf("%-20s $%-15d %-15s %-20s\n",
                        i.getName(),
                        i.getPrice(),
                        i.isAvailable() ? "Available" : "Not Available",
                        i.getCategory());
                found = true;
            }
        }
        System.out.println("_____________________________________________________________________________________________");

        if (!found) {
            System.out.println("Item not found");
        }
    }

    public void display_specific_item(String name) {
        TreeMap<String, Food_Item> fooditems = menu.getFooditems();
        boolean found = false;
        System.out.println("_____________________________________________________________________________________________");
        for (Food_Item i : fooditems.values()) {
            if (Objects.equals(i.getName(), name)){
                System.out.printf("%-20s $%-15d %-15s %-20s\n",
                        i.getName(),
                        i.getPrice(),
                        i.isAvailable() ? "Available" : "Not Available",
                        i.getCategory());
                found = true;
            }
        }
        System.out.println("______________________________________________________________________________________________");
        if (!found) {
            System.out.println("Item not found");
        }
    }


}

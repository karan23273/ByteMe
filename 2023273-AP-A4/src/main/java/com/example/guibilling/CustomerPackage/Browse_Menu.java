package com.example.guibilling.CustomerPackage;

import com.example.guibilling.Admin_Interface.Food_Item;
import com.example.guibilling.Admin_Interface.Menu_Management;

import java.io.*;
import java.util.*;

public class Browse_Menu {
    Menu_Management menu;
    private PrintStream printStream;
    private File file;

    public File getFile() {
        return file;
    }

    public PrintStream getPrintStream() {
        return printStream;
    }

    public Browse_Menu() throws FileNotFoundException {
        menu = new Menu_Management();
        file = new File("output.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Error creating file: " + e.getMessage());
            }
        }
        printStream = new PrintStream(file);
    }

    public void displayMenu() {
        TreeMap<String, Food_Item> foodies = menu.getFooditems();
        List<Food_Item> itemsList = new ArrayList<>(foodies.values());

        try {
//            printStream.println("______________________________________________________________________________________");
//            printStream.printf("%-20s %-15s %-15s %-20s%n", "Item Name", "Item Price", "Availability", "Category");
//            printStream.println("______________________________________________________________________________________");

            for (Food_Item item : itemsList) {
                printStream.printf("%s,%d,%s,%s\n",
                        item.getName(),
                        item.getPrice(),
                        item.isAvailable() ? "Available" : "Not Available",
                        item.getCategory());
            }

//            printStream.println("______________________________________________________________________________________");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            printStream.close();
        }
    }
//    public void displayMenu() {
//        TreeMap<String, Food_Item> foodies = menu.getFooditems();
//        List<Food_Item> itemsList = new ArrayList<>(foodies.values());
//
//        try {
//            printStream.println("______________________________________________________________________________________");
//            printStream.printf("%-20s %-15s %-15s %-20s%n", "Item Name", "Item Price", "Availability", "Category");
//            printStream.println("______________________________________________________________________________________");
//
//            for (Food_Item item : itemsList) {
//                printStream.printf("%-20s %-15d %-15s %-20s\n",
//                        item.getName(),
//                        item.getPrice(),
//                        item.isAvailable() ? "Available" : "Not Available",
//                        item.getCategory());
//            }
//
//            printStream.println("______________________________________________________________________________________");
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            printStream.close();
//        }
//    }

    public void displayBYprice() {
        TreeMap<String, Food_Item> fooditems = menu.getFooditems();
        List<Food_Item> itemsList = new ArrayList<>(fooditems.values());

        Scanner scan = new Scanner(System.in);
        System.out.println("1. Sort items in increasing order of price");
        System.out.println("2. Sort items in decreasing order of price");
        System.out.print("Enter your choice: ");
        String choice = scan.nextLine();

        try {
            if (choice.equals("1")) {
                itemsList.sort(Comparator.comparing(Food_Item::getPrice)); // Ascending order
//                printStream.println("Menu sorted by price in ascending order:");
            } else if (choice.equals("2")) {
                itemsList.sort(Comparator.comparing(Food_Item::getPrice).reversed()); // Descending order
//                printStream.println("Menu sorted by price in descending order:");
            } else {
//                printStream.println("Invalid choice. Displaying unsorted menu.");
                return;
            }

//            printStream.println("_______________________________________________________________________________________");
//            printStream.printf("%-20s %-15s %-15s %-20s%n", "Item Name", "Item Price", "Availability", "Category");
//            printStream.println("_______________________________________________________________________________________");

            for (Food_Item item : itemsList) {
                printStream.printf("%s,%d,%s,%s\n",
                        item.getName(),
                        item.getPrice(),
                        item.isAvailable() ? "Available" : "Not Available",
                        item.getCategory());
            }

//            printStream.println("_______________________________________________________________________________________");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            printStream.close();
        }
    }

    public void displayBYcategory(String cat) {
        TreeMap<String, Food_Item> fooditems = menu.getFooditems();
        List<Food_Item> itemsList = new ArrayList<>(fooditems.values());

        itemsList.sort(Comparator.comparing(Food_Item::getCategory));
        try {
            boolean found = false;

//            printStream.println("_______________________________________________________________________________________");
//            printStream.printf("%-20s %-15s %-15s %-20s%n", "Item Name", "Item Price", "Availability", "Category");
//            printStream.println("_______________________________________________________________________________________");

            for (Food_Item i : itemsList) {
                if (Objects.equals(i.getCategory(), cat)) {
                    printStream.printf("%s,%d,%s,%s\n",
                            i.getName(),
                            i.getPrice(),
                            i.isAvailable() ? "Available" : "Not Available",
                            i.getCategory());
                    found = true;
                }
            }
//            printStream.println("_______________________________________________________________________________________");

            if (!found) {
                printStream.println("Item not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            printStream.close();
        }
    }

    public void display_specific_item(String name) {
        TreeMap<String, Food_Item> fooditems = menu.getFooditems();

        boolean found = false;
        try {
//            printStream.println("_____________________________________________________________________________________________");
            for (Food_Item i : fooditems.values()) {
                if (Objects.equals(i.getName(), name)) {
                    printStream.printf("%s,%d,%s,%s\n",
                            i.getName(),
                            i.getPrice(),
                            i.isAvailable() ? "Available" : "Not Available",
                            i.getCategory());
                    found = true;
                }
            }
//            printStream.println("_____________________________________________________________________________________________");

            if (!found) {
                printStream.println("Item not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            printStream.close();
        }
    }
}










//package com.example.guibilling.CustomerPackage;
//
//
//import com.example.guibilling.Admin_Interface.Food_Item;
//import com.example.guibilling.Admin_Interface.Menu_Management;
//
//import java.io.*;
//import java.util.*;
//
//public class Browse_Menu {
//    Menu_Management menu;
//    private FileOutputStream fileOutputStream;
//    private PrintStream printStream;
//    private File file;
//
//    public File getFile() {
//        return file;
//    }
//    public FileOutputStream getFileOutputStream() {
//        return fileOutputStream;
//    }
//    public PrintStream getPrintStream() {
//        return printStream;
//    }
//
//    public Browse_Menu() throws FileNotFoundException {
//        menu = new Menu_Management();
//        file = new File("output.txt");
//        if (!file.exists()) {
//            try {
//                file.createNewFile();
//            } catch (IOException e) {
//                System.out.println("Error creating file: " + e.getMessage());
//            }
//        }
//        fileOutputStream = new FileOutputStream(file);
//        printStream = new PrintStream(fileOutputStream);
//
//    }
//    public void displayMenu() {
//        TreeMap<String, Food_Item> foodies = menu.getFooditems();
//        List<Food_Item> itemsList = new ArrayList<>(foodies.values());
//        try{
//            System.setOut(printStream);
//            System.out.println("______________________________________________________________________________________    ");
//            System.out.printf("%-20s %-15s %-15s %-20s%n", "Item Name", "Item Price", "Availability", "Category");
//            System.out.println("______________________________________________________________________________________    ");
//
//            for (Food_Item item : itemsList) {
//                System.out.printf("%-20s %-15d %-15s %-20s\n",
//                        item.getName(),
//                        item.getPrice(),
//                        item.isAvailable() ? "Available" : "Not Available",
//                        item.getCategory());
//            }
//
//            System.out.println("______________________________________________________________________________________"   );
//        } catch (Exception e) {
//            System.setOut(System.out);
//            e.printStackTrace();
//
//        } finally {
//            System.setOut(System.out);
//            printStream.close();
//        }
//    }
//
//
//
//    public void displayBYprice() {
//        TreeMap<String, Food_Item> fooditems = menu.getFooditems();
//        List<Food_Item> itemsList = new ArrayList<>(fooditems.values());
//
//        Scanner scan = new Scanner(System.in);
//        System.out.println("1. Sort items in increasing order of price");
//        System.out.println("2. Sort items in decreasing order of price");
//        System.out.print("Enter your choice: ");
//        String choice = scan.nextLine();
//
//        try{
//            System.setOut(printStream);
//
//            if (choice.equals("1")) {
//                itemsList.sort(Comparator.comparing(Food_Item::getPrice)); // Ascending order
//                System.out.println("Menu sorted by price in ascending order:");
//            } else if (choice.equals("2")) {
//                itemsList.sort(Comparator.comparing(Food_Item::getPrice).reversed()); // Descending order
//                System.out.println("Menu sorted by price in descending order:");
//            } else {
//                System.out.println("Invalid choice. Displaying unsorted menu.");
//                return;
//            }
//
//            System.out.println("_______________________________________________________________________________________");
//            System.out.printf("%-20s %-15s %-15s %-20s%n", "Item Name", "Item Price", "Availability", "Category");
//            System.out.println("_______________________________________________________________________________________");
//
//            for (Food_Item item : itemsList) {
//                System.out.printf("%-20s %-15d %-15s %-20s\n",
//                        item.getName(),
//                        item.getPrice(),
//                        item.isAvailable() ? "Available" : "Not Available",
//                        item.getCategory());
//            }
//
//            System.out.println("_______________________________________________________________________________________");
//
//        } catch (Exception e) {
//            System.setOut(System.out);
//            e.printStackTrace();
//        } finally {
//            System.setOut(System.out);
//            printStream.close();
//        }
//    }
//
//    public void displayBYcategory(String cat) {
//        TreeMap<String, Food_Item> fooditems = menu.getFooditems();
//        List<Food_Item> itemsList = new ArrayList<>(fooditems.values());
//
//        itemsList.sort(Comparator.comparing(Food_Item::getCategory));
//        try {
//            System.setOut(printStream);
//            boolean found = false;
//
//            System.out.println("_______________________________________________________________________________________");
//            System.out.printf("%-20s %-15s %-15s %-20s%n", "Item Name", "Item Price", "Availability", "Category");
//            System.out.println("_______________________________________________________________________________________");
//
//            for (Food_Item i : itemsList) {
//                if (Objects.equals(i.getCategory(), cat)){
//                    System.out.printf("%-20s $%-15d %-15s %-20s\n",
//                            i.getName(),
//                            i.getPrice(),
//                            i.isAvailable() ? "Available" : "Not Available",
//                            i.getCategory());
//                    found = true;
//                }
//            }
//            System.out.println("_______________________________________________________________________________________");
//
//            if (!found) {
//                System.out.println("Item not found");
//            }
//        } catch (Exception e) {
//            System.setOut(System.out);
//            e.printStackTrace();
//        } finally {
//            System.setOut(System.out);
//            printStream.close();
//        }
//
//    }
//
//    public void display_specific_item(String name) {
//        TreeMap<String, Food_Item> fooditems = menu.getFooditems();
//
//        boolean found = false;
//        try {
//            System.setOut(printStream);
//
//            System.out.println("_____________________________________________________________________________________________");
//            for (Food_Item i : fooditems.values()) {
//                if (Objects.equals(i.getName(), name)){
//                    System.out.printf("%-20s $%-15d %-15s %-20s\n",
//                            i.getName(),
//                            i.getPrice(),
//                            i.isAvailable() ? "Available" : "Not Available",
//                            i.getCategory());
//                    found = true;
//                }
//            }
//            System.out.println("______________________________________________________________________________________________");
//            if (!found) {
//                System.out.println("Item not found");
//            }
//        } catch (Exception e) {
//            System.setOut(System.out);
//            e.printStackTrace();
//        } finally {
//            System.setOut(System.out);
//            printStream.close();
//        }
//    }
//
//
//}

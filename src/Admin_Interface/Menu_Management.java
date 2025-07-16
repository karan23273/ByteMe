package Admin_Interface;


import Customer.Customer_data;

import java.util.*;


public class Menu_Management {
    private static TreeMap<String, Food_Item> fooditems = new TreeMap<>() {{
        put("Pizza", new Food_Item("Pizza", 220, true, "Snack"));
        put("Burger", new Food_Item("Burger", 120, true, "Snack"));
        put("Salad", new Food_Item("Salad", 89, true, "Meal"));
    }};
    private static PriorityQueue<Order> orderQueue=new PriorityQueue<>(Comparator.comparing((Order o) -> o.getCustomer().isVip() ? 1 : 0).reversed().thenComparing(Order::getT));
    private static ArrayList<Order> History=new ArrayList<>();

    public static TreeMap<String, Food_Item> getFooditems() {
        return fooditems;
    }


    public static void setFooditems(TreeMap<String, Food_Item> fooditems) {
        Menu_Management.fooditems = fooditems;
    }

    public static PriorityQueue<Order> getOrderQueue() {
        return orderQueue;
    }
    public static void addOrder(Order order) {
        orderQueue.add(order);
    }
    public static void setOrderQueue(PriorityQueue<Order> orderQueue) {
        Menu_Management.orderQueue = orderQueue;
    }
    public static ArrayList<Order> getHistory() {
        return History;
    }
    public static void setHistory(ArrayList<Order> History) {

        Menu_Management.History = History;
    }



    public Menu_Management() {

    }

    public static void add_New_items(String item_name, int item_price, String category) {
        Food_Item item = new Food_Item(item_name, item_price, true, category);
        fooditems.put(item.getName(), item);
    }



    public static void Update_existing_items(String item_name) {

        int method_no = -1;
        Scanner i = new Scanner(System.in);
        Food_Item item = fooditems.get(item_name);
        if (item != null) {
            while (method_no != 0) {
                System.out.println("Choose what you want to update..\n");
                System.out.println("1 -> Price");
                System.out.println("2 -> Availability");
                System.out.println("LOG OUT (PRESS 0)\n");

                try {
                    System.out.print("Enter your choice: ");
                    method_no = i.nextInt();

                    switch (method_no) {
                        case 1:
                            System.out.print("Enter new price: ");
                            int newPrice = i.nextInt();
                            item.setPrice(newPrice);
                            System.out.println("Price updated successfully.");
                            break;
                        case 2:
                            System.out.print("Enter availability (true/false): ");
                            boolean newAvailability = i.nextBoolean();
                            item.setAvailable(newAvailability);
                            System.out.println("Availability updated successfully.");
                            break;
                        case 0:
                            System.out.println("Logging out...");
                            break;
                        default:
                            System.err.println("Invalid option! Please select a valid option.");
                    }
                } catch (InputMismatchException e) {
                    System.err.println("Invalid input! Please enter a valid number.");
                    i.next(); // Clear the invalid input
                }
            }
        } else {
            System.out.println("Item not found");
        }
    }

    public static void remove_item(String item_name) {

        Food_Item item = getFooditems().get(item_name);
        if (item != null) {
            fooditems.remove(item_name);
            for (Order order : getOrderQueue()) {
                if (order.getOrder().containsKey(item)) {
                    order.setStatus("Denied");

                }
            }
            System.out.println("Item Removed successfully");
        } else {
            System.out.println("Item not found");
        }
    }


}

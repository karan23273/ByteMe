package Admin_Interface;

public class Order_Management {
    Menu_Management mainMenu;
    public Order_Management(Menu_Management mainMenu) {
        this.mainMenu = mainMenu;
    }

    public void View_pending_order() {
        boolean fg = false;
        System.out.println("------------------------------------");
        for(Order o : mainMenu.getOrderQueue()) {
            if (!o.getStatus().equals("DELIVERED") && !o.getStatus().equals("REFUNDED")) {
                fg = true;
                System.out.print("Order Id: "+o.getOrderId());
                System.out.printf(" Customer:  %s  (%d)\n",o.getCustomer().getName() ,o.getCustomer().getCustomerId());
                System.out.println("Order Status: " + o.getStatus());
                for (Food_Item f: o.getOrder().keySet()) {
                    System.out.printf("%d  x  1    %s\n", o.getOrder().get(f), f.getName());
                }
            }
            System.out.println("------------------------------------");
        }
        if (!fg){
            System.out.println("No pending order");
        }
    }

    public void Update_order_status(int id,String status) {
        for(Order o : Menu_Management.getOrderQueue()) {
            if(o.getOrderId() == id) {
                o.setStatus(status);
                if(status.equals("DELIVERED") || status.equals("REFUNDED") || status.equals("DENIED")) {
                    Menu_Management.getOrderQueue().remove(o);
                    Menu_Management.getHistory().add(o);
                }
                System.out.println("Order Updated Successfully");
                return;
            }
        }
        System.out.println("Order Updated Failed");
    }

    public void Process_refund(int id) {
        for(Order o : mainMenu.getOrderQueue()) {
            if(o.getOrderId() == id && o.getStatus().equals("CANCELLED")) {
                o.setStatus("REFUNDED");
                Menu_Management.getOrderQueue().remove(o);
                Menu_Management.getHistory().add(o);
                System.out.println("Order Refunded Successfully");
                return;
            }
        }
        System.out.println("Order with order id " + id + " not found"  );
    }

    public void Handle_special_request(int id) {
        for (Order o : Menu_Management.getOrderQueue()) {
            if(o.getOrderId() == id) {
                if (!o.getSpecial().equals("NA")) {
                    String s = o.getSpecial();
                    String s1 = o.getSpecial() + "Checked";
                    o.setSpecial(s1);
                    System.out.printf("Order with special request '%s' Updated Successfully\n", s);

                }else{
                    System.out.println("No Special Request for order id "+ id);
                }
                return;
            }
        }
        System.out.println("Order with order id " +id+ " not found"  );
    }
}

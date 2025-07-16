package Customer;

import java.util.HashMap;

public class Customer_Info {
    private static HashMap<String, Customer_data> Credential=new HashMap<>();

    public HashMap<String, Customer_data> getCredential() {
        return Credential;
    }

    public void setCredential(HashMap<String, Customer_data> credential) {
        Credential = credential;
    }
}

// need to store cart as well

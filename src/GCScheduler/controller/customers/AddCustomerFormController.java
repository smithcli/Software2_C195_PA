package GCScheduler.controller.customers;

import javafx.event.ActionEvent;

public class AddCustomerFormController extends CustomerFormController{

    public void initialize(){
        super.setCombos();
    }

    @Override
    void saveListener(ActionEvent event) {
        String name = nameField.getText();
    }
}

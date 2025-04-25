package org.cantinesystem;

import javafx.application.Platform;
import Service.MenuDAO;
import Service.MenuService;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Menu;
import models.Employee;
import javafx.scene.control.Label;
import java.util.ArrayList;
import java.util.List;

/**
 * Controls the menu screen, displaying menu items and handling user actions such as ordering and logging out.
 */

public class MenuController
{
    private Employee employee;
    private MenuDAO menuDAO;
    private List<Menu> menuItems = new ArrayList<>();

    /**
     * Sets employee data and updates the UI with employee details.
     *
     * @param employee the employee whose data is to be displayed
     */

    public void setEmployeeData(Employee employee)
    {
        this.employee = employee;
        // display or store the employee data
        idLabel.setText("ID:" + employee.getEmployee_id());
        saldoLabel.setText("Saldo:" + employee.getSaldo() + "DKK");
    }

    @FXML
    private Label priceLabel;

    @FXML
    private javafx.scene.control.Button confirmButton;

    @FXML
    private Label saldoLabel;

    @FXML
    private Label idLabel;

    @FXML
    private ListView<String> menuList;

    @FXML
    private javafx.scene.control.Button logOutButton;

    /**
     * Initializes the controller, loading the menu items from the database.
     */

    @FXML
    public void initialize()
    {
        menuDAO = new MenuService();
        loadMenuItems();
    }

    /**
     * Loads menu items from the database and displays them in the menu list.
     */

    private void loadMenuItems()
    {
        ObservableList<String> displayList = FXCollections.observableArrayList();
        try
        {
            menuItems.addAll(menuDAO.getAllMenusItems());
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }


        for (Menu item : menuItems)
        {
            displayList.add(item.getFood() + " - " + item.getPrice() + " DKK");
        }

        menuList.setItems(displayList);
    }

    /**
     * Handles the confirm button click. Checks if the employee has enough saldo to purchase the selected item.
     */

    @FXML
    private void handleConfirmButton() {
        int selectedIndex = menuList.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Menu selectedItem = menuItems.get(selectedIndex);
            double itemPrice = selectedItem.getPrice();
            double currentSaldo = employee.getSaldo();

            if (currentSaldo >= itemPrice) {
                currentSaldo -= itemPrice;
                employee.setSaldo(employee.getSaldo());
                saldoLabel.setText("Saldo: " + String.format("%.2f", currentSaldo) + " DKK");
                priceLabel.setText("Price: " + itemPrice + " DKK");
            } else {
                priceLabel.setText("Not enough saldo!");
            }
        } else {
            priceLabel.setText("Select an item first!");
        }
    }

    /**
     * Logs out the user by closing the application.
     */

    @FXML
    private void handleLogOutButton() {
        Platform.exit();
    }

}
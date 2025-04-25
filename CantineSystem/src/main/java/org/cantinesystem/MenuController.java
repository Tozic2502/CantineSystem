package org.cantinesystem;

import Service.MenuDAO;
import Service.MenuService;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Menu;
import models.Employee;

import java.util.ArrayList;
import java.util.List;

public class MenuController
{
    private Employee employee;
    private MenuDAO menuDAO;
    private List<Menu> menuItems = new ArrayList<>();

    public void setEmployeeData(Employee employee)
    {
        this.employee = employee;
        // display or store the employee data
    }

    @FXML
    private ListView<String> menuList;

    @FXML
    public void initialize()
    {
        menuDAO = new MenuService();
        loadMenuItems();
    }

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
}
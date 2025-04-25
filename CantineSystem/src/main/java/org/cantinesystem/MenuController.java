package org.cantinesystem;

import Utils.SqlConnection;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Menu;
import Utils.CatineDAO;
import java.math.BigDecimal;

import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.List;

public class MenuController{
    public void setEmployeeData(int employeeId, BigDecimal saldo) {
        // display or store the employee data
    }

    @FXML
    private ListView<String> menuList;

    @FXML
    public void initialize() throws SQLException {
        loadMenuItems();
    }

    private void loadMenuItems() throws SQLException {
        List<Menu> menus = new ArrayList<>();
        ObservableList<String> displayList = FXCollections.observableArrayList();
        try (Connection conn = SqlConnection.getConnection()) {
            CallableStatement stmt = conn.prepareCall("{Call Get_Menu ()}");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                int Menu_id = rs.getInt("Menu_id");
                String Food = rs.getString("Food");
                double Price = rs.getFloat("Price");

                menus.add(new Menu(Menu_id, Food, Price));
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        for (Menu item : menus) {
            displayList.add(item.getFood() + " - " + item.getPrice() + " DKK");
        }

        menuList.setItems(displayList);
    }
}


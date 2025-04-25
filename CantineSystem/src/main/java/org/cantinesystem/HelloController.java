package org.cantinesystem;

import Service.EmployeeDAO;
import Service.EmployeeService;
import Utils.SqlConnection;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import models.Employee;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.*;

/**
 * Controls login logic and UI transitions between username and ID-based logins.
 */
public class HelloController
{
    private EmployeeDAO employeeDAO;

    @FXML Label LoginID, LoginUserName;
    @FXML Button BUserName, BID, BConfirmID;
    @FXML TextField TUserName, TID, TPassword;

    /**
     * Initializes the employeeDAO for database interaction.
     */

    @FXML
    public void initialize()
    {
        this.employeeDAO = new EmployeeService();
    }

    /**
     * Switches to username login mode and shows the username field.
     */
    @FXML
    public void OnUserNameChosen()
    {
        LoginChosen();
        LoginUserName.setText("Please Enter your Username and Password");
        TUserName.setVisible(true);

    }

    /**
     * Switches to ID login mode and shows the ID field.
     */
    @FXML
    public void OnIDChosen()
    {
        LoginChosen();
        LoginUserName.setVisible(false);
        LoginID.setText("Please Enter Your ID");
        TID.setVisible(true);
        //Sets KeyEvent listener on field after this login method is chosen
        TID.setOnKeyReleased(keyEvent ->
        {
            if (keyEvent.getCode() == KeyCode.ENTER)
            {
                BConfirmID.fire();
            }
        });
        BConfirmID.setVisible(true);

    }

    /**
     * Confirms the entered ID, loads the menu if the employee is found.
     *
     * @param event the action event that triggers the method
     * @throws IOException if the menu view cannot be loaded
     */

    @FXML
    public void OnConfirmID(ActionEvent event) throws IOException
    {
        try
        {
            Employee employee = employeeDAO.getEmployeeByEmployeeNR(TID.getText());

            if (employee != null)
            {
                // Load menu-view.fxml
                FXMLLoader loader = new FXMLLoader(getClass().getResource("menu-view.fxml"));
                Parent root = loader.load();

                // Pass data to the controller
                MenuController controller = loader.getController();
                controller.setEmployeeData(employee);

                // Replace the scene
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.setTitle("Menu");
                stage.show();
            }
            else
            {
                System.out.println("User not found");
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }



    /**
     * Hides the username and ID buttons after a login method is chosen.
     */
    public void LoginChosen()
    {
        BUserName.setVisible(false);
        BID.setVisible(false);
    }

}
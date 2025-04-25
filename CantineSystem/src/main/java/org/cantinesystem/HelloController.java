package org.cantinesystem;

import Utils.SqlConnection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.*;


public class HelloController {
    @FXML Label LoginID, LoginUserName;
    @FXML Button BUserName, BID, BConfirmID;
    @FXML TextField TUserName, TID, TPassword;



    @FXML
    public void Initialize() {

    }

    /**
     * Changes to Username and password for login
     */
    @FXML
    public void OnUserNameChosen(){
        LoginChosen();
        LoginUserName.setText("Please Enter your Username and Password");
        TUserName.setVisible(true);

    }

    /**
     * Changes to id login
     */
    @FXML
    public void OnIDChosen(){
        LoginChosen();
        LoginUserName.setVisible(false);
        LoginID.setText("Please Enter Your ID");
        TID.setVisible(true);
        BConfirmID.setVisible(true);


    }


    @FXML
    public void OnConfirmID(ActionEvent event) throws IOException {
        String id = TID.getText();

        try (Connection conn = SqlConnection.getConnection()) {
            assert conn != null;
            CallableStatement stmt = conn.prepareCall("{Call Get_Employee_By_Employee_nr (?)}");
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();


            if (!rs.next())
            {
                System.out.println("User not found");
                return;
            }
            else
            {
                System.out.println("Hell world");

                int employeeId = rs.getInt("Employee_Id");
                BigDecimal saldo = rs.getBigDecimal("Saldo");

                // Load menu-view.fxml
                FXMLLoader loader = new FXMLLoader(getClass().getResource("menu-view.fxml"));
                Parent root = loader.load();

                // Pass data to the controller
                MenuController controller = loader.getController();
                controller.setEmployeeData(employeeId, saldo);


                // Replace the scene
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.setTitle("Menu");
                stage.show();
                conn.close();
            }




        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    /**
     * Removes other button
     */
    public void LoginChosen(){
        BUserName.setVisible(false);
        BID.setVisible(false);
    }

}
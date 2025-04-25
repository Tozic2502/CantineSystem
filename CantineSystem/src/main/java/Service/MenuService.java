package Service;

import Utils.SqlConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Menu;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MenuService implements MenuDAO
{
    @Override
    public List<Menu> getAllMenusItems()
    {
        List<Menu> menuItems = new ArrayList<>();
        try (Connection conn = SqlConnection.getConnection())
        {
            CallableStatement stmt = conn.prepareCall("{Call Get_Menu ()}");
            ResultSet rs = stmt.executeQuery();

            while (rs.next())
            {
                Menu menu = new Menu();
                menu.setMenu_id(rs.getInt("Menu_id"));
                menu.setFood(rs.getString("Food"));
                menu.setPrice(rs.getFloat("Price"));
                menuItems.add(menu);
            }
            rs.close();
            stmt.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return menuItems;
    }
}

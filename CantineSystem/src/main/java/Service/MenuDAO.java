package Service;

import models.Menu;

import java.sql.SQLException;
import java.util.List;

public interface MenuDAO
{
    List<Menu> getAllMenusItems();
}

package models;

public class Menu {
    private String food;
    private double price;
    private int menu_id;

    public int getMenu_id()
    {
        return menu_id;
    }

    public void setMenu_id(int menu_id)
    {
        this.menu_id = menu_id;
    }

    public String getFood()
    {
        return food;
    }

    public void setFood(String food)
    {
        this.food = food;
    }

    public double getPrice()
    {
        return price;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }

    public Menu()
    {
    }

    public Menu(int menu_id, String food, double price) {
        this.menu_id = menu_id;
        this.food = food;
        this.price = price;
    }
}

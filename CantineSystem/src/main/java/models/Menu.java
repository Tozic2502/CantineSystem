package models;

public class Menu {
    private String food;
    private double price;
    private int menu_id;

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Menu(int menu_id, String food, double price) {
        this.menu_id = menu_id;
        this.food = food;
        this.price = price;
    }
}

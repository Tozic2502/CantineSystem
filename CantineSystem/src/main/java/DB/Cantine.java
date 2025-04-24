package DB;

public class Cantine {
    private int no;
    private String name;
    private double price;


    public Cantine(int no, String name, double price){
        this.no=no;
        this.name=name;
        this.price=price;
    }
    public Cantine(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString(){
        return this.no + " " + this.name + " " + this.price;
    }
    public int getNo() {
        return no;
    }
    public void setNo(int no) {
        this.no = no;

    }


}
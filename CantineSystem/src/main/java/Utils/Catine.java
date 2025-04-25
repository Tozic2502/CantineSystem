package Utils;

/**
 * Represents a Catine with a number, name, and price.
 */

public class Catine {
    private int no;
    private String name;
    private double price;

    /**
     * Constructs a Catine with the specified details.
     *
     * @param no the catine number
     * @param name the catine name
     * @param price the catine price
     */

    public Catine(int no, String name, double price){
        this.no=no;
        this.name=name;
        this.price=price;
    }

    /** Default constructor */
    public Catine(){

    }

    /** @return the catine name */
    public String getName() {
        return name;
    }

    /** @param name the catine name */
    public void setName(String name) {
        this.name = name;
    }

    /** @return the catine price */
    public double getPrice() {
        return price;
    }

    /** @param price the catine price */
    public void setPrice(double price) {
        this.price = price;
    }

    /** @return string representation of the catine (no, name, price) */
    @Override
    public String toString(){
        return this.no + " " + this.name + " " + this.price;
    }

    /** @return the catine number */
    public int getNo() {
        return no;
    }

    /** @param no the catine number */
    public void setNo(int no) {
        this.no = no;

    }


}
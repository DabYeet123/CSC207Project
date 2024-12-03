package oldstuff.Views.House.DataObject;

import javax.swing.*;

public class HouseObject {

    private final String name;
    private final String address;
    private final int x;
    private final int y;
    private final double price;
    private String owner;

    public HouseObject(String name, String address, int x, int y, double price, String owner) {
        this.name = name;
        this.address = address;
        this.x = x;
        this.y = y;
        this.price = price;
        this.owner = owner;
    }

    public HouseObject() {
        name = "";
        address = "";
        x = 0;
        y = 0;
        price = 0;
        owner = "";
    }

    public void createButton(JPanel panel, HouseController controller) {
        new HouseButton(this, x, y, 20, 20, panel,controller);
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double getPrice() {
        return price;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

}

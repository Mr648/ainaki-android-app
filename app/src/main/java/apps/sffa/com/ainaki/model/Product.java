package apps.sffa.com.ainaki.model;

/**
 * Created by Diako on 21/05/2018.
 */

public class Product {

    private int id;
    private String name;
    private double price;
    private String image;

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getImage() {
        return image;

    }

    public void setImage(String image) {
        this.image = image;
    }


    public Product(int id, String name){
        this.id = id;
        this.name= name;
    }
}

package apps.sffa.com.ainaki.model;

/**
 * Created by Diako on 21/05/2018.
 */

public class Product extends Model {

    protected String name;
    protected float price;
    protected String image;
    protected int brandId;
    protected int warrantyId;
    protected int categoryId;
    protected String description;


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    public Product(int id, String name) {
        super(id);
        this.name = name;
    }
}

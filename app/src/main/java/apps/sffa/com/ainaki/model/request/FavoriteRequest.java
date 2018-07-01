package apps.sffa.com.ainaki.model.request;

/**
 * Created by mr-code on 6/14/2018.
 */

public class FavoriteRequest extends GeneralRequest {

    private String productId;
    private String productCategory;

    public FavoriteRequest(String authKey,
                           String productId, String productCategory) {
        super(authKey);
        this.productId = productId;
        this.productCategory = productCategory;
    }


    public FavoriteRequest(String authKey) {
        super(authKey);
        this.productId = null;
        this.productCategory = null;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }
}

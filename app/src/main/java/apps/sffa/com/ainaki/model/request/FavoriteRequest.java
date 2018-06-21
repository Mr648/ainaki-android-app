package apps.sffa.com.ainaki.model.request;

/**
 * Created by mr-code on 6/14/2018.
 */

public class FavoriteRequest {

    private String authKey;
    private String productId;
    private String productCategory;

    public FavoriteRequest(String authKey,
                           String productId,
                           String productCategory) {

        this.authKey = authKey;
        this.productId = productId;
        this.productCategory = productCategory;
    }

    public String getAuthKey() {
        return authKey;
    }

    public void setAuthKey(String authKey) {
        this.authKey = authKey;
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

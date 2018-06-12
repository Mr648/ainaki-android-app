package apps.sffa.com.ainaki.webservice;

import java.util.List;

import apps.sffa.com.ainaki.model.Product;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by mr-code on 6/9/2018.
 */

public interface ProductListWebService {

    @GET("/users/{id}/favorites")
    Call<List<Product>> getFavorites(@Path("id") int id);

    @GET("/products/{category}/{filter}")
    Call<List<Product>> getProducts(@Path("category") String category, @Path("filter") String filter);
}

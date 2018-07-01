package apps.sffa.com.ainaki.webservice;

import java.util.List;

import apps.sffa.com.ainaki.model.Model;
import apps.sffa.com.ainaki.model.Product;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by mr-code on 6/9/2018.
 */

public interface ProductListWebService {

    @GET("/products/{category}/{filter}")
    Call<List<Model>> getProducts(@Path("category") String category, @Path("filter") String filter);

    @GET("/products/{category}/{id}")
    Call<Model> getProductById(@Path("category") String category, @Path("id") int id);
}

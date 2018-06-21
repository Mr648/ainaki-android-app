package apps.sffa.com.ainaki.webservice;

import java.util.List;

import apps.sffa.com.ainaki.model.Product;
import apps.sffa.com.ainaki.model.request.FavoriteRequest;
import apps.sffa.com.ainaki.model.response.GeneralResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Diako on 19/06/2018.
 */

public interface UserWebService {

    @POST("users/like/{productId}")
    Call<GeneralResponse> like(@Body FavoriteRequest request, @Path("productId") Integer productId);

    @POST("users/dislike/{productId}")
    Call<GeneralResponse> dislike(@Body FavoriteRequest request, @Path("productId") Integer productId);

    @GET("/users/{like}/{filter}")
    Call<List<Product>> getProducts(@Path("category") String category, @Path("filter") String filter);


}

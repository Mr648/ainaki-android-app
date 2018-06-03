package apps.sffa.com.ainaki.webservice;

import apps.sffa.com.ainaki.model.request.LoginRequest;
import apps.sffa.com.ainaki.model.response.LoginResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Diako on 26/05/2018.
 */

public interface LoginWebService {

    @POST("login")
    Call<LoginResponse> login(@Body LoginRequest request);




}

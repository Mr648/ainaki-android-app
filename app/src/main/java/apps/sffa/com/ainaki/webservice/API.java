package apps.sffa.com.ainaki.webservice;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Diako on 26/05/2018.
 */

public class API {


    public  static  final  String BASE_URL= "http://192.168.2.1:8080/";
//    public  static  final  String BASE_URL= "http://169.254.214.101/";

    private static Retrofit retrofit;


    //    public  static  final  String BASE_URL= "http://169.254.214.101/";
    public static Retrofit getRetrofit() {

        return (retrofit == null) ? initRetrofit() : retrofit;
    }

    private static Retrofit initRetrofit() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();


        retrofit = new Retrofit.Builder()
                .baseUrl(API.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit;
    }
}

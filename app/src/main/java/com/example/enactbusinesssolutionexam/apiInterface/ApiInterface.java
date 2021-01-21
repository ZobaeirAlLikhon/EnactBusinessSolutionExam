package com.example.enactbusinesssolutionexam.apiInterface;
import android.graphics.Bitmap;

import com.example.enactbusinesssolutionexam.model.GetModel;
import com.example.enactbusinesssolutionexam.model.LoginModel;
import com.example.enactbusinesssolutionexam.model.Model;
import com.google.gson.JsonObject;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
public interface ApiInterface {
    @Multipart
    @POST("image.php")
    Call<Model> uploadeImage(
            @Part MultipartBody.Part image);
    @GET("medicine-list.php")
    Call<GetModel> getMovie();
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @POST("member-login.php")
    Call<LoginModel> login(@Body JsonObject locationPost);
//    @Query("mobile") String mobile, @Query("password") String password
}


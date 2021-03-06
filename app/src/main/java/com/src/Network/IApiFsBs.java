package com.src.Network;

import com.src.Model.BaseResponse;
import com.src.Model.Cate;
import com.src.Model.Gift;
import com.src.Model.Images;
import com.src.Model.Product;
import com.src.Model.Review;
import com.src.Model.User;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface IApiFsBs {

    //user
    @FormUrlEncoded
    @POST("users/login")
    Call<BaseResponse<User>> handlerLogin(@Field("email") String email, @Field("password") String password);

    @FormUrlEncoded
    @POST("users/register")
    Call<BaseResponse<User>> handlerRegister(
                                @Field("email") String email,
                               @Field("password") String password,
                               @Field("phone") String phone,
                               @Field("address") String address,
                               @Field("username") String userName);

    @FormUrlEncoded
    @POST("users/change-password")
    Call<BaseResponse<String>> handlerChangePassword(@Field("oldPassword") String password, @Field("newPassword") String newPassword);

    @GET("users/profile")
    Call<BaseResponse<User>> getProfile();
    @POST("upload/photo")
    Call<String> uploadImages(@Part MultipartBody.Part photo);

    //product
    @GET("products/list")
    Call<BaseResponse<List<Product>>> getListProduct();

    @GET("products/new_list")
    Call<BaseResponse<List<Product>>> getNewListProduct();

    @GET("products/list_paging")
    Call<BaseResponse<List<Product>>> getListProductPaging(@Query("page") int page);

    @GET("products/list/{id}")
    Call<BaseResponse<List<Product>>> getListProductForCate(@Path("id") String cateId);

    @GET("products/images/{id}")
    Call<BaseResponse<List<Images>>> getImagesProduct(@Path("id") String productId);

    @GET("products/search")
    Call<BaseResponse<List<Product>>> getSearchProduct(@Query("search") String query);


    @FormUrlEncoded
    @POST("products/add_review")
    Call<BaseResponse<Review.ReviewDetails>> addComment(@Field("comment") String comment, @Field("rate") int rate, @Field("productId") String productId);

    @GET("products/list_review")
    Call<BaseResponse<List<Review>>> getListReview(@Query("productId") String productId);

    //Category
    @GET("cates/list")
    Call<BaseResponse<List<Cate>>> getListCate();


    //order
    @FormUrlEncoded
    @POST("orders/add")
    Call<BaseResponse<String>> addOrder(@Field("order_details") String jsonOrderDetails, @Field("order") String jsonOrder);
//    Call<ResponseBody> addOrder(@Body String jsonArray);


    //banner
    @GET("cates/list_banner")
    Call<BaseResponse<List<Images>>> getBanner();

}

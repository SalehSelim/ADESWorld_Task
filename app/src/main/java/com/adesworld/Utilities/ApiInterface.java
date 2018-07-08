package com.adesworld.Utilities;

import com.adesworld.Model.Document;
import com.adesworld.Model.Category;

import retrofit2.Call;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by ssasa on 07-Jul-18.
 */

public interface ApiInterface {

    @POST("GetAdesWorldCategories")
    @Headers({Constants.AUTHORIZATION, Constants.CONTENT_TYPE})
    Call<Category> getCategories();

    @POST("GetAdesWorldBooks")
    @Headers({Constants.AUTHORIZATION, Constants.CONTENT_TYPE})
    Call<Document> getDocuments(@Query(Constants.CATEGORY_ID) String category_id);
}

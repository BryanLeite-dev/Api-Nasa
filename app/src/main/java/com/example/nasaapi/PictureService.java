package com.example.nasaapi;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface PictureService {

    @GET("?api_key=9TwkQMMTeOzGRp5F2gnDXCju3a8g3K0GQeA4rm5I")
    Call <ArrayList<DtoPictureDay>> getPictureOfDay(@Query("start_date") String start_date, @Query("end_date") String end_date);

}



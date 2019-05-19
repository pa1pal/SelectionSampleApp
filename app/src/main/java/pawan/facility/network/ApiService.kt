package pawan.facility.network

import pawan.facility.data.model.Home
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by Pawan Pal on 19/5/19.
 */
interface ApiService {
    @GET("/iranjith4/ad-assignment/db")
    fun getSurveyData(): Call<Home>
}
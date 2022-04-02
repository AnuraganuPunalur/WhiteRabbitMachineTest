package com.anurag.whiterabbitmachinetest.api

import com.anurag.whiterabbitmachinetest.model.EmployeeDetailsResponseItem
import retrofit2.http.GET

interface ApiMethods {

    @GET("v2/5d565297300000680030a986/")
    suspend fun getAllEmployeeDetails(): List<EmployeeDetailsResponseItem>
}
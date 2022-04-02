package com.anurag.whiterabbitmachinetest.roomDatabase

import androidx.room.TypeConverter
import com.anurag.whiterabbitmachinetest.model.Address
import com.anurag.whiterabbitmachinetest.model.Company
import com.anurag.whiterabbitmachinetest.model.Geo
import com.google.gson.Gson

class ConverterHelper {

    @TypeConverter
    fun fromAddress(address: Address): String? {

        return Gson().toJson(address)
    }

    @TypeConverter
    fun toAddress(address: String): Address? {

        return Gson().fromJson(address, Address::class.java)
    }

    @TypeConverter
    fun fromCompany(company: Company): String?{

        return Gson().toJson(company)
    }

    @TypeConverter
    fun toCompany(company: String): Company? {

        return Gson().fromJson(company, Company::class.java)
    }

    @TypeConverter
    fun fromGeo(geo: Geo): String?{

        return Gson().toJson(geo)
    }

    @TypeConverter
    fun toGeo(geo: String): Geo? {

        return Gson().fromJson(geo, Geo::class.java)
    }
}
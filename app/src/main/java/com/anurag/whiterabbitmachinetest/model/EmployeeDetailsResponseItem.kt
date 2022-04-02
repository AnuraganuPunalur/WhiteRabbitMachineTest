package com.anurag.whiterabbitmachinetest.model


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity(tableName = "employeeTable")
@Parcelize
data class EmployeeDetailsResponseItem(

    @SerializedName("address")
    val address: Address?,

    @SerializedName("company")
    val company: Company?,

    @SerializedName("email")
    val email: String?,

    @PrimaryKey
    @SerializedName("id")
    val id: Int?,

    @SerializedName("name")
    val name: String?,
    @SerializedName("phone")
    val phone: String?,
    @SerializedName("profile_image")
    val profileImage: String?,
    @SerializedName("username")
    val username: String?,
    @SerializedName("website")
    val website: String?
): Parcelable
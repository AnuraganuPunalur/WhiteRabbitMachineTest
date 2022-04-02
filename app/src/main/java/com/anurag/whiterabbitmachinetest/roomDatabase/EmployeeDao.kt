package com.anurag.whiterabbitmachinetest.roomDatabase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.anurag.whiterabbitmachinetest.model.EmployeeDetailsResponseItem

@Dao
interface EmployeeDao {

    @Insert
    suspend fun addEmployeeData(employeeData: EmployeeDetailsResponseItem)

    @Query("SELECT * FROM employeeTable")
    suspend fun getAllEmployeeData(): List<EmployeeDetailsResponseItem>
}
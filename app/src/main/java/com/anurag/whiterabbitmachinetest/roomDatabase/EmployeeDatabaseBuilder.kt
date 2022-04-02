package com.anurag.whiterabbitmachinetest.roomDatabase

import android.content.Context
import androidx.room.Room

object EmployeeDatabaseBuilder {

    private var employeeDataBaseInstance: EmployeeDatabase? = null

    @Synchronized
    fun getEmployeeDataBaseInstance(context: Context): EmployeeDatabase {

        if (employeeDataBaseInstance == null){

            employeeDataBaseInstance = buildEmployeeDatabase(context)
        }

        return employeeDataBaseInstance as EmployeeDatabase
    }

    private fun buildEmployeeDatabase(context: Context): EmployeeDatabase {

        return Room.databaseBuilder(context.applicationContext, EmployeeDatabase::class.java, "EmployeeDatabase").build()
    }
}
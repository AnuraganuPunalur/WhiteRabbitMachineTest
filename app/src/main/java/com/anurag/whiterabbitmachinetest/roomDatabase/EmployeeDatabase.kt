package com.anurag.whiterabbitmachinetest.roomDatabase

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.anurag.whiterabbitmachinetest.model.EmployeeDetailsResponseItem

@Database(entities = [EmployeeDetailsResponseItem::class], version = 1)
@TypeConverters(ConverterHelper::class)
abstract class EmployeeDatabase: RoomDatabase() {

    abstract fun getEmployeeDao(): EmployeeDao
}
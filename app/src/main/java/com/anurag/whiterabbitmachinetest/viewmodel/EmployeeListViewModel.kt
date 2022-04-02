package com.anurag.whiterabbitmachinetest.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.*
import com.anurag.whiterabbitmachinetest.api.ApiService
import com.anurag.whiterabbitmachinetest.model.EmployeeDetailsResponseItem
import com.anurag.whiterabbitmachinetest.roomDatabase.EmployeeDatabaseBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.lang.Exception

class EmployeeListViewModel(application: Application): AndroidViewModel(application) {

    private var mutableEmployeeDataFromDB: MutableLiveData<List<EmployeeDetailsResponseItem>> = MutableLiveData()
    private lateinit var employeeDataFromDB: LiveData<List<EmployeeDetailsResponseItem>>
    private lateinit var employeeListData: LiveData<List<EmployeeDetailsResponseItem>>
    private var employeeListMutableLiveData: MutableLiveData<List<EmployeeDetailsResponseItem>> = MutableLiveData()

    fun getEmployeeData(): LiveData<List<EmployeeDetailsResponseItem>> {

        employeeListData = employeeListMutableLiveData
        return employeeListData
    }

    fun getAllEmployeeList(context: Context) {

        viewModelScope.launch {

            try {

                val employeeListResponse = ApiService.getApiMethods().getAllEmployeeDetails()
                employeeListMutableLiveData.postValue(employeeListResponse)

                withContext(Dispatchers.IO){

                    saveAllEmployeeDetailsToDB(getApplication(), employeeListResponse)
                }

            }catch (e: Exception){

                if (e is HttpException){


                }
            }
        }
    }

    private suspend fun saveAllEmployeeDetailsToDB(context: Context, employeeList: List<EmployeeDetailsResponseItem>){

        for (employee in employeeList){

            EmployeeDatabaseBuilder.getEmployeeDataBaseInstance(context).getEmployeeDao().addEmployeeData(employee)
        }
    }

    fun getEmployeeDataFromDB(): LiveData<List<EmployeeDetailsResponseItem>>{

        employeeDataFromDB = mutableEmployeeDataFromDB
        return employeeDataFromDB
    }

    fun getAllEmployeeListFromDB() {

        viewModelScope.launch(Dispatchers.IO) {

            mutableEmployeeDataFromDB.postValue(EmployeeDatabaseBuilder.getEmployeeDataBaseInstance(getApplication()).getEmployeeDao().getAllEmployeeData())

//            withContext(Dispatchers.Main){
//
//                employeeDataFromDB = mutableEmployeeDataFromDB
//            }
        }
    }
}
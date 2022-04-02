package com.anurag.whiterabbitmachinetest.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.anurag.whiterabbitmachinetest.R
import com.anurag.whiterabbitmachinetest.adapter.EmployeeListAdapter
import com.anurag.whiterabbitmachinetest.databinding.ActivityMainBinding
import com.anurag.whiterabbitmachinetest.model.EmployeeDetailsResponseItem
import com.anurag.whiterabbitmachinetest.utils.AppConstants
import com.anurag.whiterabbitmachinetest.utils.AppUtils
import com.anurag.whiterabbitmachinetest.viewmodel.EmployeeListViewModel

class MainActivity : AppCompatActivity(), EmployeeListAdapter.EmployeeClickListener {

    private lateinit var mainEmployeeList: MutableList<EmployeeDetailsResponseItem>
    private lateinit var searchList: MutableList<EmployeeDetailsResponseItem>
    private lateinit var employeeListViewModel: EmployeeListViewModel
    private lateinit var employeeList: MutableList<EmployeeDetailsResponseItem>
    private lateinit var employeeListAdapter: EmployeeListAdapter
    private lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        employeeListViewModel = ViewModelProvider(this)[EmployeeListViewModel::class.java]

        searchList = ArrayList()
        mainEmployeeList = ArrayList()

        initRecyclerView()

        setDataObservers()

        getAllEmployeesFromDb()

        settingUpEmployeeSearch()
    }

    private fun settingUpEmployeeSearch(){

        activityMainBinding.searchViewEmployeeList.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                searchList.clear()
                val searchQuery = newText?.lowercase()
                if (searchQuery != null){

                    if (searchQuery.isNotEmpty()){

                        for (i in mainEmployeeList){

                            if (i.name!!.lowercase().contains(searchQuery) ||  i.email!!.lowercase().contains(searchQuery)){

                                searchList.add(i)
                            }
                        }

                        employeeListAdapter.setEmployeeList(searchList)
                    }else{

                        searchList.clear()
                        searchList.addAll(mainEmployeeList)
                        employeeListAdapter.setEmployeeList(searchList)
                    }
                }

                return false
            }

        })
    }

    private fun setDataObservers(){

        employeeListViewModel.getEmployeeData().observe(this, Observer<List<EmployeeDetailsResponseItem>>{

                allEmployeeList ->

            allEmployeeList.let {

                if (it.isNotEmpty()){

                    mainEmployeeList.clear()
                    mainEmployeeList.addAll(it)
                    employeeListAdapter.setEmployeeList(it)
                }
            }
        })

        employeeListViewModel.getEmployeeDataFromDB().observe(this, Observer<List<EmployeeDetailsResponseItem>>{

            it.let {

                if (it.isNotEmpty()){

                    mainEmployeeList.clear()
                    mainEmployeeList.addAll(it)
                    employeeListAdapter.setEmployeeList(it)
                }else{

                    getAllEmployeesFromInternet(this)
                }
            }
        })
    }

    private fun initRecyclerView(){

        employeeList = ArrayList()
        employeeListAdapter = EmployeeListAdapter(employeeList, this)
        activityMainBinding.recyclerEmployeeList.apply {

            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = employeeListAdapter
        }
    }

    private fun getAllEmployeesFromInternet(context: Context){

        if (!AppUtils.isConnectedToInternet(context)){

            AppUtils.showToast(context, getString(R.string.no_internet_error))
            return
        }

        employeeListViewModel.getAllEmployeeList(context)

    }

    private fun getAllEmployeesFromDb(){

        employeeListViewModel.getAllEmployeeListFromDB()
    }

    override fun onEmployeeClicked(employeeData: EmployeeDetailsResponseItem) {

        val intent = Intent(this, EmployeeDetailsActivity::class.java)
        intent.putExtra(AppConstants.EMPLOYEE_DETAILS, employeeData)
        startActivity(intent)
    }
}
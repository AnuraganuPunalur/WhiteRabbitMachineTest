package com.anurag.whiterabbitmachinetest.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.anurag.whiterabbitmachinetest.R
import com.anurag.whiterabbitmachinetest.databinding.ActivityEmployeeDetailsBinding
import com.anurag.whiterabbitmachinetest.model.EmployeeDetailsResponseItem
import com.anurag.whiterabbitmachinetest.utils.AppConstants
import com.anurag.whiterabbitmachinetest.utils.HelperExtensions.loadImage

class EmployeeDetailsActivity : AppCompatActivity() {

    private lateinit var employeeDetailsBinding: ActivityEmployeeDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        employeeDetailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_employee_details)

        if (intent.getParcelableExtra<EmployeeDetailsResponseItem>(AppConstants.EMPLOYEE_DETAILS) != null){

            setUpEmployeeDetails(intent.getParcelableExtra<EmployeeDetailsResponseItem>(AppConstants.EMPLOYEE_DETAILS)!!)
        }
    }

    private fun setUpEmployeeDetails(employeeDetails: EmployeeDetailsResponseItem){

        employeeDetailsBinding.ivEmployeeImageInDetails.loadImage(employeeDetails.profileImage)

        employeeDetailsBinding.tvEmployeeNameInDetails.text = employeeDetails.name
        ("(" + employeeDetails.username + ")").also { employeeDetailsBinding.tvEmployeeUserNameInDetails.text = it }
        employeeDetailsBinding.tvEmployeeEmailInDetails.text = employeeDetails.email
        employeeDetailsBinding.tvEmployeeWebsiteInDetails.text = employeeDetails.website
        employeeDetailsBinding.tvEmployeePhonInDetails.text = employeeDetails.phone
        employeeDetailsBinding.tvEmployeeAddressInDetails.text = employeeDetails.address?.suite + "," +  employeeDetails.address?.city + "," + employeeDetails.address?.street + "," + employeeDetails.address?.zipcode
        employeeDetailsBinding.tvCompanyAddressInDetails.text = employeeDetails.company?.name  + "," +  employeeDetails.company?.catchPhrase  + "," +  employeeDetails.company?.bs
    }
}
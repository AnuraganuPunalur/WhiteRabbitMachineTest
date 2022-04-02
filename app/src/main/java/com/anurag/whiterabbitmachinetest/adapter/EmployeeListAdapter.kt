package com.anurag.whiterabbitmachinetest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.anurag.whiterabbitmachinetest.databinding.AdapterEmployeeListBinding
import com.anurag.whiterabbitmachinetest.model.EmployeeDetailsResponseItem
import com.anurag.whiterabbitmachinetest.utils.EmployeeAdapterDiffUtil
import com.anurag.whiterabbitmachinetest.utils.HelperExtensions.loadImage

class EmployeeListAdapter(private val employeeList: MutableList<EmployeeDetailsResponseItem>,
                          val employeeClickListener: EmployeeClickListener)
    : RecyclerView.Adapter<EmployeeListAdapter.EmployeeHolder>() {

    //Function used to set list of employee data to this adapter
    fun setEmployeeList(newEmployeeList: List<EmployeeDetailsResponseItem>){

        val diffUtil = EmployeeAdapterDiffUtil(employeeList, newEmployeeList)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        employeeList.clear()
        employeeList.addAll(newEmployeeList)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeHolder {

        val adapterEmployeeListBinding = AdapterEmployeeListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EmployeeHolder(adapterEmployeeListBinding)
    }

    override fun onBindViewHolder(holder: EmployeeHolder, position: Int) {

        holder.setBindingOfData(employeeList[position])

        holder.adapterEmployeeListBinding.layoutEmployeeList.setOnClickListener {

            employeeClickListener.onEmployeeClicked(employeeList[position])
        }
    }

    override fun getItemCount(): Int {

        return employeeList.size
    }

    class EmployeeHolder(val adapterEmployeeListBinding: AdapterEmployeeListBinding): RecyclerView.ViewHolder(adapterEmployeeListBinding.root) {

        fun setBindingOfData(employeeData: EmployeeDetailsResponseItem){

            adapterEmployeeListBinding.employeeDetails = employeeData
            adapterEmployeeListBinding.ivEmployeeImageInList.loadImage(employeeData.profileImage)
        }
    }

    interface EmployeeClickListener{

        fun onEmployeeClicked(employeeData: EmployeeDetailsResponseItem)
    }

}
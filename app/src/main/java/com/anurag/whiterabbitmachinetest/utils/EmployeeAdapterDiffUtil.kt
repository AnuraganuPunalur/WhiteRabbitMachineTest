package com.anurag.whiterabbitmachinetest.utils

import androidx.recyclerview.widget.DiffUtil
import com.anurag.whiterabbitmachinetest.model.EmployeeDetailsResponseItem

class EmployeeAdapterDiffUtil(private val oldEmployeeList: List<EmployeeDetailsResponseItem>, private val newEmployeeList: List<EmployeeDetailsResponseItem>): DiffUtil.Callback() {

    override fun getOldListSize(): Int {

        return oldEmployeeList.size
    }

    override fun getNewListSize(): Int {

        return newEmployeeList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {

        return oldEmployeeList[oldItemPosition].id === newEmployeeList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {

        return oldEmployeeList[oldItemPosition].id == newEmployeeList[newItemPosition].id && oldEmployeeList[oldItemPosition].email == newEmployeeList[newItemPosition].email
    }
}
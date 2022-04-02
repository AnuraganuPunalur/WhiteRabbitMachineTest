package com.anurag.whiterabbitmachinetest.utils

import android.content.Context
import android.net.ConnectivityManager
import android.widget.Toast

object AppUtils {

    fun isConnectedToInternet(context: Context): Boolean {

        val conMgr = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return conMgr.activeNetworkInfo != null && conMgr.activeNetworkInfo!!.isConnected
    }

    fun showToast(context: Context, messageToShow: String){

        Toast.makeText(context, messageToShow, Toast.LENGTH_LONG).show()
    }
}
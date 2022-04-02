package com.anurag.whiterabbitmachinetest.utils

import android.widget.ImageView
import com.anurag.whiterabbitmachinetest.R
import com.bumptech.glide.Glide

object HelperExtensions {

    fun ImageView.loadImage(urlToLoad: String?){

        Glide.with(this).load(urlToLoad).placeholder(resources.getDrawable(R.drawable.ic_launcher_foreground, null)).into(this)
    }

}
package com.example.paginationlibrarysampleapp.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.paginationlibrarysampleapp.R

inline fun loadImageFromUrl(imageView: ImageView,imagePath:String,context: Context)
{
    Glide.with(context).load(context.getString(R.string.image_base_url)+imagePath).into(imageView)
}
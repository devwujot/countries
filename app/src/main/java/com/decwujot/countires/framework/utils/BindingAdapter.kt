package com.decwujot.countires.framework.utils

import android.content.Context
import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.Glide.with
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestOptions
import com.decwujot.countires.R
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYouListener

@BindingAdapter("loadImage")
fun ImageView.loadUrl(url: String?) {
    url?.let { link ->
        context?.let {
            GlideToVectorYou
                .init()
                .with(context.applicationContext)
                .withListener(object : GlideToVectorYouListener {
                    override fun onLoadFailed() {
                    }

                    override fun onResourceReady() {
                    }

                })
                .setPlaceHolder(R.drawable.loading, R.drawable.ic_launcher)
                .load(Uri.parse(link), this);
        }
    }
}
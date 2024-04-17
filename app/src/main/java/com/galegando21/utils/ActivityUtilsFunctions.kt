package com.galegando21.utils
import android.content.Intent
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.galegando21.BannerFragment
import com.galegando21.R

fun setBanner(activity: FragmentActivity, bannerTextId: Int) {
    val bannerFragment = activity.supportFragmentManager.findFragmentById(R.id.bannerFragment) as BannerFragment
    activity.supportFragmentManager.beginTransaction().runOnCommit {
        bannerFragment.setBannerText(activity.getString(bannerTextId))
    }.commit()
}

fun setOnBackPressed(activity: AppCompatActivity, destinationActivityClass: Class<*>) {
    activity.onBackPressedDispatcher.addCallback(activity, object: OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            Intent(activity, destinationActivityClass).also {
                activity.startActivity(it)
            }
        }
    })
}
package com.stac.eatitdog.extensions


import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

fun Fragment.shortToast(message: String?) {
    Toast.makeText(requireContext().applicationContext, message.toString(), Toast.LENGTH_SHORT).show()
}

fun Fragment.shortToast(message: Int) {
    Toast.makeText(requireContext().applicationContext, message, Toast.LENGTH_SHORT).show()
}

fun Fragment.longToast(message: String?) {
    Toast.makeText(requireContext().applicationContext, message, Toast.LENGTH_LONG).show()
}

fun Fragment.longToast(message: Int) {
    Toast.makeText(requireContext().applicationContext, message, Toast.LENGTH_LONG).show()
}

fun Fragment.startActivity(activity: Class<*>) {
    startActivity(Intent(requireContext().applicationContext, activity).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP))
}

fun Fragment.startActivityWithFinish(activity: Class<*>) {
    startActivityWithFinish(Intent(requireContext().applicationContext, activity).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP))
}

fun Fragment.startActivityWithFinish(intent: Intent) {
    startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP))
    this.requireActivity().finish()
}

fun Fragment.startActivityWithFinishAll(activity: Class<*>) {
    val intent = Intent(context!!.applicationContext, activity)
    intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
    startActivity(intent)
    this.requireActivity().finishAffinity()
}

fun Fragment.startActivitiesWithFinish(vararg activity: Class<*>) {
    val intents: ArrayList<Intent> = ArrayList()
    for (clazz in activity) {
        val intent = Intent(context!!.applicationContext, clazz)
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        intents.add(intent)
    }
    this.requireActivity().startActivities(intents.toArray(arrayOf<Intent?>()))
    this.requireActivity().finish()
}

inline fun <reified T : ViewModel> Fragment.getViewModel(factory: ViewModelProvider.Factory): T =
    ViewModelProvider(this, factory)[T::class.java]

inline fun <reified T : ViewModel> Fragment.getViewModel(): T =
    ViewModelProvider(this)[T::class.java]

fun Fragment.openVideoFromUrl(url: String) {
    val intent = Intent(Intent.ACTION_VIEW)
    intent.data = Uri.parse(url)
    intent.setPackage("com.google.android.youtube")
    startActivity(intent)
}

fun Fragment.openUrlWithBrowser(url: String) {
    val intent = Intent(Intent.ACTION_VIEW)
    intent.data = Uri.parse(url)
    startActivity(intent)
}
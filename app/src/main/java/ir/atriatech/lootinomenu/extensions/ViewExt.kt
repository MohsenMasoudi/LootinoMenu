package ir.atriatech.babanfood.extensions

import android.graphics.Typeface
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar

import ir.atriatech.lootinomenu.R

fun View.showSnackBar(message: String, duration: Int) {
	Snackbar.make(this, message, duration).show()
}

//Snackbar
var mSnackbar: Snackbar? = null

fun View.addToCartSnackbar(message: String, actionText: String, duration: Int, onClickListener: View.OnClickListener) {
	val snackbar = Snackbar.make(this, message, duration)
			.setAction(actionText, onClickListener)

	mSnackbar = snackbar

	val mTypeface = Typeface.createFromAsset(context.assets, context.getString(R.string.IranSansBold))

	val snackText = snackbar.view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
	snackText.typeface = mTypeface

	val snackAction = snackbar.view.findViewById<TextView>(com.google.android.material.R.id.snackbar_action)
	snackAction.typeface = mTypeface
	snackAction.setTextColor(ContextCompat.getColor(context, R.color.sea_buckthorn))

	snackbar.view.setOnClickListener(onClickListener)

	snackbar.show()
}

fun View.dismissSnackbar() {
	mSnackbar?.dismiss()
	mSnackbar = null
}
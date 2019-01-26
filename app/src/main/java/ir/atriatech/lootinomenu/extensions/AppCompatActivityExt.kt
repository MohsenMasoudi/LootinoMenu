//package ir.atriatech.babanfood.extensions
//
//import android.app.Activity
//import android.content.Context
//import android.content.DialogInterface
//import android.graphics.Typeface
//import android.view.View
//import android.view.inputmethod.InputMethodManager
//import android.widget.TextView
//import android.widget.Toast
//import androidx.annotation.Nullable
//import androidx.appcompat.app.AlertDialog
//import androidx.appcompat.app.AppCompatActivity
//import androidx.constraintlayout.widget.ConstraintLayout
//import androidx.core.content.ContextCompat
//import androidx.fragment.app.Fragment
//import androidx.fragment.app.FragmentManager
//import androidx.fragment.app.FragmentTransaction
//import ir.atriatech.babanfood.R
//import ir.atriatech.babanfood.base.*
//
//
///*========================Dialog==============================*/
//fun AppCompatActivity.mRetryDialog(title: String, message: String, callback: MyDialogCallback): AlertDialog {
//	val builder = AlertDialog.Builder(this)
//	builder.setTitle(title)
//	builder.setMessage(message)
//	builder.setCancelable(false)
//	builder.setPositiveButton(R.string.tryAgain) { dialog, _ ->
//		callback.positive(dialog)
//	}
//	builder.setNegativeButton(R.string.close) { dialog, _ ->
//		callback.negative(dialog)
//	}
//
//	return builder.create()
//}
//
//fun AppCompatActivity.mUpdateDialog(message: String, callback: MyDialogCallback): AlertDialog {
//	val builder = AlertDialog.Builder(this)
//	builder.setTitle(R.string.updateTitle)
//	builder.setMessage(message)
//	builder.setCancelable(false)
//	builder.setPositiveButton(R.string.updateTitle, null)
//
//	val mDialog = builder.create()
//
//	mDialog.setOnShowListener(object : DialogInterface.OnShowListener {
//		override fun onShow(dialog: DialogInterface) {
//			mDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
//				callback.positive(dialog)
//			}
//		}
//	})
//
//	return mDialog
//}
//
//fun AppCompatActivity.mDialog(title: String, message: String, options: MyDialogOptions, callback: MyDialogCallback): AlertDialog {
//	val builder = AlertDialog.Builder(this)
//	builder.setTitle(title)
//	builder.setMessage(message)
//	builder.setCancelable(options.cancelable)
//	builder.setOnDismissListener { dialog ->
//		callback.dismiss(dialog)
//	}
//	if (options.positive != null) {
//		builder.setPositiveButton(options.positive) { dialog, _ ->
//			callback.positive(dialog)
//		}
//	}
//
//	if (options.negative != null) {
//		builder.setNegativeButton(options.negative) { dialog, _ ->
//			callback.negative(dialog)
//		}
//	}
//
//	if (options.neutral != null) {
//		builder.setNeutralButton(options.neutral) { dialog, _ ->
//			callback.negative(dialog)
//		}
//	}
//
//	val dialog = builder.create()
//
//	dialog.setOnShowListener(object : DialogInterface.OnShowListener {
//		override fun onShow(dialogInterface: DialogInterface?) {
//
//			//Colors
//			if (options.positiveColor != 0)
//				dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(options.positiveColor)
//
//			if (options.negativeColor != 0)
//				dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(options.negativeColor)
//
//			if (options.neutralColor != 0)
//				dialog.getButton(AlertDialog.BUTTON_NEUTRAL).setTextColor(options.neutralColor)
//
//			//Typefaces
//			if (options.positiveBold)
//				dialog.getButton(AlertDialog.BUTTON_POSITIVE).typeface = Typeface.createFromAsset(assets, getString(R.string.IranSansBold))
//
//			if (options.negativeBold)
//				dialog.getButton(AlertDialog.BUTTON_NEGATIVE).typeface = Typeface.createFromAsset(assets, getString(R.string.IranSansBold))
//
//			if (options.neutralBold)
//				dialog.getButton(AlertDialog.BUTTON_NEUTRAL).typeface = Typeface.createFromAsset(assets, getString(R.string.IranSansBold))
//		}
//	})
//
//	return dialog
//}
//
//fun AppCompatActivity.mDialogCustom(title: String, view: View, options: MyDialogOptions, callback: MyDialogCallback?): AlertDialog {
//	val builder = AlertDialog.Builder(this)
//	if (options.isTitle)
//		builder.setTitle(title)
//	else if (options.customTitleId != -1) {
//		view.findViewById<TextView>(options.customTitleId).text = title
//	}
//	builder.setView(view)
//	builder.setCancelable(options.cancelable)
//	builder.setOnDismissListener { dialog ->
//		callback?.dismiss(dialog)
//	}
//	if (options.positive != null) {
//		builder.setPositiveButton(options.positive, null)
//	}
//
//	if (options.negative != null) {
//		builder.setNegativeButton(options.negative) { dialog, _ ->
//			callback?.negative(dialog)
//		}
//	}
//
//	if (options.neutral != null) {
//		builder.setNeutralButton(options.neutral) { dialog, _ ->
//			callback?.negative(dialog)
//		}
//	}
//
//	val dialog = builder.create()
//
//	dialog.setOnShowListener(object : DialogInterface.OnShowListener {
//		override fun onShow(dialogInterface: DialogInterface) {
//
//			if (options.customCloseId != -1) {
//				view.findViewById<View>(options.customCloseId).setOnClickListener { dialogInterface.dismiss() }
//			}
//
//			if (options.positive != null)
//				dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener { callback?.positive(dialogInterface) }
//
//			if (options.positive != null && options.positiveColor != 0)
//				dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(options.positiveColor)
//
//			if (options.negativeColor != 0)
//				dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(options.negativeColor)
//
//			if (options.neutralColor != 0)
//				dialog.getButton(AlertDialog.BUTTON_NEUTRAL).setTextColor(options.neutralColor)
//
//			//Typefaces
//			if (options.positive != null && options.positiveBold)
//				dialog.getButton(AlertDialog.BUTTON_POSITIVE).typeface = Typeface.createFromAsset(assets, getString(R.string.IranSansBold))
//
//			if (options.negativeBold)
//				dialog.getButton(AlertDialog.BUTTON_NEGATIVE).typeface = Typeface.createFromAsset(assets, getString(R.string.IranSansBold))
//
//			if (options.neutralBold)
//				dialog.getButton(AlertDialog.BUTTON_NEUTRAL).typeface = Typeface.createFromAsset(assets, getString(R.string.IranSansBold))
//		}
//	})
//
//	return dialog
//}
//
///*========================Toast==============================*/
//var toast: Toast? = null
//
///**
// * Show toast
// *
// * @param message String
// * @param delay Toast.LENGTH_SHORT or Toast.LENGTH_LONG
// * @param state TOAST_STATE_ERROR or TOAST_STATE_SUCCESS or TOAST_STATE_WARNING
// */
//fun AppCompatActivity.mToast(message: String, delay: Int, state: Int) {
//	toast?.cancel()
//	toast = null
//
//	val view = layoutInflater.inflate(R.layout.template_toast, findViewById(R.id.clToast))
//	val textColor: Int
//	val bgColor: Int
//
//	when (state) {
//		TOAST_STATE_ERROR//Error
//		-> {
//			textColor = ContextCompat.getColor(this, R.color.txtToastError)
//			bgColor = ContextCompat.getColor(this, R.color.bgToastError)
//		}
//
//		TOAST_STATE_SUCCESS//Success
//		-> {
//			textColor = ContextCompat.getColor(this, R.color.txtToastSuccess)
//			bgColor = ContextCompat.getColor(this, R.color.bgToastSuccess)
//		}
//
//		TOAST_STATE_WARNING//Warning
//		-> {
//			textColor = ContextCompat.getColor(this, R.color.bgToastWarning)
//			bgColor = ContextCompat.getColor(this, R.color.txtToastWarning)
//		}
//
//		else//Default
//		-> {
//			textColor = ContextCompat.getColor(this, R.color.bgToastDefault)
//			bgColor = ContextCompat.getColor(this, R.color.txtToastDefault)
//		}
//	}
//
//	val txtToast = view.findViewById<TextView>(R.id.txtToast)
//	val clToast = view.findViewById<ConstraintLayout>(R.id.clToast)
//
//	txtToast.setTextColor(textColor)
//	txtToast.text = message
//
//	clToast.setBackgroundColor(bgColor)
//
//
//	toast = Toast(this)
//	toast?.duration = delay
//	toast?.view = view
//	toast?.show()
//}
//
//fun AppCompatActivity.cancelToast() {
//	toast?.cancel()
//}
//
///*========================Fragment==============================*/
//fun AppCompatActivity.addFragment(frameId: Int, @Nullable source: Fragment?, destination: Fragment, tag: String, isAnim: Boolean, removeSource: Boolean) {
//	supportFragmentManager.transact {
//		if (isAnim) {
//			setCustomAnimations(R.anim.in_from_right, R.anim.out_to_left)
//		}
//		add(frameId, destination, tag)
//		if (source != null)
//			if (removeSource)
//				remove(source)
//			else
//				hide(source)
//	}
//}
//
//fun AppCompatActivity.showHideFragment(source: Fragment, destination: Fragment, isAnim: Boolean) {
//	supportFragmentManager.transact {
//		if (isAnim) {
//			setCustomAnimations(R.anim.in_from_right, R.anim.out_to_left)
//		}
//		hide(source)
//		show(destination)
//	}
//}
//
//fun AppCompatActivity.removeFragment(source: Fragment, destination: Fragment, isAnim: Boolean) {
//	supportFragmentManager.transact {
//		if (isAnim) {
//			setCustomAnimations(R.anim.in_from_left, R.anim.out_to_right)
//		}
//		remove(source)
//		show(destination)
//	}
//}
//
//private inline fun FragmentManager.transact(action: FragmentTransaction.() -> Unit) {
//	beginTransaction().apply {
//		action()
//	}.commit()
//}
//
///*========================Keyboard==============================*/
//fun AppCompatActivity.closeKeyboard() {
//	val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
//	if (inputMethodManager.isAcceptingText) {
//		if (currentFocus != null) {
//			inputMethodManager.hideSoftInputFromWindow(currentFocus!!.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
//		}
//	}
//}
//
//fun AppCompatActivity.openkeyboard(view: View) {
//	val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//	inputMethodManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
//}

package ir.atriatech.lootinomenu.management.add_or_edit_food


import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteConstraintException
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.himanshurawat.imageworker.Extension
import com.himanshurawat.imageworker.ImageWorker
import com.squareup.picasso.Picasso
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import es.dmoral.toasty.Toasty
import ir.atriatech.lootinomenu.*
import ir.atriatech.lootinomenu.management.ManagementActivity
import ir.atriatech.lootinomenu.management.ManagementActivityCallBack
import ir.atriatech.lootinomenu.management.choose_sub_menu.ChooseSubMenuFragment
import ir.atriatech.lootinomenu.management.management_food_list.ManagementFoodListFragment
import ir.atriatech.lootinomenu.model.Food
import kotlinx.android.synthetic.main.fragment_add_or_edit_food.*
import java.io.ByteArrayOutputStream


class AddOrEditFoodFragment : Fragment() {
	lateinit var food: Food
	var foodForChangCheck: Food = Food()
	var foodId = 0
	var subMenuId = 0
	var menuId = 0
	lateinit var uri: Uri

	companion object {
		fun newInstance(foodId: Int, subMenuId: Int, menuId: Int): AddOrEditFoodFragment {
			val addOrEditFoodFragment = AddOrEditFoodFragment()
			val arg = Bundle()
			arg.putInt(ARG_FOOD_ID_ADD_OR_EDIT_FOOD_FRAGMENT, foodId)
			arg.putInt(ARG_SUB_MENU_ID_ADD_OR_EDIT_FOOD_FRAGMENT, subMenuId)
			arg.putInt(ARG_MENU_ID_ADD_OR_EDIT_FOOD_FRAGMENT, menuId)
			addOrEditFoodFragment.arguments = arg
			return addOrEditFoodFragment
		}

		fun newInstance(subMenuId: Int, menuId: Int): AddOrEditFoodFragment {
			val addOrEditFoodFragment = AddOrEditFoodFragment()
			val arg = Bundle()
			arg.putInt(ARG_SUB_MENU_ID_ADD_OR_EDIT_FOOD_FRAGMENT, subMenuId)
			arg.putInt(ARG_MENU_ID_ADD_OR_EDIT_FOOD_FRAGMENT, menuId)

			addOrEditFoodFragment.arguments = arg
			return addOrEditFoodFragment
		}

		var foodCompanion: Food = Food()

	}

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_add_or_edit_food, container, false)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		if (foodId != 0 && menuId != 0) {
			val context = view.context as ManagementActivity
			food = context.appDataBase.foodDao().findById(foodId)

			edit_text_name.setText(food.productName)
			edit_text_price.setText(food.price.toString())
			edit_text_detail.setText(food.productDetail)
			txt_add_or_edit_title.text = "ویرایش  " + food.productName
			loadPhoto(activity as ManagementActivity)


		} else {
			food = Food()
			food.menuId = menuId
			food.subMenuId = subMenuId

		}
		setFoodToCheckChang()
		setChanges()
		if (subMenuId != 0) {
			text_view_choose_sub_menu_add_or_remove.hint = getSubMenuText(food.subMenuId)
		} else {
			text_view_choose_sub_menu_add_or_remove.hint = "دسته بندی نشده"
		}
		img_uploadArea.setOnClickListener {
			CropImage.activity()
				.setGuidelines(CropImageView.Guidelines.ON)
				.start(view.context, this)
		}
		img_btn_back_btn.setOnClickListener {
			val callback: ManagementActivityCallBack = context as ManagementActivity
			callback.ManagmentFragmentLoader(ManagementFoodListFragment.newInstance(subMenuId))

		}
		btn_save.setOnClickListener {
			try {
				food.productName = edit_text_name.text.toString()
				food.productDetail = edit_text_detail.text.toString()
				food.price = Integer.parseInt(edit_text_price.text.toString())
			} catch (e: Exception) {
			}
			Log.d("tag25", food.price.toString())


			if (checkNothingBeEmpty()) {

				if (::uri.isInitialized) {
					savePhoto(view.context)
				}
				if (isfoodChanged()) {
					updateOrInsert(food)
					AddOrEditFoodFragment.foodCompanion = Food()
					Toasty.success(
						this@AddOrEditFoodFragment.context!!,
						"تغییرات با موفیت انجام شد",
						Toast.LENGTH_SHORT, true
					).show()
					val callback: ManagementActivityCallBack = context as ManagementActivity
					callback.ManagmentFragmentLoader(
						ManagementFoodListFragment.newInstance(
							subMenuId
						)
					)
				} else if (!isfoodChanged()) {
					Toasty.info(
						this@AddOrEditFoodFragment.context!!,
						"تغییری انجام نشد",
						Toast.LENGTH_SHORT, true
					).show()
					val callback: ManagementActivityCallBack = context as ManagementActivity
					callback.ManagmentFragmentLoader(
						ManagementFoodListFragment.newInstance(
							subMenuId
						)
					)
				}
			} else {
				Toasty.error(
					this@AddOrEditFoodFragment.context!!,
					"تمام فیلد ها را پر کنید",
					Toast.LENGTH_SHORT, true
				).show()
			}

		}
		text_view_choose_sub_menu_add_or_remove.setOnClickListener {
			keepChanges()
			(activity as ManagementActivityCallBack).ManagmentFragmentLoader(
				ChooseSubMenuFragment.newInstance(
					foodId
				)
			)
		}
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		try {
			menuId = arguments?.getInt(ARG_MENU_ID_ADD_OR_EDIT_FOOD_FRAGMENT)!!
			subMenuId = arguments?.getInt(ARG_SUB_MENU_ID_ADD_OR_EDIT_FOOD_FRAGMENT)!!
			foodId = arguments?.getInt(ARG_FOOD_ID_ADD_OR_EDIT_FOOD_FRAGMENT)!!
		} catch (e: Exception) {
		}

	}


	override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
		if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
			val result = CropImage.getActivityResult(data)
			if (resultCode == -1) {
				val resultUri = result.uri
				uri = resultUri
				setImageView(this@AddOrEditFoodFragment.context!!)
			} else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
				val error = result.error
			}
		}
	}

	private fun setImageView(context: Context) {
		food.picPath = uri
		if (food.picPath == null) {
			val frescoFilPath = IMAGE_ASSETS_ADDRESS_BIG + food.id + ".jpg"
			uploadIcon.setImageURI(frescoFilPath)
		} else {
			uploadIcon.setImageURI(uri, context)
		}
		uploadText.visibility = View.INVISIBLE
		previewImage.visibility = View.INVISIBLE

	}

	private fun savePhoto(context: Context) {
		val bitmap: Bitmap = MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
		ImageWorker.to(context).directory(IMAGE_DIRECTORY).subDirectory(IMAGE_SUB_DIRECTORY)
			.setFileName(foodId.toString()).withExtension(Extension.JPEG).save(bitmap)
		food.picPath = getImageUri(context, bitmap)
		keepChanges()
	}

	private fun loadPhoto(context: ManagementActivity) {
		if (food.picPath == null) {
			val frescoFilPath = IMAGE_ASSETS_ADDRESS_BIG + food.id + ".jpg"
			uploadIcon.setImageURI(frescoFilPath)
		} else if (::uri.isInitialized) {
			uploadIcon.setImageURI(uri, context)
		} else {
			uploadIcon.setImageURI(food.picPath, context)

		}
		uploadText.visibility = View.INVISIBLE
	}

	private fun getImageUri(inContext: Context, inImage: Bitmap): Uri {
		val bytes = ByteArrayOutputStream()
		inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
		val path =
			MediaStore.Images.Media.insertImage(inContext.contentResolver, inImage, "Title", null)
		return Uri.parse(path)
	}

	private fun checkNothingBeEmpty(): Boolean {
		if (edit_text_name.text.toString() != "") {
			if (edit_text_price.text.toString() != "") {
				if (edit_text_detail.text.toString() != "") {
					if (subMenuId != 0) {
						if (menuId != 0) {
							return true

						}
					}
				}
			}
		}
		return false

	}

	private fun updateOrInsert(food: Food) {
		try {
		food.productName = edit_text_name.text.toString()
		food.productDetail = edit_text_detail.text.toString()
		food.price = Integer.parseInt(edit_text_price.text.toString())
		} catch (e: Exception) {
		}
		try {
			(activity as ManagementActivity).appDataBase.foodDao().insert(food)
		} catch (exception: SQLiteConstraintException) {
			(activity as ManagementActivity).appDataBase.foodDao().update(food)
		}

	}

	private fun getSubMenuText(subMenuId: Int): String {
		return (activity as ManagementActivity).appDataBase.subMenuDao().findById(subMenuId).name
	}

	private fun keepChanges() {
		if (edit_text_name.text.toString() != "") {
			foodCompanion.productName = edit_text_name.text.toString()
		}
		if (edit_text_detail.text.toString() != "") {
			foodCompanion.productDetail = edit_text_detail.text.toString()
		}
		if (edit_text_price.text.toString() != "") {
			foodCompanion.price = Integer.parseInt(edit_text_price.text.toString())
		}
		if (::food.isInitialized) {
			if (food.picPath != null) {
				foodCompanion.picPath = food.picPath

			}
		}

	}

	private fun setChanges() {
		if (foodCompanion.productName != "") {
			edit_text_name.setText(foodCompanion.productName)
		}
		if (foodCompanion.productDetail != "") {
			edit_text_detail.setText(foodCompanion.productDetail)
		}
		if (foodCompanion.price != 0) {
			edit_text_price.setText(foodCompanion.price.toString())
		}
		if (foodCompanion.picPath != null) {
			Picasso.get().load(foodCompanion.picPath).into(uploadIcon)
			uploadText.visibility = View.INVISIBLE

		}

	}


	fun setFoodToCheckChang() {
		foodForChangCheck.productName = food.productName
		foodForChangCheck.productDetail = food.productDetail
		foodForChangCheck.price = food.price
		foodForChangCheck.menuId = food.menuId
		foodForChangCheck.subMenuId = food.subMenuId
		foodForChangCheck.picPath = food.picPath
		foodForChangCheck.foodOrder = food.foodOrder
	}

	fun isfoodChanged(): Boolean {
		if (foodForChangCheck.productName == food.productName) {
			if (foodForChangCheck.productDetail == food.productDetail) {
				if (foodForChangCheck.price == food.price) {
					if (foodForChangCheck.menuId == food.menuId) {
						if (foodForChangCheck.picPath == food.picPath) {
							if (foodForChangCheck.foodOrder == food.foodOrder){
								if(foodForChangCheck.subMenuId == food.subMenuId){
									return false

								}
							}
						}
					}
				}
			}
		}
		return true


	}

}

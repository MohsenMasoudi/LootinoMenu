package ir.atriatech.lootinomenu.management.add_or_edit_food


import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteConstraintException
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.himanshurawat.imageworker.Extension
import com.himanshurawat.imageworker.ImageWorker
import com.squareup.picasso.Picasso
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import ir.atriatech.lootinomenu.ARG_FOOD_ID_ADD_OR_EDIT_FOOD_FRAGMENT
import ir.atriatech.lootinomenu.ARG_MENU_ID_ADD_OR_EDIT_FOOD_FRAGMENT
import ir.atriatech.lootinomenu.ARG_SUB_MENU_ID_ADD_OR_EDIT_FOOD_FRAGMENT
import ir.atriatech.lootinomenu.R
import ir.atriatech.lootinomenu.management.ManagementActivity
import ir.atriatech.lootinomenu.management.ManagementActivityCallBack
import ir.atriatech.lootinomenu.management.management_food_list.ManagementFoodListFragment
import ir.atriatech.lootinomenu.model.Food
import kotlinx.android.synthetic.main.fragment_add_or_edit_food.*
import java.io.ByteArrayOutputStream


class AddOrEditFoodFragment : Fragment() {
	lateinit var food: Food

	var isPermisionGranted: Boolean = false
	var foodId = 0
	var subMenuId = 1
	var menuId = 1
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

//		private val EXTERNAL_STORAGE = Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE

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
		spinner.visibility=View.INVISIBLE
		if (foodId != 0) {
			val context = view.context as ManagementActivity
			food = context.appDataBase.foodDao().findById(foodId)
			edit_text_name.setText(food.productName)
			edit_text_price.setText(food.price.toString())
			edit_text_detail.setText(food.productDetail)
			txt_add_or_edit_title.text = "ویرایش  " + food.productName


			loadPhoto()


		} else {
			food = Food()
			food.menuId = menuId
			food.subMenuId = subMenuId
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
		setUpSpinner(view.context)
		btn_save.setOnClickListener {
			if (checkNothingBeEmpty()) {
				if (::uri.isInitialized) {
					savePhoto(view.context)
				}

				updateOrInsert(food)

				Toast.makeText(
					this@AddOrEditFoodFragment.context,
					"تغییرات با موفیت انجام شد",
					Toast.LENGTH_SHORT
				).show()
				val callback: ManagementActivityCallBack = context as ManagementActivity
				callback.ManagmentFragmentLoader(ManagementFoodListFragment.newInstance(subMenuId))
			} else {
				Toast.makeText(
					this@AddOrEditFoodFragment.context,
					"تمام فیلد ها را پر کنید",
					Toast.LENGTH_SHORT
				).show()
			}

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

	private fun setUpSpinner(context: Context) {
		val subMenuListString: MutableList<String> = mutableListOf()
		val subMenuList =
			(context as ManagementActivity).appDataBase.subMenuDao().getAllWithMenuId(menuId)
		for (i in subMenuList.indices) {
			subMenuListString.add(subMenuList[i].name)
		}
		val dataAdapter = ArrayAdapter<String>(
			context,
			android.R.layout.simple_spinner_item, subMenuListString
		)
		dataAdapter.setDropDownViewResource(ir.atriatech.lootinomenu.R.layout.spiner_text_template)
		spinner.adapter = dataAdapter

	}

	override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
		if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
			val result = CropImage.getActivityResult(data)
			if (resultCode == -1) {
				val resultUri = result.uri
				uri = resultUri
				setImageView()
			} else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
				val error = result.error
			}
		}
	}

	private fun setImageView() {
		uploadIcon.setImageURI(uri)
		uploadText.visibility = View.INVISIBLE
		previewImage.visibility = View.INVISIBLE

	}

	private fun savePhoto(context: Context) {
		val bitmap: Bitmap = MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
		ImageWorker.to(context).directory("LotinoApp").subDirectory("Pictures")
			.setFileName(foodId.toString()).withExtension(Extension.PNG).save(bitmap)
		food.picPath = getImageUri(context, bitmap)
	}

	private fun loadPhoto() {
		Picasso.get().load(food.picPath).into(uploadIcon)
		uploadText.visibility = View.INVISIBLE
	}

	fun getImageUri(inContext: Context, inImage: Bitmap): Uri {
		val bytes = ByteArrayOutputStream()
		inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
		val path =
			MediaStore.Images.Media.insertImage(inContext.contentResolver, inImage, "Title", null)
		return Uri.parse(path)
	}

	fun checkNothingBeEmpty(): Boolean {
		if (!edit_text_name.text.toString().equals("")) {
			if (!edit_text_price.text.toString().equals("")) {
				if (!edit_text_detail.text.toString().equals("")) {
					return true
				}
			}
		}
		return false

	}

	private fun updateOrInsert(food: Food) {
		try {
			food.productName=edit_text_name.text.toString()
			food.productDetail=edit_text_detail.text.toString()
			food.price=Integer.parseInt(edit_text_price.text.toString())
		} catch (e: Exception) {
		}
		try {
			(activity as ManagementActivity).appDataBase.foodDao().insert(food)
		} catch (exception: SQLiteConstraintException) {
			(activity as ManagementActivity).appDataBase.foodDao().update(food)
		}

	}
}

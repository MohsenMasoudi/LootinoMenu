package ir.atriatech.lootinomenu.management.add_or_edit_food


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import ir.atriatech.lootinomenu.ARG_FOOD_ID_ADD_OR_EDIT_FOOD_FRAGMENT
import ir.atriatech.lootinomenu.ARG_MENU_ID_ADD_OR_EDIT_FOOD_FRAGMENT
import ir.atriatech.lootinomenu.ARG_SUB_MENU_ID_ADD_OR_EDIT_FOOD_FRAGMENT
import ir.atriatech.lootinomenu.R
import ir.atriatech.lootinomenu.management.ManagementActivity
import ir.atriatech.lootinomenu.management.ManagementActivityCallBack
import ir.atriatech.lootinomenu.management.sub_menu_food_list.SubMenuFoodListFragment
import kotlinx.android.synthetic.main.fragment_add_or_edit_food.*

class AddOrEditFoodFragment : Fragment() {
	var foodId = 0
	var subMenuId = 1
	var menuId = 1

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
		if (foodId != 0) {
			val context = view.context as ManagementActivity
			val food = context.appDataBase.foodDao().findById(foodId)
			edit_text_name.setText(food.productName)
			edit_text_price.setText(food.price.toString() + " تومان")
			edit_text_detail.setText(food.productDetail)
			txt_add_or_edit_title.text = "ویرایش  " + food.productName

		}
		img_btn_back_btn.setOnClickListener {
			val callback: ManagementActivityCallBack = context as ManagementActivity
			callback.ManagmentFragmentLoader(SubMenuFoodListFragment.newInstance(subMenuId))

		}
		setUpSpinner(view.context)
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

	fun setUpSpinner(context: Context) {
		var subMenuListString: MutableList<String> = mutableListOf()
		val subMenuList =
			(context as ManagementActivity).appDataBase.subMenuDao().getAllWithMenuId(menuId)
		for (i in subMenuList.indices) {
			subMenuListString.add(subMenuList[i].name)
		}
		var dataAdapter = ArrayAdapter<String>(
			context,
			android.R.layout.simple_spinner_item, subMenuListString
		)
		dataAdapter.setDropDownViewResource(R.layout.spiner_text_template)
		spinner.setAdapter(dataAdapter)

	}


}

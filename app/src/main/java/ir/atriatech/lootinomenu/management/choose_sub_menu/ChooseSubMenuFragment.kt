package ir.atriatech.lootinomenu.management.choose_sub_menu


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import ir.atriatech.lootinomenu.ARG_CHOOSE_SUB_MENU_FRAGMENT
import ir.atriatech.lootinomenu.R
import ir.atriatech.lootinomenu.management.ManagementActivity
import ir.atriatech.lootinomenu.management.ManagementActivityCallBack
import ir.atriatech.lootinomenu.management.add_or_edit_food.AddOrEditFoodFragment
import ir.atriatech.lootinomenu.management.callBackChoosSubMenu
import ir.atriatech.lootinomenu.model.SubMenu
import kotlinx.android.synthetic.main.fragment_choose_sub_menu.*

class ChooseSubMenuFragment : Fragment(), callBackChooseSubMenuFragment {

	override fun setSubMenu(subMenuId: Int, menuId: Int) {
		val callBack: ManagementActivityCallBack = activity as ManagementActivityCallBack
		if (foodId != 0) {
			val food = (activity as ManagementActivity).appDataBase.foodDao().findById(foodId)
			if (food.subMenuId!=subMenuId){
				isSubMenuChanged=true
			}
			food.subMenuId = subMenuId
			food.menuId = menuId
			(activity as ManagementActivity).appDataBase.foodDao().update(food)
			callBack.ManagmentFragmentLoader(
				AddOrEditFoodFragment.newInstance(
					foodId,
					subMenuId,
					menuId
				)
			)
		} else {
			callBack.ManagmentFragmentLoader(
				AddOrEditFoodFragment.newInstance(
					subMenuId = subMenuId,
					menuId = menuId
				)
			)
		}
	}

	var foodId: Int = 0
	lateinit var list: MutableList<SubMenu>

	companion object {
		var isSubMenuChanged:Boolean=false

		fun newInstance(foodId: Int): ChooseSubMenuFragment {
			val chooseSubMenuFragment = ChooseSubMenuFragment()
			val arg = Bundle()
			arg.putInt(ARG_CHOOSE_SUB_MENU_FRAGMENT, foodId)
			chooseSubMenuFragment.arguments = arg
			return chooseSubMenuFragment

		}
	}

	lateinit var adapter: ChooseSubMenuAdapter
	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_choose_sub_menu, container, false)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		list = getSubMenuList()
		adapter = ChooseSubMenuAdapter(list,this)
		recycler_view_choose_sub_menu.layoutManager = LinearLayoutManager(view.context)
		recycler_view_choose_sub_menu.adapter = adapter
		img_btn_back_choose_subMenu.setOnClickListener {
			(activity as callBackChoosSubMenu).loadLastFragment()
		}

	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		isSubMenuChanged=false
		val arg = arguments
		try {
			foodId = arg?.getInt(ARG_CHOOSE_SUB_MENU_FRAGMENT, 0)!!
		} catch (e: Exception) {
		}

	}

	private fun getSubMenuList(): MutableList<SubMenu> {
		return (activity as ManagementActivity).appDataBase.subMenuDao().getAll()
	}
}

interface callBackChooseSubMenuFragment {
	fun setSubMenu(subMenuId: Int, menuId: Int)
}
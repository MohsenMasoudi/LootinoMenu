package ir.atriatech.lootinomenu.management.management_food_list


import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import ir.atriatech.lootinomenu.ARG_MANAGEMENT_FOOD_LIST_FRAGMENT
import ir.atriatech.lootinomenu.R
import ir.atriatech.lootinomenu.management.ManagementActivity
import ir.atriatech.lootinomenu.management.ManagementActivityCallBack
import ir.atriatech.lootinomenu.management.add_or_edit_food.AddOrEditFoodFragment
import ir.atriatech.lootinomenu.management.management_panel.ManagementPanelFragment
import ir.atriatech.lootinomenu.management.sub_menu.SubMenuFragment
import ir.atriatech.lootinomenu.model.Food
import ir.atriatech.lootinomenu.model.SubMenu
import kotlinx.android.synthetic.main.fragment_management_food_list.*


class ManagementFoodListFragment : Fragment() {
	lateinit var foodlist: MutableList<Food>
	lateinit var subMenu: SubMenu
	private var subMenuId: Int? = null
	lateinit var adapter: ManagementFoodListAdapter

	companion object {
		fun newInstance(subMenuId: Int): ManagementFoodListFragment {
			val managementFoodListFragment = ManagementFoodListFragment()
			val arg = Bundle()
			arg.putInt(ARG_MANAGEMENT_FOOD_LIST_FRAGMENT, subMenuId)
			managementFoodListFragment.arguments = arg
			return managementFoodListFragment

		}
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		val arg = arguments
		subMenuId = arg?.getInt(ARG_MANAGEMENT_FOOD_LIST_FRAGMENT, 0)
		getListOfFood(subMenuId!!)

	}

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_management_food_list, container, false)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		hideAddNewFood()
		if (::foodlist.isLateinit && foodlist.size > 0) {
			empty_layout.visibility = View.GONE
			setRecyclerView(view.context)
		} else {
			recycler_view_food_list_for_sub_menu_fragment.visibility = View.GONE
			txt_nothing_found.text = getString(R.string.nothing_found)
		}

		val callback: ManagementActivityCallBack = context as ManagementActivity
		img_btn_back_sub_menu_food_list.setOnClickListener {
			if (subMenuId == 0) {
				callback.ManagmentFragmentLoader(ManagementPanelFragment())
			} else {
				callback.ManagmentFragmentLoader(SubMenuFragment.newInstance(subMenu.menuId))

			}
		}
		txt_toolbar_title.text = "محصولات " + subMenu.name
		img_btn_add_new_food.setOnClickListener {

			callback.ManagmentFragmentLoader(
				AddOrEditFoodFragment.newInstance(
					subMenuId = subMenu.subMenuId, menuId = subMenu.menuId
				)
			)
		}
		btn_add_new_to_empty.setOnClickListener {
			callback.ManagmentFragmentLoader(
				AddOrEditFoodFragment.newInstance(
					subMenuId = subMenu.subMenuId, menuId = subMenu.menuId
				)
			)
		}



	}

	@SuppressLint("SetTextI18n")
	override fun onResume() {
		super.onResume()

	}

	private fun getListOfFood(submenuId: Int) {

		foodlist =
			(activity as ManagementActivity).appDataBase.foodDao().findBySubId(submenuId)
		subMenu = (activity as ManagementActivity).appDataBase.subMenuDao().findById(submenuId)
		if (!::subMenu.isInitialized) {
			subMenu = SubMenu()
			subMenu.name = "دسته بندی نشده"
			subMenu.order = 500
			subMenu.menuId = 0
			subMenu.subMenuId = 0
		}

	}

	private fun setRecyclerView(context: Context) {
		adapter = ManagementFoodListAdapter()
		adapter.foodList = foodlist
		recycler_view_food_list_for_sub_menu_fragment.layoutManager = LinearLayoutManager(context)
		recycler_view_food_list_for_sub_menu_fragment.adapter = adapter
	}

	fun hideAddNewFood() {
		if (subMenuId == 0) {
			img_btn_add_new_food.visibility = View.INVISIBLE
			btn_add_new_to_empty.visibility = View.INVISIBLE
		}
	}


}

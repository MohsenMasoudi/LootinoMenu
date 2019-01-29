package ir.atriatech.lootinomenu.management.sub_menu_food_list


import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import ir.atriatech.lootinomenu.ARG_SUB_MENU_FOOD_LIST_FRAGMENT
import ir.atriatech.lootinomenu.R
import ir.atriatech.lootinomenu.management.ManagementActivity
import ir.atriatech.lootinomenu.management.ManagementActivityCallBack
import ir.atriatech.lootinomenu.management.sub_menu.SubMenuFragment
import ir.atriatech.lootinomenu.model.Food
import ir.atriatech.lootinomenu.model.SubMenu
import kotlinx.android.synthetic.main.fragment_sub_menu_food_list.*


class SubMenuFoodListFragment : Fragment() {
	lateinit var foodlist: MutableList<Food>
	lateinit var subMenu: SubMenu
	private var subMenuId: Int? = null
	lateinit var adapter: SubMenuFoodListAdapter

	companion object {
		fun newInstance(subMenuId: Int): SubMenuFoodListFragment {
			val subMenuFoodListFragment = SubMenuFoodListFragment()
			val arg = Bundle()
			arg.putInt(ARG_SUB_MENU_FOOD_LIST_FRAGMENT, subMenuId)
			subMenuFoodListFragment.arguments = arg
			return subMenuFoodListFragment

		}
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		val arg = arguments
		subMenuId = arg?.getInt(ARG_SUB_MENU_FOOD_LIST_FRAGMENT, 0)
		getListOfFood(subMenuId!!)

	}

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_sub_menu_food_list, container, false)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		if (::foodlist.isLateinit && foodlist.size > 0) {
			empty_layout.visibility = View.GONE
			setRecyclerView(view.context)
		} else {
			recycler_view_food_list_for_sub_menu_fragment.visibility = View.GONE
			txt_nothing_found.text = getString(R.string.nothing_found)
		}

		val callback: ManagementActivityCallBack = context as ManagementActivity
		img_btn_back_sub_menu_food_list.setOnClickListener {
			callback.ManagmentFragmentLoader(SubMenuFragment.newInstance(subMenu.menuId))
		}
		txt_toolbar_title.text = "محصولات دسته بندی " + subMenu.name


	}

	@SuppressLint("SetTextI18n")
	override fun onResume() {
		super.onResume()

	}

	private fun getListOfFood(submenuId: Int) {

		foodlist =
			(activity as ManagementActivity).appDataBase.foodDao().findBySubId(submenuId)
		subMenu = (activity as ManagementActivity).appDataBase.subMenuDao().findById(submenuId)
		if (!::subMenu.isInitialized){
			subMenu = SubMenu()
			subMenu.name="دسته بندی نشده"
			subMenu.order=20
			subMenu.menuId=0
			subMenu.subMenuId=0
		}

	}

	fun setRecyclerView(context: Context) {
		adapter = SubMenuFoodListAdapter()
		adapter.foodList = foodlist
		recycler_view_food_list_for_sub_menu_fragment.layoutManager = LinearLayoutManager(context)
		recycler_view_food_list_for_sub_menu_fragment.adapter = adapter
	}
}

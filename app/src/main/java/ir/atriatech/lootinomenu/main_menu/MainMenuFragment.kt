package ir.atriatech.lootinomenu.main_menu


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.atriatech.lootinomenu.ARG_SUB_MAIN_MANU_LIST_MULTIPLE_VIEW_FRAGMENT
import ir.atriatech.lootinomenu.R
import ir.atriatech.lootinomenu.TYPE_FOOD
import ir.atriatech.lootinomenu.main_menu.main_menu_two_view_holder.MainMenuAdapter
import ir.atriatech.lootinomenu.model.Food
import ir.atriatech.lootinomenu.model.SubMenu


class MainMenuFragment : Fragment() {
	private lateinit var subMenuList: MutableList<SubMenu>
	lateinit var adapter: MainMenuAdapter
	private lateinit var items: MutableList<Any>

	companion object {
		fun newInstance(menuId: Int): MainMenuFragment {
			var mainMenuWIthMultipleViewFragment =
				MainMenuFragment()
			val arg = Bundle()
			arg.putInt(ARG_SUB_MAIN_MANU_LIST_MULTIPLE_VIEW_FRAGMENT, menuId)
			mainMenuWIthMultipleViewFragment.arguments = arg
			return mainMenuWIthMultipleViewFragment
		}
	}

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		return inflater.inflate(
			ir.atriatech.lootinomenu.R.layout.fragment_main_menu,
			container,
			false
		)
	}

	private fun getListOfSubMenus(listId: Int) {
		Log.d("tag22", listId.toString())

		subMenuList =
			(activity as MainMenuActivity).appDataBase.subMenuDao().getAllWithMenuId(listId)

		Log.d("tag22", subMenuList.size.toString())
	}

	private fun getListofFood(subMenuId: Int): MutableList<Food> {
		return (activity as MainMenuActivity).appDataBase.foodDao().findBySubId(subMenuId)

	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		val recyclerView =
			view.findViewById(R.id.recycler_view_fragment_main_menu_with_multiple_view) as RecyclerView
		recyclerView.recycledViewPool.setMaxRecycledViews(TYPE_FOOD, 50)
		items =  mutableListOf()

		for (i in subMenuList.indices) {
			items.add(subMenuList[i])
			items.addAll(getListofFood(subMenuList[i].subMenuId))
		}
		adapter.twoViewList = items
		recyclerView.adapter = adapter
		recyclerView.layoutManager = LinearLayoutManager(activity)
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		adapter= MainMenuAdapter()
		val args = arguments
		val menuId: Int = args?.getInt(ARG_SUB_MAIN_MANU_LIST_MULTIPLE_VIEW_FRAGMENT) ?: 1
		getListOfSubMenus(menuId)

	}
}

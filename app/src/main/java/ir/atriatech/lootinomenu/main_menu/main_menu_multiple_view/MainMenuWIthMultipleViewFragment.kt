package ir.atriatech.lootinomenu.main_menu.main_menu_multiple_view


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
import ir.atriatech.lootinomenu.main_menu.MainMenuActivity
import ir.atriatech.lootinomenu.model.Food
import ir.atriatech.lootinomenu.model.SubMenu
import me.drakeet.multitype.Items
import me.drakeet.multitype.MultiTypeAdapter


class MainMenuWIthMultipleViewFragment : Fragment() {
	lateinit var subMenulist: MutableList<SubMenu>
	lateinit var adapter: MultiTypeAdapter
	lateinit var items: Items

	companion object {
		fun newInstance(menuId: Int): MainMenuWIthMultipleViewFragment {
			var mainMenuWIthMultipleViewFragment = MainMenuWIthMultipleViewFragment()
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
		// Inflate the layout for this fragment
		return inflater.inflate(
			ir.atriatech.lootinomenu.R.layout.fragment_main_menu_with_multiple_view,
			container,
			false
		)
	}

	fun getListOfSubMenus(listId: Int) {
		Log.d("tag22", listId.toString())

		subMenulist =
			(activity as MainMenuActivity).appDataBase.subMenuDao().getAllWithMenuId(listId)

		Log.d("tag22", subMenulist.size.toString())
	}

	fun getListofFood(subMenuId: Int): MutableList<Food> {
		return (activity as MainMenuActivity).appDataBase.foodDao().findBySubId(subMenuId)

	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		val recyclerView =
			view.findViewById(R.id.recycler_view_fragment_main_menu_with_multiple_view) as RecyclerView

		adapter.register(Food::class.java, FoodViewBinder())
		adapter.register(SubMenu::class.java, SubMenuViewBinder())
		recyclerView.adapter = adapter
		recyclerView.layoutManager = LinearLayoutManager(activity)

		items = Items()

		for (i in subMenulist.indices) {
			items.add(subMenulist[i])
			items.addAll(getListofFood(subMenulist[i].subMenuId))
		}
		adapter.setItems(items);
		adapter.notifyDataSetChanged();
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		adapter = MultiTypeAdapter()
		val args = arguments
		val menuId: Int = args?.getInt(ARG_SUB_MAIN_MANU_LIST_MULTIPLE_VIEW_FRAGMENT) ?: 1
		getListOfSubMenus(menuId)

	}
}

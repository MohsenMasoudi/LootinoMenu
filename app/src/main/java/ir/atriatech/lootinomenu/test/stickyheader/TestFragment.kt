package ir.atriatech.lootinomenu.test.stickyheader


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.shuhart.stickyheader.StickyHeaderItemDecorator
import ir.atriatech.lootinomenu.ARG_SUB_TEST_FRAGMENT
import ir.atriatech.lootinomenu.databinding.FragmentTestBinding
import ir.atriatech.lootinomenu.main_menu.MainMenuActivity
import ir.atriatech.lootinomenu.model.Food
import ir.atriatech.lootinomenu.model.SubMenu
import kotlinx.android.synthetic.main.fragment_test.*
import java.util.*

class TestFragment : Fragment() {
	lateinit var subMenulist: MutableList<SubMenu>

	companion object {
		fun newInstance(menuId: Int): TestFragment {
			var testFragment = TestFragment()
			val arg = Bundle()
			arg.putInt(ARG_SUB_TEST_FRAGMENT, menuId)
			testFragment.arguments = arg
			return testFragment
		}
	}

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		val binding = DataBindingUtil.inflate<FragmentTestBinding>(
			inflater,
			ir.atriatech.lootinomenu.R.layout.fragment_test,
			container,
			false
		)
		return binding.root
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		getListOfSubMenus(1)

	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		recycler_view_test.layoutManager = LinearLayoutManager(activity)
		val adapter = SectionAdapter()
		recycler_view_test.adapter = adapter
		val decorator = StickyHeaderItemDecorator(adapter)
		decorator.attachToRecyclerView(recycler_view_test)
		adapter.items = object : ArrayList<Section>() {
			init {
				var section = 0
////				            add(new CustomHeader());
//				for (i in 0..27) {
//					if (i < 12) {
//						if (i % 4 == 0) {
//							section = i
//							add(SectionHeader(section, SubMenu()))
//						} else {
//							add(SectionItem(section, Food()))
//						}
//					} else {
//						if (i % 8 == 0) {
//							section = i
//							add(SectionHeader(section,SubMenu()))
//						} else {
//							add(SectionItem(section,Food()))
//						}
//					}
//				}
				for ( i in subMenulist.indices) {
					add(SectionHeader(section, subMenulist[i]))
					section++
					val listOfFoods: MutableList<Food> = getListofFood(i)
					for (j in listOfFoods.indices){
						add(SectionItem(section, listOfFoods[j]))
						section++

					}
					adapter.notifyDataSetChanged()

			}
			}
		}
		adapter.notifyDataSetChanged()
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
}

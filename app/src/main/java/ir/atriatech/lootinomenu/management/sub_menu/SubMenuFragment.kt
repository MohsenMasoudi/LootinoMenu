package ir.atriatech.lootinomenu.management.sub_menu


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import ir.atriatech.lootinomenu.ARG_SUB_MENU_FRAGMENT

import ir.atriatech.lootinomenu.databinding.FragmentSubMenuBinding
import ir.atriatech.lootinomenu.model.SubMenu
import ir.atriatech.lootinomenu.management.ManagementActivity
import kotlinx.android.synthetic.main.fragment_sub_menu.*
import com.h6ah4i.android.widget.advrecyclerview.draggable.RecyclerViewDragDropManager
import androidx.recyclerview.widget.SimpleItemAnimator
import ir.atriatech.lootinomenu.management.ManagementActivityCallBack
import ir.atriatech.lootinomenu.management.add_or_edit_sub_menu.AddOrEditSubMenuFragment
import ir.atriatech.lootinomenu.management.management_panel.ManagementPanelAdapter
import ir.atriatech.lootinomenu.management.management_panel.ManagementPanelFragment


class SubMenuFragment : Fragment() {
	lateinit var mAdapter: SubMenuAdapter
	lateinit var subMenulist: MutableList<SubMenu>
	private var menuId: Int? = null
	companion object {
		fun newInstance(menuId: Int): SubMenuFragment {
			val subMenuFragment = SubMenuFragment()
			val arg = Bundle()
			arg.putInt(ARG_SUB_MENU_FRAGMENT, menuId)
			subMenuFragment.arguments = arg
			return subMenuFragment

		}
	}



	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		val arg = arguments
		menuId = arg?.getInt(ARG_SUB_MENU_FRAGMENT, 0)
		getListOfFood(menuId!!)

	}

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		// Inflate the layout for this fragment
		val binding = DataBindingUtil.inflate<FragmentSubMenuBinding>(
			inflater,
			ir.atriatech.lootinomenu.R.layout.fragment_sub_menu,
			container,
			false
		)
		when (menuId) {
			1 -> binding.menuName =getString(ir.atriatech.lootinomenu.R.string.coffee_shop_sub_menu_list)
			2 -> binding.menuName = getString(ir.atriatech.lootinomenu.R.string.restaurant_sub_menu_list)
		}
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		mAdapter= SubMenuAdapter()
		mAdapter.subMenuList=subMenulist
		val dragDropManager = RecyclerViewDragDropManager()
		val wrappedAdapter = dragDropManager.createWrappedAdapter(mAdapter)
		recycler_view_sub_menu_fragment.adapter=wrappedAdapter
		recycler_view_sub_menu_fragment.layoutManager = LinearLayoutManager(activity)
		(recycler_view_sub_menu_fragment.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
		dragDropManager.attachRecyclerView(recycler_view_sub_menu_fragment)
		val callback: ManagementActivityCallBack = context as ManagementActivity
		img_btn_back.setOnClickListener {
			callback.ManagmentFragmentLoader(ManagementPanelFragment())
		}

		img_btn_add_new_sub_menu.setOnClickListener {
			callback.ManagmentFragmentLoader(AddOrEditSubMenuFragment.newInstance(menuId!!))
		}


	}

	fun getListOfFood(listId: Int) {
		Log.d("tag22", listId.toString())

		subMenulist =
			(activity as ManagementActivity).appDataBase.subMenuDao().getAllWithMenuId(listId)
		Log.d("tag22", subMenulist.size.toString())
	}

}


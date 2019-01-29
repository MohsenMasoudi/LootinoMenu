//package ir.atriatech.lootinomenu.main_menu.main_menu_expand
//
//import android.view.View
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractExpandableItemAdapter
//import ir.atriatech.lootinomenu.main_menu.MainMenuActivity
//import ir.atriatech.lootinomenu.model.SubMenu
//
//class MyAdapter(val mainMenuActivity: MainMenuActivity) :
//	AbstractExpandableItemAdapter<MyAdapter.MyGroupVH, MyAdapter.MyChildVH>() {
//	override fun getChildCount(groupPosition: Int): Int {
//		return mainMenuActivity.appDataBase.foodDao().countFoodsOfSubMenu(groupPosition)
//	}
//
//	override fun onCheckCanExpandOrCollapseGroup(
//		holder: MyGroupVH,
//		groupPosition: Int,
//		x: Int,
//		y: Int,
//		expand: Boolean
//	): Boolean {
//
//	}
//
//	override fun onCreateGroupViewHolder(parent: ViewGroup?, viewType: Int): MyGroupVH {
//	}
//
//	override fun onCreateChildViewHolder(parent: ViewGroup?, viewType: Int): MyChildVH {
//	}
//
//	override fun getGroupId(groupPosition: Int): Long {
//	}
//
//	override fun onBindChildViewHolder(
//		holder: MyChildVH,
//		groupPosition: Int,
//		childPosition: Int,
//		viewType: Int
//	) {
//	}
//
//	override fun getChildId(groupPosition: Int, childPosition: Int): Long {
//	}
//
//	override fun getGroupCount(): Int {
//		return subMenuList.size
//	}
//
//	override fun onBindGroupViewHolder(holder: MyGroupVH, groupPosition: Int, viewType: Int) {
//	}
//
//	init {
//		setHasStableIds(true)
//	}
//
//	class MyGroupVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
//
//	}
//
//	class MyChildVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
//
//	}
//
//	lateinit var subMenuList: MutableList<SubMenu>
//}
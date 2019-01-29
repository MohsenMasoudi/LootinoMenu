package ir.atriatech.lootinomenu.management.sub_menu

import AppDH
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.h6ah4i.android.widget.advrecyclerview.draggable.DraggableItemAdapter
import com.h6ah4i.android.widget.advrecyclerview.draggable.ItemDraggableRange
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractDraggableItemViewHolder
import ir.atriatech.lootinomenu.R
import ir.atriatech.lootinomenu.data_base.room.AppDataBase
import ir.atriatech.lootinomenu.management.ManagementActivity
import ir.atriatech.lootinomenu.management.ManagementActivityCallBack
import ir.atriatech.lootinomenu.management.add_or_edit_sub_menu.AddOrEditSubMenuFragment
import ir.atriatech.lootinomenu.management.management_panel.ManagementPanelAdapter
import ir.atriatech.lootinomenu.management.sub_menu_food_list.SubMenuFoodListFragment
import ir.atriatech.lootinomenu.model.Food
import ir.atriatech.lootinomenu.model.SubMenu
import kotlinx.android.synthetic.main.item_sub_menu_recycler_view.view.*
import javax.inject.Inject


class SubMenuAdapter : RecyclerView.Adapter<SubMenuAdapter.SubMenuViewHolder>()
	, DraggableItemAdapter<SubMenuAdapter.SubMenuViewHolder> {
	override fun onGetItemDraggableRange(
		holder: SubMenuViewHolder,
		position: Int
	): ItemDraggableRange? {
		return null
	}

	protected val component by lazy { AppDH.baseComponent() }


	init {
		component.inject(this)
	}

	@Inject
	lateinit var appDataBase: AppDataBase

	override fun onCheckCanStartDrag(
		holder: SubMenuViewHolder,
		position: Int,
		x: Int,
		y: Int
	): Boolean {
		val itemView = holder.itemView
		val dragHandle = holder.draghandeler
		val handleWidth = dragHandle.width
		val handleHeight = dragHandle.height
		val handleLeft = dragHandle.left
		val handleTop = dragHandle.top
		return x >= handleLeft && x < handleLeft + handleWidth &&
				y >= handleTop && y < handleTop + handleHeight
	}


	override fun onItemDragStarted(position: Int) {
		notifyDataSetChanged()

	}

	override fun onMoveItem(fromPosition: Int, toPosition: Int) {


//		val firstOrder= subMenuList[fromPosition].order
////		Log.d("tag23",firstOrder.toString())
//		val secondOrder= subMenuList[toPosition].order
////		Log.d("tag23",secondOrder.toString())
//
//		subMenuList[fromPosition].order=secondOrder
////		Log.d("tag23",subMenuList[fromPosition].name.toString())
//
//		subMenuList[toPosition].order=firstOrder
//		Log.d("tag23",subMenuList[toPosition].name.toString())
		val removed = subMenuList.removeAt(fromPosition)
		subMenuList.add(toPosition, removed)
//		Log.d("tag23",removed.order.toString())


	}

	override fun onCheckCanDrop(draggingPosition: Int, dropPosition: Int): Boolean {
		return false
	}

	override fun onItemDragFinished(fromPosition: Int, toPosition: Int, result: Boolean) {

		appDataBase.subMenuDao().updateAll(subMenuList)
		notifyDataSetChanged()

	}

	override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
		super.onAttachedToRecyclerView(recyclerView)
		notifyDataSetChanged()
	}

	var subMenuList: MutableList<SubMenu> = mutableListOf()
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubMenuViewHolder {
		val layoutInflater = LayoutInflater.from(parent.context)
		val view =
			layoutInflater.inflate(R.layout.item_sub_menu_recycler_view, parent, false)
		return SubMenuAdapter.SubMenuViewHolder(view, parent.context)
	}

	override fun getItemId(position: Int): Long {
		return subMenuList.get(position).order
	}

	override fun getItemCount(): Int {
		return subMenuList.size
	}

	override fun onBindViewHolder(holder: SubMenuViewHolder, position: Int) {
		holder.bindUI(subMenuList[position])
	}

	class SubMenuViewHolder(itemView: View, val context: Context) :
		AbstractDraggableItemViewHolder(itemView) {
		var draghandeler: ImageView = itemView.img_move_item_sub_menu
		fun bindUI(subMenu: SubMenu) {
			val callback: ManagementPanelAdapter.CallBack = context as ManagementActivity
			val managementActivityCallBack: ManagementActivityCallBack = context
			itemView.txt_item_sub_menu.text = subMenu.name + subMenu.order
			itemView.img_edit_item_sub_menu.setOnClickListener {

				managementActivityCallBack.ManagmentFragmentLoader(
					AddOrEditSubMenuFragment.newInstance(
						subMenu.menuId, subMenu.subMenuId
					)
				)


			}
			itemView.img_delete_item_sub_menu.setOnClickListener {
				val listOfFood: MutableList<Food> =
					context.appDataBase.foodDao()
						.findBySubId(subMenu.subMenuId)
				for (i in listOfFood.indices) {
					listOfFood[i].menuId = 0
				}
				context.appDataBase.foodDao()
					.updateAll(foodList = listOfFood)

				context.appDataBase.subMenuDao().delete(subMenu)
				callback.getFragmentToLoad(subMenu.menuId - 1)
			}
			itemView.img_add_food_to_sub_menu.setOnClickListener {
				managementActivityCallBack.ManagmentFragmentLoader(
					SubMenuFoodListFragment.newInstance(
						subMenu.subMenuId
					)
				)
			}

		}

	}

	init {
		setHasStableIds(true)

	}
}
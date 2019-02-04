package ir.atriatech.lootinomenu.management.sub_menu

import AppDH
import android.content.Context
import android.util.Log
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
import ir.atriatech.lootinomenu.management.ManagementCallBackForDeleteSubMenuWarning
import ir.atriatech.lootinomenu.management.add_or_edit_sub_menu.AddOrEditSubMenuFragment
import ir.atriatech.lootinomenu.management.management_food_list.ManagementFoodListFragment
import ir.atriatech.lootinomenu.model.SubMenu
import ir.atriatech.lootinomenu.util.DrawableUtils
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

	private val component by lazy { AppDH.baseComponent() }


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
//		val itemView = holder.itemView
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
		val removed = subMenuList.removeAt(fromPosition)
		subMenuList.add(toPosition, removed)
	}

	override fun onCheckCanDrop(draggingPosition: Int, dropPosition: Int): Boolean {
		return false
	}

	override fun onItemDragFinished(fromPosition: Int, toPosition: Int, result: Boolean) {
		var setOrder: Long = 1
		for (i in subMenuList.indices) {
			subMenuList[i].order = setOrder
			setOrder++
			Log.d(
				"tag26",
				subMenuList[i].name + " " + subMenuList[i].order.toString() + " " + subMenuList[i].secondOrder.toString()
			)


		}
		notifyDataSetChanged()
		appDataBase.subMenuDao().updateAll(subMenuList)


	}

	override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
		super.onAttachedToRecyclerView(recyclerView)

	}

	var subMenuList: MutableList<SubMenu> = mutableListOf()
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubMenuViewHolder {
		val layoutInflater = LayoutInflater.from(parent.context)
		val view =
			layoutInflater.inflate(R.layout.item_sub_menu_recycler_view, parent, false)
		return SubMenuAdapter.SubMenuViewHolder(view, parent.context)
	}

	override fun getItemId(position: Int): Long {
		return subMenuList.get(position).secondOrder
	}

	override fun getItemCount(): Int {
		return subMenuList.size
	}

	override fun onBindViewHolder(holder: SubMenuViewHolder, position: Int) {
		holder.bindUI(subMenuList[position])
		holder.itemView.item_sub_menu_manager_activity_container
		val dragState = holder.getDragState()

		if (dragState.isUpdated) {
			val bgResId: Int

			when {
				dragState.isActive -> {
					bgResId = R.drawable.bg_item_dragging_active_state

					// need to clear drawable state here to get correct appearance of the dragging item.
					DrawableUtils.clearState(holder.itemView.item_sub_menu_manager_activity_container.foreground)
				}
				dragState.isDragging -> bgResId = R.drawable.bg_item_dragging_state
				else -> bgResId = R.drawable.bg_item_normal_state
			}

			holder.itemView.item_sub_menu_manager_activity_container.setBackgroundResource(bgResId)
		}
	}

	class SubMenuViewHolder(itemView: View, val context: Context) :
		AbstractDraggableItemViewHolder(itemView) {
		var draghandeler: ImageView = itemView.img_move_item_sub_menu
		fun bindUI(subMenu: SubMenu) {
			val managementActivityCallBack: ManagementActivityCallBack =
				context as ManagementActivity
			val deleteSubMenuWarning: ManagementCallBackForDeleteSubMenuWarning = context
			itemView.txt_item_sub_menu.text = subMenu.name
			itemView.img_edit_item_sub_menu.setOnClickListener {

				managementActivityCallBack.ManagmentFragmentLoader(
					AddOrEditSubMenuFragment.newInstance(
						subMenu.menuId, subMenu.subMenuId
					)
				)


			}
			itemView.img_delete_item_sub_menu.setOnClickListener {

				deleteSubMenuWarning.deleteWarningCallBack(subMenu)

			}
			itemView.img_add_food_to_sub_menu.setOnClickListener {
				managementActivityCallBack.ManagmentFragmentLoader(
					ManagementFoodListFragment.newInstance(
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
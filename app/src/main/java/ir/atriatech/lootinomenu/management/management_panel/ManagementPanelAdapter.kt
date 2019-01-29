package ir.atriatech.lootinomenu.management.management_panel

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ir.atriatech.lootinomenu.MANAGEMENT_PANEL_LIST
import ir.atriatech.lootinomenu.R
import ir.atriatech.lootinomenu.management.ManagementActivity
import kotlinx.android.synthetic.main.item_management_panel_recycler_view.view.*

class ManagementPanelAdapter :
	RecyclerView.Adapter<ManagementPanelAdapter.ManagementPanelViewHolder>() {
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ManagementPanelViewHolder {
		val layoutInflater = LayoutInflater.from(parent.context)
		val view =
			layoutInflater.inflate(R.layout.item_management_panel_recycler_view, parent, false)
		return ManagementPanelViewHolder(
			view,
			parent.context
		)
	}

	override fun getItemCount(): Int {
		return MANAGEMENT_PANEL_LIST.size
	}

	override fun onBindViewHolder(holder: ManagementPanelViewHolder, position: Int) {
		holder.bindUI(MANAGEMENT_PANEL_LIST[position], position)
	}

	class ManagementPanelViewHolder(itemView: View, context: Context) :
		RecyclerView.ViewHolder(itemView) {
		var callback: CallBack = context as ManagementActivity



		fun bindUI(txt: String, item: Int) {
			itemView.txt_item_management_panel_recycler_view.text = txt
			itemView.setOnClickListener {
				callback.getFragmentToLoad(item)
			}
		}

	}

	interface CallBack {
		fun getFragmentToLoad(listId: Int)
	}
}



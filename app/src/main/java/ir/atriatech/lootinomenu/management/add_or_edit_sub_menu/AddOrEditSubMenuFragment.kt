package ir.atriatech.lootinomenu.management.add_or_edit_sub_menu


import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import ir.atriatech.lootinomenu.ARG_MENU_ID_ADD_OR_EDIT_SUB_MENU_FRAGMENT
import ir.atriatech.lootinomenu.ARG_SUB_MENU_ID_ADD_OR_EDIT_SUB_MENU_FRAGMENT
import ir.atriatech.lootinomenu.R
import ir.atriatech.lootinomenu.management.ManagementActivity
import ir.atriatech.lootinomenu.management.management_panel.ManagementPanelAdapter
import ir.atriatech.lootinomenu.model.SubMenu
import kotlinx.android.synthetic.main.fragment_add_or_edit_sub_menu.*


class AddOrEditSubMenuFragment : Fragment() {
	var menuId: Int = 0
	lateinit var subMenu: SubMenu

	companion object {
		fun newInstance(menuId: Int, subMenuId: Int): AddOrEditSubMenuFragment {
			val addOrEditSubMenuFragment = AddOrEditSubMenuFragment()
			val arg = Bundle()
			arg.putInt(ARG_MENU_ID_ADD_OR_EDIT_SUB_MENU_FRAGMENT, menuId)
			arg.putInt(ARG_SUB_MENU_ID_ADD_OR_EDIT_SUB_MENU_FRAGMENT, subMenuId)
			addOrEditSubMenuFragment.arguments = arg
			return addOrEditSubMenuFragment

		}

		fun newInstance(menuId: Int): AddOrEditSubMenuFragment {
			val addOrEditSubMenuFragment = AddOrEditSubMenuFragment()
			val arg = Bundle()
			arg.putInt(ARG_MENU_ID_ADD_OR_EDIT_SUB_MENU_FRAGMENT, menuId)
			addOrEditSubMenuFragment.arguments = arg
			return addOrEditSubMenuFragment

		}
	}

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_add_or_edit_sub_menu, container, false)
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		val arg = arguments
		if (arg != null) {
			if (arg.getInt(ARG_SUB_MENU_ID_ADD_OR_EDIT_SUB_MENU_FRAGMENT, 0) != 0) {
				val subMenuId = arg.getInt(ARG_SUB_MENU_ID_ADD_OR_EDIT_SUB_MENU_FRAGMENT, 0)
				menuId = arg.getInt(ARG_MENU_ID_ADD_OR_EDIT_SUB_MENU_FRAGMENT, 0)

				subMenu = getSubMenu(subMenuId)
			} else {
				menuId = arg.getInt(ARG_MENU_ID_ADD_OR_EDIT_SUB_MENU_FRAGMENT, 0)
				subMenu = SubMenu()
				subMenu.menuId = menuId
				subMenu.order = findLastOrderNumber(menuId) + 1
			}
		}


	}

	fun getSubMenu(subMenuId: Int): SubMenu {
		return (activity as ManagementActivity).appDataBase.subMenuDao().findById(subMenuId)

	}

//	fun updateSubMenu(subMenuId: Int): SubMenu {
//		return (activity as ManagementActivity).appDataBase.subMenuDao().findById(subMenuId)
//
//	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		if (subMenu.name == "") {
			txt_add_or_edit_title.text = "دسته بندی جدید"
			edit_text_add_or_edit_sub_menu.hint = "نام دسته بندی"
			btn_save_sub_menu.text = "ذخیره دسته بندی"
		} else {
			txt_add_or_edit_title.text = "ویرایش دسته بندی"
			edit_text_add_or_edit_sub_menu.setText(subMenu.name)
			btn_save_sub_menu.text = "تغییر نام دسته بندی"

		}
		btn_save_sub_menu.setOnClickListener {
			if (edit_text_add_or_edit_sub_menu.text.toString() == "") {
				Toast.makeText(
					view.context,
					"یک نام برای دسته بندی انتخاب کنید",
					Toast.LENGTH_SHORT
				).show()
			} else {
				subMenu.name = edit_text_add_or_edit_sub_menu.text.toString()
				updateOrInsert(subMenu)
				goToSubMenu()
			}
		}
		img_btn_back_btn.setOnClickListener {
			goToSubMenu()
		}
	}

	private fun updateOrInsert(subMenu: SubMenu) {
		try {
			(activity as ManagementActivity).appDataBase.subMenuDao().insert(subMenu)
		} catch (exception: SQLiteConstraintException) {
			(activity as ManagementActivity).appDataBase.subMenuDao().update(subMenu)
		}

	}

	private fun findLastOrderNumber(menuId: Int): Long {
		var lastOrder: Long = 1

		val subMenuList: MutableList<SubMenu>
		subMenuList =
			(activity as ManagementActivity).appDataBase.subMenuDao().getAllWithMenuId(menuId)
		for (i in 0 until subMenuList.size - 1) {
			var t = subMenuList.indices
			if (subMenuList[i].order > subMenuList[i + 1].order) {
				lastOrder = subMenuList[i].order
			} else {
				lastOrder = subMenuList[i + 1].order
			}
		}
		return lastOrder
	}

	fun goToSubMenu() {
		val callback: ManagementPanelAdapter.CallBack =
			this@AddOrEditSubMenuFragment.activity as ManagementActivity
		callback.getFragmentToLoad(menuId - 1)
	}
}

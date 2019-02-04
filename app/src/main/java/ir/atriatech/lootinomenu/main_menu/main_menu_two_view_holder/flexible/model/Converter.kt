package ir.atriatech.lootinomenu.main_menu.main_menu_two_view_holder.flexible.model

import ir.atriatech.lootinomenu.model.Food
import ir.atriatech.lootinomenu.model.SubMenu

class MyConverter {
	companion object {
		fun food2MainItem(food: Food): MainItem {
			val mainItem=
				MainItem()
			mainItem.id=food.id
			mainItem.foodOrder=food.foodOrder
			mainItem.menuId=food.menuId
			mainItem.picPath=food.picPath
			mainItem.productDetail=food.productDetail
			mainItem.productName=food.productName
			mainItem.subMenuId=food.subMenuId
			mainItem.price=food.price
			return mainItem
		}
		fun foodList2MainItemList(list: MutableList<Food>):MutableList<MainItem>{
			var listItem:MutableList<MainItem> = mutableListOf()
			for (i in list.indices){
				val mainItem=
					food2MainItem(
						list[i]
					)
				listItem.add(mainItem)
			}
			return listItem
		}fun subMenu2HeaderItem(subMenu: SubMenu): HeaderItem {
			val headerItem=
				HeaderItem()
			headerItem.menuId=subMenu.menuId
			headerItem.name=subMenu.name
			headerItem.order=subMenu.order
			headerItem.secondOrder=subMenu.secondOrder
			headerItem.subMenuId=subMenu.subMenuId

			return headerItem
		}
		fun subMenuList2HeaderItemList(list: MutableList<SubMenu>):MutableList<HeaderItem>{
			var listItem:MutableList<HeaderItem> = mutableListOf()
			for (i in list.indices){
				val mainItem=
					subMenu2HeaderItem(
						list[i]
					)
				listItem.add(mainItem)
			}
			return listItem
		}
	}
}
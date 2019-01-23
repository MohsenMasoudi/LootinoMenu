package ir.atriatech.lootinomenu.main

import ir.atriatech.lootinomenu.data_base.room.AppDataBase
import ir.atriatech.lootinomenu.model.Food
import ir.atriatech.lootinomenu.model.SubMenu

interface MainContracter {
    interface View{

    }
    interface Model{
        fun getAllInnerDbFoods():MutableList<Food>
        fun getAllInnerDbSubMenu():MutableList<SubMenu>
        fun isThisFirstTimeToRunApp():Boolean
        fun getRoomdataBase():AppDataBase

    }
    interface Presenter{

    }
}
package ir.atriatech.lootinomenu.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "sub_menu")
class SubMenu {
    @PrimaryKey(autoGenerate = true)
     var subMenuId: Int = 0
     var name: String = ""
     var order:Int=0
}
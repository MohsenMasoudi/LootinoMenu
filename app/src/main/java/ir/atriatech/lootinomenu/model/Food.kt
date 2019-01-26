package ir.atriatech.lootinomenu.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

//foreignKeys = arrayOf(
//ForeignKey(
//entity = SubMenu::class,
//parentColumns = arrayOf("subMenuId"),
//childColumns = arrayOf("id"),
//onDelete = ForeignKey.SET_DEFAULT

@Entity(tableName = "food")
class Food() {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    var menuId: Int = 0
    var subMenuId = 0
    var productName: String = ""
    var productDetail: String = ""
    var foodOrder:Int=0
    var price: Int = 0

}

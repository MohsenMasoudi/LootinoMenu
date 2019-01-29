package ir.atriatech.lootinomenu.data_base

import android.content.Context
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper
import ir.atriatech.lootinomenu.DATA_BASE_NAME
import ir.atriatech.lootinomenu.DATA_BASE_VERSION

class FoodDataBase(context: Context)
    : SQLiteAssetHelper(context,
	DATA_BASE_NAME, null,
	DATA_BASE_VERSION
)
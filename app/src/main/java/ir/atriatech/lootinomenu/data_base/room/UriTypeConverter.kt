package ir.atriatech.lootinomenu.data_base.room

import android.net.Uri
import androidx.room.TypeConverter


class UriTypeConverter {
	@TypeConverter
	fun toString(value: String?): Uri? {
		return if (value == null) null else Uri.parse(value)
	}

	@TypeConverter
	fun toLong(value: Uri?): String? {
		return (if (value == null) null else value.toString())
	}
}
package ir.atriatech.lootinomenu.management.management_panel


import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.himanshurawat.imageworker.Extension
import com.himanshurawat.imageworker.ImageWorker
import ir.atriatech.lootinomenu.IMAGE_DIRECTORY
import ir.atriatech.lootinomenu.IMAGE_SUB_DIRECTORY
import ir.atriatech.lootinomenu.R
import ir.atriatech.lootinomenu.main.MainActivity
import ir.atriatech.lootinomenu.management.ManagementActivity
import kotlinx.android.synthetic.main.fragment_management_panel.*
import java.io.ByteArrayOutputStream


class ManagementPanelFragment : Fragment() {
	lateinit var mAdapter: ManagementPanelAdapter

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {


		return inflater.inflate(R.layout.fragment_management_panel, container, false)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		mAdapter = ManagementPanelAdapter()
		recycler_view_fragment_management_panel.adapter = mAdapter
		recycler_view_fragment_management_panel.layoutManager = LinearLayoutManager(activity)
		img_btn_back_btn.setOnClickListener {
			val launch = MainActivity.newIntent(view.context)
			launch.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
			startActivity(launch)
		}
		img_btn_sync.setOnClickListener {
			//			loadImages()
			Toast.makeText(activity, "کلیک", Toast.LENGTH_SHORT).show()
			loadImages()
		}
		img_btn_sync.visibility=View.INVISIBLE
	}

	private fun getImageUri(inContext: Context, inImage: Bitmap): Uri {
		val bytes = ByteArrayOutputStream()
		inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
		val path =
			MediaStore.Images.Media.insertImage(inContext.contentResolver, inImage, "Title", null)
		return Uri.parse(path)
	}

	private fun getBitmap(context: Context, name: String): Bitmap? {
		return ImageWorker.from(context).directory(IMAGE_DIRECTORY).subDirectory(IMAGE_SUB_DIRECTORY)
			.setFileName(name).withExtension(Extension.JPEG).load()
	}

	private fun loadImages() {
		val listOfFoods = (activity as ManagementActivity).appDataBase.foodDao().getAll()
		for (i in listOfFoods.indices) {
			var bitMap: Bitmap? = null

			try {
				bitMap =
					getBitmap(
						(activity as ManagementActivity).applicationContext,
						listOfFoods[i].id.toString()
					)
			} catch (e: Exception) {

			}
			if (bitMap != null) {
				try {
					listOfFoods[i].picPath = getImageUri(activity as ManagementActivity, bitMap)
				} catch (e: Exception) {
				}

			}
		}
		(activity as ManagementActivity).appDataBase.foodDao().updateAll(listOfFoods)
		Toast.makeText(activity, "آپدیت انجام شد", Toast.LENGTH_SHORT).show()

	}
}

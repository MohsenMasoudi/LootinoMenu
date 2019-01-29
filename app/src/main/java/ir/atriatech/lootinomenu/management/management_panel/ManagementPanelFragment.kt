package ir.atriatech.lootinomenu.management.management_panel


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import ir.atriatech.lootinomenu.R
import ir.atriatech.lootinomenu.main.MainActivity
import kotlinx.android.synthetic.main.fragment_management_panel.*


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
	}


}

package ir.atriatech.lootinomenu.main_menu

import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.recyclerview.widget.RecyclerView
import ir.atriatech.lootinomenu.main_menu.main_menu_multiple_view.MainMenuWIthMultipleViewFragment
import ir.atriatech.lootinomenu.model.Food
import ir.atriatech.lootinomenu.test.stickyheader.TestFragment
import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter

class MainMenuViewPagerAdapter (fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

	override fun getItem(position: Int): Fragment {
		return when (position) {
			0 -> {
				MainMenuWIthMultipleViewFragment.newInstance(1)
			}
			else -> MainMenuWIthMultipleViewFragment.newInstance(2)

			}
		}


	override fun getCount(): Int {
		return 2
	}

	override fun getPageTitle(position: Int): CharSequence {
		return when (position) {
			0 -> "کافی شاپ"
			1 -> "رستوران"
			else -> {
				return "Third Tab"
			}
		}
	}

}
package ir.atriatech.lootinomenu.main_menu

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class MainMenuViewPagerAdapter (fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

	override fun getItem(position: Int): Fragment {
		return when (position) {
			0 -> {
				MainMenuFragment.newInstance(1)
			}
			else -> MainMenuFragment.newInstance(2)

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
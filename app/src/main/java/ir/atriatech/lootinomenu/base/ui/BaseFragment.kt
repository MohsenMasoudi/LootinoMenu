package ir.atriatech.lootinomenu.base.ui

import android.content.Context
import androidx.fragment.app.Fragment


open class BaseFragment : Fragment() {

    protected var callback: Callback? = null
    protected var isPause: Boolean = false

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        if (context is Callback) {
            callback = context
        }

    }

    override fun onDetach() {
        super.onDetach()

        callback = null
    }

    override fun onPause() {
        super.onPause()
        isPause = true
    }

    override fun onResume() {
        super.onResume()
        isPause = false
    }


    interface Callback {



    }

}